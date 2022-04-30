use warehouse;

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
    location '/hive/warehouse/ads/ads_visit_stat'