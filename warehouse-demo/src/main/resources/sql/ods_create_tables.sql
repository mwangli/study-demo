-- create database warehouse;
use warehouse;

--ods_log 日志数据原始表
drop table if exists ods_logs;
create external table ods_logs
(
    line string comment '日志详情'
) comment '日志数据原始表'
    partitioned by (dt string)
    location '/hive/warehouse/ods/ods_log';

--ods_activity_info 活动信息原始表
drop table if exists ods_activity_info;
create external table ods_activity_info
(
    id            bigint comment '活动id',
    activity_name string comment '活动名称',
    activity_type string comment '活动类型（1：满减，2：折扣）',
    activity_desc string comment '活动描述',
    start_time    timestamp comment '开始时间',
    end_time      timestamp comment '结束时间',
    create_time   timestamp comment '创建时间'
) comment '活动信息原始表'
    partitioned by (dt string)
    row format delimited fields terminated by '\t'
    location '/hive/warehouse/ods/ods_activity_info';

--ods_activity_rule 活动规则原始表
drop table if exists ods_activity_rule;
create external table ods_activity_rule
(
    id               int comment '编号',
    activity_id      int comment '类型',
    activity_type    string comment '活动类型',
    condition_amount decimal comment '满减金额',
    condition_num    bigint comment '满减件数',
    benefit_amount   decimal comment '优惠金额',
    benefit_discount decimal comment '优惠折扣',
    benefit_level    bigint comment '优惠级别'
) comment '活动规则原始表'
    partitioned by (dt string)
    row format delimited fields terminated by '\t'
    location '/hive/warehouse/ods/ods_activity_rule';

--ods_activity_sku 活动商品原始表
drop table if exists ods_activity_sku;
create external table ods_activity_sku
(
    id          bigint comment '编号',
    activity_id bigint comment '活动id ',
    sku_id      bigint comment 'sku_id',
    create_time timestamp comment '创建时间'
) comment '活动商品原始表'
    partitioned by (dt string)
    row format delimited fields terminated by '\t'
    location '/hive/warehouse/ods/ods_activity_sku';

--ods_base_attr_info 平台属性原始表
drop table if exists ods_base_attr_info;
create external table ods_base_attr_info
(
    id             bigint comment '编号',
    attr_name      string comment '属性名称',
    category_id    bigint comment '分类id',
    category_level int comment '分类层级'
) comment '平台属性原始表'
    partitioned by (dt string)
    row format delimited fields terminated by '\t'
    location '/hive/warehouse/ods/ods_base_attr_info';

--ods_base_attr_value 平台属性值原始表
drop table if exists ods_base_attr_value;
create external table ods_base_attr_value
(
    id         bigint comment '编号',
    value_name string comment '属性值名称',
    attr_id    bigint comment '属性id'
) comment '平台属性值原始表'
    partitioned by (dt string)
    row format delimited fields terminated by '\t'
    location '/hive/warehouse/ods/ods_base_attr_value';

--ods_base_category1 一级分类原始表
drop table if exists ods_base_category1;
create external table ods_base_category1
(
    id   bigint comment '编号',
    name string comment '分类名称'
) comment '一级分类原始表'
    partitioned by (dt string)
    row format delimited fields terminated by '\t'
    location '/hive/warehouse/ods/ods_base_category1';

--ods_base_category2 二级分类原始表
drop table if exists ods_base_category2;
create external table ods_base_category2
(
    id           bigint comment '编号',
    name         string comment '二级分类名称',
    category1_id bigint comment '一级分类编号'
) comment '二级分类原始表'
    partitioned by (dt string)
    row format delimited fields terminated by '\t'
    location '/hive/warehouse/ods/ods_base_category2';

--ods_base_category3 三级分类原始表
drop table if exists ods_base_category3;
create external table ods_base_category3
(
    id           bigint comment '编号',
    name         string comment '三级分类名称',
    category2_id bigint comment '二级分类编号'
) comment '三级分类原始表'
    partitioned by (dt string)
    row format delimited fields terminated by '\t'
    location '/hive/warehouse/ods/ods_base_category3';

--ods_base_dic 数据字典原始表
drop table if exists ods_base_dic;
create external table ods_base_dic
(
    dic_code     string comment '编号',
    dic_name     string comment '编码名称',
    parent_code  string comment '父编号',
    create_time  timestamp comment '创建日期',
    operate_time timestamp comment '修改日期'
) comment '数据字典原始表'
    partitioned by (dt string)
    row format delimited fields terminated by '\t'
    location '/hive/warehouse/ods/ods_base_dic';

--ods_base_province 省份原始表
drop table if exists ods_base_province;
create external table ods_base_province
(
    id         bigint comment 'id',
    name       string comment '省名称',
    region_id  string comment '大区id',
    area_code  string comment '行政区位码',
    iso_code   string comment '国际编码',
    iso_3166_2 string comment 'ISO3166编码'
) comment '省份原始表'
    partitioned by (dt string)
    row format delimited fields terminated by '\t'
    location '/hive/warehouse/ods/ods_base_province';

--ods_base_region 地区原始表
drop table if exists ods_base_region;
create external table ods_base_region
(
    id          string comment '大区id',
    region_name string comment '大区名称'
) comment '地区原始表'
    partitioned by (dt string)
    row format delimited fields terminated by '\t'
    location '/hive/warehouse/ods/ods_base_region';

--ods_base_sale_attr 销售属性原始表
drop table if exists ods_base_sale_attr;
create external table ods_base_sale_attr
(
    id   bigint comment '编号',
    name string comment '销售属性名称'
) comment '销售属性原始表'
    partitioned by (dt string)
    row format delimited fields terminated by '\t'
    location '/hive/warehouse/ods/ods_base_sale_attr';

--ods_base_trademark 品牌信息原始表
drop table if exists ods_base_trademark;
create external table ods_base_trademark
(
    id       bigint comment '编号',
    tm_name  string comment '属性值',
    logo_url string comment '品牌logo的图片路径'
) comment '品牌信息原始表'
    partitioned by (dt string)
    row format delimited fields terminated by '\t'
    location '/hive/warehouse/ods/ods_base_trademark';

--ods_cart_info 加购信息原始表
drop table if exists ods_cart_info;
create external table ods_cart_info
(
    id           bigint comment '编号',
    user_id      string comment '用户id',
    sku_id       bigint comment 'skuid',
    cart_price   decimal comment '放入购物车时价格',
    sku_num      int comment '数量',
    img_url      string comment '图片文件',
    sku_name     string comment 'sku名称 (冗余)',
    is_checked   int,
    create_time  timestamp comment '创建时间',
    operate_time timestamp comment '修改时间',
    is_ordered   bigint comment '是否已经下单',
    order_time   timestamp comment '下单时间',
    source_type  string comment '来源类型',
    source_id    bigint comment '来源编号'
) comment '加购信息原始表'
    partitioned by (dt string)
    row format delimited fields terminated by '\t'
    location '/hive/warehouse/ods/ods_cart_info';

--ods_comment_info 评论信息原始表
drop table if exists ods_comment_info;
create external table ods_comment_info
(
    id           bigint comment '编号',
    user_id      bigint comment '用户id',
    nick_name    string comment '用户昵称',
    head_img     string,
    sku_id       bigint comment 'skuid',
    spu_id       bigint comment '商品id',
    order_id     bigint comment '订单编号',
    appraise     string comment '评价 1 好评 2 中评 3 差评',
    comment_txt  string comment '评价内容',
    create_time  timestamp comment '创建时间',
    operate_time timestamp comment '修改时间'
) comment '评论信息原始表'
    partitioned by (dt string)
    row format delimited fields terminated by '\t'
    location '/hive/warehouse/ods/ods_comment_info';

--ods_coupon_info 优惠券信息原始表
drop table if exists ods_coupon_info;
create external table ods_coupon_info
(
    id               bigint comment '购物券编号',
    coupon_name      string comment '购物券名称',
    coupon_type      string comment '购物券类型 1 现金券 2 折扣券 3 满减券 4 满件打折券',
    condition_amount decimal comment '满额数（3）',
    condition_num    bigint comment '满件数（4）',
    activity_id      bigint comment '活动编号',
    benefit_amount   decimal comment '减金额（1 3）',
    benefit_discount decimal comment '折扣（2 4）',
    create_time      timestamp comment '创建时间',
    range_type       string comment '范围类型 1、商品(spuid) 2、品类(三级分类id) 3、品牌',
    limit_num        int comment '最多领用次数',
    taken_count      int comment '已领用次数',
    start_time       timestamp comment '可以领取的开始日期',
    end_time         timestamp comment '可以领取的结束日期',
    operate_time     timestamp comment '修改时间',
    expire_time      timestamp comment '过期时间',
    range_desc       string comment '范围描述'
) comment '优惠券信息原始表'
    partitioned by (dt string)
    row format delimited fields terminated by '\t'
    location '/hive/warehouse/ods/ods_coupon_info';

--ods_coupon_range 优惠券范围原始表
drop table if exists ods_coupon_range;
create external table ods_coupon_range
(
    id         bigint comment '购物券编号',
    coupon_id  bigint comment '优惠券id',
    range_type string comment '范围类型 1、商品(spuid) 2、品类(三级分类id) 3、品牌',
    range_id   bigint
) comment '优惠券范围原始表'
    partitioned by (dt string)
    row format delimited fields terminated by '\t'
    location '/hive/warehouse/ods/ods_coupon_range';

--ods_coupon_use 优惠券使用原始表
drop table if exists ods_coupon_use;
create external table ods_coupon_use
(
    id            bigint comment '编号',
    coupon_id     bigint comment '购物券ID',
    user_id       bigint comment '用户ID',
    order_id      bigint comment '订单ID',
    coupon_status string comment '购物券状态（1：未使用 2：已使用）',
    get_time      timestamp comment '获取时间',
    using_time    timestamp comment '使用时间',
    used_time     timestamp comment '支付时间',
    expire_time   timestamp comment '过期时间'
) comment '优惠券使用原始表'
    partitioned by (dt string)
    row format delimited fields terminated by '\t'
    location '/hive/warehouse/ods/ods_coupon_use';

--ods_favor_info 收藏信息原始表
drop table if exists ods_favor_info;
create external table ods_favor_info
(
    id          bigint comment '编号',
    user_id     bigint comment '用户名称',
    sku_id      bigint comment 'skuid',
    spu_id      bigint comment '商品id',
    is_cancel   string comment '是否已取消 0 正常 1 已取消',
    create_time timestamp comment '创建时间',
    cancel_time timestamp comment '修改时间'
) comment '收藏信息原始表'
    partitioned by (dt string)
    row format delimited fields terminated by '\t'
    location '/hive/warehouse/ods/ods_favor_info';

--ods_order_detail 订单详情原始表
drop table if exists ods_order_detail;
create external table ods_order_detail
(
    id                    bigint comment '编号',
    order_id              bigint comment '订单编号',
    sku_id                bigint comment 'sku_id',
    sku_name              string comment 'sku名称（冗余)',
    img_url               string comment '图片名称（冗余)',
    order_price           decimal comment '购买价格(下单时sku价格）',
    sku_num               string comment '购买个数',
    create_time           timestamp comment '创建时间',
    source_type           string comment '来源类型',
    source_id             bigint comment '来源编号',
    split_total_amount    decimal,
    split_activity_amount decimal,
    split_coupon_amount   decimal
) comment '订单详情原始表'
    partitioned by (dt string)
    row format delimited fields terminated by '\t'
    location '/hive/warehouse/ods/ods_order_detail';

--ods_order_detail_activity 订单详情活动原始表
drop table if exists ods_order_detail_activity;
create external table ods_order_detail_activity
(
    id               bigint comment '编号',
    order_id         bigint comment '订单id',
    order_detail_id  bigint comment '订单明细id',
    activity_id      bigint comment '活动ID',
    activity_rule_id bigint comment '活动规则',
    sku_id           bigint comment 'skuID',
    create_time      timestamp comment '获取时间'
) comment '订单详情活动原始表'
    partitioned by (dt string)
    row format delimited fields terminated by '\t'
    location '/hive/warehouse/ods/ods_order_detail_activity';

--ods_order_detail_coupon 订单详情优惠券原始表
drop table if exists ods_order_detail_coupon;
create external table ods_order_detail_coupon
(
    id              bigint comment '编号',
    order_id        bigint comment '订单id',
    order_detail_id bigint comment '订单明细id',
    coupon_id       bigint comment '购物券ID',
    coupon_use_id   bigint comment '购物券领用id',
    sku_id          bigint comment 'skuID',
    create_time     timestamp comment '获取时间'
) comment '订单详情优惠券原始表'
    partitioned by (dt string)
    row format delimited fields terminated by '\t'
    location '/hive/warehouse/ods/ods_order_detail_coupon';

--ods_order_info 订单信息原始表
drop table if exists ods_order_info;
create external table ods_order_info
(
    id                     bigint comment '编号',
    consignee              string comment '收货人',
    consignee_tel          string comment '收件人电话',
    total_amount           decimal comment '总金额',
    order_status           string comment '订单状态',
    user_id                bigint comment '用户id',
    payment_way            string comment '付款方式',
    delivery_address       string comment '送货地址',
    order_comment          string comment '订单备注',
    out_trade_no           string comment '订单交易编号（第三方支付用)',
    trade_body             string comment '订单描述(第三方支付用)',
    create_time            timestamp comment '创建时间',
    operate_time           timestamp comment '操作时间',
    expire_time            timestamp comment '失效时间',
    process_status         string comment '进度状态',
    tracking_no            string comment '物流单编号',
    parent_order_id        bigint comment '父订单编号',
    img_url                string comment '图片路径',
    province_id            int comment '地区',
    activity_reduce_amount decimal comment '促销金额',
    coupon_reduce_amount   decimal comment '优惠券',
    original_total_amount  decimal comment '原价金额',
    feight_fee             decimal comment '运费',
    feight_fee_reduce      decimal comment '运费减免',
    refundable_time        timestamp comment '可退款日期（签收后30天）'
) comment '订单信息原始表'
    partitioned by (dt string)
    row format delimited fields terminated by '\t'
    location '/hive/warehouse/ods/ods_order_info';

--ods_order_refund_info 退单信息原始表
drop table if exists ods_order_refund_info;
create external table ods_order_refund_info
(
    id                 bigint comment '编号',
    user_id            bigint comment '用户id',
    order_id           bigint comment '订单id',
    sku_id             bigint comment 'skuid',
    refund_type        string comment '退款类型',
    refund_num         bigint comment '退货件数',
    refund_amount      decimal comment '退款金额',
    refund_reason_type string comment '原因类型',
    refund_reason_txt  string comment '原因内容',
    refund_status      string comment '退款状态（0：待审批 1：已退款）',
    create_time        timestamp comment '创建时间'
) comment '退单信息原始表'
    partitioned by (dt string)
    row format delimited fields terminated by '\t'
    location '/hive/warehouse/ods/ods_order_refund_info';

--ods_order_status_log 订单流水原始表
drop table if exists ods_order_status_log;
create external table ods_order_status_log
(
    id           bigint comment 'ID',
    order_id     bigint comment '订单ID',
    order_status string comment  '订单状态',
    operate_time timestamp comment  '操作时间'
) comment '订单流水原始表'
    partitioned by (dt string)
    row format delimited fields terminated by '\t'
    location '/hive/warehouse/ods/ods_order_status_log';

--ods_payment_info 支付信息原始表
drop table if exists ods_payment_info;
create external table ods_payment_info
(
    id               int comment '编号',
    out_trade_no     string comment '对外业务编号',
    order_id         bigint comment '订单编号',
    user_id          bigint,
    payment_type     string comment '支付类型（微信 支付宝）',
    trade_no         string comment '交易编号',
    total_amount     decimal comment '支付金额',
    subject          string comment '交易内容',
    payment_status   string comment '支付状态',
    create_time      timestamp comment '创建时间',
    callback_time    timestamp comment '回调时间',
    callback_content string comment '回调信息'
) comment '支付信息原始表'
    partitioned by (dt string)
    row format delimited fields terminated by '\t'
    location '/hive/warehouse/ods/ods_payment_info';

--ods_refund_payment 退款信息原始表
drop table if exists ods_refund_payment;
create external table ods_refund_payment
(
    id               int comment '编号',
    out_trade_no     string comment '对外业务编号',
    order_id         bigint comment '订单编号',
    sku_id           bigint,
    payment_type     string comment '支付类型（微信 支付宝）',
    trade_no         string comment '交易编号',
    total_amount     decimal comment '退款金额',
    subject          string comment '交易内容',
    refund_status    string comment '退款状态',
    create_time      timestamp comment '创建时间',
    callback_time    timestamp comment '回调时间',
    callback_content string comment '回调信息'
) comment '退款信息原始表'
    partitioned by (dt string)
    row format delimited fields terminated by '\t'
    location '/hive/warehouse/ods/ods_refund_payment';

--ods_sku_attr_value 商品属性值原始表
drop table if exists ods_sku_attr_value;
create external table ods_sku_attr_value
(
    id         bigint comment '编号',
    attr_id    bigint comment '属性id（冗余)',
    value_id   bigint comment '属性值id',
    sku_id     bigint comment 'skuid',
    attr_name  string comment '属性名',
    value_name string comment '属性值名称'
) comment '商品属性值原始表'
    partitioned by (dt string)
    row format delimited fields terminated by '\t'
    location '/hive/warehouse/ods/ods_sku_attr_value';

--ods_sku_image 商品图片原始表
drop table if exists ods_sku_image;
create external table ods_sku_image
(
    id         bigint comment '编号',
    sku_id     bigint comment '商品id',
    img_name   string comment '图片名称（冗余）',
    img_url    string comment '图片路径(冗余)',
    spu_img_id bigint comment '商品图片id',
    is_default string comment '是否默认'
) comment '商品图片原始表'
    partitioned by (dt string)
    row format delimited fields terminated by '\t'
    location '/hive/warehouse/ods/ods_sku_image';


--ods_sku_info 商品信息原始表
drop table if exists ods_sku_info;
create external table ods_sku_info
(
    id              bigint comment '库存id(itemID)',
    spu_id          bigint comment '商品id',
    price           decimal comment '价格',
    sku_name        string comment 'sku名称',
    sku_desc        string comment '商品规格描述',
    weight          decimal comment '重量',
    tm_id           bigint comment '品牌(冗余)',
    category3_id    bigint comment '三级分类id（冗余)',
    sku_default_img string comment '默认显示图片(冗余)',
    is_sale         tinyint comment '是否销售（1：是 0：否）',
    create_time     timestamp comment '创建时间'
) comment '商品信息原始表'
    partitioned by (dt string)
    row format delimited fields terminated by '\t'
    location '/hive/warehouse/ods/ods_sku_info';

--ods_sku_sale_attr_value 销售属性值原始表
drop table if exists ods_sku_sale_attr_value;
create external table ods_sku_sale_attr_value
(
    id                   bigint comment 'id',
    sku_id               bigint comment '库存单元id',
    spu_id               int comment 'spu_id(冗余)',
    sale_attr_value_id   bigint comment '销售属性值id',
    sale_attr_id         bigint,
    sale_attr_name       string,
    sale_attr_value_name string
) comment '销售属性值原始表'
    partitioned by (dt string)
    row format delimited fields terminated by '\t'
    location '/hive/warehouse/ods/ods_sku_sale_attr_value';

--ods_spu_image 商品spu图片原始表
drop table if exists ods_spu_image;
create external table ods_spu_image
(
    id       bigint comment '编号',
    spu_id   bigint comment '商品id',
    img_name string comment '图片名称',
    img_url  string comment '图片路径'
) comment '商品spu图片原始表'
    partitioned by (dt string)
    row format delimited fields terminated by '\t'
    location '/hive/warehouse/ods/ods_spu_image';

--ods_spu_info 商品spu信息原始表
drop table if exists ods_spu_info;
create external table ods_spu_info
(
    id           bigint comment '商品id',
    spu_name     string comment '商品名称',
    description  string comment '商品描述(后台简述）',
    category3_id bigint comment '三级分类id',
    tm_id        bigint comment '品牌id'
) comment '商品spu信息原始表'
    partitioned by (dt string)
    row format delimited fields terminated by '\t'
    location '/hive/warehouse/ods/ods_spu_info';

--ods_spu_sale_attr 商品spu销售属性原始表
drop table if exists ods_spu_sale_attr;
create external table ods_spu_sale_attr
(
    id                bigint comment '编号(业务中无关联)',
    spu_id            bigint comment '商品id',
    base_sale_attr_id bigint comment '销售属性id',
    sale_attr_name    string comment '销售属性名称(冗余)'
) comment '商品spu销售属性原始表'
    partitioned by (dt string)
    row format delimited fields terminated by '\t'
    location '/hive/warehouse/ods/ods_spu_sale_attr';

--ods_spu_sale_attr_value 商品spu销售属性值原始表
drop table if exists ods_spu_sale_attr_value;
create external table ods_spu_sale_attr_value
(
    id                   bigint comment '销售属性值编号',
    spu_id               bigint comment '商品id',
    base_sale_attr_id    bigint comment '销售属性id',
    sale_attr_value_name string comment '销售属性值名称',
    sale_attr_name       string comment '销售属性名称(冗余)'
) comment '商品spu销售属性值原始表'
    partitioned by (dt string)
    row format delimited fields terminated by '\t'
    location '/hive/warehouse/ods/ods_spu_sale_attr_value';

--ods_user_info 用户信息原始表
drop table if exists ods_user_info;
create external table ods_user_info
(
    id           bigint comment '编号',
    login_name   string comment '用户名称',
    nick_name    string comment '用户昵称',
    passwd       string comment '用户密码',
    name         string comment '用户姓名',
    phone_num    string comment '手机号',
    email        string comment '邮箱',
    head_img     string comment '头像',
    user_level   string comment '用户级别',
    birthday     timestamp comment '用户生日',
    gender       string comment '性别 M男,F女',
    create_time  timestamp comment '创建时间',
    operate_time timestamp comment '修改时间',
    status       string comment '状态'
) comment '用户信息原始表'
    partitioned by (dt string)
    row format delimited fields terminated by '\t'
    location '/hive/warehouse/ods/ods_user_info';