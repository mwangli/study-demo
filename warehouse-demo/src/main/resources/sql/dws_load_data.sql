use warehouse;
set hive.exec.dynamic.partition.mode=nonstrict;
set spark.executor.memory=2g;
set spark.yarn.executor.memoryOverhead=2048;

with login_tmp as (
    select user_id,
           count(1) login_count
    from dwd_page_log
    where dt = '2022-04-26'
      and user_id is not null
      and last_page_id is null
    group by user_id
),
     cart_favor_tmp as (
         select user_id,
                sum(if(action_id = 'cart_add', 1, 0))  cart_count,
                sum(if(action_id = 'favor_add', 1, 0)) favor_count
         from dwd_action_log
         where dt = '2022-04-26'
           and user_id is not null
           and action_id in ('cart_add', 'favor_add')
         group by user_id
     ),
     order_info_tmp as (
         select user_id,
                count(1)                                  order_count,
                sum(if(activity_reduce_amount > 0, 1, 0)) order_activity_count,
                sum(activity_reduce_amount)               order_activity_amount,
                sum(if(coupon_reduce_amount > 0, 1, 0))   order_coupon_count,
                sum(coupon_reduce_amount)                 order_coupon_amount,
                sum(original_total_amount)                order_original_amount,
                sum(total_amount)                         order_final_amount
         from dwd_order_info
         where (dt = '2022-04-26' or dt = '9999-99-99')
           and date_format(create_time, 'yyyy-MM-dd') = '2022-04-26'
         group by user_id
     ),
     payment_info_tmp as (
         select user_id,
                count(1)          payment_count,
                sum(total_amount) payment_amount
         from dwd_payment_info
         where dt = '2022-04-26'
         group by user_id
     ),
     order_fefund_info_tmp as (
         select user_id,
                count(1)           refund_order_count,
                sum(refund_num)    refund_order_num,
                sum(refund_amount) refund_order_amount
         from dwd_order_refund_info
         where dt = '2022-04-26'
         group by user_id
     ),
     payment_refund_info_tmp as (
         select user_id,
                count(1)          refund_payment_count,
                sum(refund_num)   refund_payment_num,
                sum(total_amount) refund_payment_amount
         from (
                  select user_id,
                         total_amount,
                         order_id,
                         sku_id
                  from dwd_refund_payment
                  where dt = '2022-04-26'
              ) p
                  left join
              (
                  select refund_num,
                         order_id,
                         sku_id
                  from dwd_order_refund_info
                  where dt >= date_add('2022-04-26', -15)
              ) o
              on p.order_id = o.order_id and p.sku_id = o.sku_id
         group by user_id
     ),
     coupon_tmp as (
         select user_id,
                sum(if(date_format(get_time, 'yyyy-MM-dd') = '2022-04-26', 1, 0))   coupon_get_count,
                sum(if(date_format(using_time, 'yyyy-MM-dd') = '2022-04-26', 1, 0)) coupon_using_count,
                sum(if(date_format(used_time, 'yyyy-MM-dd') = '2022-04-26', 1, 0))  coupon_used_count
         from dwd_coupon_use
         where (dt = '2022-04-26' or dt = '9999-99-99')
           and (date_format(get_time, 'yyyy-MM-dd') = '2022-04-26'
             or date_format(using_time, 'yyyy-MM-dd') = '2022-04-26'
             or date_format(used_time, 'yyyy-MM-dd') = '2022-04-26')
         group by user_id
     ),
     comment_tmp as (
         select user_id,
                sum(if(appraise = '1201', 1, 0)) appraise_good_count,
                sum(if(appraise = '1202', 1, 0)) appraise_mid_count,
                sum(if(appraise = '1203', 1, 0)) appraise_bad_count,
                sum(if(appraise = '1204', 1, 0)) appraise_default_count
         from dwd_comment_info
         where dt = '2022-04-26'
         group by user_id
     ),
     order_detail_tmp as (
         select user_id,
                collect_set(named_struct('sku_id', sku_id, 'sku_num', sku_num, 'order_count', order_count,
                                         'order_activity_amount', split_activity_amount, 'order_coupon_amount',
                                         split_coupon_amount, 'order_original_amount', original_amount,
                                         'order_final_amount', split_total_amount)) order_detail_status
         from (
                  select user_id,
                         sku_id,
                         count(distinct order_id)   order_count,
                         sum(sku_num)               sku_num,
                         sum(split_activity_amount) split_activity_amount,
                         sum(split_coupon_amount)   split_coupon_amount,
                         sum(original_amount)       original_amount,
                         sum(split_total_amount)    split_total_amount
                  from dwd_order_detail
                  where dt = '2022-04-26'
                  group by user_id, sku_id
              ) t
         group by user_id
     )
insert
overwrite
table
dws_user_action_daycount
partition
(
dt = '2022-04-26'
)
select coalesce(login_tmp.user_id, cart_favor_tmp.user_id, order_info_tmp.user_id,
                payment_info_tmp.user_id, order_fefund_info_tmp.user_id,
                payment_refund_info_tmp.user_id, coupon_tmp.user_id, comment_tmp.user_id,
                order_detail_tmp.user_id),
       nvl(login_count, 0),
       nvl(cart_count, 0),
       nvl(favor_count, 0),
       nvl(order_count, 0),
       nvl(order_activity_count, 0),
       nvl(order_activity_amount, 0),
       nvl(order_coupon_count, 0),
       nvl(order_coupon_amount, 0),
       nvl(order_original_amount, 0),
       nvl(order_final_amount, 0),
       nvl(payment_count, 0),
       nvl(payment_amount, 0),
       nvl(refund_order_count, 0),
       nvl(refund_order_num, 0),
       nvl(refund_order_amount, 0),
       nvl(refund_payment_count, 0),
       nvl(refund_payment_num, 0),
       nvl(refund_payment_amount, 0),
       nvl(coupon_get_count, 0),
       nvl(coupon_using_count, 0),
       nvl(coupon_used_count, 0),
       nvl(appraise_good_count, 0),
       nvl(appraise_mid_count, 0),
       nvl(appraise_bad_count, 0),
       nvl(appraise_default_count, 0),
       order_detail_status
from login_tmp
         full outer join cart_favor_tmp
                         on login_tmp.user_id = cart_favor_tmp.user_id
         full outer join order_info_tmp
                         on coalesce(login_tmp.user_id, cart_favor_tmp.user_id) = order_info_tmp.user_id
         full outer join payment_info_tmp
                         on coalesce(login_tmp.user_id, cart_favor_tmp.user_id, order_info_tmp.user_id) =
                            payment_info_tmp.user_id
         full outer join order_fefund_info_tmp
                         on coalesce(login_tmp.user_id, cart_favor_tmp.user_id, order_info_tmp.user_id,
                                     payment_info_tmp.user_id) = order_fefund_info_tmp.user_id
         full outer join payment_refund_info_tmp
                         on coalesce(login_tmp.user_id, cart_favor_tmp.user_id, order_info_tmp.user_id,
                                     payment_info_tmp.user_id, order_fefund_info_tmp.user_id) =
                            payment_refund_info_tmp.user_id
         full outer join coupon_tmp
                         on coalesce(login_tmp.user_id, cart_favor_tmp.user_id, order_info_tmp.user_id,
                                     payment_info_tmp.user_id, order_fefund_info_tmp.user_id,
                                     payment_refund_info_tmp.user_id) = coupon_tmp.user_id
         full outer join comment_tmp
                         on coalesce(login_tmp.user_id, cart_favor_tmp.user_id, order_info_tmp.user_id,
                                     payment_info_tmp.user_id, order_fefund_info_tmp.user_id,
                                     payment_refund_info_tmp.user_id, coupon_tmp.user_id) = comment_tmp.user_id

         full outer join order_detail_tmp
                         on coalesce(login_tmp.user_id, cart_favor_tmp.user_id, order_info_tmp.user_id,
                                     payment_info_tmp.user_id, order_fefund_info_tmp.user_id,
                                     payment_refund_info_tmp.user_id, coupon_tmp.user_id, comment_tmp.user_id) =
                            order_detail_tmp.user_id;

insert overwrite table dws_visitor_action_daycount partition (dt = '2022-04-26')
select t1.mid_id,
       brand,
       model,
       is_new,
       channel,
       os,
       area_code,
       version_code,
       visit_count,
       visit_pages
from (
         select mid_id,
                brand,
                model,
                if(array_contains(collect_set(is_new), '0'), '0', '1') is_new,
                collect_set(channel)                                   channel,
                collect_set(os)                                        os,
                collect_set(area_code)                                 area_code,
                collect_set(version_code)                              version_code,
                count(1)                                               visit_count
         from dwd_page_log
         where dt = '2022-04-26'
           and last_page_id is null
         group by mid_id, brand, model
     ) t1
         left join
     (
         select mid_id,
                collect_set(named_struct('page_id', page_id, 'page_count', page_count, 'during_time',
                                         during_time)) visit_pages
         from (
                  select mid_id,
                         page_id,
                         count(1)         page_count,
                         sum(during_time) during_time
                  from dwd_page_log
                  where dt = '2022-04-26'
                  group by mid_id, page_id
              ) t
         group by mid_id) t2
     on t1.mid_id = t2.mid_id;

with order_tmp as (
    select sku_id,
           count(1)                                 order_count,
           sum(sku_num)                             order_num,
           sum(if(split_activity_amount > 0, 1, 0)) order_activity_count,
           sum(if(split_coupon_amount > 0, 1, 0))   order_coupon_count,
           sum(split_activity_amount)               order_activity_amount,
           sum(split_coupon_amount)                 order_coupon_amount,
           sum(original_amount)                     order_original_amount,
           sum(split_total_amount)                  order_final_amount
    from dwd_order_detail
    where dt = '2022-04-26'
    group by sku_id
),
     payment_tmp as (
         select sku_id,
                count(1)                payment_count,
                sum(sku_num)            payment_num,
                sum(split_total_amount) payment_amount
         from dwd_order_detail
         where dt = '2022-04-26'
           and order_id in (select order_id from dwd_payment_info where callback_time is not null)
         group by sku_id
     ),
     refund_order_tmp as (
         select sku_id,
                count(1)           refund_order_count,
                sum(refund_num)    refund_order_num,
                sum(refund_amount) refund_order_amount
         from dwd_order_refund_info
         where dt = '2022-04-26'
         group by sku_id
     ),
     refund_payment_tmp as (
         select sku_id,
                count(1)           refund_payment_count,
                sum(refund_num)    refund_payment_num,
                sum(refund_amount) refund_payment_amount
         from dwd_order_refund_info
         where dt = '2022-04-26'
           and order_id in (select order_id from dwd_payment_info where callback_time is not null)
         group by sku_id
     ),
     action_tmp as (
         select action_item                            sku_id,
                sum(if(action_id = 'cart_add', 1, 0))  cart_count,
                sum(if(action_id = 'favor_add', 1, 0)) favor_count
         from dwd_action_log
         where dt = '2022-04-26'
           and action_id in ('cart_add', 'favor_add')
         group by action_item
     ),
     comment_tmp as (
         select sku_id,
                sum(if(appraise = '1201', 1, 0)) appraise_good_count,
                sum(if(appraise = '1202', 1, 0)) appraise_mid_count,
                sum(if(appraise = '1203', 1, 0)) appraise_bad_count,
                sum(if(appraise = '1204', 1, 0)) appraise_default_count
         from dwd_comment_info
         where dt = '2022-04-26'
         group by sku_id
     )
-- insert
-- overwrite
-- table
-- dws_sku_action_daycount
-- partition
-- (
-- dt = '2022-04-26'
-- )
select coalesce(order_tmp.sku_id, payment_tmp.sku_id, refund_order_tmp.sku_id, refund_payment_tmp.sku_id,
                action_tmp.sku_id, comment_tmp.sku_id),
       order_count,
       order_num,
       order_activity_count,
       order_coupon_count,
       order_activity_amount,
       order_coupon_amount,
       order_original_amount,
       order_final_amount,
       payment_count,
       payment_num,
       payment_amount,
       refund_order_count,
       refund_order_num,
       refund_order_amount,
       refund_payment_count,
       refund_payment_num,
       refund_payment_amount,
       cart_count,
       favor_count,
       appraise_good_count,
       appraise_mid_count,
       appraise_bad_count,
       appraise_default_count
from order_tmp
         full outer join payment_tmp on order_tmp.sku_id = payment_tmp.sku_id
         full outer join refund_order_tmp on coalesce(order_tmp.sku_id, payment_tmp.sku_id) = refund_order_tmp.sku_id
         full outer join refund_payment_tmp on coalesce(order_tmp.sku_id, payment_tmp.sku_id, refund_order_tmp.sku_id) =
                                               refund_payment_tmp.sku_id
         full outer join action_tmp on coalesce(order_tmp.sku_id, payment_tmp.sku_id, refund_order_tmp.sku_id,
                                                refund_payment_tmp.sku_id) = action_tmp.sku_id
         full outer join comment_tmp on coalesce(order_tmp.sku_id, payment_tmp.sku_id, refund_order_tmp.sku_id,
                                                 refund_payment_tmp.sku_id, action_tmp.sku_id) = comment_tmp.sku_id;


insert overwrite table dws_coupon_info_daycount partition (dt = '2022-04-26')
select t1.coupon_id,
       get_count,
       expire_count,
       order_count,
       order_reduce_amount,
       order_original_amount,
       order_final_amount,
       payment_count,
       payment_reduce_amount,
       payment_original_amount,
       payment_final_amount
from (
         select coupon_id,
                sum(if(date_format(get_time, 'yyyy-MM-dd') = '2022-04-26', 1, 0))    get_count,
                sum(if(date_format(expire_time, 'yyyy-MM-dd') = '2022-04-26', 1, 0)) expire_count
         from dwd_coupon_use
         where dt = '2022-04-26'
         group by coupon_id
     ) t1
         left join
     (
         select coupon_id,
                count(1)                 order_count,
                sum(split_coupon_amount) order_reduce_amount,
                sum(original_amount)     order_original_amount,
                sum(split_total_amount)  order_final_amount
         from dwd_order_detail
         group by coupon_id
     ) t2
     on t1.coupon_id = t2.coupon_id
         left join
     (
         select coupon_id,
                count(1)                 payment_count,
                sum(split_coupon_amount) payment_reduce_amount,
                sum(original_amount)     payment_original_amount,
                sum(split_total_amount)  payment_final_amount
         from dwd_order_detail
         where order_id in (select order_id from dwd_payment_info where callback_time is not null)
         group by coupon_id
     ) t3
     on t1.coupon_id = t3.coupon_id;


insert overwrite table dws_activity_info_daycount partition (dt = '2022-04-26')
select t1.activity_rule_id,
       t1.activity_id,
       order_count,
       order_reduce_amount,
       order_original_amount,
       order_final_amount,
       payment_count,
       payment_reduce_amount,
       payment_original_amount,
       payment_final_amount
from (
         select activity_rule_id,
                activity_id,
                count(1)                   order_count,
                sum(split_activity_amount) order_reduce_amount,
                sum(original_amount)       order_original_amount,
                sum(split_total_amount)    order_final_amount
         from dwd_order_detail
         where dt = '2022-04-26'
           and split_activity_amount > 0
         group by activity_rule_id, activity_id
     ) t1
         left join
     (
         select activity_rule_id,
                activity_id,
                count(1)                   payment_count,
                sum(split_activity_amount) payment_reduce_amount,
                sum(original_amount)       payment_original_amount,
                sum(split_total_amount)    payment_final_amount
         from dwd_order_detail
         where dt = '2022-04-26'
           and split_activity_amount > 0
           and order_id in (select order_id from dwd_payment_info where callback_time is not null)
         group by activity_rule_id, activity_id
     ) t2
     on t1.activity_rule_id = t2.activity_rule_id;

insert overwrite table dws_area_info_daycount partition (dt = '2022-04-26')
select t1.area_code,
       visit_count,
       login_count,
       visitor_count,
       user_count,
       order_count,
       order_amount,
       payment_count,
       payment_count,
       refund_order_count,
       refund_order_amount,
       refund_payment_count,
       refund_payment_amount
from (
         select area_code,
                count(1)                           visit_count,
                sum(if(user_id is not null, 1, 0)) login_count,
                sum(distinct mid_id)               visitor_count,
                sum(distinct user_id)              user_count
         from dwd_page_log
         where dt = '2022-04-26'
           and last_page_id is null
         group by area_code
     ) t1
         left join
     (
         select count(1)          order_count,
                sum(total_amount) order_amount,
                dim_base_province.area_code
         from dwd_order_info
                  left join dim_base_province
                            on dwd_order_info.province_id = dim_base_province.id
         group by area_code
     ) t2
     on t1.area_code = t2.area_code
         left join
     (
         select count(1)          payment_count,
                sum(total_amount) payment_amount,
                dim_base_province.area_code
         from dwd_payment_info
                  left join dim_base_province
                            on dwd_payment_info.province_id = dim_base_province.id
         group by area_code
     ) t3
     on t1.area_code = t3.area_code
         left join
     (
         select count(1)           refund_order_count,
                sum(refund_amount) refund_order_amount,
                dim_base_province.area_code
         from dwd_order_refund_info
                  left join dim_base_province
                            on dwd_order_refund_info.province_id = dim_base_province.id
         group by area_code
     ) t4
     on t1.area_code = t4.area_code
         left join
     (
         select count(1)          refund_payment_count,
                sum(total_amount) refund_payment_amount,
                dim_base_province.area_code
         from dwd_refund_payment
                  left join dim_base_province
                            on dwd_refund_payment.province_id = dim_base_province.id
         group by area_code
     ) t5
     on t1.area_code = t5.area_code






