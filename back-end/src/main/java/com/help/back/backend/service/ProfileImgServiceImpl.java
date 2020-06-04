package com.help.back.backend.service;

import com.help.back.backend.dao.ProfileImgDao;
import com.help.back.backend.domain.Image;
import com.help.back.backend.domain.ProfileImg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfileImgServiceImpl implements ProfileImgService{

    @Autowired
    private ProfileImgDao profileImgDao;

    @Override
    public int postUserProfileImg(ProfileImg profileImg) {
        return profileImgDao.postUserProfileImg(profileImg);
    }

    @Override
    public int updateUserProfileImg(ProfileImg profileImg) {
        return profileImgDao.updateUserProfileImg(profileImg);
    }

    @Override
    public int deleteUserProfileImg(int id) {
        return profileImgDao.deleteUserProfileImg(id);
    }

    @Override
    public ProfileImg getUserProfileImg(int id) {
        return profileImgDao.getUserProfileImg(id);
    }

    @Override
    public int postGroupProfileImg(ProfileImg profileImg) {
        return profileImgDao.postGroupProfileImg(profileImg);
    }

    @Override
    public int updateGroupProfileImg(ProfileImg profileImg) {
        return profileImgDao.updateGroupProfileImg(profileImg);
    }

    @Override
    public int deleteGroupProfileImg(int id) {
        return profileImgDao.deleteGroupProfileImg(id);
    }

    @Override
    public ProfileImg getGroupProfileImg(int id) {
        return profileImgDao.getGroupProfileImg(id);
    }

    @Override
    public int postDepartmentProfileImg(ProfileImg profileImg) {
        return profileImgDao.postDepartmentProfileImg(profileImg);
    }

    @Override
    public int updateDepartmentProfileImg(ProfileImg profileImg) {
        return profileImgDao.updateDepartmentProfileImg(profileImg);
    }

    @Override
    public int deleteDepartmentProfileImg(int id) {
        return profileImgDao.deleteDepartmentProfileImg(id);
    }

    @Override
    public ProfileImg getDepartmentProfileImg(int id) {
        return profileImgDao.getDepartmentProfileImg(id);
    }

    @Override
    public List<Image> getAllImg() {
        return profileImgDao.getAllImg();
    }


}
