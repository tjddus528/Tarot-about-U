package com.example.tarotaboutU.src.user;

import com.example.tarotaboutU.config.BaseException;
import com.example.tarotaboutU.config.BaseResponse;
import com.example.tarotaboutU.config.BaseResponseStatus;
import com.example.tarotaboutU.src.user.model.GetUserRes;
import com.example.tarotaboutU.src.user.model.PatchUserReq;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private final UserProvider userProvider;
    @Autowired
    private final UserService userService;

    public UserController(UserProvider userProvider, UserService userService) {
        this.userProvider = userProvider;
        this.userService = userService;
    }
    /**
     * TODO: 유저 회원가입 API(계정연동)
     */

    /**1.2
     * 전체 회원 조회 API
     * [GET] /users
     */
    //Query String

    @ResponseBody
    @GetMapping("/list") // (GET) 127.0.0.1:3000/user/list
    public BaseResponse<List<GetUserRes>> getUsersList() {
        try{
            List<GetUserRes> getUserResList = userProvider.getUserListRes();
            return new BaseResponse<>(getUserResList);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    /**
     * 1.3
     * 특정 유저 조회 API
     * @param userId
     * @return
     */
    @ResponseBody
    @GetMapping("/{userId}") // (GET) 127.0.0.1:9000/users/3
    public BaseResponse<GetUserRes> getUser(@PathVariable("userId") int userId) {
        try{
            GetUserRes getUser = userProvider.getUser(userId);
            return new BaseResponse<>(getUser);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    /**
     * 1.4
     * 유저 프로필 수정 API
     * @param userId
     * @param patchUserReq
     * @return
     */
    @ResponseBody
    @PatchMapping("/{userId}")
    public BaseResponse<String> modifyUser(@PathVariable("userId") int userId, @RequestBody PatchUserReq patchUserReq) {

        System.out.println("patchUserReq.getName() = " + patchUserReq.getName());
        System.out.println("patchUserReq.getBD() = " + patchUserReq.getBirthday());
        System.out.println("patchUserReq.getGender() = " + patchUserReq.getGender());
        if(patchUserReq.getName() == null) {
            return new BaseResponse<>(BaseResponseStatus.POST_USERS_EMPTY_EMAIL);
        }
        try{
            userService.modifyUser(userId, patchUserReq);
            String result = "유저프로필 수정에 성공하였습니다.";
            return new BaseResponse<>(result);
        } catch (BaseException exception) {
            System.out.println("exception = " + exception);
            return new BaseResponse<>((exception.getStatus()));
        }

    }
}
