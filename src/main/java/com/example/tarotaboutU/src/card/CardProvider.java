package com.example.tarotaboutU.src.card;

import com.example.tarotaboutU.config.BaseException;
import com.example.tarotaboutU.src.card.model.GetCardRes;
import com.example.tarotaboutU.src.card.model.GetCardSetRes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.tarotaboutU.config.BaseResponseStatus.DATABASE_ERROR;

@Service
public class CardProvider {
    private final CardDao cardDao;
    @Autowired
    public CardProvider(CardDao cardDao) {
        this.cardDao = cardDao;
    }

    public List<GetCardRes> getCardList() throws BaseException {
        try{
            List<GetCardRes> getCardRes = cardDao.getCardList();
            return getCardRes;
        } catch (Exception exception) {
            System.out.println("exception = " + exception);
            throw new BaseException(DATABASE_ERROR);
        }
    }
    public List<GetCardSetRes> getCardSetList() throws BaseException {
        try{
            List<GetCardSetRes> getCardSetRes = cardDao.getCardSetList();
            return getCardSetRes;
        } catch (Exception exception) {
            System.out.println("exception = " + exception);
            throw new BaseException(DATABASE_ERROR);
        }
    }
    public GetCardRes getOneCard(int tarotId) throws BaseException{
        try{
            GetCardRes getCardRes = cardDao.getOneCard(tarotId);
            return getCardRes;
        } catch (Exception exception) {
            System.out.println("exception = " + exception);
            throw new BaseException(DATABASE_ERROR);
        }
    }
    public GetCardSetRes getThreeCard(int setId) throws BaseException{
        try{
            GetCardSetRes getCardSetRes = cardDao.getThreeCard(setId);
            return getCardSetRes;
        } catch (Exception exception) {
            System.out.println("exception = " + exception);
            throw new BaseException(DATABASE_ERROR);
        }
    }
}
