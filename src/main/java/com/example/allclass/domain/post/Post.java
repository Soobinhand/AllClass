package com.example.allclass.domain.post;

import com.example.allclass.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Post extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 500, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    private Long categoryid;

    @Builder
    public Post(String title, String content, String author, Long categoryid){
        this.title = title;
        this.content = content;
        this.author = author;
        this.categoryid = categoryid;
    }

    public void update(String title, String content){
        this.title = title;
        this.content = content;
    }
}
