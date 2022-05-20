package com.example.tarotaboutU.src.diary;

import com.example.tarotaboutU.config.BaseException;
import com.example.tarotaboutU.src.diary.model.GetDiaryRes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.tarotaboutU.config.BaseResponseStatus.DATABASE_ERROR;
import static com.example.tarotaboutU.config.BaseResponseStatus.USERS_EMPTY_USER_ID;

@Service
public class DiaryProvider {
    final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final DiaryDao diaryDao;

    @Autowired
    public DiaryProvider(DiaryDao diaryDao) {
        this.diaryDao = diaryDao;
    }

    public List<GetDiaryRes> retrieveDiary(int userId) throws BaseException {
        // 유저가 유효한지 validation
        if(checkUserExist(userId) == 0)
        {
            throw new BaseException(USERS_EMPTY_USER_ID);
        }
        try{

            List<GetDiaryRes> getDiaries = diaryDao.selectDiary(userId);
            return getDiaries;
        }
        catch (Exception exception) {
            System.out.println("exception = " + exception);
            throw new BaseException(DATABASE_ERROR);
        }
    }
    public int checkUserExist(int userIdx) throws BaseException{
        try{
            return diaryDao.checkUserExist(userIdx);
        } catch (Exception exception){
            throw new BaseException(DATABASE_ERROR);
        }
    }
    public int checkDiaryExist(int diaryId) throws BaseException{
        try{
            return diaryDao.checkDiaryExists(diaryId);
        } catch (Exception exception){
            throw new BaseException(DATABASE_ERROR);
        }
    }

}
