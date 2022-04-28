use warehouse;

--dws_user_action_daycount
drop table if exists dws_user_action_daycount;
create table dws_user_action_daycount
(
    user_id                string comment '用户id',
    login_count            bigint comment '登陆次数',
    cart_count             bigint comment '加购次数',
    favor_count            bigint comment '收藏次数',
    order_count            bigint comment '下单次数',
    order_activity_account bigint comment '订单参数活动次数',
    order_activity_amount  decimal comment '订单参与活动减免金额',
    order_coupon_count     bigint comment '订单用券次数',
    order_coupon_amount    decimal comment '订单用券金额',
    order_original_amount  decimal comment '原始订单金额',
    order_final_amount     decimal comment '订单最终金额',
    payment_count          bigint comment '支付次数',
    payment_amount         decimal comment '支付金额',
    refund_order_account   bigint comment '退单次数',
    refund_order_num       bigint comment '退单件数',
    refund_order_amount    decimal comment '退单金额',
    refund_payment_count   bigint comment '退款次数',
    refund_payment_num     bigint comment '退款件数',
    refund_payment_amount  decimal comment '退款金额',
    coupon_get_count       bigint comment '优惠券领取次数',
    coupon_using_count     bigint comment '优惠券下单次数',
    coupon_used_count      bigint comment '优惠券支付次数',
    appraise_good_count    bigint comment '好评次数',
    appraise_mid_count     bigint comment '中评次数',
    appraise_bad_count     bigint comment '差评次数',
    appraise_default_count bigint comment '默认评价次数',
    order_detail_status    array<struct<sku_id :string,sku_num :bigint, order_count :bigint, order_activity_amount
                                        :decimal, order_coupon_amount :decimal,order_oroginal_amount :decimal,order_final_amount
                                        :decimal>> comment '订单明细'
)
    partitioned by (dt string)
    stored as parquet
    location '/hive/warehouse/dws/dws_user_action_daycount'
    tblproperties ('parquet.compression' = 'lzo');