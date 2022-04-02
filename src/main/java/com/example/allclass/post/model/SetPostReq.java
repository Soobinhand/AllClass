package com.example.allclass.post.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SetPostReq {
    private long userIdx; // 작성자
    private String title;
    private String content;
    private int categoryIdx;
}