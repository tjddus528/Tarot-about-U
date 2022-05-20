package com.example.tarotaboutU.src.user;

import com.example.tarotaboutU.config.BaseException;
import com.example.tarotaboutU.src.user.model.PatchUserReq;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import static com.example.tarotaboutU.config.BaseResponseStatus.*;

@Service
public class UserService {
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final UserDao userDao;
    private final UserProvider userProvider;

    public UserService(UserDao userDao, UserProvider userProvider) {
        this.userDao = userDao;
        this.userProvider = userProvider;
    }

    public void modifyUser(int userId, PatchUserReq patchUserReq) throws BaseException {
        // validation
        try{
            int result = userDao.modifyUser(userId, patchUserReq);
            if (result == 0){
                throw new BaseException(MODIFY_FAIL_USERPROFILE);
            }
        } catch (Exception exception) {
            System.out.println("exception = " + exception);
            throw new BaseException(DATABASE_ERROR);
        }
    }
}
