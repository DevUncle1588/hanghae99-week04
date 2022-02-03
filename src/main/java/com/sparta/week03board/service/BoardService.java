package com.sparta.week03board.service;

import com.sparta.week03board.model.Board;
import com.sparta.week03board.repository.BoardRepository;
import com.sparta.week03board.dto.BoardRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class BoardService {

    private final BoardRepository boardRepository;

    @Autowired
    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    public Board createBoard(BoardRequestDto requestDto, Long userId ) {
// 요청받은 DTO 로 DB에 저장할 객체 만들기
        Board board = new Board(requestDto, userId);

        boardRepository.save(board);

        return board;
    }

//    @Transactional
//    public Long update(Long id, BoardRequestDto requestDto) {
//        Board board = boardRepository.findById(id).orElseThrow(
//                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
//        );
//        board.update(requestDto);
//        return board.getId();
//    }

    public Board updateBoard(Long id, BoardRequestDto requestDto) {
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new NullPointerException("해당 아이디가 존재하지 않습니다."));

        board.update(requestDto);
        return board;
    }

    @Transactional
    public void deletePost(Long id) {
        boardRepository.deleteById(id);
    }

    @Transactional
    public Optional getOnePost(Long id) {
        return boardRepository.findById(id);
    }

    public List<Board> getBoard(Long userId) {
        return boardRepository.findAllByUserId(userId);
    }

    public List<Board> getAllBoards() {
        return boardRepository.findAllByOrderByCreatedAtDesc();
    }




//    @Transactional
//    public Long readOnePost(Long id, BoardRequestDto requestDto) {
//        Board board = boardRepository.findById(id).orElseThrow(
//                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
//        );
//        board.update(requestDto);
//        return board.getId();
//    }
}