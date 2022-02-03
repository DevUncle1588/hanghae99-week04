package com.sparta.week03board.repository;

import com.sparta.week03board.model.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {
    List<Board> findAllByUserId(Long userId);
    List<Board> findAllByOrderByCreatedAtDesc();
}
