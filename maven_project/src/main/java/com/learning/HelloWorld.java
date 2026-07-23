package com.learning;

/**
 * 目标：了解Maven
 *      什么是坐标？
 *              Maven中的坐标是资源（jar）的唯一标识，通过该坐标可以唯一定位资源位置。
 *              使用坐标来定义项目或引入项目中需要的依赖
 *      坐标主要组成：（pom.xml）
 *              groupId:定义当前Maven项目隶属组织名称（通常是域名反写，例如：com.learning）
 *              artifactId:定义当前Maven项目名称（通常是模块名称，例如：order-service、good-service）
 *              version:定义当前项目版本号：
 *                          SNAPSHOT:功能尚不稳定、尚处于开发中的版本，即快照版本（开发中）
 *                          RELEASE:功能趋于文档、当前更新停止，可以用于发行的版本（开发完毕，测试通过，最终对外发布）
 *       依赖配置：(依赖变更，需刷新重载)
 *              依赖：指当前项目运行所需要的jar包，一个项目中可以引入多个依赖。
 *              配置：
 *                  1、在pom.xml 中编写<dependencies>标签
 *                  2、在<dependencies>标签中，使用<dependency>引入坐标
 *                  3、定义坐标的 groupId,artifactId,version
 *                  4、点击刷新按钮，引入最新加入的坐标
 *             排除依赖：
 *                  <exclusions>...</exclusions>
 *      （导入Maven项目：
 *              选择文件：pom.xml，建议将要导入的maven项目复制到项目目录下，再进行导入）
 *       生命周期：
 *              为了对所有的maven项目构建过程进行抽象和统一。
 *              每套生命周期包含的阶段phase是有顺序的，后面的阶段依赖于前面的阶段。
 *              Maven中有3套相互独立的生命周期：
 *                               clean：清理工作。
 *                               default：核心工作，如：编译、测试、打包、安装、部署等。
 *                               site：生成报告、发布站点等。
 *             生命周期阶段：
                     * clean：移除上一次构建生成的文件
                     * compile：编译项目源代码
                     * test：使用合适的单元测试框架运行测试(junit测试框架)
                     * package：将编译后的文件打包，如：jar、war等
                     * install：安装项目到本地仓库
 */
public class HelloWorld {
    public static void main(String[] args) {
        System.out.println("Hello World");
    }
}
