package com.example.tarotaboutU.src.inventory;

import com.example.tarotaboutU.config.BaseException;
import com.example.tarotaboutU.src.inventory.model.PostTarotResultReq;
import com.example.tarotaboutU.src.inventory.model.PostThreeTarotReq;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.example.tarotaboutU.config.BaseResponseStatus.*;

@Service
public class InventoryService {
    final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final InventoryProvider inventoryProvider;
    private final InventoryDao inventoryDao;


    public InventoryService(InventoryProvider inventoryProvider, InventoryDao inventoryDao) {
        this.inventoryProvider = inventoryProvider;
        this.inventoryDao = inventoryDao;
    }

    @Transactional
    public void saveTarotResult(int userId, PostTarotResultReq postTarotResultReq) throws BaseException {
        try{
            int result = inventoryDao.saveTarotResult(userId, postTarotResultReq);
            if (result == 0){
                throw new BaseException(POST_FAIL_SAVE_TAROT);
            }
            this.addTarotToInventory(result, postTarotResultReq);
        } catch (Exception exception) {
            System.out.println("exception = " + exception);
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public void addTarotToInventory(int pickId, PostTarotResultReq postTarotResultReq) throws BaseException {
        try{
            int result = inventoryDao.insertInventory(pickId, postTarotResultReq);
            if (result == 0){
                throw new BaseException(POST_FAIL_TAROT_INVENTORY);
            }
        } catch (Exception exception) {
            System.out.println("exception = " + exception);
            throw new BaseException(DATABASE_ERROR);
        }
    }
    @Transactional
    public void deleteTarotResult(int userId, int pickId) throws BaseException {

        if(inventoryProvider.checkTarotResultExist(pickId) == 0) {
            throw new BaseException(POST_EMPTY_POST_ID);
        }
        if(inventoryDao.getUserIdByPickId(pickId) != userId) {
            throw new BaseException(INVALID_TAROT_ID);
        }
        try{
            int result = inventoryDao.deleteTarotResult(pickId);
            if (result == 0) {
                throw new BaseException(MODIFY_FAIL_POST);
            }
        }
        catch (Exception exception) {
            System.out.println("exceptionService = " + exception.getMessage());
            throw new BaseException(DATABASE_ERROR);
        }
    }
}
