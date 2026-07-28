-- 查询所有数据库
show databases ;

-- 创建数据库
create database db02;

-- 切换数据库
use db01;

-- 查询当前正在使用的数据库
select  database();

-- 删除数据库
drop database db02;

-- --------------------> DDL 表操作 <--------------------------
-- 约束：约束是作用于表中字段上的规则，用于限制存储在表中的数据。
-- 目的：保证数据库中数据的正确性、有效性和完整性。
--   约束          描述                                关键字
-- 非空约束  限制该字段值不能为ull                       not null
-- 唯一约束  保证字段的所有数据都是唯一、不重复的            unique
-- 主键约束  主键是一行数据的唯一标识，要求非空且唯一         primary key
-- 默认约束  保存数据时，如果未指定该字段值，则采用默认值      default
-- 外键约束  让两张表的数据建立连接，保证数据的一致性和完整性   foreign key

-- 创建表
create table user(
                     id int comment 'ID，唯一标识',
                     username varchar(50) comment'用户名',
                     name varchar(10) comment '姓名',
                     age int comment '年龄',
                     gender char(1) comment '性别'
)comment '用户信息表';

-- 创建表（约束）
create table user(
                     id int primary key auto_increment comment 'ID，唯一标识', -- 主键约束 auto_increment
                     username varchar(50) not null unique comment'用户名', -- 非空且唯一
                     name varchar(10) not null comment '姓名', -- 非空
                     age int comment '年龄',
                     gender char(1) default'女' comment '性别' -- 默认
)comment '用户信息表';

-- 删除行
-- delete from user where id = 3;