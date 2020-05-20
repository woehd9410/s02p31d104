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
    
    @GetMapping("/todo")
    public ResponseEntity<List<Schedule>> getTodoLists() throws Exception{
        List<Schedule> list = null;
        System.out.println("들어왔다=======================================");
        try{
            list = scheduleService.getTodoList();    
            System.out.println(list.get(0));
            return new ResponseEntity<List<Schedule>>(list,HttpStatus.OK); 
        }catch(Exception e){
            return new ResponseEntity<List<Schedule>>(list,HttpStatus.NO_CONTENT); 
        }
    }
}