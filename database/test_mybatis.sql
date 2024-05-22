create database if not exists db_shardingsphere_demo_0;
create database if not exists db_shardingsphere_demo_1;
create database if not exists db_shardingsphere_demo_2;

use db_shardingsphere_demo_0;

drop table if exists test_mybatis;
create table test_mybatis
(
    id   bigint(20)   not null comment '主键id' primary key,
    age  int          null comment '年龄',
    name varchar(255) null comment '姓名'
) comment '测试mybatis' charset utf8;

create table test_mybatis_0 like test_mybatis;
create table test_mybatis_1 like test_mybatis;

use db_shardingsphere_demo_1;

drop table if exists test_mybatis;
create table test_mybatis
(
    id   bigint(20)   not null comment '主键id' primary key,
    age  int          null comment '年龄',
    name varchar(255) null comment '姓名'
) comment '测试mybatis' charset utf8;

create table test_mybatis_0 like test_mybatis;
create table test_mybatis_1 like test_mybatis;

use db_shardingsphere_demo_2;

drop table if exists test_mybatis;
create table test_mybatis
(
    id   bigint(20)   not null comment '主键id' primary key,
    age  int          null comment '年龄',
    name varchar(255) null comment '姓名'
) comment '测试mybatis' charset utf8;

create table test_mybatis_0 like test_mybatis;
create table test_mybatis_1 like test_mybatis;
