package com.example.allclass.comment;


import com.example.allclass.comment.model.SetCommentReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Repository
public class CommentDao {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public int setComment(SetCommentReq setCommentReq) {
        String commentQuery = "insert into comment (postIdx, userIdx, commentContent) values (?,?,?)";
        Object[] params = new Object[]{setCommentReq.getPostIdx(), setCommentReq.getUserIdx(), setCommentReq.getContent()};
        jdbcTemplate.update(commentQuery, params);

        String lastInserIdQuery = "select last_insert_id()";
        return this.jdbcTemplate.queryForObject(lastInserIdQuery, int.class);
    }
}