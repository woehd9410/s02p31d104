package com.help.back.backend.service;

import com.help.back.backend.dao.BoardDao;
import com.help.back.backend.domain.Board;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardServiceImpl implements BoardService {

    @Autowired
    private BoardDao boardDao;

    @Override
    public int postBoard(Board board) {
        return boardDao.postBoard(board);
    }

    @Override
    public int deleteBoard(int id) {
        return boardDao.deleteBoard(id);
    }

    @Override
    public int updateBoard(Board board) {
        return boardDao.updateBoard(board);
    }

    @Override
    public List<Board> getAllBoard() {
        return boardDao.getAllBoard();
    }

    @Override
    public Board getBoardById(int id) {
        return boardDao.getBoardById(id);
    }

    @Override
    public List<Board> getBoardByEmail(String email) {
        return boardDao.getBoardByEmail(email);
    }
}
