package com.example.allclass.comment.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SetCommentReq {
    private long userIdx;
    private long postIdx;
    private String content;
}