package com.example.tarotaboutU.src.inventory.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
public class GetTarotListRes {
    private int pickId;
    private int userId;
    private int questionId;
    private Date pickDate;
    private int tarotId;
    private String tarotName_e;
    private String tarotUrlImage;
    private String yesOrNo;
    private String todayTarot;
    private String mindTarot;
}
