package com.learning;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

/**
 * 测试类
 */
@DisplayName("用户信息测试类")
public class UserService2Test {
    private UserService userService;

    @BeforeEach
    public void setUp() {
        userService = new UserService();
    }

    /**
     * 测试获取性别 - null
     */
    @Test
    @DisplayName("获取性别-null值得")
    public void testGetGender1() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            userService.getGender(null);
        });
    }

    /**
     * 测试获取性别 - ""
     */
    @Test
    @DisplayName("获取性别-空串")
    public void testGetGender2() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            userService.getGender("");
        });
    }

    /**
     * 测试获取性别 - 长度不足
     */
    @Test
    @DisplayName("获取性别-长度不足")
    public void testGetGender3() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            userService.getGender("110");
        });
    }

    /**
     * 测试获取性别 - 超出长度
     */
    @Test
    @DisplayName("获取性别-长度超出")
    public void testGetGender4() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            userService.getGender("11000000200001001001111100000");
        });
    }

    /**
     * 测试获取性别 - 正常：男
     */
    @DisplayName("获取性别-正常男性身份证")
    @ParameterizedTest
    @ValueSource(strings = {"110002200505091218","110002200505091238","110002200505091258"})
    public void testGetGender5(String idCard) {
        UserService userService = new UserService();
        String gender = userService.getGender(idCard);
        //断言
        Assertions.assertEquals("男",gender);
    }

    /**
     * 测试获取性别 - 正常：女
     */
    @DisplayName("获取性别-正常女性身份证")
    @ParameterizedTest
    @ValueSource(strings = {"110002200505091228","110002200505091248","110002200505091268"})
    public void testGetGender6(String idCard) {
        UserService userService = new UserService();
        String gender = userService.getGender(idCard);
        //断言
        Assertions.assertEquals("女",gender);
    }

    /**
     * 测试获取年龄 - 正常
     */
    @Test
    @DisplayName("获取年龄-正常身份证")
    public void testGetAge() {
        Integer age = userService.getAge("110002200505091228");
        Assertions.assertEquals(21,age);
    }

    /**
     * 测试获取年龄 - null值
     */
    @Test
    @DisplayName("获取年龄-null值")
    public void testGetAge2() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            userService.getAge(null);
        });
    }

    /**
     * 测试获取年龄 - 长度超长
     */
    @Test
    @DisplayName("获取年龄-长度超长")
    public void testGetAge3() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            userService.getAge("11000000200001001001111100000");
        });
    }

    /**
     * 测试获取年龄 - 长度不足
     */
    @Test
    @DisplayName("获取年龄-长度超长")
    public void testGetAge4() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            userService.getAge("11000000200");
        });
    }
}
