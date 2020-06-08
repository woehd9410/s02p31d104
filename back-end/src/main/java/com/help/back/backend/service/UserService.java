package com.help.back.backend.service;

import com.help.back.backend.domain.Group;
import com.help.back.backend.dto.Login;
import com.help.back.backend.domain.User;
import com.help.back.backend.dto.ResultGroup;
import com.help.back.backend.dto.ResultUser;

import java.util.List;

public interface UserService {
    List<ResultUser> getUsers();
    List<ResultUser> getUsersByName(String name);
    List<ResultUser> getUsersByEmail(String email);
    List<ResultUser> getUsersById(int id);
    ResultUser login(Login login);
    int postUser(User user);
    int updateUser(User user);
    int updateUserPassword(Login user);
    int deleteUser(int id);
    List<ResultGroup> searchGroupByUserId(int id);
}
