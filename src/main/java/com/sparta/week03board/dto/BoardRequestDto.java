package com.sparta.week03board.dto;

import lombok.Getter;

@Getter
public class BoardRequestDto {
    private String title;
    private String username;
    private String contents;
}