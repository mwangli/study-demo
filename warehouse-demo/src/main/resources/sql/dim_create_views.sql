use warehouse;

create or replace view dim_user_info_view as
select distinct id,
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
where dt = '9999-99-99';

select *
from dim_user_info_view;

create or replace view dim_sku_info_view as
select distinct id,
                price,
                sku_name,
                sku_desc,
                weight,
                is_sale,
                spu_id,
                spu_name,
                create_time,
                category3_id,
                category3_name,
                category2_id,
                category2_name,
                category1_id,
                category1_name,
                tm_id,
                tm_name
from dim_sku_info
where dt = current_date;

create or replace view dim_base_province_view as
select distinct id,
                province_name,
                area_code,
                iso_code,
                iso_3166_2,
                region_id,
                region_name
from dim_base_province
order by id;

create or replace view dwd_order_detail_view as
select distinct id,
                order_id,
                user_id,
                sku_id,
                province_id,
                activity_id,
                activity_rule_id,
                coupon_id,
                source_id,
                source_type,
                sku_num,
                original_amount,
                split_activity_amount,
                split_coupon_amount,
                split_total_amount,
                create_time,
                dt
from dwd_order_detail
where dt = current_date;


