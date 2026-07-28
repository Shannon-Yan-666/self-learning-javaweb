package org.example.service.impl;

import org.example.dao.UserDao;
import org.example.dao.impl.UserDaoImpl;
import org.example.pojo.User;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Service层-实现类：对数据的逻辑，进行处理
 */

@Service  //将当前类交给IOC容器管理
            //@Component的衍生注解：标注在业务层类上
public class UserServiceImpl implements UserService {

    @Autowired //应用程序运行时，会自动查找该类型的been对象，并赋值给该成员变量
    private UserDao userDao ;//拿到dao对象

    @Override
    public List<User> findAll() {
        //1、调用dao对象，获取数据
        List<String> readLines = userDao.findAll();//利用dao的方法，获取原始数据

        //2、解析用户信息，封装为User对象 -list集合
        List<User> userList = readLines.stream().map(line -> {
            String[] parts = line.split(",");
            Integer id = Integer.parseInt(parts[0]);
            String username = parts[1];
            String password = parts[2];
            String name = parts[3];
            Integer age = Integer.valueOf(parts[4]);
            LocalDateTime updateTime = LocalDateTime.parse(parts[5], DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            return new User(id, username, password, name, age, updateTime);
        }).toList();

        return userList;
    }
}
