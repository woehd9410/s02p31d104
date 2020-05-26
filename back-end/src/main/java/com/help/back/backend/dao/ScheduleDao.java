package com.help.back.backend.dao;

import com.help.back.backend.domain.Schedule;
import com.help.back.backend.dto.ScheduleDate;

import java.util.List;

public interface ScheduleDao {
    List<Schedule> getPersonalSchedule(int userId);
    List<Schedule> getPersonalScheduleByDate(ScheduleDate scheduleDate);
    int postPersonalSchedule(Schedule schedule);
    int updatePersonalSchedule(Schedule schedule);
    int deleteSchedule(int id);

    List<Schedule> getGroupSchedule(int userId);
    List<Schedule> getGroupScheduleByDate(ScheduleDate scheduleDate);
    int postGroupSchedule(Schedule schedule);
    int updateGroupSchedule(Schedule schedule);
}
