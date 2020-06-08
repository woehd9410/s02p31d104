package com.help.back.backend.service;

import com.help.back.backend.dao.ScheduleDao;
import com.help.back.backend.domain.Schedule;
import com.help.back.backend.dto.ResultGroupSchedule;
import com.help.back.backend.dto.ScheduleDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleServiceImpl implements ScheduleService{

    @Autowired
    private ScheduleDao scheduleDao;

    @Override
    public List<Schedule> getTodoLists(int userId) {
        return scheduleDao.getTodoLists(userId);
    }

    @Override
    public int addTodo(Schedule schedule) {
        return scheduleDao.addTodo(schedule);
    }

    @Override
    public int deleteTodo(int id) {
        return scheduleDao.deleteTodo(id);
    }

    @Override
    public int deleteCompleteTodo() {
        return scheduleDao.deleteCompleteTodo();
    }

    @Override
    public int updateTodoState(Schedule schedule) {
        return scheduleDao.updateTodoState(schedule);
    }

    @Override
    public int todoListToSchedule(Schedule schedule) {
        return scheduleDao.todoListToSchedule(schedule);
    }

    @Override
    public List<ResultGroupSchedule> searchGroupScheduleByGroupId(int gid) {
        return scheduleDao.searchGroupScheduleByGroupId(gid);
    }

    @Override
    public List<Schedule> getPersonalSchedule(int userId) {
        return scheduleDao.getPersonalSchedule(userId);
    }

    @Override
    public List<Schedule> getPersonalScheduleByDate(ScheduleDate scheduleDate) {
        return scheduleDao.getPersonalScheduleByDate(scheduleDate);
    }

    @Override
    public int postPersonalSchedule(Schedule schedule) {
        return scheduleDao.postPersonalSchedule(schedule);
    }

    @Override
    public int updatePersonalSchedule(Schedule schedule) {
        return scheduleDao.updatePersonalSchedule(schedule);
    }

    @Override
    public int deleteSchedule(int id) {
        return scheduleDao.deleteSchedule(id);
    }

    @Override
    public List<Schedule> getGroupSchedule(int userId) {
        return scheduleDao.getGroupSchedule(userId);
    }

    @Override
    public List<Schedule> getGroupScheduleByDate(ScheduleDate scheduleDate) {
        return scheduleDao.getGroupScheduleByDate(scheduleDate);
    }

    @Override
    public int postGroupSchedule(Schedule schedule) {
        return scheduleDao.postGroupSchedule(schedule);
    }

    @Override
    public int updateGroupSchedule(Schedule schedule) {
        return scheduleDao.updateGroupSchedule(schedule);
    }

  

}
