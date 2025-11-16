
create table t_room(
id int primary key not null,
room_name varchar,
description text
);


create table t_device_type(
type_id int primary key not null,
type_name varchar not null,
description text
);

create table t_user_devices(
id int primary key unique not null,
brand_id int,
name varchar(100),
device_type int,
room_id int,
is_online boolean,
is_active boolean,
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
alter table t_user_devices add constraint fk_user_devices_room_id_room foreign key(room_id) references t_room(id);
alter table t_user_devices add constraint fk_user_device_device_type foreign key(device_type) references t_device_type(type_id);

alter table t_user_devices add user_id int;
alter table t_user_devices add constraint fk_user_devices_user_id foreign key(user_id) references t_user(id);