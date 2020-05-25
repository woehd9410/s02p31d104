package com.help.back.backend.dao;

import com.help.back.backend.domain.Schedule;

import java.util.List;

public interface ScheduleDao {
    List<Schedule> getPersonalSchedule(int userId);
    int postPersonalSchedule(Schedule schedule);
    int updatePersonalSchedule(Schedule schedule);
    int deletePersonalSchedule(int id);
}
