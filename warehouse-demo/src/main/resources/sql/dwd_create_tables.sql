use warehouse;

drop table if exists dwd_start_log;
create table dwd_start_log
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
)
    partitioned by (dt string)
    stored as parquet
    location '/hive/warehouse/dwd/dwd_start_log'
    tblproperties ('parquet.compression' = 'lzo');


drop table if exists dwd_page_log;
create table dwd_page_log
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
)
    partitioned by (dt string)
    stored as parquet
    location '/hive/warehouse/dwd/dwd_page_log'
    tblproperties ('parquet.compression' = 'lzo');

drop table if exists dwd_action_log;
create table dwd_action_log
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
)
    partitioned by (dt string)
    stored as parquet
    location '/hive/warehouse/dwd/dwd_action_log'
    tblproperties ('parquet.compression' = 'lzo');

drop table if exists dwd_display_log;
create table dwd_display_log
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
    source_type      string comment '来源类型',
    display_type      string comment '曝光类型',
    display_item      string comment '曝光目标id',
    display_item_type string comment '曝光目标类型',
    display_order     string comment '曝光顺序',
    pos_id            string comment '曝光位置',
    ts                bigint comment '产生时间'
)
    partitioned by (dt string)
    stored as parquet
    location '/hive/warehouse/dwd/dwd_display_log'
    tblproperties ('parquet.compression' = 'lzo')