package com.example.tarotaboutU.src.diary.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SetResult {
    private int setId;
    private Object setInfo;
    private String setSummary;
    private String mindTarot;
}
