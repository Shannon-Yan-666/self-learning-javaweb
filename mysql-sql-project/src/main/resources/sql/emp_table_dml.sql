USE db01;

-- --------------------> DML 表操作 【数据操作语言】<--------------------------
-- --------------------> DML：插入数据 - insert <--------------------------
-- 1. 插入数据时，指定的字段顺序需要与值的顺序是一对应的。
-- 2. 字符串和日期型数据应该包含在引号中（单引号、双引号都可以）。
-- 3. 插入的数据大小/长度，应该在字段的规定范围内。

-- 指定字段添加数据
-- insert into表名(字段名1，字段名2) values(值1，值2);

-- 全部字段添加数据
-- insert into 表名 values (值1, 值2,...);

-- 批量添加数据（指定字段）
-- insert into 表名（字段名1，字段名2) values(值1，值2)，(值1，值2);

-- 批量添加数据（全部字段）
-- insert into 表名 values (值1,值2,...),(值1,值2,...);

-- 练习
-- 为 emp 表的 username , password , name , gender , phone 字段插入值
insert into emp(username,password,name,gender,phone) values ('Bonnie','123456','邦妮',2,'15006666888');

-- 为 emp 表的 所有字段插入值
insert into emp values (null,'Engfa','6668888','英法',2,'13066669999',3,2000,'2026-08-01','1.jpg',now(),now());

-- 批量为 emp 表的 username , password , name , gender , phone 字段插入数据
insert into emp(username,password,name,gender,phone) values
                                                         ('Nam','1234567','老水',2,'15006666886'),
                                                         ('Lom','12345678','老风',2,'15006666889');


-- --------------------> DML：更新数据 - update <--------------------------
-- 修改语句的条件可以有，也可以没有，如果没有条件，则会修改整张表的所有数据。⚠️

-- 修改数据
-- update 表名 set 字段名1 = 值1 ， 字段名2 = 值2 , .... [ where 条件⚠️] ;

-- 练习
-- 将 emp 表的ID为 1的员工 用户名更新为 'Bennie'，姓名name字段更新为 '邦邦'
update emp set username = 'Bennie' , name = '邦邦' where id = 1 ;

-- 将 emp 表的所有员工的入职日期更新为'2020-01-01'
update emp set  entry_date = '2020-01-01' ;

-- --------------------> DML：删除数据 - delete <--------------------------
-- 1. DELETE 语句的条件可以有，也可以没有，如果没有条件，则会删除整张表的所有数据。⚠️
-- 2. DELETE 语句不能删除某一个字段的值(如果要操作，可以使用UPDATE，将该字段的值置为NULL)。

-- 删除数据
-- delete from 表名 [where 条件⚠️];

-- 练习
-- 删除 emp 表中 ID为 1 的员工
delete from emp where id = 1;

-- 删除 emp 表中的所有员工
-- delete from emp;⚠️