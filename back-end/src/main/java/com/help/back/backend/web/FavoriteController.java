package com.help.back.backend.web;

import com.help.back.backend.domain.Favorite;
import com.help.back.backend.domain.User;
import com.help.back.backend.dto.ResultUser;
import com.help.back.backend.service.FavoriteService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = {"1. Favorite"})
@RequiredArgsConstructor
@RequestMapping("/api")
@CrossOrigin(origins = "*")
@RestController
public class FavoriteController {

    private final FavoriteService favoriteService;

    @GetMapping("/v1/favorite/{toId}")
    public List<ResultUser> getById(@PathVariable int toId) {
        return favoriteService.getFavoritesByToId(toId);
    }

    @PostMapping("/v1/favorite")
    public ResponseEntity post(@RequestBody Favorite favorite) {
        int result = favoriteService.postFavorite(favorite);
        return result > 0 ? new ResponseEntity(HttpStatus.CREATED) : new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/v1/favorite")
    public ResponseEntity delete(@RequestBody Favorite favorite) {
        int result = favoriteService.deleteFavorite(favorite);
        return result > 0 ? new ResponseEntity(HttpStatus.OK) : new ResponseEntity(HttpStatus.BAD_REQUEST);
    }
}
