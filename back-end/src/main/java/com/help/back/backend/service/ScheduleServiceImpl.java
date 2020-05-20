package com.help.back.backend.service;

import com.help.back.backend.domain.Schedule;
import com.help.back.backend.dao.ScheduleDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleServiceImpl implements ScheduleService{

    @Autowired
    private ScheduleDao scheduleDao;

    @Override
    public List<Schedule> getTodoList() {
        System.out.println("serviceImpl======================");
        return scheduleDao.getTodoLists();
    }

}
