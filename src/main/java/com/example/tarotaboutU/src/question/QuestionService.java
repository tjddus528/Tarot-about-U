package com.example.tarotaboutU.src.question;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuestionService {
    final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final QuestionProvider questionProvider;
    private final QuestionDao questionDao;

    @Autowired
    public QuestionService(QuestionDao questionDao, QuestionProvider questionProvider) {
        this.questionDao = questionDao;
        this.questionProvider = questionProvider;
    }
}
