package com.help.back.backend.web;

import com.help.back.backend.domain.Image;
import com.help.back.backend.domain.ProfileImg;
import com.help.back.backend.service.ProfileImgService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = {"1. ProfileImg"})
@RequiredArgsConstructor
@RequestMapping("/api")
@CrossOrigin(origins = "*")
@RestController
public class ProfileImgController {

    @Autowired
    private ProfileImgService profileImgService;

    @PostMapping("/v1/profile-img/user")
    public ResponseEntity<ProfileImg> postUserProfileImg(@RequestBody ProfileImg profileImg) throws Exception{
        try {
            System.out.println("개인 프로필 이미지 추가");
            System.out.println(profileImg.toString());
            int ans = profileImgService.postUserProfileImg(profileImg);
            if(ans == 1){
                System.out.println("추가 : " + profileImg.toString());
                return new ResponseEntity<ProfileImg>(profileImg, HttpStatus.OK);
            }else{
                return new ResponseEntity(HttpStatus.NO_CONTENT);
            }
        }catch(Exception e) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
    }

    @DeleteMapping("/v1/profile-img/user/{id}")
    public ResponseEntity deleteUserProfileImg(@PathVariable("id") int id) throws Exception{
        try {
            System.out.println("개인 프로필 이미지 삭제");
            System.out.println(id);
            int ans = profileImgService.deleteUserProfileImg(id);
            if(ans == 1){
                System.out.println("삭제성공 " );
                return new ResponseEntity( HttpStatus.OK);
            }else{
                return new ResponseEntity(HttpStatus.NO_CONTENT);
            }
        }catch(Exception e) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
    }

    @PutMapping("/v1/profile-img/user")
    public ResponseEntity updateUserProfileImg(@RequestBody ProfileImg profileImg) throws Exception{
        try {
            System.out.println("개인 프로필 이미지 수정");
            System.out.println(profileImg.toString());
            int ans = profileImgService.updateUserProfileImg(profileImg);
            if(ans == 1){
                System.out.println("수정성공 " );
                return new ResponseEntity( HttpStatus.OK);
            }else{
                return new ResponseEntity(HttpStatus.NO_CONTENT);
            }
        }catch(Exception e) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/v1/profile-img/user/{id}")
    public ResponseEntity<ProfileImg> getUserProfileImg(@PathVariable("id") int id) throws Exception{
        ProfileImg ans = null;
        try {
            System.out.println("개인 프로필 이미지 조회");
            System.out.println(id);
            ans = profileImgService.getUserProfileImg(id);
            if(ans != null){
                System.out.println("조회 : " + ans );
                return new ResponseEntity<ProfileImg>(ans, HttpStatus.OK);
            }else{
                return new ResponseEntity(HttpStatus.NO_CONTENT);
            }
        }catch(Exception e) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping("/v1/profile-img/group")
    public ResponseEntity<ProfileImg> postGroupProfileImg(@RequestBody ProfileImg profileImg) throws Exception{
        try {
            System.out.println("그룹 프로필 이미지 추가");
            System.out.println(profileImg.toString());
            int ans = profileImgService.postGroupProfileImg(profileImg);
            if(ans == 1){
                System.out.println("추가 : " + profileImg.toString());
                return new ResponseEntity<ProfileImg>(profileImg, HttpStatus.OK);
            }else{
                return new ResponseEntity(HttpStatus.NO_CONTENT);
            }
        }catch(Exception e) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
    }

    @DeleteMapping("/v1/profile-img/group/{id}")
    public ResponseEntity deleteGroupProfileImg(@PathVariable("id") int id) throws Exception{
        try {
            System.out.println("그룹 프로필 이미지 삭제");
            System.out.println(id);
            int ans = profileImgService.deleteGroupProfileImg(id);
            if(ans == 1){
                System.out.println("삭제성공 " );
                return new ResponseEntity( HttpStatus.OK);
            }else{
                return new ResponseEntity(HttpStatus.NO_CONTENT);
            }
        }catch(Exception e) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
    }

    @PutMapping("/v1/profile-img/group")
    public ResponseEntity updateGroupProfileImg(@RequestBody ProfileImg profileImg) throws Exception{
        try {
            System.out.println("그룹 프로필 이미지 수정");
            System.out.println(profileImg.toString());
            int ans = profileImgService.updateGroupProfileImg(profileImg);
            if(ans == 1){
                System.out.println("수정성공 " );
                return new ResponseEntity( HttpStatus.OK);
            }else{
                return new ResponseEntity(HttpStatus.NO_CONTENT);
            }
        }catch(Exception e) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/v1/profile-img/group/{id}")
    public ResponseEntity<ProfileImg> getGroupProfileImg(@PathVariable("id") int id) throws Exception{
        ProfileImg ans = null;
        try {
            System.out.println("그룹 프로필 이미지 조회");
            System.out.println(id);
            ans = profileImgService.getGroupProfileImg(id);
            if(ans != null){
                System.out.println("조회 : " + ans );
                return new ResponseEntity<ProfileImg>(ans, HttpStatus.OK);
            }else{
                return new ResponseEntity(HttpStatus.NO_CONTENT);
            }
        }catch(Exception e) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/v1/profile-img")
    public ResponseEntity<Image> getAllImg(){
        List<Image> images = null;
        images = profileImgService.getAllImg();
        return images == null ? new ResponseEntity(HttpStatus.NO_CONTENT) : new ResponseEntity(images,HttpStatus.OK);
    }


}
