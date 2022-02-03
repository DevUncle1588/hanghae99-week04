package com.sparta.week03board.repository;

import com.sparta.week03board.model.Reply;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReplyRepository extends JpaRepository<Reply, Long> {
    List<Reply> findAllByUserId(Long userId);
    List<Reply> findAllByBoardId(Long boardId);
    List<Reply> findAllByBoardIdOrderByCreatedAtDesc(Long boardId);
}
