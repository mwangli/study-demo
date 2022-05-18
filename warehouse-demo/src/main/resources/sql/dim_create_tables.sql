use warehouse;

--dim_sku_info 商品维度表
drop table if exists dim_sku_info;
create external table dim_sku_info
(
    id                   bigint comment '商品id',
    price                decimal comment '商品价格',
    sku_name             string comment '商品名称',
    sku_desc             string comment '商品描述',
    weight               decimal comment '商品重量',
    is_sale              boolean comment '是否在售',
    spu_id               bigint comment 'spu编号',
    spu_name             string comment 'spu名称',
    create_time          timestamp comment '创建时间',
    category3_id         bigint comment '三级分类id',
    category3_name       string comment '三级分类名称',
    category2_id         bigint comment '三级分类id',
    category2_name       string comment '二级分类名称',
    category1_id         bigint comment '一级分类id',
    category1_name       string comment '一级分类名称',
    tm_id                bigint comment '品牌id',
    tm_name              string comment '品牌名称',
    sku_attr_values      array<struct<attr_id :bigint,attr_name :string,value_id :bigint,value_name
                                      :string>> comment '商品属性',
    sku_sale_attr_values array<struct<sale_attr_id :bigint,sale_attr_name :string,sale_value_id :bigint,sale_value_name
                                      :string>> comment '销售属性'
) comment '商品维度表'
    partitioned by (dt string)
    stored as parquet
    location '/hive/warehouse/dim/dim_sku_info'
    tblproperties ('parquet.compression' = 'lzo');

--dim_coupon_info 优惠券维度表
drop table if exists dim_coupon_info;
create external table dim_coupon_info
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
    expire_time      timestamp comment '过期时间'
) comment '优惠券维度表'
    partitioned by (dt string)
    stored as parquet
    location '/hive/warehouse/dim/dim_coupon_info'
    tblproperties ('parquet.compression' = 'lzo');


--dim_activity_rule_info 活动规则维度表
drop table if exists dim_activity_rule_info;
create external table dim_activity_rule_info
(
    activity_rule_id bigint comment '活动规则id',
    activity_id      bigint comment '活动id',
    activity_name    string comment '活动名称',
    activity_type    string comment '活动类型（1：满减，2：折扣）',
    start_time       timestamp comment '开始时间',
    end_time         timestamp comment '结束时间',
    create_time      timestamp comment '创建时间',
    condition_amount decimal comment '满减金额',
    condition_num    bigint comment '满减件数',
    benefit_amount   decimal comment '优惠金额',
    benefit_discount decimal comment '优惠折扣',
    benefit_level    bigint comment '优惠级别'
) comment '活动规则维度表'
    partitioned by (dt string)
    stored as parquet
    location '/hive/warehouse/dim/dim_activity_rule_info'
    tblproperties ('parquet.compression' = 'lzo');

--dim_base_province 地区维度表
drop table if exists dim_base_province;
create external table dim_base_province
(
    id            bigint comment 'id',
    province_name string comment '省名称',
    area_code     string comment '行政区位码',
    iso_code      string comment '国际编码',
    iso_3166_2    string comment 'ISO3166编码',
    region_id     string comment '大区id',
    region_name   string comment '大区名称'
) comment '地区维度表'
    stored as parquet
    location '/hive/warehouse/dim/dim_base_province'
    tblproperties ('parquet.compression' = 'lzo');

--dim_user_info 用户维度表
drop table if exists dim_user_info;
create external table dim_user_info
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
    start_date   string comment '开始日期',
    end_date     string comment '结束日期'
) comment '用户维度表'
    partitioned by (dt string)
    stored as parquet
    location '/hive/warehouse/dim/dim_user_info'
    tblproperties ('parquet.compression' = 'lzo');

--dim_date_info 时间维度表
drop table if exists dim_date_info;
create external table dim_date_info
(
    date_id      string comment '日期',
    year         string comment '年',
    month        string comment '月',
    day          string comment '日',
    week         string comment '星期',
    day_of_year  string comment '一年中的第几天',
    week_of_year string comment '一年中的第几周',
    quarter      string comment '季度',
    is_workday   string comment '是否工作日',
    holiday      string comment '节假日'
) comment '时间维度表'
    row format delimited fields terminated by '\t'
    location '/hive/warehouse/dim/dim_date_info';