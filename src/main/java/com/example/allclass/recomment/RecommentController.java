package com.example.allclass.recomment;


import com.example.allclass.config.BaseException;
import com.example.allclass.config.BaseResponse;
import com.example.allclass.recomment.model.SetRecommentReq;
import com.example.allclass.recomment.model.SetRecommentRes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/recomment")
public class RecommentController {
    private RecommentService recommentService;
    private RecommentProvider recommentProvider;

    @Autowired
    public RecommentController(RecommentService recommentService, RecommentProvider recommentProvider) {
        this.recommentProvider = recommentProvider;
        this.recommentService = recommentService;
    }

    @ResponseBody
    @PostMapping("")
    public BaseResponse<SetRecommentRes> setRecomment(@RequestBody SetRecommentReq setRecommentReq) {
        try {
            SetRecommentRes setRecommentRes = recommentService.setRecommentRes(setRecommentReq);
            return new BaseResponse<>(setRecommentRes);
        }catch (BaseException e) {
            return new BaseResponse<>((e.getStatus()));
        }
    }
}