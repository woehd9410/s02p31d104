package com.help.back.backend.web;

import com.help.back.backend.domain.Schedule;
import com.help.back.backend.domain.User;
import com.help.back.backend.service.ScheduleService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = {"1. Schedule"})
@RequiredArgsConstructor
@RequestMapping("/api")
@RestController
public class ScheduleController {

    @Autowired
    ScheduleService scheduleService;

    @PostMapping("v1/personal-schedule")
    public ResponseEntity<Schedule> postPersonalSchedule(@RequestBody Schedule schedule) throws Exception{
        try {
            System.out.println("개인 스케쥴 추가");
            System.out.println(schedule.toString());
            int ans = scheduleService.postPersonalSchedule(schedule);
            if(ans == 1){
                System.out.println("추가 : " + schedule.toString());
                return new ResponseEntity<Schedule>(schedule, HttpStatus.OK);
            }else{
                return new ResponseEntity(HttpStatus.NO_CONTENT);
            }
        }catch(Exception e) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
    }
}
