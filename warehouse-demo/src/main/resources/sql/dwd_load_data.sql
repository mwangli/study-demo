use warehouse;

insert overwrite table dwd_start_log partition (dt = '2022-04-26')
select get_json_object(line, '$.common.ar'),
       get_json_object(line, '$.common.ba'),
       get_json_object(line, '$.common.ch'),
       get_json_object(line, '$.common.is_new'),
       get_json_object(line, '$.common.md'),
       get_json_object(line, '$.common.mid'),
       get_json_object(line, '$.common.os'),
       get_json_object(line, '$.common.uid'),
       get_json_object(line, '$.common.vc'),
       get_json_object(line, '$.start.entry'),
       get_json_object(line, '$.start.loading_time'),
       get_json_object(line, '$.start.open_ad_id'),
       get_json_object(line, '$.start.open_ad_ms'),
       get_json_object(line, '$.start.open_ad_skip_ms'),
       get_json_object(line, '$.ts')
from ods_logs
where dt = '2022-04-26'
  and get_json_object(line, '$.start') is not null;

insert overwrite table dwd_page_log partition (dt = '2022-04-26')
select get_json_object(line, '$.common.ar'),
       get_json_object(line, '$.common.ba'),
       get_json_object(line, '$.common.ch'),
       get_json_object(line, '$.common.is_new'),
       get_json_object(line, '$.common.md'),
       get_json_object(line, '$.common.mid'),
       get_json_object(line, '$.common.os'),
       get_json_object(line, '$.common.uid'),
       get_json_object(line, '$.common.vc'),
       get_json_object(line, '$.page.during_time'),
       get_json_object(line, '$.page.item'),
       get_json_object(line, '$.page.item_type'),
       get_json_object(line, '$.page.last_page_id'),
       get_json_object(line, '$.page.page_id'),
       get_json_object(line, '$.page.source_type'),
       get_json_object(line, '$.ts')
from ods_logs
where dt = '2022-04-26'
  and get_json_object(line, '$.page') is not null;

-- insert overwrite table dwd_action_log partition (dt = '2022-04-26')
select get_json_object(line, '$.common.ar'),
       get_json_object(line, '$.common.ba'),
       get_json_object(line, '$.common.ch'),
       get_json_object(line, '$.common.is_new'),
       get_json_object(line, '$.common.md'),
       get_json_object(line, '$.common.mid'),
       get_json_object(line, '$.common.os'),
       get_json_object(line, '$.common.uid'),
       get_json_object(line, '$.common.vc'),
       get_json_object(line, '$.page.during_time'),
       get_json_object(line, '$.page.item'),
       get_json_object(line, '$.page.item_type'),
       get_json_object(line, '$.page.last_page_id'),
       get_json_object(line, '$.page.page_id'),
       get_json_object(line, '$.page.source_type'),
       get_json_object(line, '$.actions'),
       get_json_object(line, '$.ts')
from ods_logs
where dt = '2022-04-26'
  and get_json_object(line, '$.actions') is not null;
