package com.sparta.week03board.dto;

import lombok.Getter;

@Getter
public class ReplyRequestDto {
    private String username;
    private String contents;
    private Long boardId;
    private String userId;
}