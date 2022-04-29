use warehouse;
set hive.exec.dynamic.partition.mode=nonstrict;

with login_tmp as (
    select dt,
           user_id,
           count(1) login_count
    from dwd_page_log
    where user_id is not null
      and last_page_id is null
    group by user_id, dt
),
     cart_favor_tmp as (
         select dt,
                user_id,
                sum(if(action_id = 'cart_add', 1, 0))  cart_count,
                sum(if(action_id = 'favor_add', 1, 0)) favor_count
         from dwd_action_log
         where user_id is not null
           and action_id in ('cart_add', 'favor_add')
         group by user_id, dt
     ),
     order_info_tmp as (
         select date_format(create_time, 'yyyy-MM-dd')    order_date,
                user_id,
                count(1)                                  order_count,
                sum(if(activity_reduce_amount > 0, 1, 0)) order_activity_count,
                sum(activity_reduce_amount)               order_activity_amount,
                sum(if(coupon_reduce_amount > 0, 1, 0))   order_coupon_count,
                sum(coupon_reduce_amount)                 order_coupon_amount,
                sum(original_total_amount)                order_original_amount,
                sum(total_amount)                         order_final_amount
         from dwd_order_info
         group by user_id, date_format(create_time, 'yyyy-MM-dd')
     ),
     payment_info_tmp as (
         select dt,
                user_id,
                count(1)          payment_count,
                sum(total_amount) payment_amount
         from dwd_payment_info
         where dt != '9999-99-99'
         group by user_id, dt
     ),
     order_fefund_info_tmp as (
         select dt,
                user_id,
                count(1)           refund_order_count,
                sum(refund_num)    refund_order_num,
                sum(refund_amount) refund_order_amount
         from dwd_order_refund_info
         group by user_id, dt
     ),
     payment_refund_info_tmp as (
         select dt,
                user_id,
                count(1)          refund_payment_count,
                sum(refund_num)   refund_payment_num,
                sum(total_amount) refund_payment_amount
         from (
                  select dt,
                         user_id,
                         total_amount,
                         order_id,
                         sku_id
                  from dwd_refund_payment
                  where dt != '9999-99-99'
              ) p
                  left join
              (
                  select refund_num,
                         order_id,
                         sku_id
                  from dwd_order_refund_info
                  where dt != '9999-99-99'
              ) o
              on p.order_id = o.order_id and p.sku_id = o.sku_id
         group by user_id, dt
     ),
     coupon_tmp as (
         select coupon_get_tmp.user_id,
                coupon_get_tmp.get_date,
                coupon_get_count,
                coupon_using_count,
                coupon_used_count
         from (
                  select date_format(get_time, 'yyyy-MM-dd') get_date,
                         user_id,
                         count(1)                            coupon_get_count
                  from dwd_coupon_use
                  where get_time is not null
                  group by user_id, date_format(get_time, 'yyyy-MM-dd')
              ) coupon_get_tmp
                  left join
              (
                  select date_format(using_time, 'yyyy-MM-dd') using_date,
                         user_id,
                         count(1)                              coupon_using_count
                  from dwd_coupon_use
                  where using_time is not null
                  group by user_id, date_format(using_time, 'yyyy-MM-dd')
              ) coupon_using_tmp on coupon_get_tmp.user_id = coupon_using_tmp.user_id
                  and coupon_get_tmp.get_date = coupon_using_tmp.using_date
                  left join (
             select date_format(used_time, 'yyyy-MM-dd') used_date,
                    user_id,
                    count(1)                             coupon_used_count
             from dwd_coupon_use
             where used_time is not null
             group by user_id, date_format(used_time, 'yyyy-MM-dd')
         ) coupon_used_tmp on coupon_get_tmp.user_id = coupon_used_tmp.user_id
             and coupon_get_tmp.get_date = coupon_used_tmp.used_date
     ),
     comment_tmp as (
         select dt,
                user_id,
                sum(if(appraise = '1201', 1, 0)) appraise_good_count,
                sum(if(appraise = '1202', 1, 0)) appraise_mid_count,
                sum(if(appraise = '1203', 1, 0)) appraise_bad_count,
                sum(if(appraise = '1204', 1, 0)) appraise_default_count
         from dwd_comment_info
         group by user_id, dt
     ),
     order_detail_tmp as (
         select dt,
                user_id,
                collect_set(named_struct('sku_id', sku_id, 'sku_num', sku_num, 'order_count', order_count,
                                         'order_activity_amount', split_activity_amount, 'order_coupon_amount',
                                         split_coupon_amount, 'order_original_amount', original_amount,
                                         'order_final_amount', split_total_amount)) order_detail_status
         from (
                  select dt,
                         user_id,
                         sku_id,
                         count(distinct order_id)   order_count,
                         sum(sku_num)               sku_num,
                         sum(split_activity_amount) split_activity_amount,
                         sum(split_coupon_amount)   split_coupon_amount,
                         sum(original_amount)       original_amount,
                         sum(split_total_amount)    split_total_amount
                  from dwd_order_detail
                  where user_id = 337
                    and dt = '2022-04-27'
                    and sku_id = 33
                  group by user_id, dt, sku_id
              ) t
         group by user_id, dt
     )
insert overwrite table dws_user_action_daycount partition(dt)
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
       order_detail_status,
       coalesce(login_tmp.dt, cart_favor_tmp.dt, order_info_tmp.order_date, payment_info_tmp.dt,
                order_fefund_info_tmp.dt, payment_refund_info_tmp.dt, coupon_tmp.get_date,
                comment_tmp.dt, order_detail_tmp.dt)
from login_tmp
         full outer join cart_favor_tmp
                         on login_tmp.user_id = cart_favor_tmp.user_id
                             and login_tmp.dt = cart_favor_tmp.dt
         full outer join order_info_tmp
                         on coalesce(login_tmp.user_id, cart_favor_tmp.user_id) = order_info_tmp.user_id
                             and coalesce(login_tmp.dt, cart_favor_tmp.dt) = order_info_tmp.order_date
         full outer join payment_info_tmp
                         on coalesce(login_tmp.user_id, cart_favor_tmp.user_id, order_info_tmp.user_id) =
                            payment_info_tmp.user_id
                             and
                            coalesce(login_tmp.dt, cart_favor_tmp.dt, order_info_tmp.order_date) = payment_info_tmp.dt
         full outer join order_fefund_info_tmp
                         on coalesce(login_tmp.user_id, cart_favor_tmp.user_id, order_info_tmp.user_id,
                                     payment_info_tmp.user_id) = order_fefund_info_tmp.user_id
                             and
                            coalesce(login_tmp.dt, cart_favor_tmp.dt, order_info_tmp.order_date, payment_info_tmp.dt) =
                            order_fefund_info_tmp.dt
         full outer join payment_refund_info_tmp
                         on coalesce(login_tmp.user_id, cart_favor_tmp.user_id, order_info_tmp.user_id,
                                     payment_info_tmp.user_id, order_fefund_info_tmp.user_id) =
                            payment_refund_info_tmp.user_id
                             and
                            coalesce(login_tmp.dt, cart_favor_tmp.dt, order_info_tmp.order_date, payment_info_tmp.dt,
                                     order_fefund_info_tmp.dt) = payment_refund_info_tmp.dt
         full outer join coupon_tmp
                         on coalesce(login_tmp.user_id, cart_favor_tmp.user_id, order_info_tmp.user_id,
                                     payment_info_tmp.user_id, order_fefund_info_tmp.user_id,
                                     payment_refund_info_tmp.user_id) = coupon_tmp.user_id
                             and
                            coalesce(login_tmp.dt, cart_favor_tmp.dt, order_info_tmp.order_date, payment_info_tmp.dt,
                                     order_fefund_info_tmp.dt, payment_refund_info_tmp.dt) = coupon_tmp.get_date
         full outer join comment_tmp
                         on coalesce(login_tmp.user_id, cart_favor_tmp.user_id, order_info_tmp.user_id,
                                     payment_info_tmp.user_id, order_fefund_info_tmp.user_id,
                                     payment_refund_info_tmp.user_id, coupon_tmp.user_id) = comment_tmp.user_id
                             and
                            coalesce(login_tmp.dt, cart_favor_tmp.dt, order_info_tmp.order_date, payment_info_tmp.dt,
                                     order_fefund_info_tmp.dt, payment_refund_info_tmp.dt, coupon_tmp.get_date) =
                            comment_tmp.dt
         full outer join order_detail_tmp
                         on coalesce(login_tmp.user_id, cart_favor_tmp.user_id, order_info_tmp.user_id,
                                     payment_info_tmp.user_id, order_fefund_info_tmp.user_id,
                                     payment_refund_info_tmp.user_id, coupon_tmp.user_id, comment_tmp.user_id) =
                            order_detail_tmp.user_id
                             and
                            coalesce(login_tmp.dt, cart_favor_tmp.dt, order_info_tmp.order_date, payment_info_tmp.dt,
                                     order_fefund_info_tmp.dt, payment_refund_info_tmp.dt, coupon_tmp.get_date,
                                     comment_tmp.dt) = order_detail_tmp.dt