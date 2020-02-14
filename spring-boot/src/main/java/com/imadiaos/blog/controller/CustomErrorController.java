package com.imadiaos.blog.controller;

import com.imadiaos.blog.entity.ResponseEntity;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// 不在 Swagger 文档中输出当前的控制器
@ApiIgnore
@RestController
public class CustomErrorController implements ErrorController {
    @Override
    public String getErrorPath() {
        return "/error";
    }

    @RequestMapping("/error")
    public ResponseEntity handleError(HttpServletRequest request, HttpServletResponse response) {
        return new ResponseEntity(response.getStatus(),
                (String) request.getAttribute("javax.servlet.error.message"), null);
    }
}