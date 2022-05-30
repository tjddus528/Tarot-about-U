package com.example.tarotaboutU.src.diary.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
public class PatchDiaryReq {
    private int diaryId;
    private int userId;
    private String title;
    private String content;
}
