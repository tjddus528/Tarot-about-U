package com.example.tarotaboutU.src.question;

import com.example.tarotaboutU.config.BaseException;
import com.example.tarotaboutU.src.question.model.GetQuestionRes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.tarotaboutU.config.BaseResponseStatus.DATABASE_ERROR;

@Service
public class QuestionProvider {
    private final QuestionDao questionDao;
    @Autowired
    public QuestionProvider(QuestionDao questionDao) {
        this.questionDao = questionDao;
    }

    public List<GetQuestionRes> getQuestionList() throws BaseException {
        try{
            List<GetQuestionRes> getQuestionResList = questionDao.getQuestionList();
            return getQuestionResList;
        } catch (Exception exception) {
            System.out.println("exception = " + exception);
            throw new BaseException(DATABASE_ERROR);
        }
    }
}
