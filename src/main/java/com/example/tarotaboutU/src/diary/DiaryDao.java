package com.example.tarotaboutU.src.diary;

import com.example.tarotaboutU.src.diary.model.GetDiaryRes;
import com.example.tarotaboutU.src.diary.model.PatchDiaryReq;
import com.example.tarotaboutU.src.diary.model.PostDiaryReq;
import com.example.tarotaboutU.src.diary.model.PostDiaryRes;
import com.example.tarotaboutU.src.inventory.model.GetTarotsPickedByUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class DiaryDao {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public int insertDiary(int userId, PostDiaryReq postDiaryReq) {
        String insertDiaryQuery = "INSERT INTO diary (user_id, create_date, title, content, status) VALUES(?, ?, ?, ?, ?) ";
        Object[] insertDiaryParam = new Object[]{
                userId,
                postDiaryReq.getCreateDate(),
                postDiaryReq.getTitle(),
                postDiaryReq.getContent(),
                "ACTIVE",
        };
        this.jdbcTemplate.update(insertDiaryQuery,insertDiaryParam);
        String lastInsertIdxQuery = "select last_insert_id()";
        return this.jdbcTemplate.queryForObject(lastInsertIdxQuery, int.class);

    }

    public List<GetDiaryRes> selectDiary(int userId){
        String selectDiaryQuery = "SELECT diary_id, user_id, create_date, title, content, status " +
                "FROM diary WHERE status='ACTIVE' and user_id=?;";
        int selectDiaryParam=userId;
        return this.jdbcTemplate.query(selectDiaryQuery,
                (rs,rowNum) -> new GetDiaryRes(
                        rs.getInt("diary_id"),
                        rs.getInt("user_id"),
                        rs.getDate("create_date"),
                        rs.getString("title"),
                        rs.getString("content"),
                        rs.getString("status")
                ), selectDiaryParam);
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
