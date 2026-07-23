package org.example.springboot_web_quickstart;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//表示当前类是一个请求处理类
@RestController
public class HelloController {

    //http://localhost:8080/hello?name=
    //表示请求路径
    @RequestMapping("/hello")
    public String sayHello(String name) {
        System.out.println("name ： " + name);
        return "Hello " + name + "~";
    }
}
