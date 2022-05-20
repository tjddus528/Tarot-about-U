package com.example.tarotaboutU.src.inventory.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
public class GetTarotsPickedByUser {
    private int pickId;
    private int userId;
    private int questionId;
    private String oneOrSet;
    private int tarotId;
    private int setId;
    private Date pickDate;
}
