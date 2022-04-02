package com.example.allclass.recomment;

import org.springframework.stereotype.Service;

@Service
public class RecommentProvider {
    private RecommentDao recommentDao;
    public RecommentProvider(RecommentDao recommentDao) {
        this.recommentDao = recommentDao;
    }
}