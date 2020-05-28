package com.help.back.backend.service;

import com.help.back.backend.domain.Board;

import java.util.List;

public interface BoardService {
    int postBoard(Board board);
    int deleteBoard(int id);
    int updateBoard(Board board);
    List<Board> getAllBoard();
    Board getBoardById(int id);
    List<Board> getBoardByEmail(String email);
}
