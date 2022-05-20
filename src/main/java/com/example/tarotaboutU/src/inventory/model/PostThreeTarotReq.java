package com.example.tarotaboutU.src.inventory.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
public class PostThreeTarotReq {
    private int ThreetarotPickId;
    private int questionId;
    private int setId;
    private int userId;
    private Date pickDate;
}
