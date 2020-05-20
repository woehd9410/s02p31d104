package com.help.back.backend.service;

import com.help.back.backend.domain.Schedule;

import java.util.List;

public interface ScheduleService {
    List<Schedule> getTodoLists();
    int addTodo(Schedule schedule);
    int deleteTodo(int id);
    int updateTodo(Schedule schedule);
}
