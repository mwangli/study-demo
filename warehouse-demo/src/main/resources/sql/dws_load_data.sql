use warehouse;
set hive.exec.dynamic.partition.mode=nonstrict;
set spark.executor.memory=2g;
set spark.yarn.executor.memoryOverhead=2048;

with login_tmp as (
    select user_id,
           count(1) login_count
    from dwd_page_log
    where dt = '20220-4-29'
      and user_id is not null
      and last_page_id is null
    group by user_id
),
     cart_favor_tmp as (
         select user_id,
                sum(if(action_id = 'cart_add', 1, 0))  cart_count,
                sum(if(action_id = 'favor_add', 1, 0)) favor_count
         from dwd_action_log
         where dt = '20220-4-29'
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
         where (dt = '20220-4-29' or dt = '9999-99-99')
           and date_format(create_time, 'yyyy-MM-dd') = '20220-4-29'
         group by user_id
     ),
     payment_info_tmp as (
         select user_id,
                count(1)          payment_count,
                sum(total_amount) payment_amount
         from dwd_payment_info
         where dt = '20220-4-29'
         group by user_id
     ),
     order_fefund_info_tmp as (
         select user_id,
                count(1)           refund_order_count,
                sum(refund_num)    refund_order_num,
                sum(refund_amount) refund_order_amount
         from dwd_order_refund_info
         where dt = '20220-4-29'
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
                  where dt = '20220-04-29'
              ) p
                  left join
              (
                  select refund_num,
                         order_id,
                         sku_id
                  from dwd_order_refund_info
                  where dt >= date_add('2022-04-29', -15)
              ) o
              on p.order_id = o.order_id and p.sku_id = o.sku_id
         group by user_id
     ),
     coupon_tmp as (
         select user_id,
                sum(if(date_format(get_time, 'yyyy-MM-dd') = '2022-06-29', 1, 0))   coupon_get_count,
                sum(if(date_format(using_time, 'yyyy-MM-dd') = '2022-06-29', 1, 0)) coupon_using_count,
                sum(if(date_format(used_time, 'yyyy-MM-dd') = '2022-06-29', 1, 0))  coupon_used_count
         from dwd_coupon_use
         where (dt = '2022-06-29' or dt = '9999-99-99')
           and (date_format(get_time, 'yyyy-MM-dd') = '2022-06-29'
             or date_format(using_time, 'yyyy-MM-dd') = '2022-06-29'
             or date_format(used_time, 'yyyy-MM-dd') = '2022-06-29')
         group by user_id
     ),
     comment_tmp as (
         select user_id,
                sum(if(appraise = '1201', 1, 0)) appraise_good_count,
                sum(if(appraise = '1202', 1, 0)) appraise_mid_count,
                sum(if(appraise = '1203', 1, 0)) appraise_bad_count,
                sum(if(appraise = '1204', 1, 0)) appraise_default_count
         from dwd_comment_info
         where dt = '20220-4-29'
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
                  where dt = '20220-4-29'
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
dt = '20022-04-29'
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
                            order_detail_tmp.user_id
