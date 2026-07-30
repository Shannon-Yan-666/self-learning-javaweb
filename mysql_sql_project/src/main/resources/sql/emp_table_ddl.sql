-- --------------------> DDL 表操作 【数据类型】<--------------------------
-- 数值类型     占用字节	 有符号 (SIGNED) 范围	        无符号 (UNSIGNED) 范围	使用场景 & 建议
-- tinyint	    1           -128 ~ 127	                0 ~ 255	        ✅常用；年龄、状态标识（0/1）；年龄推荐 tinyint unsigned
-- int     	    4	      -2147483648 ~ 2147483647	    0 ~ 4294967295	✅传统主键首选；普通 id、数量；💡注意：新版互联网项目主键逐步改用 bigint
-- bigint	    8	      −263 ~ 263−1	                0 ~ 264−1	    ✅🔥企业主流主键类型，订单 id、用户 id 强烈推荐 bigint，避免 int 溢出
-- float	    4	        单精度浮点	                —	            ❌不推荐存储金额，精度丢失；仅科学计算场景
-- double	    8	        双精度浮点                   	—	            ❌禁止存金额，依旧存在精度缺陷
-- decimal(m,n)	可变	        高精度定点数	                —	            ✅🔥金额、价格必须使用 decimal；例：decimal(10,2) 总长度 10 位，小数 2 位

-- 字符串类型	    容量上限	            类型特点	                            使用场景 & 建议
-- char(n)	    最多255字符	    定长字符串，空间固定，查询速度快    	    ✅固定长度数据：手机号 (11)、身份证 (18)；存储不足长度自动补空格
-- varchar(n)	最多65535字节	    变长字符串，占用空间随内容变化，节省磁盘	    ✅🔥最常用：用户名、姓名、地址、标题；n 代表字符数量（MySQL8.0）
-- tinytext	    255字符	        短文本	                            ⚠️开发极少使用，优先 varchar
-- text	        65535字符	    长文本	                            ✅文章正文、评论大段文字；💡注意：不能设置默认值、查询性能弱于 varchar

-- 日期时间类型   字节	        取值范围	                格式	                   推荐程度
-- date	        3	1000-01-01 ~ 9999-12-31	    yyyy-MM-dd	             ✅生日、仅日期场景
-- time	        3	-838:59:59 ~ 838:59:59	    HH:mm:ss	             ⚠️单独使用少，多用于时间段
-- datetime	    8	1000-01-01 ~ 9999-12-31	    yyyy-MM-dd HH:mm:ss      ✅🔥企业主流；记录完整时间（创建时间、操作时间）

-- 案例：设计员工表 emp
-- 基础字段：id 主键;create_time 创建时间 ;update_time 修改时间;
create table emp(
                    id int unsigned primary key auto_increment comment 'ID，主键', -- 主键，无符号范围，自增（从1往上）
                    username varchar(20) not null unique comment '用户名', -- 可变长度，最长20，非空，唯一
                    password varchar(32) default'123456' comment '密码' ,  -- 默认值，暂不考虑密码加密问题，32位：最常见的加密算法，加密后的长度
                    name varchar(10) not null comment '姓名', -- 可变长度，最长10，非空
                    gender tinyint unsigned not null comment '性别，1 男；2 女', -- 无符号范围，非空
                    phone char(11) not null unique comment '手机号', -- 固定长度，长度11，非空，唯一
                    job tinyint unsigned comment '职位，1 主任；2 讲师； 3 学工主管；4 教研主管；5 咨询师 ', -- 无符号范围
                    salary int unsigned comment '薪资',-- 无符号范围
                    entry_date date comment '入职日期',
                    image varchar(255) comment '头像',-- 图片：存储访问路径，可变长度，最大值
                    create_time datetime comment '创建时间',
                    update_time datetime comment '修改时间'
)comment '员工表';

-- --------------------> DDL 表结构-【查询、修改、删除】 <--------------------------
-- show tables;               -- 查询当前数据库的所有表
-- desc 表名；                 -- 查询表结构（指定表）
-- show create table 表名;     -- 查询建表语句（指定表）

-- alter table 表名 add字段名 类型(长度)[comment 注释] [约束];              -- 添加字段
-- alter table 表名 modify 字段名 新数据类型(长度);                        -- 修改字段类型
-- alter table 表名 change旧字段名 新字段名类型(长度)[comment 注释] [约束]；  -- 修改字段名与字段类型
-- alter table 表名 drop column 字段名;                                  -- 删除字段
-- alter table 表名 rename to 新表名;                                    -- 修改表名

-- drop  table [if exists] 表名;                                        -- 删除表（表内数据也会被删除）

-- 查询表结构
desc emp;

-- 查询建表语句
show create table emp;

-- 字段：添加字段 wechat varchar(13)
alter table emp add  wechat varchar(13) comment '微信';

-- 字段：修改字段类型 wechat varchar(15)
alter table  emp modify wechat varchar(15) comment '微信';

-- 字段：修改字段名 wechat -> wechat_num varchar(15)
alter table emp change  wechat wechat_num varchar(15) comment '微信';

-- 字段：删除字段 wechat_num
alter table emp drop column wechat_num;

-- 修改表名
alter table emp rename to employee;

-- 删除表(表内数据也会被删除)
-- drop table employee;