package com.help.back.backend.web;

import com.help.back.backend.domain.Group;
import com.help.back.backend.domain.GroupUser;
import com.help.back.backend.domain.User;
import com.help.back.backend.dto.GroupDto;
import com.help.back.backend.dto.ResultUser;
import com.help.back.backend.service.GroupService;
import com.help.back.backend.service.UserService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(tags = { "1. Group" })
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping("/api")
@RestController
public class GroupController {

    @Autowired
    private UserService userService;

    @Autowired
    private GroupService groupService;

    @GetMapping("v1/group/{id}")
    public ResponseEntity<Group> getGroup(@PathVariable("id") int id) throws Exception {
        Group group = null;
        try {
            System.out.println("그룹 조회");
            System.out.println(id);
            group = groupService.getGroupHavingUser(id);
            if (group != null) {
                System.out.println(group.toString());
                return new ResponseEntity<Group>(group, HttpStatus.OK);
            } else {
                return new ResponseEntity(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
    }

    @Transactional
    @PostMapping("v1/group")
    public ResponseEntity<Group> postGroup(@RequestBody GroupDto groupDto) throws Exception{
        try {
            System.out.println("그룹 추가");
            System.out.println(groupDto.toString());
            Group group = new Group();
            group.setName(groupDto.getName());
            int ans = groupService.postGroup(group);
            if (ans == 1) {
                System.out.println("그룹 추가 성공 : " + group.toString());
                for(String str : groupDto.getMembers()){
                    GroupUser groupUser = new GroupUser();
                    if(str.indexOf("#") > 0){
                        groupUser.setGroupId(group.getId());
                        String[] arr = str.split("#");
                        groupUser.setUserId(Integer.parseInt(arr[1]));
                        groupUser.setIsHost(0);
                        groupService.postGroupUser(groupUser);
                    }else if(str.indexOf("@") > 0){
                        groupUser.setGroupId(group.getId());
                        List<ResultUser> user = userService.getUsersByEmail(str);
                        groupUser.setUserId(user.get(0).getId());
                        groupUser.setIsHost(0);
                        groupService.postGroupUser(groupUser);
                    }
                }
                return new ResponseEntity<Group>(group, HttpStatus.OK);
                }else{
                    return new ResponseEntity(HttpStatus.NO_CONTENT);
                }

        }catch(Exception e) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
    }

    @DeleteMapping("v1/group/{id}")
    public ResponseEntity deleteGroup(@PathVariable("id") int id) throws Exception {
        try {
            System.out.println("그룹 삭제");
            System.out.println(id);
            int ans = groupService.deleteGroup(id);
            if (ans == 1) {
                System.out.println("삭제성공");
                return new ResponseEntity(HttpStatus.OK);
            } else {
                return new ResponseEntity(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
    }

    @PutMapping("v1/group")
    public ResponseEntity updateGroup(@RequestBody Group group) throws Exception {
        try {
            System.out.println("그룹 수정");
            System.out.println(group.toString());
            int ans = groupService.updateGroup(group);
            if (ans == 1) {
                System.out.println("수정성공");
                return new ResponseEntity(HttpStatus.OK);
            } else {
                return new ResponseEntity(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping("v1/group-user")
    public ResponseEntity<GroupUser> postGroupUser(@RequestBody GroupUser groupUser) throws Exception {
        try {
            System.out.println("그룹 멤버 추가");
            System.out.println(groupUser.toString());
            int ans = groupService.postGroupUser(groupUser);
            System.out.println(ans);
            if (ans == 1) {
                System.out.println("추가 : " + groupUser.toString());
                return new ResponseEntity<GroupUser>(groupUser, HttpStatus.OK);
            } else {
                return new ResponseEntity(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
    }

    @DeleteMapping("v1/group-user")
    public ResponseEntity<GroupUser> deleteGroupUser(@RequestParam("user-id") String uid,
            @RequestParam("group-id") String gid) throws Exception {
        try {
            System.out.println("그룹 멤버 삭제");
            System.out.println("user-id : " + uid + " group-id : " + gid);
            Map map = new HashMap();

            map.put("user-id", uid); // 가져온 데이터에 키와 벨류값을 지정
            map.put("group-id", gid);
            int ans = groupService.deleteGroupUser(map);
            if (ans == 1) {
                System.out.println("삭제 성공 ");
                return new ResponseEntity(HttpStatus.OK);
            } else {
                return new ResponseEntity(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
    }

    @PutMapping("v1/group-user")
    public ResponseEntity<GroupUser> updateGroupUser(@RequestBody GroupUser groupUser) throws Exception {
        try {
            System.out.println("그룹 멤버 수정");
            System.out.println(groupUser.toString());

            int ans = groupService.updateGroupUser(groupUser);
            if (ans == 1) {
                System.out.println("수정 성공 ");
                return new ResponseEntity(HttpStatus.OK);
            } else {
                return new ResponseEntity(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("v1/group-user/{group-id}")
    public ResponseEntity<List<GroupUser>> getGroupUser(@PathVariable("group-id") int id) throws Exception {
        List<GroupUser> list = null;
        try {
            System.out.println("그룹 멤버 조회");
            list = groupService.getGroupUser(id);
            if (list != null) {
                System.out.println(list);
                return new ResponseEntity<List<GroupUser>>(list, HttpStatus.OK);
            } else {
                return new ResponseEntity(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("v1/group-user/id/{user-id}")
    public ResponseEntity<List<GroupUser>> getGroupUserByUserid(@PathVariable("user-id") int id) throws Exception {
        List<GroupUser> list = null;
        try {
            System.out.println("그룹 조회");
            list = groupService.getGroupUserByUserid(id);
            if (list != null) {
                System.out.println(list);
                return new ResponseEntity<List<GroupUser>>(list, HttpStatus.OK);
            } else {
                return new ResponseEntity(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
    }
}
