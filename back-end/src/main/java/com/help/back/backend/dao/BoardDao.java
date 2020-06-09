package com.help.back.backend.dao;

import com.help.back.backend.domain.Board;

import java.util.List;

public interface BoardDao {
    int postBoard(Board board);
    int deleteBoard(int id);
    int updateBoard(Board board);
    List<Board> getAllBoard();
    Board getBoardById(int id);
    List<Board> getBoardByEmail(String email);
}
