package com.help.back.backend.web;

import com.help.back.backend.domain.Schedule;
import com.help.back.backend.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class ScheduleController {
    
    @Autowired
    ScheduleService scheduleService;
    
    @GetMapping("/todo/get")
    public ResponseEntity<List<Schedule>> getTodoLists() throws Exception{
        List<Schedule> list = null;
        try{
            list = scheduleService.getTodoLists();    
            return new ResponseEntity<List<Schedule>>(list,HttpStatus.OK); 
        }catch(Exception e){
            return new ResponseEntity<List<Schedule>>(list,HttpStatus.NO_CONTENT); 
        }
    }

    @PostMapping("/todo/add")
    public ResponseEntity addTodo(@RequestBody Schedule schedule) throws Exception{
        try{
            System.out.println("todolist 추가");
            int result = scheduleService.addTodo(schedule);
            System.out.println("todolist 추가 결과" + result);
            return new ResponseEntity(HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
    }

    @PutMapping("/todo/update")
    public ResponseEntity updateTodo(@RequestBody Schedule schedule) throws Exception{
        try{
            System.out.println("todolist update");
            String[] startTemp = schedule.getStartTime().split("T");
            String startTemp1 = startTemp[1].substring(0,8);
            schedule.setStartTime(startTemp[0] + " " + startTemp1);
            String[] endTemp = schedule.getEndTime().split("T");
            String endTemp1 = endTemp[1].substring(0,8);
            schedule.setEndTime(endTemp[0] + " " + endTemp1);
            
            int result = scheduleService.updateTodo(schedule);
            System.out.println("todolist update result " + result);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @DeleteMapping("/todo/delete/{id}")
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
}