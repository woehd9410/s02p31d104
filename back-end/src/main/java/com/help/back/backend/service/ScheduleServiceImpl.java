package com.help.back.backend.service;

import com.help.back.backend.dao.ScheduleDao;
import com.help.back.backend.domain.Schedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleServiceImpl implements ScheduleService {


    @Autowired
    ScheduleDao scheduleDao;

    @Override
    public List<Schedule> getPersonalSchedule(int userId) {
        return scheduleDao.getPersonalSchedule(userId);
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
    public int deletePersonalSchedule(int id) {
        return scheduleDao.deletePersonalSchedule(id);
    }

}
