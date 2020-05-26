package com.help.back.backend.service;

import com.help.back.backend.domain.Schedule;

import java.util.List;

public interface ScheduleService {
    List<Schedule> getTodoLists(int userId);
    int addTodo(Schedule schedule);
    int deleteTodo(int id);
    int updateTodoState(Schedule schedule);
    int todoListToSchedule(Schedule schedule);
}
