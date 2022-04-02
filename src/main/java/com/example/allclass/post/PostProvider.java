package com.example.allclass.post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostProvider {
    private PostDao postDao;

    @Autowired
    public PostProvider(PostDao postDao) {
        this.postDao = postDao;
    }


}