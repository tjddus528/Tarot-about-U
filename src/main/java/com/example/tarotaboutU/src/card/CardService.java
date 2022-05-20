package com.example.tarotaboutU.src.card;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CardService {
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final CardDao cardDao;
    private final CardProvider cardProvider;
    @Autowired
    public CardService(CardDao cardDao, CardProvider cardProvider) {
        this.cardDao = cardDao;
        this.cardProvider = cardProvider;
    }
}
