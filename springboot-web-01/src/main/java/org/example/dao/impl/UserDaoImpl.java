package org.example.dao.impl;

import cn.hutool.core.io.IoUtil;
import org.example.dao.UserDao;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * Dao层-实现类：加载/读取/访问数据（操作文件 / 数据库）
 *             数据的增删改查
 */

@Repository("userDao") //将类产生的对象，交给IOC容器管理(通常不会为bean指定名字，直接使用默认类名首字母小写)
            //@Component的衍生注解：标注在数据访问层类上(由于与mybatis整合，用的少)
public class UserDaoImpl implements UserDao {

    @Override
    public List<String> findAll() {
        //1、加载并读取user.txt文件，获取用户数据
        //使用this关键字，获取这个类的Class对象，获取类的加载器，调用方法获取资源 As转化成一个流
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("user.txt");
        if(inputStream == null){
            return new ArrayList<>();
        }
        //按行读取数据，封装数据
        ArrayList<String> readLines = IoUtil.readLines(inputStream, StandardCharsets.UTF_8, new ArrayList<String>());
        return readLines;
    }
}
