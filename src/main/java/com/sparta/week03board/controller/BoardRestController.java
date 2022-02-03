package com.sparta.week03board.controller;

import com.sparta.week03board.model.Board;
import com.sparta.week03board.model.UserRoleEnum;
import com.sparta.week03board.repository.BoardRepository;
import com.sparta.week03board.dto.BoardRequestDto;
import com.sparta.week03board.security.UserDetailsImpl;
import com.sparta.week03board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class BoardRestController {
    private final BoardService boardService;

    @Autowired
    public BoardRestController(BoardService boardService) {
        this.boardService = boardService;
    }

    @GetMapping("/api/posts")
//    public List<Board> readPosts() {
    public List<Board> readPosts(Model model, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        System.out.println(userDetails==null);
//      Long userId = userDetails.getUser().getId();

        return boardService.getAllBoards();
//        return boardService.getBoard(userId);
    }


    @PostMapping("/api/posts")
    public Board createBoard(@RequestBody BoardRequestDto requestDto,
                             @AuthenticationPrincipal UserDetailsImpl userDetails) {
        Long userId = userDetails.getUser().getId();
        Board board = boardService.createBoard(requestDto, userId);
        return board;
    }


    @GetMapping("/api/posts/{targetId}")
    public Optional<Board> readOne(@PathVariable Long targetId) {
        System.out.println("readOnetargetId"+targetId);
        Optional<Board> board = boardService.getOnePost(targetId);
//        List<Board> board = boardService.getBoard(targetId);
        return board;
    }



    // (관리자용) 등록된 모든 상품 목록 조회
    @GetMapping("/api/admin/posts")
    public List<Board> getAllBoards() {
        return boardService.getAllBoards();
    }


    @PutMapping("/api/board/{targetId}")
    public Long updateBoard(@PathVariable Long targetId, @RequestBody BoardRequestDto requestDto) {
        Board board = boardService.updateBoard(targetId, requestDto);

        // 응답 보내기 (업데이트된 상품 id)
        return board.getId();

    }


    @DeleteMapping("/delete/{id}")
    public Long deleteBoard(@PathVariable Long id) {
        boardService.deletePost(id);
        return id;
    }

}
