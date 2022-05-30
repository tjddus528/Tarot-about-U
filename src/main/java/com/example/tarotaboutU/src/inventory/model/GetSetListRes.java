package com.example.tarotaboutU.src.inventory.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
public class GetSetListRes {
    private int pickId;
    private int userId;
    private int questionId;
    private Date pickDate;
    private int setId;
    private String setSummary;
    private Object setInfo;
    private String mindTarot;
}
