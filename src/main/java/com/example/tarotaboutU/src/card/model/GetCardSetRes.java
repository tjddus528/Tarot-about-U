package com.example.tarotaboutU.src.card.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.apache.tomcat.util.json.JSONParser;

@Getter
@Setter
@AllArgsConstructor
public class GetCardSetRes {
    private int setId;
    private String setSummary;
    private Object setInfo;
    private String mindTarot;
}
