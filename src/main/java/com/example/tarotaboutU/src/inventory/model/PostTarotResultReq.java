package com.example.tarotaboutU.src.inventory.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Setter
@Getter
@NoArgsConstructor
public class PostTarotResultReq {
    private int questionId;
    private String oneOrSet;
    private int tarotId;
    private int setId;
    private Date pickDate;
}
