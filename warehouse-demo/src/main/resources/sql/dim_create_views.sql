use warehouse;

create or replace view dim_user_info_view as
select *
from dim_user_info
where dt = '9999-99-99';

select *
from dim_user_info_view;

create or replace view dim_sku_info_view as
select id,
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

select *
from dim_sku_info_view;
