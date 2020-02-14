package com.imadiaos.blog.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseEntity {

    // 返回状态
    private Integer status;

    // 返回消息
    private String msg;

    // 数据
    private Object data;
}
