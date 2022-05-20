package com.example.tarotaboutU.src.inventory;

import com.example.tarotaboutU.config.BaseException;
import com.example.tarotaboutU.config.BaseResponse;
import com.example.tarotaboutU.src.inventory.model.GetInventoryRes;
import com.example.tarotaboutU.src.inventory.model.GetTarotsPickedByUser;
import com.example.tarotaboutU.src.inventory.model.PostTarotResultReq;
import com.example.tarotaboutU.src.inventory.model.PostThreeTarotReq;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
