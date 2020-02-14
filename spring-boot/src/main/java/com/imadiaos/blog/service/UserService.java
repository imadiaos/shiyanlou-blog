package com.imadiaos.blog.service;

import com.imadiaos.blog.entity.UserEntity;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    /**
     * 注册
     * @param username 用户名
     * @param password 密码
     */
    void register(String username, String password);

    /**
     * 通过用户名查询用户信息
     * @param username  用户名
     * @return  用户实体类
     */
    UserEntity findUser(String username);

    /**
     * 登录操作
     * @param username 用户名
     * @param password 密码
     * @return 用户实体类
     */
    UserEntity login(String username, String password);

    /**
     * 修改用户密码
     * @param id    用户id
     * @param password  用户密码
     */
    void updatePassword(Integer id, String password);
}
