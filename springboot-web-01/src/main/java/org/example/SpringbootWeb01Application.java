package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * IOC注解：
 *       要把某个对象交给IOC容器管理，需要在对应的类上加上如下注解之一：
 *          	位置 			 注解				   说明
 *           @Component    声明bean的基础注解        不属于以下三类时，用此注解
 *           @Controller   @Component的衍生注解	    标注在控制层类上
 *           @Service      @Component的衍生注解     标注在业务层类上
 *           @Repository   @Component的衍生注解	    标注在数据访问层类上(由于与mybatis整合，用的少)
 *
 * IOC详解-注意事项：
 *      前面声明bean的四大注解，要想生效，还需要被组件扫描注解@ComponentScan扫描。
 *      该注解虽然没有显式配置，但是实际上已经包含在了启动类声明注解 @SpringBootApplication 中，默认扫描的范围是启动类所在包及其子包。
 */

//http://localhost:8080/user.html
@SpringBootApplication //该注解已具备组件扫描作用，作用于启动类所在包及其子包（超出范围则无效）
public class SpringbootWeb01Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootWeb01Application.class, args);
    }

}
