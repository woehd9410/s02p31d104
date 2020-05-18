package com.help.back.backend.service;

import com.help.back.backend.domain.User;

import java.util.List;

public interface UserService {
    List<User> getUsers();
    int postUser(User user);
}
