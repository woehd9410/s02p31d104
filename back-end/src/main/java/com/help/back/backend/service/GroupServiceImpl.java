package com.help.back.backend.service;

import com.help.back.backend.dao.GroupDao;
import com.help.back.backend.domain.Group;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GroupServiceImpl implements GroupService{

    @Autowired
    private GroupDao groupDao;

    @Override
    public int postGroup(Group group) {
        return groupDao.postGroup(group);
    }
}
