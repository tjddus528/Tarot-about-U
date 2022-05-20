package com.example.tarotaboutU.src.diary;

import com.example.tarotaboutU.config.BaseException;
import com.example.tarotaboutU.config.BaseResponse;
import com.example.tarotaboutU.config.BaseResponseStatus;
import com.example.tarotaboutU.src.diary.model.GetDiaryRes;
import com.example.tarotaboutU.src.diary.model.PatchDiaryReq;
import com.example.tarotaboutU.src.diary.model.PostDiaryReq;
import com.example.tarotaboutU.src.diary.model.PostDiaryRes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/diary")
public class DiaryController {
    final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final DiaryProvider diaryProvider;
    private final DiaryService diaryService;

    @Autowired
    public DiaryController(DiaryProvider diaryProvider, DiaryService diaryService) {
        this.diaryProvider = diaryProvider;
        this.diaryService = diaryService;
    }

    @ResponseBody
    @PostMapping("")
    public BaseResponse<PostDiaryRes> postDiary(@RequestBody PostDiaryReq postDiaryReq) {
        try{
            if(postDiaryReq.getContent().length() > 450) {
                return new BaseResponse<>(BaseResponseStatus.POST_POSTS_INVALID_CONTENTS);
            }
            PostDiaryRes postDiaryRes = diaryService.createDiary(postDiaryReq.getUserId(), postDiaryReq);
            return new BaseResponse<>(postDiaryRes);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }
    @ResponseBody
    @GetMapping("")
    public BaseResponse<List<GetDiaryRes>> getPosts(@RequestParam int userId) {
        try{
            List<GetDiaryRes> getDiaryRes = diaryProvider.retrieveDiary(userId);
            return new BaseResponse<>(getDiaryRes);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }
    @ResponseBody
    @PatchMapping("{diaryId}")
    public BaseResponse<String> modifyDiary(@PathVariable("diaryId") int diaryId, @RequestBody PatchDiaryReq patchDiaryReq) {
        try{
            if(patchDiaryReq.getContent().length() > 450)
            {
                return new BaseResponse<>(BaseResponseStatus.POST_POSTS_INVALID_CONTENTS);
            }
            diaryService.modifyDiary(patchDiaryReq.getUserId(), diaryId, patchDiaryReq);
            String result = "다이어리 수정을 완료했습니다.";
            return new BaseResponse<>(result);
        } catch(BaseException exception){
            System.out.println("exception = " + exception.getStatus());
            return new BaseResponse<>((exception.getStatus()));
        }
    }
    @ResponseBody
    @PatchMapping("/{diaryId}/status")
    public BaseResponse<String> deleteDiary(@PathVariable("diaryId") int diaryId) {
        try{

            diaryService.deleteDiary(diaryId);
            String result = "다이어리 삭제를 완료했습니다.";
            return new BaseResponse<>(result);
        } catch(BaseException exception){
            System.out.println("exceptionController = " + exception.getStatus());
            return new BaseResponse<>((exception.getStatus()));
        }
    }
}
