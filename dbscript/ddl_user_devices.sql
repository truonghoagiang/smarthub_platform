
create table t_user_devices(
id int primary key unique not null,
brand_id int,
name varchar(100),
device_type varchar(50),
room varchar(50),
is_online varchar(2),
is_active varchar(2),
capabilities jsonb,
current_state jsonb,
properties jsonb,
model varchar(20),
firmware_version varchar(20),
last_actived timestamp,
create_date timestamp,
update_time timestamp
);

alter table t_user_devices add constraint fk_user_device_user_brand_id foreign key(brand_id) references t_brand_connection(id);
