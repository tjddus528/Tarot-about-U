package com.example.tarotaboutU.src.diary.model;

import com.example.tarotaboutU.src.card.model.GetCardRes;
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
    private String oneOrSet; //"one" "set" "null"
    private int tarotId; // 다이어리에 저장된 타로결과(1장) 정보
    private int setId; // 다이어리에 저장된 세트결과(3장) 정보
    private int questionId;
}
