package com.example.allclass.domain.category;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int categoryId;

    private String categoryName;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;
    private String status;

    @Builder
    public Category(int categoryId, String categoryName, LocalDateTime created_at,
                    LocalDateTime updated_at, String status){
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.status = status;
    }

    @Override
    public String toString() {
        return "Category{" +
                "categoryId=" + categoryId +
                ", categoryName='" + categoryName + '\'' +
                ", created_at=" + created_at +
                ", updated_at=" + updated_at +
                ", status='" + status + '\'' +
                '}';
    }
}
