package com.learning.mapper;

import com.learning.pojo.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper //应用程序在运行时，会自动为该接口创建一个实现类对象（代理对象-基于动态代理技术）
        //会自动将该实现类对象，存入到IOC容器 -bean
public interface UserMapper {

    /*
        作用：查询所有用户
     */
    //@Select("select * from user")
    public List<User> findAll();

    /*
        作用：根据用户名+密码，查询用户
        参数值传递：
                #{对象属性名}：封装在User对象中，使用占位符+对象属性名（注意不是字段名）
                方法
                形参在编译后的字节码文件中，不保留形参名，所以需要添加@Param 注解，为方法形参起名字
     */
     @Select("select * from user where username=#{username} and password = #{password}")
    public User findByUsernameAndPassword(@Param("username") String username,@Param("password") String password);

    /*
        作用：删除指定用户
        参数值传递：
                #{id} ：占位符，执行时会替换为 ？ ，生成预编译SQL（安全，性能高）
     */
    @Delete("delete from user where id = #{id}")
    public Integer deleteById(Integer id);

    /*
        作用：新增用户
        参数值传递：
                #{对象属性名}：封装在User对象中，使用占位符+对象属性名（注意不是字段名）
     */
    @Insert("insert into user(username,password,name,age)values (#{username},#{password},#{name},#{age})")
    public Integer insert(User user);

    /*
        作用：根据ID更新用户信息
        参数值传递：
                 #{对象属性名}：封装在User对象中，使用占位符+对象属性名（注意不是字段名）
     */
    @Update("update user set username=#{username},password=#{password},name=#{name},age=#{age} where id=#{id}")
    public Integer update(User user);
}

