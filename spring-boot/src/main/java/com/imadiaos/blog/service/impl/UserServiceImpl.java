package com.imadiaos.blog.service.impl;

import com.imadiaos.blog.entity.UserEntity;
import com.imadiaos.blog.mapper.UserMapper;
import com.imadiaos.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public void register(String username, String password) {
        UserEntity userEntity = userMapper.findByUsername(username);
        // 使用 Spring 自带的 Assert，这里抛出的异常会被 ExceptionController 捕获并处理返回给前端
        Assert.isNull(userEntity, "该用户名已被注册");
        // 对用户的密码进行加密
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        int status = userMapper.createUser(username, encoder.encode(password), System.currentTimeMillis());
        Assert.isTrue(status == 1, "注册失败");
    }

    @Override
    public UserEntity findUser(String username) {
        UserEntity userEntity = userMapper.findByUsername(username);
        Assert.notNull(userEntity, "该用户不存在");
        return userEntity;
    }

    @Override
    public UserEntity login(String username, String password) {
        UserEntity userEntity = userMapper.findByUsernameAndPassword(username, password);
        Assert.notNull(userEntity, "用户名或密码错误");
        return userEntity;
    }

}