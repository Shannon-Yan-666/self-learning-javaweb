package org.example.service;

import org.example.pojo.User;

import java.util.List;

/**
 * Service层-接口：对数据的逻辑，进行处理
 */
public interface UserService {

    /*
        查询所有用户信息
     */
    public List<User> findAll();
}
