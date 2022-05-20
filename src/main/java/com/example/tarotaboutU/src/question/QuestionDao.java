package com.example.tarotaboutU.src.question;

import com.example.tarotaboutU.src.question.model.GetQuestionRes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class QuestionDao {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<GetQuestionRes> getQuestionList(){
        String getQuestionQuery = "select * from question";
        return this.jdbcTemplate.query(getQuestionQuery,
                (rs,rowNum) -> new GetQuestionRes(
                        rs.getInt("question_id"),
                        rs.getString("question_m"),
                        rs.getString("one_or_set")
                ));
    }

}
