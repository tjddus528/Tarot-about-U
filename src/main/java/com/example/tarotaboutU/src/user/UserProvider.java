package com.example.tarotaboutU.src.user;

import com.example.tarotaboutU.config.BaseException;
import com.example.tarotaboutU.src.user.model.GetUserRes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.tarotaboutU.config.BaseResponseStatus.DATABASE_ERROR;

@Service
public class UserProvider {
    private final UserDao userDao;

    final Logger logger = LoggerFactory.getLogger(this.getClass());

    public UserProvider(UserDao userDao) {
        this.userDao = userDao;
    }
    /**
     * 전체 유저리스트 조회
     * @return
     * @throws BaseException
     */
    public List<GetUserRes> getUserListRes() throws BaseException{
        try{
            List<GetUserRes> getUserResList = userDao.getUsersList();
            return getUserResList;
        } catch (Exception exception) {
            System.out.println("exception = " + exception);
            throw new BaseException(DATABASE_ERROR);
        }
    }
    public GetUserRes getUser(int userId) throws BaseException{
        try{
            GetUserRes getUserRes = userDao.getUser(userId);
            return getUserRes;
        } catch (Exception exception) {
            System.out.println("exception = " + exception);
            throw new BaseException(DATABASE_ERROR);
        }
    }
}

