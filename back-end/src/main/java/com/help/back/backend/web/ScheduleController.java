package com.help.back.backend.web;

import com.help.back.backend.domain.Schedule;
import com.help.back.backend.domain.User;
import com.help.back.backend.service.ScheduleService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PutMapping("v1/personal-schedule")
    public ResponseEntity updatePersonalSchedule(@RequestBody Schedule schedule) throws Exception{
        try {
            System.out.println("개인 스케쥴 수정");
            System.out.println(schedule.toString());
            int ans = scheduleService.updatePersonalSchedule(schedule);
            if(ans == 1){
                System.out.println("수정 성공  : " + ans);
                return new ResponseEntity(HttpStatus.OK);
            }else{
                return new ResponseEntity(HttpStatus.NO_CONTENT);
            }
        }catch(Exception e) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
    }

    @DeleteMapping("/v1/personal-schedule")
    public ResponseEntity deletePersonalSchedule(@RequestParam("id") int id) throws Exception{
        try {
            System.out.println("개인 스케쥴 삭제");
            System.out.println(id);
            int ans = scheduleService.deletePersonalSchedule(id);
            if(ans == 1){
                System.out.println("삭제 성공  : " + ans);
                return new ResponseEntity(HttpStatus.OK);
            }else{
                return new ResponseEntity(HttpStatus.NO_CONTENT);
            }
        }catch(Exception e) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/v1/personal-schedule")
    public ResponseEntity<List<Schedule>> getPersonalSchedule(@RequestParam("user_id") int user_id) throws Exception{
        List<Schedule> list = null;
        try {
            System.out.println("개인 스케쥴 검색");
            list = scheduleService.getPersonalSchedule(user_id);
            System.out.println(list);
            return new ResponseEntity<List<Schedule>>(list,HttpStatus.OK);
        }catch(Exception e) {
            return new ResponseEntity<List<Schedule>>(list,HttpStatus.NO_CONTENT);
        }

    }
}
