

create table t_brand_connection(
id int primary key unique not null,
user_id int not null,
brand_id int,
connection_type varchar(20),
display_name varchar(200),
auth_type varchar(10),
account_identifier varchar(10),
access_token text,
refresh_token text,
expired_date timestamp,
api_key varchar (200),
api_secret varchar(200),
additional_config text,
actived varchar(2),
synced_enable varchar(2),
last_synced_at timestamp,
create_date timestamp,
update_time timestamp
);

alter table t_brand_connection add constraint fk_brand_connection_user_id foreign key(user_id) references t_user(id);
alter table t_brand_connection add constraint fk_brand_id_brand_id foreign key(brand_id) references t_brand(brand_id);


