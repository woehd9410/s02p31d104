package com.help.back.backend.dao;

import com.help.back.backend.domain.Favorite;
import com.help.back.backend.domain.User;

import java.util.List;

public interface FavoriteDao {
    List<User> getFavoritesByToId(int toId);
    int postFavorite(Favorite favorite);
    int deleteFavorite(Favorite favorite);
}
