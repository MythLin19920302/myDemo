package com.cjt.dao;

import com.cjt.domain.User;

import java.util.List;

public interface UserMapper {
    void insert(User record);

    List<User> getUser();

}