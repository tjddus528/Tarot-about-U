package com.example.tarotaboutU.src.diary.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TarotResult {
    private int tarotId;
    private String tarotName_e;
    private String tarotUrlImage;
    private String yesOrNo;
    private String todayTarot;
    private String loveTarot;
}
