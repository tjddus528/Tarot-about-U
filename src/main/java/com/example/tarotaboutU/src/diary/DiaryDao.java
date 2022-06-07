package com.example.tarotaboutU.src.diary;

import com.example.tarotaboutU.config.BaseException;
import com.example.tarotaboutU.config.BaseResponse;
import com.example.tarotaboutU.src.diary.model.*;
import com.example.tarotaboutU.src.inventory.model.GetTarotsPickedByUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

import static com.example.tarotaboutU.config.BaseResponseStatus.DATABASE_ERROR;

@Repository
public class DiaryDao {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public int insertDiary(int userId, boolean tarotExist, PostDiaryReq postDiaryReq) {
        String insertDiaryQuery = "INSERT INTO diary (user_id, create_date, title, content, status, one_or_set, tarot_id, set_id, question_id) " +
                "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?) ";
        Object[] insertDiaryParam = new Object[]{
                userId,
                postDiaryReq.getCreateDate(),
                postDiaryReq.getTitle(),
                postDiaryReq.getContent(),
                "ACTIVE",
                postDiaryReq.getOneOrSet(),
                postDiaryReq.getTarotId(),
                postDiaryReq.getSetId(),
                postDiaryReq.getQuestionId()
        };
        this.jdbcTemplate.update(insertDiaryQuery,insertDiaryParam);
        String lastInsertIdxQuery = "select last_insert_id()";
        return this.jdbcTemplate.queryForObject(lastInsertIdxQuery, int.class);

    }

    public List<GetDiaryRes> selectDiary(int userId){
        String selectDiaryQuery = "select diary_id, user_id, create_date, title, content, status, one_or_set, tarot_id, set_id, question_id\n" +
                "from diary\n" +
                "where status='ACTIVE' and user_id=?;";
        int selectDiaryParam=userId;
        try {
            return this.jdbcTemplate.query(selectDiaryQuery,
                    (rs, rowNum) -> new GetDiaryRes(
                            rs.getInt("diary_id"),
                            rs.getInt("user_id"),
                            rs.getDate("create_date"),
                            rs.getString("title"),
                            rs.getString("content"),
                            rs.getString("status"),
                            rs.getString("one_or_set"),
                            rs.getInt("tarot_id"),
                            rs.getInt("set_id"),
                            rs.getInt("question_id")
                    ), selectDiaryParam);
        } catch (EmptyResultDataAccessException e) {
            System.out.println("e = " + e);
            return null;
        }
    }

    public int checkUserExist(int userId){
        String checkUserExistQuery = "select exists(select user_id from user where user_id = ?)";
        int checkUserExistParam = userId;
        return this.jdbcTemplate.queryForObject(checkUserExistQuery,
                int.class,
                checkUserExistParam);

    }
    public int checkDiaryExists(int diaryId){
        String checkDiaryExistQuery = "select exists(select diary_id from diary where diary_id = ?)";
        int checkDiaryExistParam = diaryId;
        return this.jdbcTemplate.queryForObject(checkDiaryExistQuery,
                int.class,
                checkDiaryExistParam);

    }
    public int updateDiary(int diaryId, PatchDiaryReq patchDiaryReq){
        String updateDiaryQuery = "UPDATE diary SET title=? , content=? WHERE diary_id=?";
        Object []updateDiaryParams = new Object[] {
                patchDiaryReq.getTitle(),
                patchDiaryReq.getContent(),
                diaryId};
        return this.jdbcTemplate.update(updateDiaryQuery, updateDiaryParams);
    }
    public int deleteDiary(int diaryId){
        String deleteDiaryQuery = "UPDATE diary SET status='INACTIVE' WHERE diary_id=?";
        int deleteDiaryParams = diaryId;
        return this.jdbcTemplate.update(deleteDiaryQuery, deleteDiaryParams);
    }
}
