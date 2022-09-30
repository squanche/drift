package com.example.DB.entity;

import lombok.Data;

/**
 * @author yangzhch
 * @date 2022/9/30 11:51
 */
@Data
public class User {
    private Long id;
    private String name;
    private Integer age;
    private String email;
}