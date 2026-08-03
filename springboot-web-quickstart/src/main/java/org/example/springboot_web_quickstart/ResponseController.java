package org.example.springboot_web_quickstart;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 *  目标：了解HTTP协议-响应协议
 *  响数据的数据格式：
 *      响应行：响应数据第一行（协议、状态码、描述）
 *            常见的响应状态码（五大类）：
                 * 1xx：响应中-临时状态码，表示请求已经接收，告诉客户端应该继续请求或者如果它已经完成则忽略它。
                 * 2xx：成功-表示请求已经被成功接收，处理已完成。
                 * 3xx：重定向-重定向到其他地方；让客户端再发起一次请求以完成整个处理。
                 * 4xx：客户端错误-处理发生错误，责任在客户端。如：请求了不存在的资源、客户端未被授权、禁止访问等。
                 * 5xx：服务器错误-处理发生错误，责任在服务端。如：程序抛出异常等。
 *      响应头：第二行开始：格式key：value
 *             常见的响应头:
                 * Content-Type：表示该响应内容的类型，例如text/html，application/json。
                 * Content-Length：表示该响应内容的长度（字节数）。
                 * Content-Encoding：表示该响应压缩算法，例如gzip。
                 * Cache-Control：指示客户端应如何缓存，例如max-age=300表示可以最多缓存300秒。
                 * Set-Cookie：告诉浏览器为当前页面所在的域设置cookie。
 *      空行
 *      响应体：最后一部分，存放数据
 */
@RestController
public class ResponseController {

    //方式一：HttpServletResponse 设置响应数据
    @RequestMapping("/response")
    public void response(HttpServletResponse response) throws IOException {
        //1、设置响应状态码（通常状态码不用设定，服务器会自动生成）
        response.setStatus(HttpServletResponse.SC_OK);

        //2、设置响应头（通常不需要设置）
        response.setHeader("name","bonnie");

        //3、设置响应体
        response.getWriter().write("<h1>hello response</h1>");//有异常抛出

    }

    //方式二：Spring-ResponseEntity
    @RequestMapping("/response2")
    public ResponseEntity<String> getResponseEntity(){
        return ResponseEntity
                .status(HttpServletResponse.SC_OK)//设置响应状态码（通常不需要设置）
                .header("name","bonnie")//设置响应头（通常不需要设置）
                .body("<h1>hello response2</h1>");//设置响应体
    }
}
