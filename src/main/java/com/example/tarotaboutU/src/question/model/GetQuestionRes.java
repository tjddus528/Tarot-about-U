package com.example.tarotaboutU.src.question.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GetQuestionRes {
    private int questionId;
    private String questionM;
    private String oneOrSet;
}
