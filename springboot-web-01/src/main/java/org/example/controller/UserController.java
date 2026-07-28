package org.example.controller;

import cn.hutool.core.io.IoUtil;
import org.example.pojo.User;
import org.example.service.UserService;
import org.example.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
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
 *用户信息的Controller层-控制层：接收请求，响应数据
 *
 * IOC注解：
 *      要把某个对象交给IOC容器管理，需要在对应的类上加上如下注解之一：
         * 	位置 			 注解				   说明
         * @Component    声明bean的基础注解        不属于以下三类时，用此注解
         * @Controller   @Component的衍生注解	    标注在控制层类上
         * @Service      @Component的衍生注解     标注在业务层类上
         * @Repository   @Component的衍生注解	    标注在数据访问层类上(由于与mybatis整合，用的少)
 *
 * IOC详解-注意事项：在Springboot集成web开发中，声明控制器bean只能用@Controller。
 *
 * DI详解：
 *      1、属性注入（追求简洁，可选）
 *              优点：代码简洁、方便快速开发。
 *              缺点：隐藏了类之间的依赖关系、可能会破坏类的封装性。
 *      2、构造函数注入（追求安全性，可选）
 *              优点：能清晰地看到类的依赖关系、提高了代码的安全性。
 *              缺点：代码繁琐、如果构造参数过多，可能会导致构造函数臃肿。
 *      3、setter注入（不常用）
 *              优点：保持了类的封装性，依赖关系更清晰。
 *              缺点：需要额外编写setter方法，增加了代码量。
 *
 *      依赖注入的注解     @Autowired  ：默认按照类型自动装配（spring框架）
 *      如果同类型的bean存在多个：
 *                      @Primary  :提升类的优先级
 *                      @Autowired + @Qualifier :指定注入的bean
 *                      @Resource :指定注入的bean，默认按照名称注入（java提供的注解）
 *
 */

@RestController
//底层：
//ResponseBody ：将用controller的返回值，直接作为响应体的数据直接响应
//              如果返回值是对象/集合，先转jason，再响应回去
public class UserController {
/*    @RequestMapping("/list")
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
    }*/

    /*
    方式一：属性注入
    @Autowired //依赖注入
    private UserService userService
     */

    /*
    方式二：构造器注入
    private final UserService userService;
    @Autowired //依赖注入（如果当前类中只存在一个构造函数，@Autowired 可以省略）
    public UserController(UserService userService) {
        this.userService = userService;
    }
    */

    /*
    方式三：setter注入
     */
    private UserService userService;
    @Autowired //依赖注入
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/list")
    public List<User> list() {
        //1、调用service，获取数据
        List<User> userList = userService.findAll();

        //2、返回数据（json格式）
        //将方法的返回值声明为List<User>，服务器会自动转换为jason格式，再响应给前端
        return userList;
    }
}
