package com.imadiaos.blog.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 内容实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContentEntity {
    private Integer cid;

    private String title;

    private String content;

    private Integer authorId;

    private Long createTime;
}
