package com.help.back.backend.web;

import com.help.back.backend.domain.Group;
import com.help.back.backend.domain.GroupUser;
import com.help.back.backend.domain.Schedule;
import com.help.back.backend.service.GroupService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api(tags = {"1. Group"})
@RequiredArgsConstructor
@RequestMapping("/api")
@RestController
public class GroupController {

    @Autowired
    private GroupService groupService;

    @PostMapping("v1/group")
    public ResponseEntity<Group> postGroup(@RequestBody Group group) throws Exception{
        try {
            System.out.println("그룹 추가");
            System.out.println(group.toString());
            int ans = groupService.postGroup(group);
            if(ans == 1){
                System.out.println("추가 : " + group.toString());
                return new ResponseEntity<Group>(group, HttpStatus.OK);
            }else{
                return new ResponseEntity(HttpStatus.NO_CONTENT);
            }
        }catch(Exception e) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
    }

    @DeleteMapping("v1/group/{id}")
    public ResponseEntity deleteGroup(@PathVariable("id") int id) throws Exception{
        try {
            System.out.println("그룹 삭제");
            System.out.println(id);
            int ans = groupService.deleteGroup(id);
            if(ans == 1){
                System.out.println("삭제성공");
                return new ResponseEntity( HttpStatus.OK);
            }else{
                return new ResponseEntity(HttpStatus.NO_CONTENT);
            }
        }catch(Exception e) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
    }

    @PutMapping("v1/group")
    public ResponseEntity updateGroup(@RequestBody Group group) throws Exception{
        try {
            System.out.println("그룹 수정");
            System.out.println(group.toString());
            int ans = groupService.updateGroup(group);
            if(ans == 1){
                System.out.println("수정성공");
                return new ResponseEntity( HttpStatus.OK);
            }else{
                return new ResponseEntity(HttpStatus.NO_CONTENT);
            }
        }catch(Exception e) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping("v1/group-user")
    public ResponseEntity<GroupUser> postGroupUser(@RequestBody GroupUser groupUser) throws Exception{
        try {
            System.out.println("그룹 멤버 추가");
            System.out.println(groupUser.toString());
            int ans = groupService.postGroupUser(groupUser);
            System.out.println(ans);
            if(ans == 1){
                System.out.println("추가 : " + groupUser.toString());
                return new ResponseEntity<GroupUser>(groupUser, HttpStatus.OK);
            }else{
                return new ResponseEntity(HttpStatus.NO_CONTENT);
            }
        }catch(Exception e) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
    }
}
