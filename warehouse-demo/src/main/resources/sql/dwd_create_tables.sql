use warehouse;

--dwd_start_log 启动日志明细表
drop table if exists dwd_start_log;
create external table dwd_start_log
(
    area_code       string comment '地区编码',
    brand           string comment '手机品牌',
    channel         string comment '渠道',
    is_new          string comment '是否首次启动',
    model           string comment '手机型号',
    mid_id          string comment '设备id',
    os              string comment '操作系统',
    user_id         string comment '用户id',
    version_code    string comment 'APP版本号',
    entry           string comment 'icon手机图标 notice通知 install安装启动',
    loading_time    bigint comment '启动加载时间',
    open_ad_id      string comment '广告页id',
    open_ad_ms      bigint comment '广告总共播放时长',
    open_ad_skip_ms bigint comment '用户跳过广告时间',
    ts              bigint comment '产生时间'
) comment '启动日志明细表'
    partitioned by (dt string)
    stored as parquet
    location '/hive/warehouse/dwd/dwd_start_log'
    tblproperties ('parquet.compression' = 'lzo');

--dwd_page_log 页面日志明细表
drop table if exists dwd_page_log;
create external table dwd_page_log
(
    area_code      string comment '地区编码',
    brand          string comment '手机品牌',
    channel        string comment '渠道',
    is_new         string comment '是否首次启动',
    model          string comment '手机型号',
    mid_id         string comment '设备id',
    os             string comment '操作系统',
    user_id        string comment '用户id',
    version_code   string comment 'APP版本号',
    during_time    bigint comment '持续时间',
    page_item      string comment '目标id',
    page_item_type string comment '目标类型',
    last_page_id   string comment '上页id',
    page_id        string comment '页面id',
    source_type    string comment '来源类型',
    ts             bigint comment '产生时间'
) comment '页面日志明细表'
    partitioned by (dt string)
    stored as parquet
    location '/hive/warehouse/dwd/dwd_page_log'
    tblproperties ('parquet.compression' = 'lzo');

--dwd_action_log 行为日志明细表
drop table if exists dwd_action_log;
create external table dwd_action_log
(
    area_code        string comment '地区编码',
    brand            string comment '手机品牌',
    channel          string comment '渠道',
    is_new           string comment '是否首次启动',
    model            string comment '手机型号',
    mid_id           string comment '设备id',
    os               string comment '操作系统',
    user_id          string comment '用户id',
    version_code     string comment 'APP版本号',
    during_time      bigint comment '持续时间',
    page_item        string comment '目标id',
    page_item_type   string comment '目标类型',
    last_page_id     string comment '上页id',
    page_id          string comment '页面id',
    source_type      string comment '来源类型',
    action_id        string comment '动作id',
    action_item      string comment '动作目标',
    action_item_type string comment '动作目标类型',
    ts               bigint comment '产生时间'
) comment '行为日志明细表'
    partitioned by (dt string)
    stored as parquet
    location '/hive/warehouse/dwd/dwd_action_log'
    tblproperties ('parquet.compression' = 'lzo');

--dwd_display_log 曝光日志明细表
drop table if exists dwd_display_log;
create external table dwd_display_log
(
    area_code         string comment '地区编码',
    brand             string comment '手机品牌',
    channel           string comment '渠道',
    is_new            string comment '是否首次启动',
    model             string comment '手机型号',
    mid_id            string comment '设备id',
    os                string comment '操作系统',
    user_id           string comment '用户id',
    version_code      string comment 'APP版本号',
    during_time       bigint comment '持续时间',
    page_item         string comment '目标id',
    page_item_type    string comment '目标类型',
    last_page_id      string comment '上页id',
    page_id           string comment '页面id',
    source_type       string comment '来源类型',
    display_type      string comment '曝光类型',
    display_item      string comment '曝光目标id',
    display_item_type string comment '曝光目标类型',
    display_order     string comment '曝光顺序',
    pos_id            string comment '曝光位置',
    ts                bigint comment '产生时间'
) comment '曝光日志明细表'
    partitioned by (dt string)
    stored as parquet
    location '/hive/warehouse/dwd/dwd_display_log'
    tblproperties ('parquet.compression' = 'lzo');

--dwd_error_log 错误日志明细表
drop table if exists dwd_error_log;
create external table dwd_error_log
(
    area_code       string comment '地区编码',
    brand           string comment '手机品牌',
    channel         string comment '渠道',
    is_new          string comment '是否首次启动',
    model           string comment '手机型号',
    mid_id          string comment '设备id',
    os              string comment '操作系统',
    user_id         string comment '用户id',
    version_code    string comment 'APP版本号',
    during_time     bigint comment '持续时间',
    page_item       string comment '目标id',
    page_item_type  string comment '目标类型',
    last_page_id    string comment '上页id',
    page_id         string comment '页面id',
    source_type     string comment '来源类型',
    entry           string comment 'icon手机图标 notice通知 install安装启动',
    loading_time    bigint comment '启动加载时间',
    open_ad_id      string comment '广告页id',
    open_ad_ms      bigint comment '广告总共播放时长',
    open_ad_skip_ms bigint comment '用户跳过广告时间',
    actions         string comment '动作',
    displays        string comment '曝光',
    error_code      string comment '错误码',
    msg             string comment '错误信息',
    ts              bigint comment '产生时间'
) comment '错误日志明细表'
    partitioned by (dt string)
    stored as parquet
    location '/hive/warehouse/dwd/dwd_error_log'
    tblproperties ('parquet.compression' = 'lzo');

--dwd_comment_info 评论明细表
drop table if exists dwd_comment_info;
create external table dwd_comment_info
(
    id          string comment '编号',
    user_id     string comment '用户id',
    sku_id      string comment 'sku id',
    spu_id      string comment 'spu id',
    order_id    string comment '订单id',
    appraise    string comment '商品评价',
    create_time timestamp comment '评价时间'
) comment '评论明细表'
    partitioned by (dt string)
    stored as parquet
    location '/hive/warehouse/dwd/dwd_comment_info'
    tblproperties ('parquet.compression' = 'lzo');

--dwd_order_detail 订单详情明细表
drop table if exists dwd_order_detail;
create external table dwd_order_detail
(
    id                    string comment '订单明细编号',
    order_id              string comment '订单号',
    user_id               string comment '用户id',
    sku_id                string comment 'sku id',
    province_id           string comment '省份id',
    activity_id           string comment '活动id',
    activity_rule_id      string comment '活动规则id',
    coupon_id             string comment '优惠券id',
    source_id             string comment '来源编号',
    source_type           string comment '来源类型',
    sku_num               int comment '商品数量',
    original_amount       decimal comment '原始金额',
    split_activity_amount decimal comment '活动优惠分摊',
    split_coupon_amount   decimal comment '优惠券优惠分摊',
    split_total_amount    decimal comment '最终优惠分摊',
    create_time           timestamp comment '创建时间'
) comment '订单详情明细表'
    partitioned by (dt string)
    stored as parquet
    location '/hive/warehouse/dwd/dwd_order_detail'
    tblproperties ('parquet.compression' = 'lzo');

--dwd_order_refund_info 退单信息明细表
drop table if exists dwd_order_refund_info;
create external table dwd_order_refund_info
(
    id                 string comment '编号',
    order_id           string comment '订单号',
    user_id            string comment '用户id',
    sku_id             string comment 'sku id',
    province_id        string comment '省份id',
    refund_type        string comment '退单类型',
    refund_num         string comment '退单件数',
    refund_amount      string comment '退单金额',
    refund_reason_type string comment '退单原因类型',
    create_time        timestamp comment '创建时间'
) comment '退单信息明细表'
    partitioned by (dt string)
    stored as parquet
    location '/hive/warehouse/dwd/dwd_order_refund_info'
    tblproperties ('parquet.compression' = 'lzo');


--dwd_cart_info 加购信息明细表
drop table if exists dwd_cart_info;
create external table dwd_cart_info
(
    id           string comment '编号',
    user_id      string comment '用户id',
    sku_id       string comment 'sku id',
    source_id    string comment '来源id',
    source_type  string comment '来源类型',
    cart_price   string comment '加购价格',
    is_ordered   string comment '是否下单',
    sku_num      int comment '商品数量',
    create_time  timestamp comment '创建时间',
    operate_time timestamp comment '操作时间',
    order_time   timestamp comment '操作时间'
) comment '加购信息明细表'
    partitioned by (dt string)
    stored as parquet
    location '/hive/warehouse/dwd/dwd_cart_info'
    tblproperties ('parquet.compression' = 'lzo');

--dwd_favor_info 收藏信息明细表
drop table if exists dwd_favor_info;
create external table dwd_favor_info
(
    id          string comment '编号',
    user_id     string comment '用户id',
    sku_id      string comment 'sku id',
    spu_id      string comment 'spu id',
    is_cancel   string comment '是否取消',
    create_time timestamp comment '创建时间',
    cancel_time timestamp comment '取消时间'
) comment '收藏信息明细表'
    partitioned by (dt string)
    stored as parquet
    location '/hive/warehouse/dwd/dwd_favor_info'
    tblproperties ('parquet.compression' = 'lzo');

--dwd_coupon_use 优惠券使用明细表
drop table if exists dwd_coupon_use;
create external table dwd_coupon_use
(
    id            string comment '编号',
    coupon_id     string comment '优惠券id',
    user_id       string comment '用户id',
    order_id      string comment '订单id',
    coupon_status string comment '优惠券状态',
    get_time      timestamp comment '领取时间',
    using_time    timestamp comment '下单时间',
    used_time     timestamp comment '支付时间',
    expire_time   timestamp comment '过期时间'
) comment '优惠券使用明细表'
    partitioned by (dt string)
    stored as parquet
    location '/hive/warehouse/dwd/dwd_coupon_use'
    tblproperties ('parquet.compression' = 'lzo');

--dwd_payment_info 支付信息明细表
drop table if exists dwd_payment_info;
create external table dwd_payment_info
(
    id             string comment '编号',
    order_id       string comment '订单编号',
    user_id        string comment '用户id',
    province_id    string comment '省份id',
    trade_no       string comment '交易编号',
    out_trade_no   string comment '外部交易编号',
    payment_type   string comment '支付类型',
    total_amount   decimal comment '支付金额',
    payment_status string comment '支付状态',
    create_time    timestamp comment '创建时间',
    callback_time  timestamp comment '支付完成时间'
) comment '支付信息明细表'
    partitioned by (dt string)
    stored as parquet
    location '/hive/warehouse/dwd/dwd_payment_info'
    tblproperties ('parquet.compression' = 'lzo');

--dwd_refund_payment 退款信息明细表
drop table if exists dwd_refund_payment;
create external table dwd_refund_payment
(
    id            string comment '编号',
    user_id       string comment '用户id',
    order_id      string comment '订单编号',
    sku_id        string comment 'sku id',
    province_id   string comment '省份id',
    trade_no      string comment '交易编号',
    out_trade_no  string comment '外部交易编号',
    payment_type  string comment '支付类型',
    total_amount  decimal comment '退款金额',
    refund_status decimal comment '退款状态',
    create_time   timestamp comment '创建时间',
    callback_time timestamp comment '退款时间'
) comment '退款信息明细表'
    partitioned by (dt string)
    stored as parquet
    location '/hive/warehouse/dwd/dwd_refund_payment'
    tblproperties ('parquet.compression' = 'lzo');

--dwd_order_info 订单信息明细表
drop table if exists dwd_order_info;
create external table dwd_order_info
(
    id                     bigint comment '编号',
    order_status           string comment '订单状态',
    user_id                bigint comment '用户id',
    province_id            int comment '地区',
    payment_way            string comment '付款方式',
    delivery_address       string comment '送货地址',
    out_trade_no           string comment '订单交易编号（第三方支付用)',
    tracking_no            string comment '物流单编号',
    create_time            timestamp comment '创建时间-未支付',
    payment_time           timestamp comment '支付时间-已支付',
    cancel_time            timestamp comment '取消时间-已取消',
    finish_time            timestamp comment '完成时间-已完成',
    refund_time            timestamp comment '退款时间-申请退款',
    refund_finish_time     timestamp comment '退款完成时间-同意退款',
    expire_time            timestamp comment '过期时间',
    feight_fee             decimal comment '运费',
    feight_fee_reduce      decimal comment '运费减免',
    activity_reduce_amount decimal comment '促销金额',
    coupon_reduce_amount   decimal comment '优惠券',
    original_total_amount  decimal comment '原价金额',
    total_amount           decimal comment '总金额'
) comment '订单信息明细表'
    partitioned by (dt string)
    stored as parquet
    location '/hive/warehouse/dwd/dwd_order_info'
    tblproperties ('parquet.compression' = 'lzo');