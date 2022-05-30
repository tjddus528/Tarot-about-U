package com.example.tarotaboutU.src.diary.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class PostDiaryReq {
    private int userId;
    private Date createDate;
    private String title;
    private String content;
}
