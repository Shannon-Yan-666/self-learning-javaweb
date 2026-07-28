package org.example.dao;

import java.util.List;

/**
 * Dao层-接口：加载/读取/访问数据（操作文件 / 数据库）
 *           数据的增删改查
 */
public interface UserDao {

    /*
        加载用户数据
     */
    public List<String> findAll();
}
