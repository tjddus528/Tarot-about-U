package com.example.tarotaboutU.src.user;

import com.example.tarotaboutU.src.user.model.GetUserRes;
import com.example.tarotaboutU.src.user.model.PatchUserReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class UserDao {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<GetUserRes> getUsersList(){
        String getUsersQuery = "select user_id, inventory_id, name, birthday, gender, status from user";
        return this.jdbcTemplate.query(getUsersQuery,
                (rs,rowNum) -> new GetUserRes(
                        rs.getInt("user_id"),
                        rs.getInt("inventory_id"),
                        rs.getString("name"),
                        rs.getString("birthday"),
                        rs.getString("gender"),
                        rs.getString("status")
                ));
    }

    public GetUserRes getUser(int userId){
        String getUsersQuery = "select user_id, inventory_id, name, birthday, gender, status from user where user_id = ?";
        int getUserParam = userId;
        return this.jdbcTemplate.queryForObject(getUsersQuery,
                (rs,rowNum) -> new GetUserRes(
                        rs.getInt("user_id"),
                        rs.getInt("inventory_id"),
                        rs.getString("name"),
                        rs.getString("birthDay"),
                        rs.getString("gender"),
                        rs.getString("status")
                ), getUserParam);
    }
    public int modifyUser(int userId, PatchUserReq patchUserReq){
        String modifyUserQuery = "update user set name = ?, birthday = ?, gender = ? where user_id = ? ";
        Object[] modifyUserParams = new Object[]{patchUserReq.getName(), patchUserReq.getBirthday(), patchUserReq.getGender(), userId};

        int result = this.jdbcTemplate.update(modifyUserQuery,modifyUserParams);
        return result;
    }

}
