package com.help.back.backend.web;


import com.help.back.backend.domain.Board;
import com.help.back.backend.service.BoardService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = {"1. Board"})
@RequiredArgsConstructor
@RequestMapping("/api")
@CrossOrigin(origins = "*")
@RestController
public class BoardController {

    @Autowired
    private BoardService boardService;

    @ApiOperation(value = "게시판 조회", notes = "검색조건에 맞는 게시글를 조회합니다.")
    @GetMapping("/v1/board")
    public ResponseEntity<List<Board>> getBoard() throws Exception{
        List<Board> list = null;
        try {
            list = boardService.getAllBoard();

            System.out.println(list);
            return new ResponseEntity<List<Board>>(list, HttpStatus.OK);
        }catch(Exception e) {
            return new ResponseEntity<List<Board>>(list,HttpStatus.NO_CONTENT);
        }
    }

    @ApiOperation(value = "게시글 상세 조회", notes = "하나의 게시글을 상세 조회")
    @GetMapping("/v1/board/{id}")
    public ResponseEntity<Board> getBoardById(@PathVariable("id") int id) throws Exception{
        Board board = null;
        try {
            board = boardService.getBoardById(id);
            if(board != null){
                System.out.println(board);
                return new ResponseEntity<Board>(board, HttpStatus.OK);
            }else {
                return new ResponseEntity(HttpStatus.NO_CONTENT);
            }
        }catch(Exception e) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
    }

    @ApiOperation(value = "게시글 이메일 조회", notes = "이메일로 게시글을 조회")
    @GetMapping("/v1/board/email/{email}")
    public ResponseEntity<List<Board>> getBoardByEmail(@PathVariable("email") String email) throws Exception{
        List<Board> list = null;
        try {
            list = boardService.getBoardByEmail(email);
            if(list != null){
                System.out.println(list);
                return new ResponseEntity<List<Board>>(list, HttpStatus.OK);
            }else {
                return new ResponseEntity(HttpStatus.NO_CONTENT);
            }
        }catch(Exception e) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
    }

    @ApiOperation(value = "게시글 추가", notes = "게시글을 추가합니다.")
    @PostMapping("/v1/board")
    public ResponseEntity<Board> postBoard(@RequestBody Board board) throws Exception{
        try {
            System.out.println("게시글 추가");
            System.out.println(board.toString());
            int ans = boardService.postBoard(board);
            if(ans == 1){
                System.out.println("추가 : " + board.toString());
                return new ResponseEntity<Board>(board,HttpStatus.OK);
            }else{
                return new ResponseEntity(HttpStatus.NO_CONTENT);
            }
        }catch(Exception e) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
    }

    @ApiOperation(value = "게시글 수정", notes = "게시글을 수정합니다.")
    @PutMapping("/v1/board")
    public ResponseEntity updateBoard(@RequestBody Board board) throws Exception{
        try {
            System.out.println("유저 수정");
            System.out.println(board.toString());
            int ans = boardService.updateBoard(board);
            if(ans == 1) {
                System.out.println("수정 성공  : " + ans);
                return new ResponseEntity(HttpStatus.OK);
            }else{
                return new ResponseEntity(HttpStatus.NO_CONTENT);
            }
        }catch(Exception e) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
    }

    @ApiOperation(value = "게시글 삭제", notes = "게시글을 삭제합니다.")
    @DeleteMapping("/v1/board/{id}")
    public ResponseEntity deleteBoard(@PathVariable("id") int id) throws Exception{
        try {
            System.out.println("게시글 삭제");
            System.out.println(id);
            int ans = boardService.deleteBoard(id);
            if(ans == 1) {
                System.out.println("삭제 성공  : " + ans);
                return new ResponseEntity(HttpStatus.OK);
            }else{
                return new ResponseEntity(HttpStatus.NO_CONTENT);
            }
        }catch(Exception e) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
    }

}
