package com.help.back.backend.web;

import com.help.back.backend.domain.Schedule;
import com.help.back.backend.domain.User;
import com.help.back.backend.dto.ScheduleDate;
import com.help.back.backend.service.ScheduleService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Api(tags = {"1. Schedule"})
@RequiredArgsConstructor
@RequestMapping("/api")
@CrossOrigin(origins = "*")
@RestController
public class ScheduleController {

    @Autowired
    ScheduleService scheduleService;

    @PostMapping("v1/user/personal-schedule")
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

    @PutMapping("v1/user/personal-schedule")
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

    @DeleteMapping("/v1/user/{id}/personal-schedule")
    public ResponseEntity deletePersonalSchedule(@PathVariable("id") int id) throws Exception{
        try {
            System.out.println("개인 스케쥴 삭제");
            System.out.println(id);
            int ans = scheduleService.deleteSchedule(id);
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

    @GetMapping("/v1/user/{user-id}/personal-schedule")
    public ResponseEntity<List<Schedule>> getPersonalSchedule(@PathVariable("user-id") int user_id) throws Exception{
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

    @PostMapping("/v1/user/personal-schedule/date")
    public ResponseEntity<List<Schedule>> getPersonalScheduleByDate(@RequestBody ScheduleDate scheduleDate) throws Exception{
        List<Schedule> list = null;
        try {
            System.out.println("개인 스케쥴 날짜에 따라 검색");
            System.out.println(scheduleDate);
            list = scheduleService.getPersonalScheduleByDate(scheduleDate);
            System.out.println(list);
            return new ResponseEntity<List<Schedule>>(list,HttpStatus.OK);
        }catch(Exception e) {
            return new ResponseEntity<List<Schedule>>(list,HttpStatus.NO_CONTENT);
        }

    }

    @PostMapping("v1/group/group-schedule")
    public ResponseEntity<Schedule> postGroupSchedule(@RequestBody Schedule schedule) throws Exception{
        try {
            System.out.println("그룹 스케쥴 추가");
            System.out.println(schedule.toString());
            int ans = scheduleService.postGroupSchedule(schedule);
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

    @PutMapping("v1/group/group-schedule")
    public ResponseEntity updateGroupSchedule(@RequestBody Schedule schedule) throws Exception{
        try {
            System.out.println("그룹 스케쥴 수정");
            System.out.println(schedule.toString());
            int ans = scheduleService.updateGroupSchedule(schedule);
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

    @GetMapping("/v1/group/{group-id}/personal-schedule")
    public ResponseEntity<List<Schedule>> getGroupSchedule(@PathVariable("group-id") int gid) throws Exception{
        List<Schedule> list = null;
        try {
            System.out.println("개인 스케쥴 검색");
            list = scheduleService.getGroupSchedule(gid);
            System.out.println(list);
            return new ResponseEntity<List<Schedule>>(list,HttpStatus.OK);
        }catch(Exception e) {
            return new ResponseEntity<List<Schedule>>(list,HttpStatus.NO_CONTENT);
        }

    }

    @PostMapping("/v1/group/group-schedule/date")
    public ResponseEntity<List<Schedule>> getGroupScheduleByDate(@RequestBody ScheduleDate scheduleDate) throws Exception{
        List<Schedule> list = null;
        try {
            System.out.println("그룹 스케쥴 날짜에 따라 검색");
            System.out.println(scheduleDate);
            list = scheduleService.getGroupScheduleByDate(scheduleDate);
            System.out.println(list);
            return new ResponseEntity<List<Schedule>>(list,HttpStatus.OK);
        }catch(Exception e) {
            return new ResponseEntity<List<Schedule>>(list,HttpStatus.NO_CONTENT);
        }

    }
    
    @GetMapping("/v1/user/{userId}/to-do-list")
    public ResponseEntity<List<Schedule>> getTodoLists(@PathVariable int userId) throws Exception{
        List<Schedule> list = null;
        try{
            list = scheduleService.getTodoLists(userId);    
            System.out.println(list.get(0));
            return new ResponseEntity<List<Schedule>>(list,HttpStatus.OK); 
        }catch(Exception e){
            return new ResponseEntity<List<Schedule>>(list,HttpStatus.NO_CONTENT); 
        }
    }

    @PostMapping("/v1/to-do-list")
    public ResponseEntity<Schedule> addTodo(@RequestBody Schedule schedule) throws Exception{
        try{
            System.out.println("todolist 추가");
            int result = scheduleService.addTodo(schedule);
            if(result == 1){
                return new ResponseEntity<Schedule>(schedule, HttpStatus.OK);
            }else{
                return new ResponseEntity(HttpStatus.NO_CONTENT);
            }
        }catch(Exception e){
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
    }

    @PutMapping("/v1/to-do-list/{id}")
    public ResponseEntity updateTodoState(@PathVariable int id ,@RequestParam("is-completed") int isCompleted) throws Exception{
        try{
            System.out.println("todolist update");
            Schedule schedule = new Schedule();
            schedule.setId(id);
            schedule.setIsCompleted(isCompleted);
            int result = scheduleService.updateTodoState(schedule);
            System.out.println("todolist update result " + result);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @DeleteMapping("/v1/to-do-list/{id}")
    public ResponseEntity deleteTodo(@PathVariable int id) throws Exception{
        try{
            System.out.println("todolist delete");
            int result = scheduleService.deleteTodo(id);
            System.out.println("todolist delete result " + result);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @DeleteMapping("/v1/to-do-list")
    public ResponseEntity deleteCompleteToDo() throws Exception{
        try{
            System.out.println("complete todolist delete");
            int result = scheduleService.deleteCompleteTodo();
            System.out.println("complete todolist delete result " + result);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @PutMapping("/v1/to-do-list")
    public ResponseEntity todoListToSchedule(@RequestBody Schedule schedule) throws Exception{
        try{
            System.out.println("todolist update");
            int result = scheduleService.todoListToSchedule(schedule);
            System.out.println("todolist update result " + result);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
	}

	@GetMapping("/v1/ics")
    public ResponseEntity ics(@RequestParam String url) {
        try {

            List<Schedule> schedules = new ArrayList<>();
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(
                            new URL(url)
                                    .openStream()));
            String str;
            boolean stFlag = false, etFlag = false, cFlag = false, tFlag = false;
            String startTime = "", endTime = "", content = "", title = "";
            while ((str = br.readLine()) != null) {
                String[] strSplit = str.split(":");
                if ("DTSTART".equals(strSplit[0])) {
                    stFlag = true;
                    if (strSplit.length >= 2) {
                        String dateStr = strSplit[1];
                        LocalDateTime ldt = LocalDateTime.of(
                                Integer.parseInt(dateStr.substring(0, 4)),
                                Integer.parseInt(dateStr.substring(4, 6)),
                                Integer.parseInt(dateStr.substring(6, 8)),
                                Integer.parseInt(dateStr.substring(9, 11)),
                                Integer.parseInt(dateStr.substring(11, 13)),
                                Integer.parseInt(dateStr.substring(13, 15))).plusHours(9);
                        ;
                        startTime = ldt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                    }
                } else if ("DTEND".equals(strSplit[0])) {
                    etFlag = true;
                    if (strSplit.length >= 2) {
                        String dateStr = strSplit[1];
                        LocalDateTime ldt = LocalDateTime.of(
                                Integer.parseInt(dateStr.substring(0, 4)),
                                Integer.parseInt(dateStr.substring(4, 6)),
                                Integer.parseInt(dateStr.substring(6, 8)),
                                Integer.parseInt(dateStr.substring(9, 11)),
                                Integer.parseInt(dateStr.substring(11, 13)),
                                Integer.parseInt(dateStr.substring(13, 15))).plusHours(9);
                        ;
                        endTime = ldt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                    }
                } else if ("SUMMARY".equals(strSplit[0])) {
                    tFlag = true;
                    if (strSplit.length >= 2) title = strSplit[1];
                } else if ("DESCRIPTION".equals(strSplit[0])) {
                    cFlag = true;
                    if (strSplit.length >= 2) content = strSplit[1];
                }
                if (stFlag && etFlag && tFlag && cFlag) {
                    schedules.add(Schedule.builder().title(title).content(content).startTime(startTime).endTime(endTime).build());
                    stFlag = etFlag = tFlag = cFlag = false;
                }

            }
            return new ResponseEntity(schedules, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }
}
