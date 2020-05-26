package com.help.back.backend.dao;

import com.help.back.backend.domain.Group;

public interface GroupDao {
    int postGroup(Group group);
    int deleteGroup(int id);
}
