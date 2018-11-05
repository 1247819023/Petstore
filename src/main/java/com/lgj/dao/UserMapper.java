package com.lgj.dao;

import com.lgj.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(Integer uid);

    int insert(User record);

    User selectByPrimaryKey(Integer uid);

    List<User> selectAll();

    int updateByPrimaryKey(User record);

    User selectByName(@Param("user_name") String user_name);

    int login(@Param("user_name") String user_name, @Param("password") String password);
}