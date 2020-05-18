package com.help.back.backend.service;

import com.help.back.backend.domain.User;

import java.util.List;

public interface UserService {
    List<User> getUsers(User user);
    int postUser(User user);
    int updateUser(User user);
    int deleteUser(String user_id);
}
