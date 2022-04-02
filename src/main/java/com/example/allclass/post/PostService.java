package com.example.allclass.post;

import com.example.allclass.config.BaseException;
import com.example.allclass.config.BaseResponseStatus;
import com.example.allclass.post.model.SetPostReq;
import com.example.allclass.post.model.SetPostRes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import static com.example.allclass.config.BaseResponseStatus.*;
@Service
public class PostService {
    private PostDao postDao;

    @Autowired
    public PostService(PostDao postDao) {
        this.postDao = postDao;
    }

    public SetPostRes setPostRes(SetPostReq setPostReq) throws BaseException {
        try{
            int postIdx = postDao.setPost(setPostReq);
            return new SetPostRes(postIdx);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BaseException(DATABASE_ERROR);
        }
    }
}