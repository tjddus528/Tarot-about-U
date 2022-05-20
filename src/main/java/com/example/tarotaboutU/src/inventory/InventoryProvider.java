package com.example.tarotaboutU.src.inventory;

import com.example.tarotaboutU.config.BaseException;
import com.example.tarotaboutU.src.inventory.model.GetInventoryRes;
import com.example.tarotaboutU.src.inventory.model.GetTarotsPickedByUser;
import com.example.tarotaboutU.src.user.model.GetUserRes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.tarotaboutU.config.BaseResponseStatus.DATABASE_ERROR;

@Service
public class InventoryProvider {
    final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final InventoryDao inventoryDao;
    @Autowired
    public InventoryProvider(InventoryDao inventoryDao) {
        this.inventoryDao = inventoryDao;
    }

    public List<GetTarotsPickedByUser> getInventoryRes(int userId) throws BaseException {
        try{
            List<GetTarotsPickedByUser> getTarotsPickedByUserList = inventoryDao.getInventoryRes(userId);
            return getTarotsPickedByUserList;
        } catch (Exception exception) {
            System.out.println("exception = " + exception);
            throw new BaseException(DATABASE_ERROR);
        }
    }
    public int checkTarotResultExist(int pickId) throws BaseException{
        try{
            return inventoryDao.checkTarotResultExists(pickId);
        } catch (Exception exception){
            throw new BaseException(DATABASE_ERROR);
        }
    }

}
