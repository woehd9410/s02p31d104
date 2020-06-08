package com.help.back.backend.dao;

import com.help.back.backend.domain.Schedule;
import com.help.back.backend.dto.ResultGroupSchedule;
import com.help.back.backend.dto.ScheduleDate;

import java.util.List;

public interface ScheduleDao {
    List<Schedule> getTodoLists(int userId);
    int addTodo(Schedule schedule);
    int deleteTodo(int id);
    int deleteCompleteTodo();
    int updateTodoState(Schedule schedule);
    int todoListToSchedule(Schedule schedule);
    List<Schedule> getPersonalSchedule(int userId);
    List<Schedule> getPersonalScheduleByDate(ScheduleDate scheduleDate);
    int postPersonalSchedule(Schedule schedule);
    int updatePersonalSchedule(Schedule schedule);
    int deleteSchedule(int id);

    List<Schedule> getGroupSchedule(int userId);
    List<Schedule> getGroupScheduleByDate(ScheduleDate scheduleDate);
    int postGroupSchedule(Schedule schedule);
    int updateGroupSchedule(Schedule schedule);
    List<ResultGroupSchedule> searchGroupScheduleByGroupId(int gid);
}
