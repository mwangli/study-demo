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
       sum(if(page_count = 1, 1, 0)) / count(1)
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



