

create table t_user(
id int primary key unique not null,
fullname varchar(200) not null,
phone_number varchar(20) unique not null,
email varchar(200) unique not null,
password varchar(400) not null,
role varchar(10) not null,
enabled varchar(2) default 'Y',
account_expired varchar(2) default 'N',
account_locked varchar(2) default 'N',
password_expired varchar(2) default 'N',
email_verified varchar(2) default 'N',
phone_verified varchar(2) default 'N',
avatar_url varchar(1000),
created_date timestamp,
updated_date timestamp,
last_login_date timestamp
);