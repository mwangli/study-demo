use warehouse;

--dwt_user_action_7daycount 用户行为周统计表
drop table if exists dwt_user_action_7daycount;
create external table dwt_user_action_7daycount
(
    user_id                string comment '用户id',
    login_count            bigint comment '登陆次数',
    cart_count             bigint comment '加购次数',
    favor_count            bigint comment '收藏次数',
    order_count            bigint comment '下单次数',
    order_activity_count   bigint comment '订单参数活动次数',
    order_activity_amount  decimal comment '订单参与活动减免金额',
    order_coupon_count     bigint comment '订单用券次数',
    order_coupon_amount    decimal comment '订单用券金额',
    order_original_amount  decimal comment '原始订单金额',
    order_final_amount     decimal comment '订单最终金额',
    payment_count          bigint comment '支付次数',
    payment_amount         decimal comment '支付金额',
    refund_order_count     bigint comment '退单次数',
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
    order_detail_status    array<array<struct<sku_id :string,sku_num :bigint, order_count :bigint, order_activity_amount
                                              :decimal(20), order_coupon_amount :decimal(20),order_original_amount
                                              :decimal(20),order_final_amount
                                              :decimal(20)>>> comment '订单明细'
) comment '用户行为周统计表'
    partitioned by (dt string)
    stored as parquet
    location '/hive/warehouse/dwt/dwt_user_action_7daycount'
    tblproperties ('parquet.compression' = 'lzo');

--dwt_user_action_30daycount 用户行为月统计表
drop table if exists dwt_user_action_30daycount;
create external table dwt_user_action_30daycount
(
    user_id                string comment '用户id',
    login_count            bigint comment '登陆次数',
    cart_count             bigint comment '加购次数',
    favor_count            bigint comment '收藏次数',
    order_count            bigint comment '下单次数',
    order_activity_count   bigint comment '订单参数活动次数',
    order_activity_amount  decimal comment '订单参与活动减免金额',
    order_coupon_count     bigint comment '订单用券次数',
    order_coupon_amount    decimal comment '订单用券金额',
    order_original_amount  decimal comment '原始订单金额',
    order_final_amount     decimal comment '订单最终金额',
    payment_count          bigint comment '支付次数',
    payment_amount         decimal comment '支付金额',
    refund_order_count     bigint comment '退单次数',
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
    order_detail_status    array<array<struct<sku_id :string,sku_num :bigint, order_count :bigint, order_activity_amount
                                              :decimal(20), order_coupon_amount :decimal(20),order_original_amount
                                              :decimal(20),order_final_amount
                                              :decimal(20)>>> comment '订单明细'
) comment '用户行为月统计表'
    partitioned by (dt string)
    stored as parquet
    location '/hive/warehouse/dwt/dwt_user_action_30daycount'
    tblproperties ('parquet.compression' = 'lzo');


--dwt_visitor_action_7daycount 访客行为周统计表
drop table if exists dwt_visitor_action_7daycount;
create external table dwt_visitor_action_7daycount
(
    mid_id       string comment '设备id',
    brand        string comment '设备品牌',
    model        string comment '设备型号',
    is_new       string comment '是否首次访问',
    channel      array<array<string>> comment '渠道',
    os           array<array<string>> comment '操作系统',
    area_code    array<array<string>> comment '地区id',
    version_code array<array<string>> comment '应用版本',
    visit_count  bigint comment '访问次数',
    visit_pages  array<array<struct<page_id:string,page_count:bigint,during_time:bigint>>> comment '访问页面'
) comment '访客行为周统计表'
    partitioned by (dt string)
    stored as parquet
    location '/hive/warehouse/dwt/dwt_visitor_action_7daycount'
    tblproperties ('parquet.compression' = 'lzo');

--dwt_visitor_action_30daycount 访客行为月统计表
drop table if exists dwt_visitor_action_30daycount;
create external table dwt_visitor_action_30daycount
(
    mid_id       string comment '设备id',
    brand        string comment '设备品牌',
    model        string comment '设备型号',
    is_new       string comment '是否首次访问',
    channel      array<array<string>> comment '渠道',
    os           array<array<string>> comment '操作系统',
    area_code    array<array<string>> comment '地区id',
    version_code array<array<string>> comment '应用版本',
    visit_count  bigint comment '访问次数',
    visit_pages  array<array<struct<page_id:string,page_count:bigint,during_time:bigint>>> comment '访问页面'
) comment '访客行为月统计表'
    partitioned by (dt string)
    stored as parquet
    location '/hive/warehouse/dwt/dwt_visitor_action_30daycount'
    tblproperties ('parquet.compression' = 'lzo');


--dwt_sku_action_7daycount 商品数据周统计表
drop table if exists dwt_sku_action_7daycount;
create external table dwt_sku_action_7daycount
(
    sku_id                 string comment 'sku_id',
    order_count            bigint comment '被下单次数',
    order_num              bigint comment '被下单件数',
    order_activity_count   bigint comment '参与活动被下单次数',
    order_coupon_count     bigint comment '使用优惠券被下单次数',
    order_activity_amount  decimal comment '活动减免金额',
    order_coupon_amount    decimal comment '优惠券减免金额',
    order_original_amount  decimal comment '被下单原始金额',
    order_final_amount     decimal comment '被下单最终金额',
    payment_count          bigint comment '被支付次数',
    payment_num            bigint comment '被支付件数',
    payment_amount         decimal comment '被支付金额',
    refund_order_count     bigint comment '被退单次数',
    refund_order_num       bigint comment '被退单件数',
    refund_order_amount    decimal comment '被退单金额',
    refund_payment_count   bigint comment '被退款次数',
    refund_payment_num     bigint comment '被退款件数',
    refund_payment_amount  decimal comment '被退款金额',
    cart_count             bigint comment '被加购次数',
    favor_count            bigint comment '被收藏次数',
    appraise_good_count    bigint comment '好评次数',
    appraise_mid_count     bigint comment '中评次数',
    appraise_bad_count     bigint comment '差评次数',
    appraise_default_count bigint comment '默认评价次数'
) comment '商品数据周统计表'
    partitioned by (dt string)
    stored as parquet
    location '/hive/warehouse/dwt/dwt_sku_action_7daycount'
    tblproperties ('parquet.compression' = 'lzo');

--dwt_sku_action_30daycount 商品数据月统计表
drop table if exists dwt_sku_action_30daycount;
create external table dwt_sku_action_30daycount
(
    sku_id                 string comment 'sku_id',
    order_count            bigint comment '被下单次数',
    order_num              bigint comment '被下单件数',
    order_activity_count   bigint comment '参与活动被下单次数',
    order_coupon_count     bigint comment '使用优惠券被下单次数',
    order_activity_amount  decimal comment '活动减免金额',
    order_coupon_amount    decimal comment '优惠券减免金额',
    order_original_amount  decimal comment '被下单原始金额',
    order_final_amount     decimal comment '被下单最终金额',
    payment_count          bigint comment '被支付次数',
    payment_num            bigint comment '被支付件数',
    payment_amount         decimal comment '被支付金额',
    refund_order_count     bigint comment '被退单次数',
    refund_order_num       bigint comment '被退单件数',
    refund_order_amount    decimal comment '被退单金额',
    refund_payment_count   bigint comment '被退款次数',
    refund_payment_num     bigint comment '被退款件数',
    refund_payment_amount  decimal comment '被退款金额',
    cart_count             bigint comment '被加购次数',
    favor_count            bigint comment '被收藏次数',
    appraise_good_count    bigint comment '好评次数',
    appraise_mid_count     bigint comment '中评次数',
    appraise_bad_count     bigint comment '差评次数',
    appraise_default_count bigint comment '默认评价次数'
)comment '商品数据月统计表'
    partitioned by (dt string)
    stored as parquet
    location '/hive/warehouse/dwt/dwt_sku_action_30daycount'
    tblproperties ('parquet.compression' = 'lzo');


--dwt_coupon_info_7daycount 优惠券数据周统计表
drop table if exists dwt_coupon_info_7daycount;
create external table dwt_coupon_info_7daycount
(
    coupon_id               string comment '优惠券id',
    get_count               bigint comment '被领取次数',
    expire_count            bigint comment '过期次数',
    order_count             bigint comment '被下单次数',
    order_reduce_amount     decimal comment '下单优惠金额',
    order_original_amount   decimal comment '下单原始金额',
    order_final_amount      decimal comment '下单最终金额',
    payment_count           bigint comment '被支付次数',
    payment_reduce_amount   decimal comment '支付优惠金额',
    payment_original_amount decimal comment '支付原始金额',
    payment_final_amount    decimal comment '支付最终金额'
) comment '优惠券数据周统计表'
    partitioned by (dt string)
    stored as parquet
    location '/hive/warehouse/dwt/dwt_coupon_info_7daycount'
    tblproperties ('parquet.compression' = 'lzo');

--dwt_coupon_info_30daycount 优惠券数据月统计表
drop table if exists dwt_coupon_info_30daycount;
create external table dwt_coupon_info_30daycount
(
    coupon_id               string comment '优惠券id',
    get_count               bigint comment '被领取次数',
    expire_count            bigint comment '过期次数',
    order_count             bigint comment '被下单次数',
    order_reduce_amount     decimal comment '下单优惠金额',
    order_original_amount   decimal comment '下单原始金额',
    order_final_amount      decimal comment '下单最终金额',
    payment_count           bigint comment '被支付次数',
    payment_reduce_amount   decimal comment '支付优惠金额',
    payment_original_amount decimal comment '支付原始金额',
    payment_final_amount    decimal comment '支付最终金额'
) comment '优惠券数据月统计表'
    partitioned by (dt string)
    stored as parquet
    location '/hive/warehouse/dwt/dwt_coupon_info_7daycount'
    tblproperties ('parquet.compression' = 'lzo');

--dwt_activity_info_7daycount 活动数据周统计表
drop table if exists dwt_activity_info_7daycount;
create external table dwt_activity_info_7daycount
(
    activity_rule_id        string comment '活动规则id',
    activity_id             string comment '活动id',
    order_count             bigint comment '活动下单次数',
    order_reduce_amount     decimal comment '活动下单优惠金额',
    order_original_amount   decimal comment '活动下单原始金额',
    order_final_amount      decimal comment '活动下单最终金额',
    payment_count           bigint comment '活动支付次数',
    payment_reduce_amount   decimal comment '活动支付优惠金额',
    payment_original_amount decimal comment '活动支付原始金额',
    payment_final_amount    decimal comment '活动支付最终金额'
) comment '活动数据周统计表'
    partitioned by (dt string)
    stored as parquet
    location '/hive/warehouse/dwt/dwt_activity_info_7daycount'
    tblproperties ('parquet.compression' = 'lzo');

--dwt_activity_info_30daycount 活动数据月统计表
drop table if exists dwt_activity_info_30daycount;
create external table dwt_activity_info_30daycount
(
    activity_rule_id        string comment '活动规则id',
    activity_id             string comment '活动id',
    order_count             bigint comment '活动下单次数',
    order_reduce_amount     decimal comment '活动下单优惠金额',
    order_original_amount   decimal comment '活动下单原始金额',
    order_final_amount      decimal comment '活动下单最终金额',
    payment_count           bigint comment '活动支付次数',
    payment_reduce_amount   decimal comment '活动支付优惠金额',
    payment_original_amount decimal comment '活动支付原始金额',
    payment_final_amount    decimal comment '活动支付最终金额'
) comment '活动数据月统计表'
    partitioned by (dt string)
    stored as parquet
    location '/hive/warehouse/dwt/dwt_activity_info_30daycount'
    tblproperties ('parquet.compression' = 'lzo');

--dwt_area_info_7daycount 地区数据周统计表
drop table if exists dwt_area_info_7daycount;
create external table dwt_area_info_7daycount
(
    area_code             string comment '地区编号',
    visit_count           bigint comment '访问次数',
    login_count           bigint comment '登录次数',
    visitor_count         bigint comment '访客人数',
    user_count            bigint comment '用户人数',
    order_count           bigint comment '下单次数',
    order_amount          decimal comment '下单金额',
    payment_count         bigint comment '支付次数',
    payment_amount        decimal comment '支付金额',
    refund_order_count    bigint comment '退单次数',
    refund_order_amount   decimal comment '退单金额',
    refund_payment_count  bigint comment '退款次数',
    refund_payment_amount decimal comment '退款金额'
) comment '地区数据周统计表'
    partitioned by (dt string)
    stored as parquet
    location '/hive/warehouse/dwt/dwt_area_info_7daycount'
    tblproperties ('parquet.compression' = 'lzo');

--dwt_area_info_30daycount 地区数据月统计表
drop table if exists dwt_area_info_30daycount;
create external table dwt_area_info_30daycount
(
    area_code             string comment '地区编号',
    visit_count           bigint comment '访问次数',
    login_count           bigint comment '登录次数',
    visitor_count         bigint comment '访客人数',
    user_count            bigint comment '用户人数',
    order_count           bigint comment '下单次数',
    order_amount          decimal comment '下单金额',
    payment_count         bigint comment '支付次数',
    payment_amount        decimal comment '支付金额',
    refund_order_count    bigint comment '退单次数',
    refund_order_amount   decimal comment '退单金额',
    refund_payment_count  bigint comment '退款次数',
    refund_payment_amount decimal comment '退款金额'
) comment '地区数据月统计表'
    partitioned by (dt string)
    stored as parquet
    location '/hive/warehouse/dwt/dwt_area_info_30daycount'
    tblproperties ('parquet.compression' = 'lzo');