package com.example.tarotaboutU.src.diary.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
public class GetDiaryRes {
    private int diaryId;
    private int userId;
    private Date createDate;
    private String title;
    private String content;
    private String status;
}
