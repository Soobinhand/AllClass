package com.example.allclass.post;

import com.example.allclass.config.BaseException;
import com.example.allclass.config.BaseResponse;
import com.example.allclass.post.model.SetPostReq;
import com.example.allclass.post.model.SetPostRes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/post")
public class PostController {
    @Autowired
    private final PostProvider postProvider;

    @Autowired
    private final PostService postService;

    public PostController(PostProvider postProvider, PostService postService) {
        this.postProvider = postProvider;
        this.postService = postService;
    }

    @ResponseBody
    @PostMapping("")
    public BaseResponse<SetPostRes> setPost(@RequestBody SetPostReq setPostReq) {
        try{
            SetPostRes setPostRes = postService.setPostRes(setPostReq);
            return new BaseResponse<>(setPostRes);
        } catch (BaseException e) {
            return new BaseResponse<>((e.getStatus()));
        }
    }
}