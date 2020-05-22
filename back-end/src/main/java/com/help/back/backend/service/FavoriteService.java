package com.help.back.backend.service;

import com.help.back.backend.domain.Favorite;
import com.help.back.backend.domain.User;

import java.util.List;

public interface FavoriteService {
    List<User> getFavoritesByToId(int toId);
    int postFavorite(Favorite favorite);
    int deleteFavorite(Favorite favorite);
}
