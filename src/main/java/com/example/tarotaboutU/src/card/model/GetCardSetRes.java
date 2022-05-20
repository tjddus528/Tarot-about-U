package com.example.tarotaboutU.src.card.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.apache.tomcat.util.json.JSONParser;

@Getter
@Setter
@AllArgsConstructor
public class GetCardSetRes {
    private String setId;
    private Object set_info;
    private String mind_tarot;
}
