package com.help.back.backend.service;

import com.help.back.backend.dao.FavoriteDao;
import com.help.back.backend.domain.Favorite;
import com.help.back.backend.domain.User;
import com.help.back.backend.dto.ResultUser;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class FavoriteServiceImpl implements FavoriteService {

    private final FavoriteDao favoriteDao;

    @Override
    @Transactional(readOnly = true)
    public List<ResultUser> getFavoritesByToId(int toId) {
        return favoriteDao.getFavoritesByToId(toId);
    }

    @Override
    @Transactional
    public int postFavorite(Favorite favorite) {
        if(favorite.getToUserId() == favorite.getFromUserId()) return 0;
        return favoriteDao.postFavorite(favorite);
    }

    @Override
    @Transactional
    public int deleteFavorite(Favorite favorite) {
        return favoriteDao.deleteFavorite(favorite);
    }
}
