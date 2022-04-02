package com.example.allclass.user;

import com.example.allclass.config.BaseException;
import com.example.allclass.user.model.PostLoginReq;
import com.example.allclass.user.model.PostLoginRes;
import com.example.allclass.user.model.User;
import com.example.allclass.utils.AES128;
import com.example.allclass.utils.JwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.example.allclass.config.BaseResponseStatus.*;

@Service
public class UserProvider {
    // *********************** 동작에 있어 필요한 요소들을 불러옵니다. *************************
    private final UserDao userDao;
    private final JwtService jwtService; // JWT부분은 7주차에 다루므로 모르셔도 됩니다!


    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired //readme 참고
    public UserProvider(UserDao userDao, JwtService jwtService) {
        this.userDao = userDao;
        this.jwtService = jwtService; // JWT부분은 7주차에 다루므로 모르셔도 됩니다!
    }
    // ******************************************************************************

    // 해당 이메일이 이미 USER에 존재하는지 확인
    public int checkEmail(String userEmail) throws BaseException {
        try{
            return userDao.checkEmail(userEmail);
        } catch (Exception exception){
            throw new BaseException(DATABASE_ERROR);
        }
    }

    // 로그인(password 검사)
    public PostLoginRes logIn(PostLoginReq postLoginReq) throws BaseException {
        User user = userDao.getPwd(postLoginReq);
        String password;
        try {
            password = new AES128(user.getSalt()).decrypt(user.getPassword()); // 암호화
            // 회원가입할 때 비밀번호가 암호화되어 저장되었기 떄문에 로그인을 할때도 암호화된 값끼리 비교를 해야합니다.
        } catch (Exception ignored) {
            throw new BaseException(PASSWORD_DECRYPTION_ERROR);
        }

        if (postLoginReq.getPassword().equals(password)) { //비말번호가 일치한다면 userIdx를 가져온다.

            if(user.getStatus().equals("active")){
                int userIdx = userDao.getPwd(postLoginReq).getUserIdx();
                String userName = userDao.userIdToUserName(userIdx);
                // jwt 반영 부분
                String jwt = jwtService.createJwt(userIdx,userName);
                return new PostLoginRes(userIdx,jwt); // 같이 그 값을 넘겨줘버려!!
            }
//            else if (user.getStatus().equals("deactive")){
//                throw new BaseException(DEACTIVE_ID);
//            }
//            else if (user.getStatus().equals("delete")){
//                throw new BaseException(DELETE_ID);
//            }
            else{
                throw new BaseException(FAILED_TO_LOGIN);
            }
        } else { // 비밀번호가 다르다면 에러메세지를 출력한다.
            throw new BaseException(FAILED_TO_LOGIN);
        }
    }

}
