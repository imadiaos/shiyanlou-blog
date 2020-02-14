package com.imadiaos.blog.mapper;

import com.imadiaos.blog.entity.UserEntity;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Repository;

// 声明为 MyBatis 的 Mapper
@Mapper
// Spring Boot 的注解，使这个文件能够被扫描到
@Repository
public interface UserMapper {

    @Insert("INSERT INTO `user` (`username`, `password`, `register_time`) " +
            "VALUES (#{username}, #{password}, #{registerTime})")
    int createUser(@Param("username") String username, @Param("password") String password,
                   @Param("registerTime") Long registerTime);

    @Results(id = "userMap", value = {
            @Result(property = "uid", column = "uid"),
            @Result(property = "username", column = "username"),
            @Result(property = "password", column = "password"),
            @Result(property = "role", column = "role"),
            @Result(property = "registerTime", column = "register_time", jdbcType = JdbcType.BIGINT)

    })
    @Select("SELECT * FROM `user` WHERE `username` = #{username} AND `password` = #{password}")
    UserEntity findByUsernameAndPassword(@Param("username") String username,
                                         @Param("password") String password);

    @Select("SELECT * FROM `user` WHERE `username` = #{username}")
    @ResultMap("userMap")
    UserEntity findByUsername(@Param("username") String username);

    @Update("UPDATE `user` SET `password`=#{password} WHERE `uid`=#{uid}")
    void updatePassword(@Param("uid") Integer uid, @Param("password") String password);

}