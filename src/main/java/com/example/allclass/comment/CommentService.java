package com.example.allclass.comment;


import com.example.allclass.comment.model.SetCommentReq;
import com.example.allclass.comment.model.SetCommentRes;
import com.example.allclass.config.BaseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.example.allclass.config.BaseResponseStatus.DATABASE_ERROR;

@Service
public class CommentService {
    private CommentDao commentDao;

    @Autowired
    public CommentService(CommentDao commentDao) {
        this.commentDao = commentDao;
    }

    public SetCommentRes setCommentRes(SetCommentReq setCommentReq) throws BaseException {
        try {
            int commentIdx = commentDao.setComment(setCommentReq);
            return new SetCommentRes(commentIdx);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BaseException(DATABASE_ERROR);
        }
    }
}