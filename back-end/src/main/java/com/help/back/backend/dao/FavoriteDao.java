package com.help.back.backend.dao;

import com.help.back.backend.domain.Favorite;
import com.help.back.backend.domain.User;
import com.help.back.backend.dto.ResultUser;

import java.util.List;

public interface FavoriteDao {
    List<ResultUser> getFavoritesByToId(int toId);
    int postFavorite(Favorite favorite);
    int deleteFavorite(Favorite favorite);
}
