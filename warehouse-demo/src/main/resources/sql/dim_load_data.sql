use warehouse;
set hive.auto.convert.join=false;
set hive.exec.dynamic.partition.mode=nonstrict;
set mapred.map.tasks.speculative.execution=false;
set mapred.reduce.tasks.speculative.execution=false;
with sku as (
    select id,
           spu_id,
           price,
           sku_name,
           sku_desc,
           weight,
           tm_id,
           category3_id,
           is_sale,
           create_time
    from ods_sku_info
    where dt = '2020-01-02'
),
     spu as (
         select id,
                spu_name
         from ods_spu_info
         where dt = '2020-01-02'
     ),
     c3 as (
         select id,
                name,
                category2_id
         from ods_base_category3
         where dt = '2020-01-02'
     ),
     c2 as (
         select id,
                name,
                category1_id
         from ods_base_category2
         where dt = '2020-01-02'
     ),
     c1 as (
         select id,
                name
         from ods_base_category2
         where dt = '2020-01-02'
     ),
     tm as (
         select id,
                tm_name
         from ods_base_trademark
         where dt = '2020-01-02'
     ),
     attr as (
         select sku_id,
                collect_set(named_struct('attr_id', attr_id, 'attr_name', attr_name, 'value_id', value_id, 'value_name',
                                         value_name)) value
         from ods_sku_attr_value
         where dt = '2020-01-02'
         group by sku_id
     ),
     sale_attr as (
         select sku_id,
                collect_set(
                        named_struct('sale_attr_id', sale_attr_id, 'sale_attr_name', sale_attr_name, 'sale_value_id',
                                     sale_attr_value_id, 'sale_value_name', sale_attr_value_name)) value
         from ods_sku_sale_attr_value
         where dt = '2020-01-02'
         group by sku_id
     )
insert overwrite table dim_sku_info partition (dt = '2020-01-02')
select sku.id,
       sku.price,
       sku.sku_name,
       sku.sku_desc,
       sku.weight,
       sku.is_sale,
       sku.spu_id,
       spu.spu_name,
       sku.create_time,
       sku.category3_id,
       c3.name,
       c2.id,
       c2.name,
       c1.id,
       c1.name,
       sku.tm_id,
       tm.tm_name,
       attr.value,
       sale_attr.value
from sku
         left join spu on sku.spu_id = spu.id
         left join c3 on sku.category3_id = c3.id
         left join c2 on c3.category2_id = c2.id
         left join c1 on c2.category1_id = c1.id
         left join tm on sku.tm_id = tm.id
         left join attr on sku.id = attr.sku_id
         left join sale_attr on sku.id = sale_attr.sku_id;

insert overwrite table dim_coupon_info partition (dt = '2020-01-02')
select id,
       coupon_name,
       coupon_type,
       condition_amount,
       condition_num,
       activity_id,
       benefit_amount,
       benefit_discount,
       create_time,
       range_type,
       limit_num,
       taken_count,
       start_time,
       end_time,
       operate_time,
       expire_time
from ods_coupon_info
where dt = '2020-01-02';

insert overwrite table dim_activity_rule_info partition (dt = '2020-01-02')
select r.id,
       i.id,
       i.activity_name,
       i.activity_type,
       i.start_time,
       i.end_time,
       i.create_time,
       r.condition_amount,
       r.condition_num,
       r.benefit_amount,
       r.benefit_discount,
       r.benefit_level
from ods_activity_rule r
         left join ods_activity_info i on r.activity_id = i.id;

insert overwrite table dim_base_province
select p.id,
       p.name,
       p.area_code,
       p.iso_code,
       p.iso_3166_2,
       p.region_id,
       r.region_name
from ods_base_province p
         left join ods_base_region r on p.region_id = r.id;

with tmp as (
    select old.id           old_id,
           old.login_name   old_login_name,
           old.nick_name    old_nick_name,
           old.passwd       old_passwd,
           old.name         old_name,
           old.phone_num    old_phone_num,
           old.email        old_email,
           old.head_img     old_head_img,
           old.user_level   old_user_level,
           old.birthday     old_birthday,
           old.gender       old_gender,
           old.create_time  old_create_time,
           old.operate_time old_operate_time,
           old.start_date   old_start_date,
           old.end_date     old_end_date,
           new.id           new_id,
           new.login_name   new_login_name,
           new.nick_name    new_nick_name,
           new.passwd       new_passwd,
           new.name         new_name,
           new.phone_num    new_phone_num,
           new.email        new_email,
           new.head_img     new_head_img,
           new.user_level   new_user_level,
           new.birthday     new_birthday,
           new.gender       new_gender,
           new.create_time  new_create_time,
           new.operate_time new_operate_time,
           new.start_date   new_start_date,
           new.end_date     new_end_date
    from (
             select id,
                    login_name,
                    nick_name,
                    passwd,
                    name,
                    phone_num,
                    email,
                    head_img,
                    user_level,
                    birthday,
                    gender,
                    create_time,
                    operate_time,
                    start_date,
                    end_date
             from dim_user_info
             where dt = '9999-99-99'
         ) old
             full outer join
         (
             select id,
                    login_name,
                    nick_name,
                    passwd,
                    md5(name)      name,
                    md5(phone_num) phone_num,
                    md5(email)     email,
                    head_img,
                    user_level,
                    birthday,
                    gender,
                    create_time,
                    operate_time,
                    '2020-01-02'   start_date,
                    '9999-99-99'   end_date
             from ods_user_info
             where dt = '2020-01-02'
         ) new
         on old.id = new.id
)
insert overwrite table dim_user_info
select nvl(new_id, old_id),
       nvl(new_login_name, old_login_name),
       nvl(new_nick_name, old_nick_name),
       nvl(new_passwd, old_passwd),
       nvl(new_name, old_name),
       nvl(new_phone_num, old_phone_num),
       nvl(new_email, old_email),
       nvl(new_head_img, old_head_img),
       nvl(new_user_level, old_user_level),
       nvl(new_birthday, old_birthday),
       nvl(new_gender, old_gender),
       nvl(new_create_time, old_create_time),
       nvl(new_operate_time, old_operate_time),
       nvl(new_start_date, old_start_date),
       nvl(new_end_date, old_end_date),
       '9999-99-99'
from tmp
union all
select old_id,
       old_login_name,
       old_nick_name,
       old_passwd,
       old_name,
       old_phone_num,
       old_email,
       old_head_img,
       old_user_level,
       old_birthday,
       old_gender,
       old_create_time,
       old_operate_time,
       old_start_date,
       cast(date_add('2020-01-02', 0) as string),
       cast(date_add('2020-01-02', 0) as string) dt
from tmp
where old_id is not null
  and new_id is not null;