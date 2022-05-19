use warehouse;

create or replace view dim_user_info_view as
select *
from dim_user_info
where dt = '9999-99-99';

select *
from dim_user_info_view;

create or replace view dim_sku_info_view as
select *
from dim_sku_info
where dt = current_date;

select *
from dim_sku_info_view;
