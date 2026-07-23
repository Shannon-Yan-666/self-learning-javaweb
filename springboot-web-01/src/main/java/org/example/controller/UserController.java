package org.example.controller;

import cn.hutool.core.io.IoUtil;
import org.example.pojo.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 *用户信息的Controller
 */

@RestController
//底层：
//ResponseBody ：将用controller的返回值，直接作为响应体的数据直接响应
//              如果返回值是对象/集合，先转jason，再响应回去
public class UserController {

    @RequestMapping("/list")
    public List<User> list() {
        //1、加载并读取user.txt文件，获取用户数据
                    //使用this关键字，获取这个类的Class对象，获取类的加载器，调用方法获取资源 As转化成一个流
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("user.txt");
        if(inputStream == null){
            return new ArrayList<>();
        }
        //按行读取数据，封装数据
        ArrayList<String> readLines = IoUtil.readLines(inputStream, StandardCharsets.UTF_8, new ArrayList<String>());

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

        //3、返回数据（json格式）
        //将方法的返回值声明为List<User>，服务器会自动转换为jason格式，再响应给前端
        return userList;
    }
}
