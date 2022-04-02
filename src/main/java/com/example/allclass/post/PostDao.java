package com.example.allclass.post;

import com.example.allclass.post.model.SetPostReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Repository
public class PostDao {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public int setPost(SetPostReq setPostReq) {
        String serPostQuery = "insert into post (userIdx, postTitle, postContent, postCategoryIdx) values (?,?,?,?)";

        Object[] params = new Object[] {setPostReq.getUserIdx(), setPostReq.getTitle(), setPostReq.getContent(), setPostReq.getCategoryIdx()};

        this.jdbcTemplate.update(serPostQuery,params);

        String lastInserIdQuery = "select last_insert_id()";
        return this.jdbcTemplate.queryForObject(lastInserIdQuery, int.class);
    }
}