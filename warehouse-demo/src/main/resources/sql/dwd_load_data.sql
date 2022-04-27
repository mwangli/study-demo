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

drop function if exists explode_json_array;
create function explode_json_array
    as 'online.mwang.hive.udtf.ExplodeJSONArray'
    using jar 'hdfs://mycluster/hive/warehouse/origin/jars/warehouse-udtf.jar';

insert overwrite table dwd_action_log partition (dt = '2022-04-26')
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
       get_json_object(action, '$.action_id'),
       get_json_object(action, '$.item'),
       get_json_object(action, '$.item_type'),
       get_json_object(line, '$.ts')
from ods_logs lateral view explode_json_array(get_json_object(line, '$.actions')) tmp as action
where dt = '2022-04-26'
  and get_json_object(line, '$.actions') is not null;

insert overwrite table dwd_action_log partition (dt = '2022-04-26')
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
       get_json_object(action, '$.action_id'),
       get_json_object(action, '$.item'),
       get_json_object(action, '$.item_type'),
       get_json_object(line, '$.ts')
from ods_logs lateral view explode_json_array(get_json_object(line, '$.actions')) tmp as action
where dt = '2022-04-26'
  and get_json_object(line, '$.actions') is not null;

insert overwrite table dwd_display_log partition (dt = '2022-04-26')
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
       get_json_object(display, '$.display_type'),
       get_json_object(display, '$.item'),
       get_json_object(display, '$.item_type'),
       get_json_object(display, '$.order'),
       get_json_object(display, '$.pos_id'),
       get_json_object(line, '$.ts')
from ods_logs lateral view explode_json_array(get_json_object(line, '$.displays')) tmp as display
where dt = '2022-04-26'
  and get_json_object(line, '$.displays') is not null;

insert overwrite table dwd_error_log partition (dt = '2022-04-26')
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
       get_json_object(line, '$.start.entry'),
       get_json_object(line, '$.start.loading_time'),
       get_json_object(line, '$.start.open_ad_id'),
       get_json_object(line, '$.start.open_ad_ms'),
       get_json_object(line, '$.start.open_ad_skip_ms'),
       get_json_object(line, '$.actions'),
       get_json_object(line, '$.displays'),
       get_json_object(line, '$.err.error_code'),
       get_json_object(line, '$.err.msg'),
       get_json_object(line, '$.ts')
from ods_logs
where dt = '2022-04-26'
  and get_json_object(line, '$.err') is not null;

insert overwrite table dwd_comment_info partition (dt = '2022-04-26')
select id,
       user_id,
       sku_id,
       spu_id,
       order_id,
       appraise,
       create_time
from ods_comment_info
where dt = '2022-04-26';

insert overwrite table dwd_order_detail partition (dt = '2022-04-26')
select d.id,
       d.order_id,
       i.user_id,
       d.sku_id,
       i.province_id,
       a.activity_id,
       a.activity_rule_id,
       c.coupon_id,
       d.source_id,
       d.source_type,
       d.sku_num,
       d.original_amount,
       d.split_activity_amount,
       d.split_coupon_amount,
       d.split_total_amount,
       d.create_time
from (
         select id,
                order_id,
                sku_id,
                source_id,
                source_type,
                order_price,
                sku_num,
                sku_num * order_price original_amount,
                split_activity_amount,
                split_coupon_amount,
                split_total_amount,
                create_time
         from ods_order_detail
         where dt = '2022-04-26'
     ) d
         left join
     (
         select id,
                user_id,
                province_id
         from ods_order_info
         where dt = '2022-04-26'
     ) i
     on d.order_id = i.id
         left join
     (
         select order_id,
                activity_id,
                activity_rule_id
         from ods_order_detail_activity
     ) a
     on d.order_id = a.order_id
         left join
     (
         select order_id,
                coupon_id
         from ods_order_detail_coupon
     ) c
     on d.order_id = c.order_id;

insert overwrite table dwd_order_refund_info partition (dt = '2022-04-26')
select r.id,
       r.order_id,
       r.user_id,
       r.sku_id,
       i.province_id,
       r.refund_type,
       r.refund_num,
       r.refund_amount,
       r.refund_reason_type,
       r.create_time
from (
         select id,
                order_id,
                user_id,
                sku_id,
                refund_type,
                refund_num,
                refund_amount,
                refund_reason_type,
                create_time
         from ods_order_refund_info
         where dt = '2022-04-26'
     ) r
         left join
     (
         select id,
                province_id
         from ods_order_info
         where dt = '2022-04-26'
     ) i
     on r.order_id = i.id;

insert overwrite table dwd_cart_info partition (dt = '2022-04-26')
select id,
       user_id,
       sku_id,
       source_id,
       source_type,
       cart_price,
       is_ordered,
       sku_num,
       create_time,
       operate_time,
       order_time
from ods_cart_info
where dt = '2022-04-26';

insert overwrite table dwd_favor_info partition (dt = '2022-04-26')
select id,
       user_id,
       sku_id,
       spu_id,
       is_cancel,
       create_time,
       cancel_time
from ods_favor_info
where dt = '2022-04-26';

set hive.exec.dynamic.partition.mode=nonstrict;
-- insert overwrite table dwd_coupon_use partition (dt)
-- select id,
--        coupon_id,
--        user_id,
--        order_id,
--        coupon_status,
--        get_time,
--        using_time,
--        used_time,
--        expire_time,
--        coalesce(date_format(used_time, 'yyyy-MM-dd'), date_format(expire_time, 'yyyy-MM-dd'), '9999-99-99')
-- from ods_coupon_use
-- where dt = '2022-04-26';

insert overwrite table dwd_coupon_use partition (dt)
select nvl(o.id, d.id),
       nvl(o.coupon_id, d.coupon_id),
       nvl(o.user_id, d.user_id),
       nvl(o.order_id, d.order_id),
       nvl(o.coupon_status, d.coupon_status),
       nvl(o.get_time, d.get_time),
       nvl(o.using_time, d.using_time),
       nvl(o.used_time, d.used_time),
       nvl(o.expire_time, d.expire_time),
       coalesce(date_format(nvl(o.used_time, d.used_time), 'yyyy-MM-dd'),
                date_format(nvl(o.expire_time, d.expire_time), 'yyyy-MM-dd'), '9999-99-99')
from (
         select id,
                coupon_id,
                user_id,
                order_id,
                coupon_status,
                get_time,
                using_time,
                used_time,
                expire_time
         from dwd_coupon_use
         where dt = '9999-99-99'
     ) d
         full outer join
     (
         select id,
                coupon_id,
                user_id,
                order_id,
                coupon_status,
                get_time,
                using_time,
                used_time,
                expire_time
         from ods_coupon_use
         where dt = '2022-04-26'
     ) o
     on d.id = o.id;

-- insert overwrite table dwd_payment_info partition (dt)
-- select p.id,
--        order_id,
--        user_id,
--        province_id,
--        trade_no,
--        out_trade_no,
--        payment_type,
--        total_amount,
--        payment_status,
--        create_time,
--        callback_time,
--        nvl(date_format(create_time, 'yyyy-MM-dd'), '9999-99-99')
-- from (
--          select id,
--                 order_id,
--                 user_id,
--                 trade_no,
--                 out_trade_no,
--                 payment_type,
--                 total_amount,
--                 payment_status,
--                 create_time,
--                 callback_time
--          from ods_payment_info
--          where dt = '2022-04-26'
--      ) p
--          left join
--      (
--          select id,
--                 province_id
--          from ods_order_info
--          where dt = '2022-04-26'
--      ) o
--      on p.order_id = o.id;

insert overwrite table dwd_payment_info partition (dt)

select nvl(o.id, d.id),
       nvl(o.order_id, d.order_id),
       nvl(o.user_id, d.user_id),
       nvl(o.province_id, d.province_id),
       nvl(o.trade_no, d.trade_no),
       nvl(o.out_trade_no, d.out_trade_no),
       nvl(o.payment_type, d.payment_type),
       nvl(o.total_amount, d.total_amount),
       nvl(o.payment_status, d.payment_status),
       nvl(o.create_time, d.create_time),
       nvl(o.callback_time, d.callback_time),
       nvl(date_format(nvl(o.callback_time, d.callback_time), 'yyyy-MM-dd'), '9999-99-99')
from (
         select id,
                order_id,
                user_id,
                province_id,
                trade_no,
                out_trade_no,
                payment_type,
                total_amount,
                payment_status,
                create_time,
                callback_time
         from dwd_payment_info
         where dt = '9999-99-99'
     ) d
         left join
     (
         select p.id,
                order_id,
                user_id,
                province_id,
                trade_no,
                out_trade_no,
                payment_type,
                total_amount,
                payment_status,
                create_time,
                callback_time
         from (
                  select id,
                         order_id,
                         user_id,
                         trade_no,
                         out_trade_no,
                         payment_type,
                         total_amount,
                         payment_status,
                         create_time,
                         callback_time
                  from ods_payment_info
                  where dt = '2022-04-26'
              ) p
                  left join
              (
                  select id,
                         province_id
                  from ods_order_info
                  where dt = '2022-04-26'
              ) o
              on p.order_id = o.id
     ) o
     on d.id = o.id;

-- insert overwrite table dwd_refund_payment partition (dt)
-- select r.id,
--        user_id,
--        order_id,
--        sku_id,
--        province_id,
--        trade_no,
--        out_trade_no,
--        payment_type,
--        total_amount,
--        refund_status,
--        create_time,
--        callback_time,
--        nvl(date_format(callback_time, 'yyyy-MM-dd'), '9999-99-99')
-- from (
--          select id,
--                 order_id,
--                 sku_id,
--                 trade_no,
--                 out_trade_no,
--                 payment_type,
--                 total_amount,
--                 refund_status,
--                 create_time,
--                 callback_time
--          from ods_refund_payment
--          where dt = '2022-04-26'
--      ) r
--          left join
--      (
--          select id,
--                 user_id,
--                 province_id
--          from ods_order_info
--          where dt = '2022-04-26'
--      ) o
--      on r.order_id = o.id;

insert overwrite table dwd_refund_payment partition (dt)
select nvl(o.id, d.id),
       nvl(o.user_id, d.user_id),
       nvl(o.order_id, d.order_id),
       nvl(o.sku_id, d.sku_id),
       nvl(o.province_id, d.province_id),
       nvl(o.trade_no, d.trade_no),
       nvl(o.out_trade_no, d.out_trade_no),
       nvl(o.payment_type, d.payment_type),
       nvl(o.total_amount, d.total_amount),
       nvl(o.refund_status, d.refund_status),
       nvl(o.create_time, d.create_time),
       nvl(o.callback_time, d.callback_time),
       nvl(date_format(nvl(o.callback_time, d.callback_time), 'yyyy-MM-dd'), '9999-99-99')
from (
         select id,
                user_id,
                order_id,
                sku_id,
                province_id,
                trade_no,
                out_trade_no,
                payment_type,
                total_amount,
                refund_status,
                create_time,
                callback_time
         from dwd_refund_payment
         where dt = '9999-99-99'
     ) d
         left join
     (
         select r.id,
                user_id,
                order_id,
                sku_id,
                province_id,
                trade_no,
                out_trade_no,
                payment_type,
                total_amount,
                refund_status,
                create_time,
                callback_time
         from (
                  select id,
                         order_id,
                         sku_id,
                         trade_no,
                         out_trade_no,
                         payment_type,
                         total_amount,
                         refund_status,
                         create_time,
                         callback_time
                  from ods_refund_payment
                  where dt = '2022-04-26'
              ) r
                  left join
              (
                  select id,
                         user_id,
                         province_id
                  from ods_order_info
                  where dt = '2022-04-26'
              ) o
              on r.order_id = o.id
     ) o
     on d.id = o.id;

-- insert overwrite table dwd_order_info partition (dt)
-- select id,
--        order_status,
--        user_id,
--        province_id,
--        payment_way,
--        delivery_address,
--        out_trade_no,
--        tracking_no,
--        create_time,
--        payment_time,
--        cancel_time,
--        finish_time,
--        refund_time,
--        refund_finish_time,
--        expire_time,
--        feight_fee,
--        feight_fee_reduce,
--        activity_reduce_amount,
--        coupon_reduce_amount,
--        original_total_amount,
--        total_amount,
--        case
--            when cancel_time is not null then date_format(cancel_time, 'yyyy-MM-dd')
--            when finish_time is not null and refund_time is null and
--                 date_add(date_format(finish_time, 'yyyy-MM-dd'), 7) <= '2022-04-26'
--                then date_add(date_format(finish_time, 'yyyy-MM-dd'), 7)
--            when refund_finish_time is not null then date_format(refund_finish_time, 'yyyy-MM-dd')
--            when expire_time is not null then date_format(expire_time, 'yyyy-MM-dd')
--            else '9999-99-99'
--            end
-- from (
--          select id,
--                 order_status,
--                 user_id,
--                 province_id,
--                 payment_way,
--                 delivery_address,
--                 out_trade_no,
--                 tracking_no,
--                 create_time,
--                 expire_time,
--                 feight_fee,
--                 feight_fee_reduce,
--                 activity_reduce_amount,
--                 coupon_reduce_amount,
--                 original_total_amount,
--                 total_amount
--          from ods_order_info
--          where dt = '2022-04-26'
--      ) i
--          left join
--      (
--          select order_id,
--                 tms['1002'] payment_time,
--                 tms['1003'] cancel_time,
--                 tms['1004'] finish_time,
--                 tms['1005'] refund_time,
--                 tms['1006'] refund_finish_time
--          from (
--                   select order_id,
--                          str_to_map(concat_ws(',', collect_set(concat(order_status, '=', operate_time))), ',',
--                                     '=') as tms
--                   from ods_order_status_log
--                   where dt = '2022-04-26'
--                   group by order_id
--               ) t
--      ) s
--      on i.id = s.order_id;

insert overwrite table dwd_order_info partition (dt)
select nvl(o.id, d.id),
       nvl(o.order_status, d.order_status),
       nvl(o.user_id, d.user_id),
       nvl(o.province_id, d.province_id),
       nvl(o.payment_way, d.payment_way),
       nvl(o.delivery_address, d.delivery_address),
       nvl(o.out_trade_no, d.out_trade_no),
       nvl(o.tracking_no, d.tracking_no),
       nvl(o.create_time, d.create_time),
       nvl(o.payment_time, d.payment_time),
       nvl(o.cancel_time, d.cancel_time),
       nvl(o.finish_time, d.finish_time),
       nvl(o.refund_time, d.refund_time),
       nvl(o.refund_finish_time, d.refund_finish_time),
       nvl(o.expire_time, d.expire_time),
       nvl(o.feight_fee, d.feight_fee),
       nvl(o.feight_fee_reduce, d.feight_fee_reduce),
       nvl(o.activity_reduce_amount, d.activity_reduce_amount),
       nvl(o.coupon_reduce_amount, d.coupon_reduce_amount),
       nvl(o.original_total_amount, d.original_total_amount),
       nvl(o.total_amount, d.total_amount),
       case
           when nvl(o.cancel_time, d.cancel_time) is not null then date_format(nvl(o.cancel_time, d.cancel_time), 'yyyy-MM-dd')
           when  nvl(o.finish_time, d.finish_time) is not null and nvl(o.refund_time, d.refund_time) is null and
                date_add(date_format( nvl(o.finish_time, d.finish_time), 'yyyy-MM-dd'), 7) <= '2022-04-26'
               then date_add(date_format( nvl(o.finish_time, d.finish_time), 'yyyy-MM-dd'), 7)
           when nvl(o.refund_finish_time, d.refund_finish_time) is not null then date_format(nvl(o.refund_finish_time, d.refund_finish_time), 'yyyy-MM-dd')
           when nvl(o.expire_time, d.expire_time) is not null then date_format(nvl(o.expire_time, d.expire_time), 'yyyy-MM-dd')
           else '9999-99-99'
           end
from (
         select id,
                order_status,
                user_id,
                province_id,
                payment_way,
                delivery_address,
                out_trade_no,
                tracking_no,
                create_time,
                payment_time,
                cancel_time,
                finish_time,
                refund_time,
                refund_finish_time,
                expire_time,
                feight_fee,
                feight_fee_reduce,
                activity_reduce_amount,
                coupon_reduce_amount,
                original_total_amount,
                total_amount
         from dwd_order_info
         where dt = '9999-99-99'
     ) d
         full outer join
     (
         select id,
                order_status,
                user_id,
                province_id,
                payment_way,
                delivery_address,
                out_trade_no,
                tracking_no,
                create_time,
                payment_time,
                cancel_time,
                finish_time,
                refund_time,
                refund_finish_time,
                expire_time,
                feight_fee,
                feight_fee_reduce,
                activity_reduce_amount,
                coupon_reduce_amount,
                original_total_amount,
                total_amount,
                case
                    when cancel_time is not null then date_format(cancel_time, 'yyyy-MM-dd')
                    when finish_time is not null and refund_time is null and
                         date_add(date_format(finish_time, 'yyyy-MM-dd'), 7) <= '2022-04-26'
                        then date_add(date_format(finish_time, 'yyyy-MM-dd'), 7)
                    when refund_finish_time is not null then date_format(refund_finish_time, 'yyyy-MM-dd')
                    when expire_time is not null then date_format(expire_time, 'yyyy-MM-dd')
                    else '9999-99-99'
                    end
         from (
                  select id,
                         order_status,
                         user_id,
                         province_id,
                         payment_way,
                         delivery_address,
                         out_trade_no,
                         tracking_no,
                         create_time,
                         expire_time,
                         feight_fee,
                         feight_fee_reduce,
                         activity_reduce_amount,
                         coupon_reduce_amount,
                         original_total_amount,
                         total_amount
                  from ods_order_info
                  where dt = '2022-04-26'
              ) i
                  left join
              (
                  select order_id,
                         tms['1002'] payment_time,
                         tms['1003'] cancel_time,
                         tms['1004'] finish_time,
                         tms['1005'] refund_time,
                         tms['1006'] refund_finish_time
                  from (
                           select order_id,
                                  str_to_map(concat_ws(',', collect_set(concat(order_status, '=', operate_time))), ',',
                                             '=') as tms
                           from ods_order_status_log
                           where dt = '2022-04-26'
                           group by order_id
                       ) t
              ) s
              on i.id = s.order_id
     ) o
     on d.id = o.id;