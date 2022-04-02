package com.example.allclass.comment;


import com.example.allclass.comment.model.SetCommentReq;
import com.example.allclass.comment.model.SetCommentRes;
import com.example.allclass.config.BaseException;
import com.example.allclass.config.BaseResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comment")
public class CommentController {
    private CommentProvider commentProvider;
    private CommentService commentService;

    public CommentController(CommentProvider commentProvider, CommentService commentService) {
        this.commentProvider = commentProvider;
        this.commentService = commentService;
    }

    @ResponseBody
    @PostMapping("")
    public BaseResponse<SetCommentRes> setComment(@RequestBody SetCommentReq setCommentReq) {
        try {
            SetCommentRes setCommentRes = commentService.setCommentRes(setCommentReq);
            return new BaseResponse<>(setCommentRes);
        }catch (BaseException e) {
            return new BaseResponse<>((e.getStatus()));
        }
    }
}