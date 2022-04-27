use warehouse;

load data inpath '/hive/warehouse/origin/sql-data/user_info/2022-04-26'
    overwrite into table ods_user_info partition (dt = '2022-04-26');
load data inpath '/hive/warehouse/origin/sql-data/user_info/2022-04-27'
    overwrite into table ods_user_info partition (dt = '2022-04-27');

load data inpath '/hive/warehouse/origin/sql-data/coupon_use/2022-04-26'
   overwrite into table ods_coupon_use partition (dt = '2022-04-26');
load data inpath '/hive/warehouse/origin/sql-data/coupon_use/2022-04-27'
    overwrite into table ods_coupon_use partition (dt = '2022-04-27');

load data inpath '/hive/warehouse/origin/sql-data/order_status_log/2022-04-26'
    overwrite into table ods_order_status_log partition (dt = '2022-04-26');
load data inpath '/hive/warehouse/origin/sql-data/order_status_log/2022-04-27'
    overwrite into table ods_order_status_log partition (dt = '2022-04-27');