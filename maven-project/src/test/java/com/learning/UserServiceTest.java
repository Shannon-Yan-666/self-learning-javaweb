package com.learning;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

/**
 * 测试类：
 *      JUnit单元测试的优点：
 *              测试代码与应用程序代码分开，便于维护
 *              可以自动生成测试报告（通过：绿色，失败：红色）
 *              一个测试方法执行失败，不会影响其他测试方法
 *
 *     断言：
 *          JUnit提供了一些辅助方法，用来帮我们确定被测试的方法是否按照预期的效果正常工作，这种方式称为断言。
 *                    断言方法                                                            描述
     * Assertions.assertEquals(Object exp, Object act, String msg)          检查两个值是否相等，不相等就报错。
     * Assertions.assertNotEquals(Object unexp, Object act, String msg)     检查两个值是否不相等，相等就报错。
     * Assertions.assertNull(Object act, String msg)                        检查对象是否为null，不为null，就报错。
     * Assertions.assertNotNull(Object act, String msg)                     检查对象是否不为null，为null，就报错。
     * Assertions.assertTrue(boolean condition, String msg)                 检查条件是否为true，不为true，就报错。
     * Assertions.assertFalse(boolean condition, String msg)                检查条件是否为false，不为false，就报错。
     * Assertions.assertThrows(class expType, Executable exec, String msg)  检查两个对象引用是否相等，不相等，就报错。
 *
 *     Junit常见注解：
 *              在JUnit中还提供了一些注解，还增强其功能，常见的注解有以下几个：
     *      注解                          说明                                          备注
     *      @Test           测试类中的方法用它修饰才能成为测试方法，才能启动执行               单元测试
     * @ParameterizedTest   参数化测试的注解（可以让单个测试运行多次，每次运行时仅参数不同）     用了该注解，就不需要aTest注解了
     * @ValueSource         参数化测试的参数来源，赋予测试方法参数                         与参数化测试注解配合使用
     * @DisplayName         指定测试类、测试方法显示的名称(默认为类名、方法名)
     * @BeforeEach          用来修饰一个实例方法，该方法会在每一个测试方法执行之前执行一次。     初始化资源(准备工作)
     * @AfterEach           用来修饰一个实例方法，该方法会在每一个测试方法执行之后执行一次。     释放资源(清理工作)
     * @BeforeAll           用来修饰一个静态方法，该方法会在所有测试方法之前只执行一次。        初始化资源(准备工作)
     * @AfterAll            用来修饰一个静态方法，该方法会在所有测试方法之后只执行一次。        释放资源(清理工作)
 *
 *      单元测试-企业开发规范
 *              原则：编写测试方法时，要尽可能的覆盖业务方法中所有可能的情况（尤其是边界值）。
 *              idCard = null
 *              idCard = ""
 *              idCard = "110"
 *              idCard = "11000000200001001001111100000"
 */
@DisplayName("用户信息的测试类")
public class UserServiceTest {
    /**
     * 使用JUnit，对UserService种的业务方法进行单元测试。
     * 1、在pom.xml 中，引入JUnit的依赖。
     * 2、在test/java目录下，创建测试类，并编写对应的测试方法，并在方法上声明@Test注解。
     * 3、运行单元测试（测试通过：绿色；测试失败：红色）
     */

    @Test
    //JUnit单元测试类命名规范为：XxxxxTest【规范】。
    //JUnit单元测试的方法，必须声明为public void 【规定】。
    public void testGetAge() {
        Integer age = new UserService().getAge("110002200505091218");
        System.out.println(age);
    }

    @Test
    public void testGetGender() {
        UserService userService = new UserService();
        String gender = userService.getGender("110002200505091218");
        System.out.println(gender);
    }

    /**
     * 断言
     */
    @Test
    public void testGetGenderWithAssert() {
        UserService userService = new UserService();
        String gender = userService.getGender("110002200505091218");
        //断言
        Assertions.assertEquals("男", gender,"性别获取错误");
    }

    @Test
    public void testGetGenderWithAssert2() {
        UserService userService = new UserService();
        String gender = userService.getGender("110002200505091218");
        //断言
        Assertions.assertThrows(IllegalArgumentException.class,()->{
            userService.getGender(null);} );
    }

    /**
     * Junit常见注解
     */
    @BeforeAll
    //静态方法，在所有的单元测试方法运行之前，运行一次
    public static void beforeAll() {
        System.out.println("BeforeAll");
    }
    @AfterAll
    //静态方法，在所有的单元测试方法运行之后，运行一次
    public static void afterAll() {
        System.out.println("AfterAll");
    }
    @BeforeEach
    //在每一个单元测试方法运行之前，运行一次
    public void beforeEach() {
        System.out.println("BeforeEach");
    }
    @AfterEach
    //在每一个单元测试方法运行之后，运行一次
    public void afterEach() {
        System.out.println("AfterEach");
    }

    /**
     * 参数化测试
     */
    @DisplayName("测试用户性别")
    @ParameterizedTest
    @ValueSource(strings = {"110002200505091218","110002200505091238","110002200505091258"})
    public void testGetGender2(String idCard) {
        UserService userService = new UserService();
        String gender = userService.getGender(idCard);
        //断言
        Assertions.assertEquals("男",gender);
    }
}
