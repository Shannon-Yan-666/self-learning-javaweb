package com.self_learning;

import com.mysql.cj.jdbc.Driver;
import com.self_learning.pojo.User;
import org.junit.jupiter.api.Test;

import java.sql.*;

/**
 * 目标：了解JDBC
 *      sun公司提供的一套操作关系型数据库的API（规范），实现由各数据库厂商提供驱动
 * 操作步骤：
 *      1、获取驱动
 *      2、获取数据库链接
 *      3、获取SQL语句执行对像
 *      4、执行SQL语句
 *              int 返回值：代表影响的记录数     executeUpdate();  执行DML语句-增删改
 *              ResultSet 封装查询返回的结果集  executeQuery();   执行DQL语句-查询
 *                        解析：resultSet.next()：光标往下移动一行
 *                        解析：resultSet.getXxx():获取字段数据
 *      5、释放资源，逆序关闭
 *
 * 预编译SQL:（PreparedStatement）
 *      优势一：可以防止SQL注入，更安全
 *            SQL注入：通过控制输入来修改事先定义好的SQL语句，以达到执行代码对服务器进行攻击的方法。
 *      优势二：性能更高
 *
 */
public class JdbcTest {

    /*
        JDBC入门程序
     */
    @Test
    public void testUpdate() throws Exception {
        //1、注册驱动
        Class.forName("com.mysql.cj.jdbc.Driver");

        //2、获取数据库连接
        String url = "jdbc:mysql://localhost:3306/你的库名";
        String user = "数据库用户名";
        String password = "数据库密码";
        Connection connection = DriverManager.getConnection(url, user, password);

        //3、获取SQL语句执行对象
        Statement statement = connection.createStatement();

        //4、执行SQL语句（静态SQL：参数硬编码）
        int i = statement.executeUpdate("update user set age = 25 where id = 1");//DML修改语句
        System.out.println("SQL语句执行完毕，影响的记录数为：" + i);

        //5、释放资源
        statement.close();
        connection.close();
    }

    @Test
    public void testSelect() {
        // 数据库连接信息
        String url = "jdbc:mysql://localhost:3306/你的库名";
        String user = "数据库用户名";
        String pwd = "数据库密码";

        Connection conn = null; //连接对象
        PreparedStatement pstmt = null;//预编译SQL语句执行对象
        ResultSet rs = null; //封装查询返回的结果

        try {
            //1.建立连接
            //执行时自动发现并注册驱动，不用手动加载
            conn = DriverManager.getConnection(url, user, pwd);

            //2.编写SQL，使用?占位符
            //预编译SQL（参数动态传递）：性能高，安全
            String sql = "select id,username,password,name,age from user where username = ? and password = ?";//DQL查询语句
            pstmt = conn.prepareStatement(sql);
            //给占位符赋值
            pstmt.setString(1, "daqiao");
            pstmt.setString(2, "123456");

            //3.执行查询
            rs = pstmt.executeQuery();//拿到结果集

            //4.遍历结果集，封装到User对象
            while (rs.next()) {
                User userObj = new User();
                userObj.setId(rs.getInt("id"));
                userObj.setUsername(rs.getString("username"));
                userObj.setPassword(rs.getString("password"));
                userObj.setName(rs.getString("name"));
                userObj.setAge(rs.getInt("age"));

                //控制台输出对象
                System.out.println(userObj);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {//finally：保证健壮、安全
            //5.关闭资源【逆序关闭】
            try { if(rs != null) rs.close(); } catch (SQLException e) {e.printStackTrace();}
            try { if(pstmt != null) pstmt.close(); } catch (SQLException e) {e.printStackTrace();}
            try { if(conn != null) conn.close(); } catch (SQLException e) {e.printStackTrace();}
        }
    }
}
