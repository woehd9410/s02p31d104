package com.help.back.backend.service;

import com.help.back.backend.domain.Schedule;

import java.util.List;

public interface ScheduleService {
    List<Schedule> getPersonalSchedule(int userId);
    int postPersonalSchedule(Schedule schedule);
    int updatePersonalSchedule(Schedule schedule);
    int deletePersonalSchedule(int id);
}
