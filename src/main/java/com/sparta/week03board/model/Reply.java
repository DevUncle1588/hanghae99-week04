package com.sparta.week03board.model;

import com.sparta.week03board.dto.BoardRequestDto;
import com.sparta.week03board.dto.ReplyRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter // get 함수를 일괄적으로 만들어줍니다.
@NoArgsConstructor // 기본 생성자를 만들어줍니다.
@Entity // DB 테이블 역할을 합니다.
public class Reply extends Timestamped{

    // ID가 자동으로 생성 및 증가합니다.
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @Column(nullable = true)
    private String username;

    @Column(nullable = false)
    private String contents;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private Long boardId;

    public Reply(ReplyRequestDto requestDto) {
        this.username = requestDto.getUsername();
        this.contents = requestDto.getContents();
    }

    public Reply(ReplyRequestDto requestDto, Long userId) {
        this.userId = userId;
        this.username = requestDto.getUsername();
        this.contents = requestDto.getContents();
        this.boardId = requestDto.getBoardId();
    }

    public void update(ReplyRequestDto requestDto) {
        this.userId = userId;
        this.username = requestDto.getUsername();
        this.contents = requestDto.getContents();
    }
}