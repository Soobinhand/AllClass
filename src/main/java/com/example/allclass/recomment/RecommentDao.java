package com.example.allclass.recomment;


import com.example.allclass.recomment.model.SetRecommentReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Repository
public class RecommentDao {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public int setRecomment(SetRecommentReq setRecommentReq) {
        String query = "insert into recomment (commentIdx, userIdx, recommentContent) values (?,?,?)";
        Object[] params = new Object[] {setRecommentReq.getCommentIdx(), setRecommentReq.getUserIdx(),setRecommentReq.getContent()};
        this.jdbcTemplate.update(query,params);

        String lastInserIdQuery = "select last_insert_id()";
        return this.jdbcTemplate.queryForObject(lastInserIdQuery, int.class);
    }
}