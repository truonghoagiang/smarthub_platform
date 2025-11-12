
create table t_refresh_token(
id int primary key unique not null,
token varchar(1000) unique not null,
user_id int,
created_date timestamp,
expired_date timestamp,
is_revoked varchar(2)
);

alter table t_refresh_token add constraint FK_REFRESH_TOKEN_USER_ID foreign key(user_id) references t_user(id);