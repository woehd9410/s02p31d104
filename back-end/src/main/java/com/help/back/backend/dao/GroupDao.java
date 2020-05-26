package com.help.back.backend.dao;

import com.help.back.backend.domain.Group;

import java.util.List;

public interface GroupDao {
    int postGroup(Group group);
    int deleteGroup(int id);
    int updateGroup(Group group);
}
