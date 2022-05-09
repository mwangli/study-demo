use warehouse;
set hive.exec.dynamic.partition.mode=nonstrict;
set spark.executor.memory=2g;
set spark.yarn.executor.memoryOverhead=2048;

insert overwrite table ads_visit_stats
select *
from ads_visit_stats
union
select '2022-04-26',
       is_new,
       recent_days,
       channel,
       count(distinct mid_id),
       sum(during_time) / 1000,
       avg(during_time) / 1000,
       sum(page_count),
       avg(page_count),
       count(1),
       sum(if(page_count = 1, 1, 0)),
       sum(if(page_count = 1, 1, 0)) / count(1),
       date('2022-04-26')
from (
         select channel,
                is_new,
                recent_days,
                mid_id,
                sum(during_time) during_time,
                count(1)         page_count
         from (
                  select mid_id,
                         recent_days,
                         last_page_id,
                         is_new,
                         channel,
                         during_time,
                         ts,
                         concat(mid_id, '-',
                                last_value(if(last_page_id is null, ts, null), true)
                                           over (partition by mid_id,recent_days order by ts)) seession_id
                  from dwd_page_log lateral view explode(array(1, 7, 30)) tmp as recent_days
                  where dt >= date_add('2022-04-26', -recent_days + 1)
              ) t
         group by seession_id, mid_id, channel, is_new, recent_days
     ) a
group by channel, is_new, recent_days;

insert overwrite table ads_page_path
select *
from ads_page_path
union
select '2022-04-26',
       recent_days,
       source,
       target,
       count(1) page_count,
       date('2022-04-26')
from (
         select concat('step', rn, ':', page_id)          source,
                concat('step', rn + 1, ':', next_page_id) target,
                recent_days
         from (
                  select page_id,
                         lead(page_id, 1, null) over (partition by session_id order by ts) next_page_id,
                         recent_days,
                         row_number() over (partition by session_id order by ts)           rn
                  from (
                           select recent_days,
                                  page_id,
                                  ts,
                                  concat(mid_id, '-',
                                         last_value(if(last_page_id is null, ts, null), true)
                                                    over (partition by mid_id order by ts)) session_id
                           from dwd_page_log lateral view explode(array(1, 7, 30)) tmp as recent_days
                           where dt >= date_add('2022-04-26', -recent_days + 1)
                       ) t1
              ) t2
     ) t3
group by source, target, recent_days;

insert overwrite table ads_user_change
select *
from ads_user_change
union
select '2022-04-26',
       (
           select count(id)
           from ods_user_info
           where id not in
                 (
                     select user_id
                     from dwt_user_action_7daycount
                     where dt = '2022-04-26'
                       and login_count > 0
                 )
       ),
       (
           select count(user_id)
           from dws_user_action_daycount
           where dt = '2022-04-26'
             and login_count > 0
             and user_id in
                 (
                     select id
                     from ods_user_info
                     where id not in
                           (
                               select user_id
                               from dwt_user_action_7daycount
                               where dt = date_add('2022-04-26', -1)
                                 and login_count > 0
                           )
                 )
       ),
       date('2022-04-26');

insert overwrite table ads_user_action
select *
from ads_user_action
union
select '2022-04-26',
       t1.recent_days,
       home_count,
       good_detail_count,
       user_cart_count,
       user_order_count,
       user_payment_count,
       date('2022-04-26')
from (
         select recent_days,
                sum(if(page_id = 'home', 1, 0))        home_count,
                sum(if(page_id = 'good_detail', 1, 0)) good_detail_count
         from dwd_page_log lateral view explode(array(1, 7, 30)) tmp as recent_days
         where dt >= date_add('2022-04-26', -recent_days + 1)
           and page_id in ('home', 'good_detail')
         group by recent_days
     ) t1
         left join
     (
         select recent_days,
                sum(if(cart_count > 0, 1, 0))    user_cart_count,
                sum(if(order_count > 0, 1, 0))   user_order_count,
                sum(if(payment_count > 0, 1, 0)) user_payment_count
         from dws_user_action_daycount lateral view explode(array(1, 7, 30)) tmp as recent_days
         where dt >= date_add('2022-04-26', -recent_days + 1)
           and (cart_count > 0 or order_count > 0 or payment_count > 0)
         group by recent_days
     ) t2
     on t1.recent_days = t2.recent_days;

insert overwrite table ads_user_retention
select *
from ads_user_retention
union
select t1.date_id,
       create_date,
       retention_days,
       retention_count,
       new_user_count,
       retention_count / new_user_count,
       date('2022-04-26')
from (
         select '2022-04-30'                                                   date_id,
                date_format(create_time, 'yyyy-MM-dd')                         create_date,
                datediff('2022-04-30', date_format(create_time, 'yyyy-MM-dd')) retention_days,
                count(1)                                                       new_user_count
         from ods_user_info
         where create_time >= date_add('2022-04-30', -7)
           and create_time < '2022-04-30'
         group by date_format(create_time, 'yyyy-MM-dd')
     ) t1
         left join
     (
         select '2022-04-30' date_id,
                count(1)     retention_count
         from dws_user_action_daycount
         where dt = '2022-04-30'
     ) t2
     on t1.date_id = t2.date_id;

insert overwrite table ads_order_spu_stats
select *
from ads_order_spu_stats
union
select '2022-04-26',
       1,
       spu_id,
       spu_name,
       tm_id,
       tm_name,
       category3_id,
       category3_name,
       category2_id,
       category2_name,
       category1_id,
       category1_name,
       sum(order_count),
       sum(order_final_amount),
       date('2022-04-26')
from dws_sku_action_daycount
         left join dim_sku_info on dws_sku_action_daycount.sku_id = dim_sku_info.id
group by spu_id, spu_name, tm_id, tm_name, category3_id, category3_name, category2_id, category2_name, category1_id,
         category1_name
union
select '2022-04-26',
       7,
       spu_id,
       spu_name,
       tm_id,
       tm_name,
       category3_id,
       category3_name,
       category2_id,
       category2_name,
       category1_id,
       category1_name,
       sum(order_count),
       sum(order_final_amount),
       date('2022-04-26')
from dwt_sku_action_7daycount
         left join dim_sku_info on dwt_sku_action_7daycount.sku_id = dim_sku_info.id
group by spu_id, spu_name, tm_id, tm_name, category3_id, category3_name, category2_id, category2_name, category1_id,
         category1_name
union
select '2022-04-26',
       30,
       spu_id,
       spu_name,
       tm_id,
       tm_name,
       category3_id,
       category3_name,
       category2_id,
       category2_name,
       category1_id,
       category1_name,
       sum(order_count),
       sum(order_final_amount),
       date('2022-04-26')
from dwt_sku_action_30daycount
         left join dim_sku_info on dwt_sku_action_30daycount.sku_id = dim_sku_info.id
group by spu_id, spu_name, tm_id, tm_name, category3_id, category3_name, category2_id, category2_name, category1_id,
         category1_name;


insert overwrite table ads_repeat_purchase
select *
from ads_repeat_purchase
union
select '2022-04-26',
       recent_days,
       tm_id,
       tm_name,
       sum(if(payment_count > 1, 1, 0)) / sum(if(payment_count >= 1, 1, 0)),
       date('2022-04-26')
from (
         select recent_days,
                tm_id,
                tm_name,
                count(1) payment_count
         from (
                  select recent_days,
                         sku_id,
                         user_id,
                         order_id
                  from dwd_order_detail d lateral view explode(array(1, 7, 30)) tmp as recent_days
                  where dt >= date_add('2022-04-26', -recent_days + 1)
                    and d.order_id in (select order_id from dwd_payment_info where callback_time is not null)
              ) d
                  left join dim_sku_info i on d.sku_id = i.id
         group by tm_id, tm_name, user_id, recent_days
     ) t
group by tm_id, tm_name, recent_days;


insert overwrite table ads_order_stats
select *
from ads_order_stats
union
select '2022-04-26',
       recent_days,
       count(user_id),
       sum(order_count),
       sum(order_final_amount),
       date('2022-04-26')
from dws_user_action_daycount lateral view explode(array(1, 7, 30)) tmp as recent_days
where dt >= date_add('2022-04-26', -recent_days + 1)
group by recent_days;

insert overwrite table ads_coupon_stats
select *
from ads_coupon_stats
union
select '2022-04-26',
       recent_days,
       coupon_id,
       coupon_name,
       start_date,
       rule_name,
       sum(get_count),
       sum(order_count),
       sum(expire_count),
       sum(order_original_amount),
       sum(order_final_amount),
       sum(order_reduce_amount),
       sum(order_reduce_amount) / sum(order_final_amount),
       date('2022-04-26')
from (
         select recent_days,
                coupon_id,
                get_count,
                order_count,
                expire_count,
                order_original_amount,
                order_final_amount,
                order_reduce_amount,
                order_reduce_amount / order_original_amount reduce_rate
         from dws_coupon_info_daycount lateral view explode(array(1, 7, 30)) tmp as recent_days
         where dt >= date_add('2022-04-26', -recent_days + 1)
     ) t1
         left join
     (
         select id,
                coupon_name,
                start_time,
                date_format(start_time, 'yyyy-MM-dd') start_date,
                case
                    when coupon_type = '3201' then concat('满', condition_amount, '元减', benefit_amount, '元')
                    when coupon_type = '3202' then concat('满', condition_num, '件打', benefit_discount, '折')
                    when coupon_type = '3203' then concat('减', benefit_amount, '元')
                    end                               rule_name
         from dim_coupon_info
         where dt = '2022-04-26'
     ) t2
     on t1.coupon_id = t2.id
group by coupon_id, coupon_name, start_date, rule_name, recent_days;


insert overwrite table ads_activity_stats
select *
from ads_activity_stats
union
select '2022-04-26',
       recent_days,
       t1.activity_id,
       activity_name,
       start_date,
       order_count,
       order_original_amount,
       order_final_amount,
       reduce_amount,
       reduce_amount / order_final_amount,
       date('2022-04-26')
from (
         select recent_days,
                activity_id,
                sum(order_count)           order_count,
                sum(order_original_amount) order_original_amount,
                sum(order_final_amount)    order_final_amount,
                sum(order_reduce_amount)   reduce_amount
         from dws_activity_info_daycount lateral view explode(array(1, 7, 30)) tmp as recent_days
         where dt = '2022-04-26'
         group by activity_id, recent_days
     ) t1
         left join
     (
         select id,
                activity_name,
                date_format(start_time, 'yyyy-MM-dd') start_date
         from ods_activity_info
         where dt = '2022-04-26'
     ) t2
     on t1.activity_id = t2.id;