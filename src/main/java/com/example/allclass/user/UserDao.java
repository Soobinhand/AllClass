package com.example.allclass.user;

import com.example.allclass.user.model.PostLoginReq;
import com.example.allclass.user.model.PostUserReq;
import com.example.allclass.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Repository
public class UserDao {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    // 회원가입
    public int createUser(PostUserReq postUserReq) {
        String createUserQuery = "insert into user (userName, userEmail, userGroupIdx, password, salt) VALUES (?,?,?,?,?)"; // 실행될 동적 쿼리문
        Object[] createUserParams = new Object[]{postUserReq.getUserName(), postUserReq.getUserEmail(), postUserReq.getUserGroupIdx(),postUserReq.getPassword(),postUserReq.getSalt()}; // 동적 쿼리의 ?부분에 주입될 값

        // post의 경우 .update를 가져온다. insert하는것은 post이므로,,,
        this.jdbcTemplate.update(createUserQuery, createUserParams);
        // email -> postUserReq.getEmail(), password -> postUserReq.getPassword(), nickname -> postUserReq.getNickname() 로 매핑(대응)시킨다음 쿼리문을 실행한다.
        // 즉 DB의 User Table에 (email, password, nickname)값을 가지는 유저 데이터를 삽입(생성)한다.

        // 그럼 생성한 다른 값들을 가져오고 싶으면 어쩌징..
        String lastInserIdQuery = "select last_insert_id()"; // 가장 마지막에 삽입된(생성된) id값은 가져온다.
        return this.jdbcTemplate.queryForObject(lastInserIdQuery, int.class); // 해당 쿼리문의 결과 마지막으로 삽인된 유저의 userIdx번호를 반환한다.
    }

    public int checkEmail(String userEmail) {
        String checkEmailQuery = "select exists(select userEmail from user where userEmail = ?)"; // User Table에 해당 email 값을 갖는 유저 정보가 존재하는가?
        String checkEmailParams = userEmail; // 해당(확인할) 이메일 값
        return this.jdbcTemplate.queryForObject(checkEmailQuery,
                int.class,
                checkEmailParams); // checkEmailQuery, checkEmailParams를 통해 가져온 값(intgud)을 반환한다. -> 쿼리문의 결과(존재하지 않음(False,0),존재함(True, 1))를 int형(0,1)으로 반환됩니다.
    }

    // 유저 아이디를 받으면 닉네임을 반환해주는 프로그램
    public String userIdToUserName(int userIdx){
        String convertNicknameQuery = "select userName from user where userIdx = ?";
        return this.jdbcTemplate.queryForObject(convertNicknameQuery,String.class,userIdx);
    }

    // 로그인: 해당 email에 해당되는 user의 암호화된 비밀번호 값을 가져온다.
    public User getPwd(PostLoginReq postLoginReq) {
        String getPwdQuery = "select userIdx,userEmail,userName,password,salt,status from user where userEmail = ?";
        // 해당 nickname을 만족하는 User의 정보들을 조회한다.
        String getPwdParams = postLoginReq.getUserEmail(); // 주입될 email값을 클라이언트의 요청에서 주어진 정보를 통해 가져온다.

        return this.jdbcTemplate.queryForObject(getPwdQuery,
                (rs, rowNum) -> new User(
                        rs.getInt("userIdx"),
                        rs.getString("userEmail"),
                        rs.getString("userName"),
                        rs.getString("password"),
                        rs.getString("salt"),
                        rs.getString("status")
                ), // RowMapper(위의 링크 참조): 원하는 결과값 형태로 받기
                getPwdParams
        ); // 한 개의 회원정보를 얻기 위한 jdbcTemplate 함수(Query, 객체 매핑 정보, Params)의 결과 반환
    }
}
