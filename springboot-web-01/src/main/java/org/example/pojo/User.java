package org.example.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 实体类：封装用户信息
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Integer id;//使用包装类，不采用基本数据类型（基本类有默认值）
    private String username;
    private String password;
    private String name;
    private Integer age;
    private LocalDateTime updateTime;
}
