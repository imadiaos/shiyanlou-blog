package com.imadiaos.blog.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor // 无参构造函数
@AllArgsConstructor // 全参构造函数
public class UserEntity {
    private Integer id;

    private String username;

    private String password;

    private String role;

    private Long registerTime;
}
