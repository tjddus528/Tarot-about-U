package com.example.tarotaboutU.src.question;

import com.example.tarotaboutU.config.BaseException;
import com.example.tarotaboutU.config.BaseResponse;
import com.example.tarotaboutU.src.question.model.GetQuestionRes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/question")
public class QuestionController {
    final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final QuestionProvider questionProvider;
    private final QuestionService questionService;
    @Autowired
    public QuestionController(QuestionProvider questionProvider, QuestionService questionService) {
        this.questionProvider = questionProvider;
        this.questionService = questionService;
    }

    /**
     * 2.1
     * 전체 타로카드 조회 API
     * GET
     */
    @ResponseBody
    @GetMapping("") // (GET) 127.0.0.1:9000/question
    public BaseResponse<List<GetQuestionRes>> getQuestionList() {
        try{
            List<GetQuestionRes> getQuestionResList = questionProvider.getQuestionList();
            return new BaseResponse<>(getQuestionResList);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }
}
