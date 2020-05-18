package com.help.back.backend.dao;

import com.help.back.backend.domain.User;

import java.util.List;

public interface UserDao {
    List<User> getUsers();
    int postUser(User user);
}
