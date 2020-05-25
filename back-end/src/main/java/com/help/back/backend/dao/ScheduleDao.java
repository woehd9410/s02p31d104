package com.help.back.backend.dao;

import com.help.back.backend.domain.Schedule;

public interface ScheduleDao {
    int postPersonalSchedule(Schedule schedule);
    int updatePersonalSchedule(Schedule schedule);
    int deletePersonalSchedule(int id);
}
