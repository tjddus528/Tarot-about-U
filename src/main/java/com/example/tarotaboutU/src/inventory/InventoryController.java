package com.example.tarotaboutU.src.inventory;

import com.example.tarotaboutU.config.BaseException;
import com.example.tarotaboutU.config.BaseResponse;
import com.example.tarotaboutU.src.inventory.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping("/inventory")
public class InventoryController {
    final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final InventoryProvider inventoryProvider;
    private final InventoryService inventoryService;

    @Autowired
    public InventoryController(InventoryProvider inventoryProvider, InventoryService inventoryService) {
        this.inventoryProvider = inventoryProvider;
        this.inventoryService = inventoryService;
    }

    /**
     * 4.1
     * 보관함 타로결과 저장 API
     * @param userId
     * @param postTarotResultReq
     * @return
     */
    @ResponseBody
    @PostMapping("/result/{userId}")
    public BaseResponse<String> postTarotToInventory(@PathVariable("userId") int userId, @RequestBody PostTarotResultReq postTarotResultReq){
        try{
            inventoryService.saveTarotResult(userId, postTarotResultReq);
            String result = "보관함에 새로운 카드결과를 성공적으로 추가했습니다.";
            return new BaseResponse<>(result);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    /**
     * 4.2
     * 저장한 타로결과 리스트 조회 API
     * @param userId
     * @return
     */
    @ResponseBody
    @GetMapping("")
    public BaseResponse<List<GetTarotsPickedByUser>> getInventoryList(@RequestParam int userId){
        try{
            List<GetTarotsPickedByUser> getTarotsPickedByUserList = inventoryProvider.getInventoryRes(userId);
            return new BaseResponse<>(getTarotsPickedByUserList);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    /**
     * 4.3
     * 타로결과 리스트 조회(question 1, 2, 3)
     * @param userId
     * @param questionId
     * @param date
     * @return
     */
    @ResponseBody
    @GetMapping("/tarot/{userId}")
    public BaseResponse<List<GetTarotListRes>> getTarotList(@PathVariable("userId") int userId, @RequestParam int questionId, Date date){
        try{
            List<GetTarotListRes> getTarotListRes = inventoryProvider.getTarotListRes(userId, questionId, date);
            return new BaseResponse<>(getTarotListRes);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    /**
     * 4.4
     * 세트결과 리스트 조회(question 4)
     * @param userId
     * @param date
     * @return
     */
    @ResponseBody
    @GetMapping("/set/{userId}")
    public BaseResponse<List<GetSetListRes>> getTarotList(@PathVariable("userId") int userId, @RequestParam Date date){
        try{
            List<GetSetListRes> getSetListRes = inventoryProvider.getSetListRes(userId, date);
            return new BaseResponse<>(getSetListRes);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    /**
     * 4.5
     * 타로 결과 삭제 API
     * @param userId
     * @param pickId
     * @return
     */
    @ResponseBody
    @PatchMapping("/{pickId}/status")
    public BaseResponse<String> deleteTarotResult(@RequestParam int userId, @PathVariable("pickId") int pickId) {
        try{

            inventoryService.deleteTarotResult(userId, pickId);
            String result = "보관함에서 타로결과삭제를 완료했습니다.";
            return new BaseResponse<>(result);
        } catch(BaseException exception){
            System.out.println("exceptionController = " + exception.getStatus());
            return new BaseResponse<>((exception.getStatus()));
        }
    }
}
