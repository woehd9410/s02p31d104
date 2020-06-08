package com.help.back.backend.service;

import com.help.back.backend.dao.GroupDao;
import com.help.back.backend.domain.Group;
import com.help.back.backend.domain.GroupUser;
import com.help.back.backend.dto.GroupDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class GroupServiceImpl implements GroupService{

    @Autowired
    private GroupDao groupDao;

    @Override
    public int postGroup(Group group) {
        return groupDao.postGroup(group);
    }

    @Override
    public int deleteGroup(int id) {
        return groupDao.deleteGroup(id);
    }

    @Override
    public int updateGroup(Group group) {
        return groupDao.updateGroup(group);
    }

    @Override
    public int postGroupUser(GroupUser groupUser) {
        return groupDao.postGroupUser(groupUser);
    }

    @Override
    public int deleteGroupUser(Map map) {
        return groupDao.deleteGroupUser(map);
    }

    @Override
    public int updateGroupUser(GroupUser groupUser) {
        return groupDao.updateGroupUser(groupUser);
    }

    @Override
    public List<GroupUser> getGroupUser(int group_id) {
        return groupDao.getGroupUser(group_id);
    }

    @Override
    public List<GroupUser> getGroupUserByUserid(int user_id) {
        return groupDao.getGroupUserByUserid(user_id);
    }

    @Override
    public Group getGroupHavingUser(int id) {
        return groupDao.getGroupHavingUser(id);
    }
}
