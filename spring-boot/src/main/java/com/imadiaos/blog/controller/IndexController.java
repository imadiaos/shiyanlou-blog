package com.imadiaos.blog.controller;

import com.imadiaos.blog.entity.ResponseEntity;
import com.imadiaos.blog.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
public class IndexController {

    @Autowired
    private UserService userService;

    // Swagger 文档注释
    @ApiOperation(value = "用户注册", notes = "登入成功返回 token，此后每次请求需要携带 token")
    @PostMapping("/register")
    public ResponseEntity register(@RequestParam @Length(min = 1, max = 100) String username,
                                   @RequestParam @Length(min = 1, max = 100) String password) {
        userService.register(username, password);
        return new ResponseEntity(HttpStatus.OK.value(), "注册成功", null);
    }
}
