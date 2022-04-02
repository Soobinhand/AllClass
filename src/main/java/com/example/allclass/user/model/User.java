package com.example.allclass.user.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor

public class User {
    private int userIdx;
    private String email;
    private String userName;
    private String password;
    private String salt;
    private String status;
}