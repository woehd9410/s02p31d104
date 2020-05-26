package com.help.back.backend.service;

import com.help.back.backend.domain.Group;

public interface GroupService {
    int postGroup(Group group);
    int deleteGroup(int id);
    int updateGroup(Group group);
}
