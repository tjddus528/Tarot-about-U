package com.example.tarotaboutU.src.card;

import com.example.tarotaboutU.config.BaseException;
import com.example.tarotaboutU.config.BaseResponse;
import com.example.tarotaboutU.config.BaseResponseStatus;
import com.example.tarotaboutU.src.card.model.GetCardRes;
import com.example.tarotaboutU.src.card.model.GetCardSetRes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cards")
public class CardController {
    final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final CardProvider cardProvider;
    private final CardService cardService;
    @Autowired
    public CardController(CardProvider cardProvider, CardService cardService) {
        this.cardProvider = cardProvider;
        this.cardService = cardService;
    }

    /**
     * 2.1
     * 전체 타로카드 조회 API
     * GET
     */
    @ResponseBody
    @GetMapping("/list") // (GET) 127.0.0.1:9000/cards
    public BaseResponse<List<GetCardRes>> getCardList() {
        try{
            List<GetCardRes> getCardRes = cardProvider.getCardList();
            return new BaseResponse<>(getCardRes);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    @ResponseBody
    @GetMapping("/setlist") // (GET) 127.0.0.1:9000/cards/set
    public BaseResponse<List<GetCardSetRes>> getCardSetList() {
        try{
            List<GetCardSetRes> getCardSetRes = cardProvider.getCardSetList();
            return new BaseResponse<>(getCardSetRes);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    /**
     * 특정 카드1장 조회
     * @param tarotId 타로아이디
     * @return 타로카드1장 결과
     */
    @ResponseBody
    @GetMapping("/tarot/{tarotId}") // (GET) 127.0.0.1:9000/cards
    public BaseResponse<GetCardRes> getOneCard(@PathVariable("tarotId") int tarotId) {
        if(tarotId >= 22 || tarotId < 0) {
            return new BaseResponse<>(BaseResponseStatus.INVALID_TAROT_ID);
        }
        try{
            GetCardRes getCardRes = cardProvider.getOneCard(tarotId);
            return new BaseResponse<>(getCardRes);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    /**
     * 특정 카드3장 조회
     * @param setId 타로세트 아이디
     * @return 타로3장 결과
     */
    @ResponseBody
    @GetMapping("/set/{setId}") // (GET) 127.0.0.1:9000/users
    public BaseResponse<GetCardSetRes> getThreeCard(@PathVariable("setId") int setId) {
        try{
            GetCardSetRes getCardSetRes = cardProvider.getThreeCard(setId);
            return new BaseResponse<>(getCardSetRes);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }

}
