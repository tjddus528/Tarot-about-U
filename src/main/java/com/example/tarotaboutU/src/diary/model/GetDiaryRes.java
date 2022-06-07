package com.example.tarotaboutU.src.diary.model;

import com.example.tarotaboutU.src.card.model.GetCardRes;
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
    private String oneOrSet;
    private int tarotId;
    private int setId;
    private int questionId;
}
