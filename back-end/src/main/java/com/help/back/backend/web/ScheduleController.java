package com.help.back.backend.web;

import com.help.back.backend.domain.Schedule;
import com.help.back.backend.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class ScheduleController {
    
    @Autowired
    ScheduleService scheduleService;
    
    @GetMapping("/api/v1/todo/{userId}")
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

    @PostMapping("/api/v1/todo")
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

    @PutMapping("/api/v1/todo/state")
    public ResponseEntity updateTodoState(@RequestBody Schedule schedule) throws Exception{
        try{
            System.out.println("todolist update");
            int result = scheduleService.updateTodoState(schedule);
            System.out.println("todolist update result " + result);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @DeleteMapping("api/v1/todo/{id}")
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

    @PutMapping("/api/v1/todo/toSchedule")
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

}