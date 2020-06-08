package com.help.back.backend.web;

import com.help.back.backend.config.security.JwtService;
import com.help.back.backend.domain.Group;
import com.help.back.backend.domain.Mail;
import com.help.back.backend.dto.Login;
import com.help.back.backend.domain.User;
import com.help.back.backend.dto.ResultGroup;
import com.help.back.backend.dto.ResultLogin;
import com.help.back.backend.dto.ResultUser;
import com.help.back.backend.service.MailService;
import com.help.back.backend.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = {"1. User"})
@CrossOrigin(origins = "*")
@RestController
public class UserController {

    @Autowired
    JwtService jwtService;

    @Autowired
    UserService userService;

    @Autowired
    MailService mailService;

    @ApiOperation(value = "유저 조회", notes = "검색조건에 맞는 유저를 조회합니다.")
    @GetMapping("/api/v1/user")
    public ResponseEntity<List<ResultUser>> getUserByName(@RequestParam(value = "id", required = false) String id, @RequestParam(value = "email", required = false) String email, @RequestParam(value = "name", required = false) String name) throws Exception{
        List<ResultUser> list = null;
        try {
            if(id == null && email == null && name != null) {
                System.out.println("유저 name으로 검색");
                list = userService.getUsersByName(name);
            }else if(id == null && email != null && name == null){
                System.out.println("유저 email로 검색");
                list = userService.getUsersByEmail(email);
            }else if(id != null && email == null && name == null){
                System.out.println("유저 id로 검색");
                list = userService.getUsersById(Integer.parseInt(id));
            }else if(id == null && email == null && name == null){
                System.out.println("유저 전체 조회");
                list = userService.getUsers();
            }
            System.out.println(list);
            return new ResponseEntity<List<ResultUser>>(list,HttpStatus.OK);
        }catch(Exception e) {
            return new ResponseEntity<List<ResultUser>>(list,HttpStatus.NO_CONTENT);
        }
    }

    @ApiOperation(value = "유저 추가", notes = "유저 정보를 추가합니다.")
    @PostMapping("/api/v1/user")
    public ResponseEntity<User> postUser(@RequestBody User user) throws Exception{
        try {
            System.out.println("유저 추가");
            System.out.println(user.toString());
            int ans = userService.postUser(user);
            if(ans == 1){
                System.out.println("추가 : " + user.toString());
                return new ResponseEntity<User>(user,HttpStatus.OK);
            }else{
                return new ResponseEntity(HttpStatus.NO_CONTENT);
            }
        }catch(Exception e) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
    }

    @ApiOperation(value = "유저 수정", notes = "유저를 수정합니다.")
    @PutMapping("/api/v1/user")
    public ResponseEntity updateUser(@RequestBody User user) throws Exception{
        try {
            System.out.println("유저 수정");
            System.out.println(user.toString());
            int ans = userService.updateUser(user);
            System.out.println("수정 성공  : " + ans);
            return new ResponseEntity(HttpStatus.OK);
        }catch(Exception e) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
    }

    @ApiOperation(value = "유저 삭제", notes = "유저를 삭제합니다.")
    @DeleteMapping("/api/v1/user")
    public ResponseEntity deleteUser(@RequestParam("user_id") int user_id) throws Exception{
        try {
            System.out.println("유저 삭제");
            System.out.println(user_id);
            int ans = userService.deleteUser(user_id);
            System.out.println("삭제 성공  : " + ans);
            return new ResponseEntity(HttpStatus.OK);
        }catch(Exception e) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
    }

    @ApiOperation(value = "유저 로그인", notes = "유저 email & password를 통해 로그인 확인 ")
    @PostMapping("/api/v1/user/login")
    public ResponseEntity<ResultLogin> login(@RequestBody Login login) throws Exception{
        ResultUser user = null;
        ResultLogin result = ResultLogin.successInstance();
        try {
            System.out.println("유저 로그인");
            System.out.println(login.toString());
            user = userService.login(login);
            System.out.println(user);
            if(user != null){
                String jwt = jwtService.create("user", user, user.getEmail());
                result.setToken(jwt);
                result.setData(user);
                System.out.println(result);
                return new ResponseEntity<ResultLogin>(result,HttpStatus.OK);
            }else{
                return new ResponseEntity(HttpStatus.NO_CONTENT);
            }
        }catch(Exception e) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
    }

    @ApiOperation(value = "유저 카카오 로그인", notes = "카카오 로그인 email 확인 ")
    @GetMapping("/api/v1/user/kakao-login")
    public ResponseEntity<ResultLogin> kakaologin(@RequestParam String email) throws Exception{
        List<ResultUser> user = null;
        ResultLogin result = ResultLogin.successInstance();
        try {
            System.out.println("카카오 로그인");
            user = userService.getUsersByEmail(email);
            System.out.println(user);
            if(user != null){
                ResultUser ans = user.get(0);
                String jwt = jwtService.create("user", ans, ans.getEmail());
                result.setToken(jwt);
                result.setData(ans);
                System.out.println(result);
                return new ResponseEntity<ResultLogin>(result,HttpStatus.OK);
            }else {
                return new ResponseEntity(HttpStatus.NO_CONTENT);
            }
        }catch(Exception e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @ApiOperation(value = "유저 pw 수정", notes = "유저의 pw를 수정합니다.")
    @PutMapping("/api/v1/user/password")
    public ResponseEntity updateUserPassword(@RequestBody Login user) throws Exception{
        try {
            System.out.println("유저 pw 수정");
            System.out.println(user.toString());
            int ans = userService.updateUserPassword(user);
            System.out.println("수정 성공  : " + ans);
            return ans == 1 ? new ResponseEntity(HttpStatus.OK) : new ResponseEntity(HttpStatus.NO_CONTENT);
        }catch(Exception e) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
    }

	@ApiOperation(value = "이메일 인증", notes = "이메일 인증")
    @PostMapping("/api/v1/user/email/{email}")
    public ResponseEntity<String> emailAuth(@PathVariable("email") String email) throws Exception{
        try {
            System.out.println("이메일인증");
            String key = mailService.mailSend(email);
            System.out.println("성공 인증 키 : " + key);

            return new ResponseEntity<String>(key, HttpStatus.OK);

        }catch(Exception e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }
	@ApiOperation(value = "그룹 리스트 가져오기", notes = "그룹 리스트 가져오기")
    @GetMapping("/api/v1/user/{id}/group")
    public ResponseEntity<String> searchGroupByUserId(@PathVariable String id) throws Exception{

        List<ResultGroup> list = null;
        list = userService.searchGroupByUserId(Integer.parseInt(id));
        if(list == null) return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity(list,HttpStatus.OK );

    }


}
