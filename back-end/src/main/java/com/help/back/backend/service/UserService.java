package com.help.back.backend.service;

import com.help.back.backend.dto.Login;
import com.help.back.backend.domain.User;
import com.help.back.backend.dto.ResultUser;

import java.util.List;

public interface UserService {
    List<ResultUser> getUsers();
    List<ResultUser> getUsersByName(String name);
    List<ResultUser> getUsersByEmail(String email);
    List<ResultUser> getUsersById(int id);
    User login(Login login);
    int postUser(User user);
    int updateUser(User user);
    int deleteUser(int id);
}
