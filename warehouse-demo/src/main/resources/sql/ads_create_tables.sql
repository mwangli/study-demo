use warehouse;

--ads_visit_stats 访客统计
drop table if exists ads_visit_stats;
create table ads_visit_stats
(
    date_id          string comment '统计日期',
    is_new           string comment '是否新用户',
    recent_days      bigint comment '统计天数：1天,7天,30天',
    channel          string comment '渠道',
    uv_count         bigint comment '日活跃用户',
    duration_sec     bigint comment '会话页面停留总时长',
    avg_duration_sec bigint comment '会话页面平均停留时长',
    page_count       bigint comment '页面浏览数量',
    avg_page_count   bigint comment '一次会话页面平均浏览数',
    session_count    bigint comment '会话次数',
    bounce_count     bigint comment '跳出次数',
    bounce_rate      bigint comment '跳出率'
)
    row format delimited fields terminated by '\t'
    location '/hive/warehouse/ads/ads_visit_stats';

--ads_page_path 路径分析
drop table if exists ads_page_path;
create table ads_page_path
(
    date_id     string comment '统计日期',
    recent_days bigint comment '最近天数:1天,7天,30天',
    source      string comment '跳转起始页面',
    target      string comment '跳转目标页面',
    path_count  bigint comment '跳转次数'
)
    row format delimited fields terminated by '\t'
    location '/hive/warehouse/ads/ads_page_path';

--ads_user_change 用户变动统计
drop table if exists ads_user_change;
create table ads_user_change
(
    date_id          string comment '统计日期',
    user_churn_count bigint comment '流失用户数',
    user_back_count  bigint comment '回流用户数'
)
    row format delimited fields terminated by '\t'
    location '/hive/warehouse/ads/ads_user_change';

--ads_user_action 用户行为分析
drop table if exists ads_user_action;
create table ads_user_action
(
    date_id           string comment '统计日期',
    recent_days       bigint comment '统计天数：1天,7天,30天',
    home_count        bigint comment '首页浏览人数',
    good_detail_count bigint comment '商品详情页浏览人数',
    car_count         bigint comment '加购人数',
    order_count       bigint comment '下单人数',
    payment_count     bigint comment '支付人数'
)
    row format delimited fields terminated by '\t'
    location '/hive/warehouse/ads/ads_user_action';

--ads_user_retention 用户留存率
drop table if exists ads_user_retention;
create table ads_user_retention
(
    date_id         string comment '统计日期',
    crate_date      string comment '用户新增日期',
    retention_day   string comment '留存天数',
    retention_count bigint comment '留存用户数量',
    new_user_count  bigint comment '新增用户数量',
    retention_rate  decimal comment '留存率'
)
    row format delimited fields terminated by '\t'
    location '/hive/warehouse/ads/ads_user_retention';

--ads_order_spu_stats 商品统计
drop table if exists ads_order_spu_stats;
create table ads_order_spu_stats
(
    date_id        string comment '统计日期',
    recent_days    bigint comment '统计天数：1天,7天,30天',
    spu_id         string comment '商品id',
    spu_name       string comment '商品名称',
    tm_id          string comment '品牌id',
    tm_name        string comment '品牌名称',
    category3_id   string comment '三级品类id',
    category3_name string comment '三级品类名称',
    category2_id   string comment '二级品类id',
    category2_name string comment '二级品类名称',
    category1_id   string comment '一级品类id',
    category1_name string comment '一级品类名称',
    order_count    bigint comment '订单数量',
    order_amount   decimal comment '订单金额'
)
    row format delimited fields terminated by '\t'
    location '/hive/warehouse/ads/ads_order_spu_stats';

--ads_repeat_purchase 品牌复购率
drop table if exists ads_repeat_purchase;
create table ads_repeat_purchase
(
    date_id              string comment '统计日期',
    recent_days          bigint comment '统计天数：1天,7天,30天',
    tm_id                string comment '品牌id',
    tm_name              string comment '品牌名称',
    repeat_purchase_rate decimal comment '复购率'
)
    row format delimited fields terminated by '\t'
    location '/hive/warehouse/ads/ads_repeat_purchase';

--ads_order_stats 订单统计
drop table if exists ads_order_stats;
create table ads_order_stats
(
    date_id          string comment '统计日期',
    recent_days      bigint comment '统计天数：1天,7天,30天',
    order_user_count bigint comment '下单人数',
    order_count      bigint comment '订单数量',
    order_amount     decimal comment '订单金额'
)
    row format delimited fields terminated by '\t'
    location '/hive/warehouse/ads/ads_order_stats';

--ads_coupon_stats 优惠券统计
drop table if exists ads_coupon_stats;
create table ads_coupon_stats
(
    date_id               string comment '统计日期',
    recent_days           bigint comment '统计天数：1天,7天,30天',
    coupon_id             string comment '优惠券id',
    coupon_name           string comment '优惠券名称',
    start_date            string comment '发布日期',
    rule_name             string comment '优惠规则',
    get_count             string comment '领取次数',
    order_count           string comment '下单次数',
    expire_count          bigint comment '过期次数',
    order_original_amount decimal comment '订单原始金额',
    order_final_amount    decimal comment '订单最金额',
    reduce_amount         decimal comment '优惠金额',
    reduce_rate           decimal comment '补贴率'
)
    row format delimited fields terminated by '\t'
    location '/hive/warehouse/ads/ads_coupon_stats';

--ads_activity_stats 活动统计
drop table if exists ads_activity_stats;
create table ads_activity_stats
(
    date_id               string comment '统计日期',
    recent_days           bigint comment '统计天数：1天,7天,30天',
    activity_id           string comment '活动id',
    activity_name         string comment '活动名称',
    start_date            string comment '活动开始日期',
    order_count           string comment '下单次数',
    order_original_amount decimal comment '订单原始金额',
    order_final_amount    decimal comment '订单最金额',
    reduce_amount         decimal comment '优惠金额',
    reduce_rate           decimal comment '补贴率'
)
    row format delimited fields terminated by '\t'
    location '/hive/warehouse/ads/ads_activity_stats';
