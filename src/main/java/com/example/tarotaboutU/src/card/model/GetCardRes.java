package com.example.tarotaboutU.src.card.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GetCardRes {
    private int tarotId;
    private String tarotName_e;
    private String tarotName_k;
    private String tarotUrlImage;
    private String meaning;
    private String yesOrNo;
    private String property;
    private String todayTarot;
    private String loveTarot;
}
