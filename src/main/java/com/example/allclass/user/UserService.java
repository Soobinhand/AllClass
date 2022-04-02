package com.example.allclass.user;

import com.example.allclass.config.BaseException;
import com.example.allclass.user.model.PostUserReq;
import com.example.allclass.user.model.PostUserRes;
import com.example.allclass.utils.AES128;
import com.example.allclass.utils.JwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

import static com.example.allclass.config.BaseResponseStatus.*;

@Service
public class UserService {
    final Logger logger = LoggerFactory.getLogger(this.getClass());


    private final UserDao userDao;
    private final UserProvider userProvider;
    private final JwtService jwtService;


    @Autowired
    public UserService(UserDao userDao, UserProvider userProvider, JwtService jwtService) {
        this.userDao = userDao;
        this.userProvider = userProvider;
        this.jwtService = jwtService;

    }

    // 회원가입(POST)
    @Transactional
    public PostUserRes createUser(PostUserReq postUserReq) throws BaseException {

        // 중복된 이메일이 없는지 먼저 체크
        if(userProvider.checkEmail(postUserReq.getUserEmail())==1){
            throw new BaseException(POST_USERS_EXISTS_EMAIL);
        }

        String pwd;
        String salt;

        try {
            // 암호화: postUserReq에서 제공받은 비밀번호를 보안을 위해 암호화시켜 DB에 저장합니다.
            // ex) password123 -> dfhsjfkjdsnj4@!$!@chdsnjfwkenjfnsjfnjsd.fdsfaifsadjfjaf
            salt = Math.round(new Date().getTime()*(Math.random()))+"123";
            postUserReq.setSalt(salt);
            pwd = new AES128(salt).encrypt(postUserReq.getPassword()); // 암호화코드
            postUserReq.setPassword(pwd);

        } catch (Exception ignored) { // 암호화가 실패하였을 경우 에러 발생
            throw new BaseException(PASSWORD_ENCRYPTION_ERROR);
        }

        try {
            int userIdx = userDao.createUser(postUserReq);
            String userName = userDao.userIdToUserName(userIdx);
            // jwt 반영 부분
            String jwt = jwtService.createJwt(userIdx,userName);
            return new PostUserRes(userIdx,userName,jwt);

        } catch (Exception exception) { // DB에 이상이 있는 경우 에러 메시지를 보냅니다.
            throw new BaseException(DATABASE_ERROR);
        }
    }

}