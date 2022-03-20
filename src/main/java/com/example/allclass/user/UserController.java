package com.example.allclass.user;

import com.example.allclass.config.BaseException;
import com.example.allclass.config.BaseResponse;
import com.example.allclass.user.model.PostUserReq;
import com.example.allclass.user.model.PostUserRes;
import com.example.allclass.utils.JwtService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * Provider
     * DB에서 값을 받아오고 싶을때? get 함수들.
     */
    @Autowired
    private final UserProvider userProvider;

    /**
     * DB에 값을 저장하는 역할.
     */
    @Autowired
    private final UserService userService;

    /**
     * 세션처럼 아이디 부여.
     */
    @Autowired
    private final JwtService jwtService;

    public UserController(UserProvider userProvider, UserService userService, JwtService jwtService) {
        this.userProvider = userProvider;
        this.userService = userService;
        this.jwtService = jwtService;
    }

    /**
     * 회원가입 API
     * [POST] /users
     */
    // Body
    @ResponseBody
    @PostMapping("")    // POST 방식의 요청을 매핑하기 위한 어노테이션
    public BaseResponse<PostUserRes> createUser(@RequestBody PostUserReq postUserReq) {
        try {
            PostUserRes postUserRes = userService.createUser(postUserReq);
            return new BaseResponse<>(postUserRes);
        } catch (BaseException exception) {
            exception.printStackTrace();
            return new BaseResponse<>((exception.getStatus()));
        }
    }

}
