package org.example.springboot_web_quickstart;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 目标：了解HTTP协议-请求协议
 * 请求数据的数据格式：
 *      请求行：请求数据第一行（请求方式、资源路径、协议）
 *      请求头：第二行开始，格式key：value
             * Host：请求的主机名
             * User-Agent：
             * 浏览器版本，例如Chrome浏览器的标识类似Mozilla/5.0 ...Chrome/79，IE浏览器的标识类似Mozilla/5.0(Windows NT ...) like Gecko
             * Accept：
             * 表示浏览器能接收的资源类型，如text/✳，image/✳或者 ✳/✳表示所有;
             *Accept-Language：
             *表示浏览器偏好的语言，服务器可以据此返回不同语言的网页；
             *Accept-Encoding：
             *表示浏览器可以支持的压缩类型，例如gzip，deflate等。
             *Content-Type：
             *请求主体的数据类型。
             *Content-Length：
             *请求主体的大小（单位：字节）。
 *      空行：
 *      请求体：存放请求参数
 *           请求方式-GET：请求参数在请求行中，没有请求体，如：/brand/findAll?name=OPPO&status=1。GET请求大小在浏览器中是有限制的。
 *           请求方式-POST：请求参数在请求体中，POST请求大小是没有限制的。

 */
@RestController
public class RequestController {

    @RequestMapping("/request")
             //引入HttpServletRequest对象：里面封装了所有的请求信息
    public String request(HttpServletRequest request){
        //1、获取请求方式
        String method = request.getMethod();
        System.out.println("method: " + method);

        //2、获取请求路径-rul地址
        StringBuffer url = request.getRequestURL();
        System.out.println("url: " + url);

        String uri = request.getRequestURI();
        System.out.println("uri: " + uri);

        //3、获取请求协议-http
        String protocol = request.getProtocol();
        System.out.println("protocol: " + protocol);

        //4、获取请求参数-name
        String name = request.getParameter("name");
        System.out.println("name:  " + name);

        //5、获取请求头-Accept
        String accept = request.getHeader("Accept");
        System.out.println("accept:  " + accept);

        String cookie= request.getHeader("Cookie");
        System.out.println("cookie:  " + cookie);

        return "OK!";
    }
}
