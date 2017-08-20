--创建schema
create schema schema1;

--创建帐户表
create table schema1.user(
id int(10) not null AUTO_INCREMENT,
name varchar(10) not null,
current double(10,2) default 0.00,
primary key (id)
)

--创建操作记录表
create table schema1.operation(
id int(10) not null AUTO_INCREMENT,
user_id int(10),
description varchar(100) not null,
primary key (id)
)