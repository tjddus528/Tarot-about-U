package com.example.tarotaboutU.src.inventory.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
public class GetTarotListReq {
    private int userId;
    private int questionId;
    private Date pickDate;
}
