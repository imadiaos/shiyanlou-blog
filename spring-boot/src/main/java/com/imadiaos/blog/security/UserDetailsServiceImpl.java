package com.imadiaos.blog.security;

import com.imadiaos.blog.entity.UserEntity;
import com.imadiaos.blog.mapper.UserMapper;
import com.imadiaos.blog.utils.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userMapper.findByUsername(username);
        if (userEntity == null) {
            throw new UsernameNotFoundException("该用户不存在");
        }

        // 存储当前线程的 uid 到 ThreadLocal 中
        Util.setCurrentUid(userEntity.getUid());
        return new User(userEntity.getUsername(), userEntity.getPassword(),
                AuthorityUtils.createAuthorityList(userEntity.getRole()));
    }
}