package com.help.back.backend.service;

import com.help.back.backend.dto.Login;
import com.help.back.backend.domain.User;

import java.util.List;

public interface UserService {
    List<User> getUsers();
    List<User> getUsersByName(String name);
    List<User> getUsersByEmail(String email);
    List<User> getUsersById(int id);
    User login(Login login);
    int postUser(User user);
    int updateUser(User user);
    int deleteUser(int id);
}
