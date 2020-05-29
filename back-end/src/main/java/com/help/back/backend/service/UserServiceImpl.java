package com.help.back.backend.service;

import com.help.back.backend.dao.UserDao;
import com.help.back.backend.domain.User;
import com.help.back.backend.dto.Login;
import com.help.back.backend.dto.ResultUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserDao userDao;
    @Override
    public List<ResultUser> getUsers() {
        return userDao.getUsers();
    }

    @Override
    public List<ResultUser> getUsersByName(String name) {
        return userDao.getUsersByName(name);
    }

    @Override
    public List<ResultUser> getUsersByEmail(String email) {
        return userDao.getUsersByEmail(email);
    }

    @Override
    public List<ResultUser> getUsersById(int id) {
        return userDao.getUsersById(id);
    }

    @Override
    public User login(Login login) {
        return userDao.login(login);
    }

    @Override
    public int postUser(User user) {
        return userDao.postUser(user);
    }

    @Override
    public int updateUser(User user) {
        return userDao.updateUser(user);
    }

    @Override
    public int deleteUser(int id) {
        return userDao.deleteUser(id);
    }
}
