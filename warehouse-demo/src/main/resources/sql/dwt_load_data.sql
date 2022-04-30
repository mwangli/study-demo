use warehouse;
set hive.exec.dynamic.partition.mode=nonstrict;
set spark.executor.memory=2g;
set spark.yarn.executor.memoryOverhead=2048;

insert overwrite table dwt_user_action_7daycount partition (dt = '2022-04-26')
select user_id,
       sum(login_count),
       sum(cart_count),
       sum(favor_count),
       sum(order_count),
       sum(order_activity_count),
       sum(order_activity_amount),
       sum(order_coupon_count),
       sum(order_coupon_amount),
       sum(order_original_amount),
       sum(order_final_amount),
       sum(payment_count),
       sum(payment_amount),
       sum(refund_order_count),
       sum(refund_order_num),
       sum(refund_order_amount),
       sum(refund_payment_count),
       sum(refund_payment_num),
       sum(refund_payment_amount),
       sum(coupon_get_count),
       sum(coupon_using_count),
       sum(coupon_used_count),
       sum(appraise_good_count),
       sum(appraise_mid_count),
       sum(appraise_bad_count),
       sum(appraise_default_count),
       collect_list(order_detail_status)
from dws_user_action_daycount
where dt <= '2022-04-26'
  and dt >= date_add('2022-04-26', -6)
group by user_id;

insert overwrite table dwt_user_action_30daycount partition (dt = '2022-04-26')
select user_id,
       sum(login_count),
       sum(cart_count),
       sum(favor_count),
       sum(order_count),
       sum(order_activity_count),
       sum(order_activity_amount),
       sum(order_coupon_count),
       sum(order_coupon_amount),
       sum(order_original_amount),
       sum(order_final_amount),
       sum(payment_count),
       sum(payment_amount),
       sum(refund_order_count),
       sum(refund_order_num),
       sum(refund_order_amount),
       sum(refund_payment_count),
       sum(refund_payment_num),
       sum(refund_payment_amount),
       sum(coupon_get_count),
       sum(coupon_using_count),
       sum(coupon_used_count),
       sum(appraise_good_count),
       sum(appraise_mid_count),
       sum(appraise_bad_count),
       sum(appraise_default_count),
       collect_list(order_detail_status)
from dws_user_action_daycount
where dt <= '2022-04-30'
  and dt >= date_add('2022-04-30', -29)
group by user_id;

insert overwrite table dwt_visitor_action_7daycount partition (dt = '2022-04-26')
select mid_id,
       brand,
       model,
       sum(is_new),
       collect_list(channel),
       collect_list(os),
       collect_list(area_code),
       collect_list(version_code),
       sum(visit_count),
       collect_list(visit_pages)
from dws_visitor_action_daycount
where dt <= '2022-04-26'
  and dt >= date_add('2022-04-26', -6)
group by mid_id, brand, model;

insert overwrite table dwt_visitor_action_7daycount partition (dt = '2022-04-26')
select mid_id,
       brand,
       model,
       sum(is_new),
       collect_list(channel),
       collect_list(os),
       collect_list(area_code),
       collect_list(version_code),
       sum(visit_count),
       collect_list(visit_pages)
from dws_visitor_action_daycount
where dt <= '2022-04-26'
  and dt >= date_add('2022-04-26', -29)
group by mid_id, brand, model;

insert overwrite table dwt_sku_action_7daycount partition (dt = '2022-04-26')
select sku_id,
       sum(order_count),
       sum(order_num),
       sum(order_activity_count),
       sum(order_coupon_count),
       sum(order_activity_amount),
       sum(order_coupon_amount),
       sum(order_original_amount),
       sum(order_final_amount),
       sum(payment_count),
       sum(payment_num),
       sum(payment_amount),
       sum(refund_order_count),
       sum(refund_order_num),
       sum(refund_order_amount),
       sum(refund_payment_count),
       sum(refund_payment_num),
       sum(refund_payment_amount),
       sum(cart_count),
       sum(favor_count),
       sum(appraise_good_count),
       sum(appraise_mid_count),
       sum(appraise_bad_count),
       sum(appraise_default_count)
from dws_sku_action_daycount
where dt <= '2022-04-26'
  and dt >= date_add('2022-04-26', -6)
group by sku_id;

insert overwrite table dwt_sku_action_30daycount partition (dt = '2022-04-26')
select sku_id,
       sum(order_count),
       sum(order_num),
       sum(order_activity_count),
       sum(order_coupon_count),
       sum(order_activity_amount),
       sum(order_coupon_amount),
       sum(order_original_amount),
       sum(order_final_amount),
       sum(payment_count),
       sum(payment_num),
       sum(payment_amount),
       sum(refund_order_count),
       sum(refund_order_num),
       sum(refund_order_amount),
       sum(refund_payment_count),
       sum(refund_payment_num),
       sum(refund_payment_amount),
       sum(cart_count),
       sum(favor_count),
       sum(appraise_good_count),
       sum(appraise_mid_count),
       sum(appraise_bad_count),
       sum(appraise_default_count)
from dws_sku_action_daycount
where dt <= '2022-04-26'
  and dt >= date_add('2022-04-26', -29)
group by sku_id;

insert overwrite table dwt_coupon_info_7daycount partition (dt = '2022-04-26')
select coupon_id,
       sum(get_count),
       sum(expire_count),
       sum(order_count),
       sum(order_reduce_amount),
       sum(order_original_amount),
       sum(order_final_amount),
       sum(payment_count),
       sum(payment_reduce_amount),
       sum(payment_original_amount),
       sum(payment_final_amount)
from dws_coupon_info_daycount
where dt <= '2022-04-26'
  and dt >= date_add('2022-04-26', -6)
group by coupon_id;

insert overwrite table dwt_coupon_info_30daycount partition (dt = '2022-04-26')
select coupon_id,
       sum(get_count),
       sum(expire_count),
       sum(order_count),
       sum(order_reduce_amount),
       sum(order_original_amount),
       sum(order_final_amount),
       sum(payment_count),
       sum(payment_reduce_amount),
       sum(payment_original_amount),
       sum(payment_final_amount)
from dws_coupon_info_daycount
where dt <= '2022-04-26'
  and dt >= date_add('2022-04-26', -29)
group by coupon_id;

insert overwrite table dwt_activity_info_7daycount partition (dt = '2022-04-26')
select activity_rule_id,
       activity_id,
       sum(order_count),
       sum(order_reduce_amount),
       sum(order_original_amount),
       sum(order_final_amount),
       sum(payment_count),
       sum(payment_reduce_amount),
       sum(payment_original_amount),
       sum(payment_final_amount)
from dws_activity_info_daycount
where dt <= '2022-04-26'
  and dt >= date_add('2022-04-26', -6)
group by activity_rule_id, activity_id;

insert overwrite table dwt_activity_info_30daycount partition (dt = '2022-04-26')
select activity_rule_id,
       activity_id,
       sum(order_count),
       sum(order_reduce_amount),
       sum(order_original_amount),
       sum(order_final_amount),
       sum(payment_count),
       sum(payment_reduce_amount),
       sum(payment_original_amount),
       sum(payment_final_amount)
from dws_activity_info_daycount
where dt <= '2022-04-26'
  and dt >= date_add('2022-04-26', -29)
group by activity_rule_id, activity_id;

insert overwrite table dwt_area_info_7daycount partition (dt = '2022-04-26')
select area_code,
       sum(visit_count),
       sum(login_count),
       sum(visitor_count),
       sum(user_count),
       sum(order_count),
       sum(order_amount),
       sum(payment_count),
       sum(payment_amount),
       sum(refund_order_count),
       sum(refund_order_amount),
       sum(refund_payment_count),
       sum(refund_payment_amount)
from dws_area_info_daycount
where dt <= '2022-04-26'
  and dt >= date_add('2022-04-26', -6)
group by area_code;

insert overwrite table dwt_area_info_30daycount partition (dt = '2022-04-26')
select area_code,
       sum(visit_count),
       sum(login_count),
       sum(visitor_count),
       sum(user_count),
       sum(order_count),
       sum(order_amount),
       sum(payment_count),
       sum(payment_amount),
       sum(refund_order_count),
       sum(refund_order_amount),
       sum(refund_payment_count),
       sum(refund_payment_amount)
from dws_area_info_daycount
where dt <= '2022-04-26'
  and dt >= date_add('2022-04-26', -29)
group by area_code;

