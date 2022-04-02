package com.example.allclass.recomment.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SetRecommentReq {
    private long commentIdx;
    private long userIdx;
    private String content;
}