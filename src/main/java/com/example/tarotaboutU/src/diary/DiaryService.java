package com.example.tarotaboutU.src.diary;

import com.example.tarotaboutU.config.BaseException;
import com.example.tarotaboutU.src.diary.model.PatchDiaryReq;
import com.example.tarotaboutU.src.diary.model.PostDiaryReq;
import com.example.tarotaboutU.src.diary.model.PostDiaryRes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.example.tarotaboutU.config.BaseResponseStatus.*;

@Service
public class DiaryService {
    final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final DiaryProvider diaryProvider;
    private final DiaryDao diaryDao;

    @Autowired
    public DiaryService(DiaryProvider diaryProvider, DiaryDao diaryDao) {
        this.diaryProvider = diaryProvider;
        this.diaryDao = diaryDao;
    }
    public PostDiaryRes createDiary(int userId, PostDiaryReq postDiaryReq) throws BaseException {
        try{
            int diaryId = diaryDao.insertDiary(userId, postDiaryReq);
            return new PostDiaryRes(diaryId);
        }
        catch (Exception exception) {
            System.out.println("exception = " + exception);
            throw new BaseException(DATABASE_ERROR);
        }
    }
    public void modifyDiary(int userIdx, int diaryId, PatchDiaryReq patchDiaryReq) throws BaseException {

        if(diaryProvider.checkUserExist(userIdx) == 0) {
            throw new BaseException(USERS_EMPTY_USER_ID);
        }
        if(userIdx != patchDiaryReq.getUserId()) {
            throw new BaseException(INVALID_USER_JWT);
        }
        if(diaryId != patchDiaryReq.getDiaryId()) {
            throw new BaseException(INVALID_TAROT_ID);
        }
        try{
            int result = diaryDao.updateDiary(diaryId, patchDiaryReq.getContent());
            if (result == 0) {
                throw new BaseException(MODIFY_FAIL_POST);
            }
        }
        catch (Exception exception) {
            System.out.println("exceptionService = " + exception.getMessage());
            throw new BaseException(DATABASE_ERROR);
        }
    }
    public void deleteDiary(int diaryId) throws BaseException {

        if(diaryProvider.checkDiaryExist(diaryId) == 0) {
            throw new BaseException(POST_EMPTY_POST_ID);
        }
        try{
            int result = diaryDao.deleteDiary(diaryId);
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
