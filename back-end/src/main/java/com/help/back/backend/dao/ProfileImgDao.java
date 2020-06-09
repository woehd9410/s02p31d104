package com.help.back.backend.dao;

import com.help.back.backend.domain.Image;
import com.help.back.backend.domain.ProfileImg;

import java.util.List;

public interface ProfileImgDao {
    int postUserProfileImg(ProfileImg profileImg);
    int updateUserProfileImg(ProfileImg profileImg);
    int deleteUserProfileImg(int id);
    ProfileImg getUserProfileImg(int id);
    int postGroupProfileImg(ProfileImg profileImg);
    int updateGroupProfileImg(ProfileImg profileImg);
    int deleteGroupProfileImg(int id);
    ProfileImg getGroupProfileImg(int id);
    int postDepartmentProfileImg(ProfileImg profileImg);
    int updateDepartmentProfileImg(ProfileImg profileImg);
    int deleteDepartmentProfileImg(int id);
    ProfileImg getDepartmentProfileImg(int id);
    List<Image> getAllImg();

}
