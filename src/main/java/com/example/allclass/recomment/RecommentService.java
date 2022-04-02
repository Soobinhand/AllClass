package com.example.allclass.recomment;


import com.example.allclass.config.BaseException;
import com.example.allclass.recomment.model.SetRecommentReq;
import com.example.allclass.recomment.model.SetRecommentRes;
import org.springframework.stereotype.Service;

import static com.example.allclass.config.BaseResponseStatus.DATABASE_ERROR;

@Service
public class RecommentService {
    private RecommentDao recommentDao;
    public RecommentService(RecommentDao recommentDao) {
        this.recommentDao = recommentDao;
    }

    public SetRecommentRes setRecommentRes(SetRecommentReq setRecommentReq) throws BaseException {
        try {
            int recommentIdx = recommentDao.setRecomment(setRecommentReq);
            return new SetRecommentRes(recommentIdx);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BaseException(DATABASE_ERROR);
        }
    }
}