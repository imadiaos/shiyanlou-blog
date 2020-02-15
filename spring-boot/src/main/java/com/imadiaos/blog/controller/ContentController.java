package com.imadiaos.blog.controller;

import com.imadiaos.blog.entity.ContentEntity;
import com.imadiaos.blog.entity.ResponseEntity;
import com.imadiaos.blog.security.IsUser;
import com.imadiaos.blog.service.ContentService;
import com.imadiaos.blog.service.TagsService;
import com.imadiaos.blog.utils.Util;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/content")
@Validated
public class ContentController {

    @Autowired
    private ContentService contentService;

    @Autowired
    private TagsService tagsService;

    @ApiOperation(value = "创建文章", notes = "创建文章，创建成功返回文章id")
    @ApiImplicitParam(name = "tags", value = "文章标签，使用,号分隔", required = true)
    @IsUser
    @PostMapping
    public ResponseEntity createContent(@RequestParam @Length(min = 1, max = 100) String title,
                                        @RequestParam @Length(min = 1) String content,
                                        @RequestParam @Length(min = 1) String tags) {
        String[] tagsSpliced = tags.split(",");
        if (tagsSpliced.length == 0) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST.value(), "请至少输入一个标签", null);
        }
        int cid = contentService.createContent(title, content, tagsSpliced, Util.getCurrentUid());
        return new ResponseEntity(HttpStatus.OK.value(), "文章创建成功", cid);
    }

    @ApiOperation(value = "删除文章")
    @IsUser
    @DeleteMapping("/{cid}")
    public ResponseEntity deleteContent(@PathVariable int cid) {
        ContentEntity contentEntity = contentService.getContent(cid);
        Assert.notNull(contentEntity, "文章不存在");
        boolean isPermit = contentEntity.getAuthorId() == Util.getCurrentUid();
        if (! isPermit) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST.value(), "你无权删除别人的文章", null);
        }
        contentService.deleteContent(cid);
        return new ResponseEntity(HttpStatus.OK.value(), "文章删除成功", null);
    }

    @ApiOperation("更新文章")
    @ApiImplicitParam(name = "tags", value = "文章标签，使用,号分隔", required = true)
    @IsUser
    @PutMapping("/{cid}")
    @Validated
    public ResponseEntity updateContent(@PathVariable int cid,
                                        @RequestParam @Length(min = 1, max = 100) String title,
                                        @RequestParam @Length(min = 1) String content,
                                        @RequestParam @Length(min = 1) String tags) {
        ContentEntity contentEntity = contentService.getContent(cid);
        boolean isPermitted = contentEntity.getAuthorId() == Util.getCurrentUid();
        if (! isPermitted) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST.value(), "你无权更新别人的文章", null);
        }
        String[] tagsSpliced = tags.split(",");
        if (tagsSpliced.length == 0) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST.value(), "请至少输入一个标签", null);
        }
        contentService.updateContent(cid, title, content, tagsSpliced);
        return new ResponseEntity(HttpStatus.OK.value(), "文章更新成功", null);
    }

    @ApiOperation("获取文章")
    @GetMapping("/{cid}")
    public ResponseEntity getContent(@PathVariable int cid) {
        ContentEntity contentEntity = contentService.getContent(cid);
        Assert.notNull(contentEntity, "文章不存在");
        Map<String, Object> data = new HashMap<>(2);
        data.put("article", contentEntity);
        data.put("tags", tagsService.getRelationshipsByCid(cid));
        return new ResponseEntity(HttpStatus.OK.value(), "获取文章成功", data);
    }

}