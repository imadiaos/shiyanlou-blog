package com.imadiaos.blog.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 标签实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TagEntity {

    private Integer tid;

    private String name;
}