package com.example.tarotaboutU.src.inventory;

import com.example.tarotaboutU.src.inventory.model.*;
import com.example.tarotaboutU.src.user.model.PatchUserReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class InventoryDao {
    private JdbcTemplate jdbcTemplate;
    private List<GetTarotsPickedByUser> tarotList;


    @Autowired
    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public int saveTarotResult(int userId, PostTarotResultReq postTarotResultReq){
        String saveTarotResultQuery = "INSERT INTO tarot_by_user\n" +
                "(user_id, question_id, one_or_set, tarot_id, set_id, pick_date)\n" +
                "VALUES\n" +
                "(?,?,?,?,?,?);\n";
        Object[] saveTarotResultParams = new Object[]{
                userId,
                postTarotResultReq.getQuestionId(),
                postTarotResultReq.getOneOrSet(),
                postTarotResultReq.getTarotId(),
                postTarotResultReq.getSetId(),
                postTarotResultReq.getPickDate(),
        };
        this.jdbcTemplate.update(saveTarotResultQuery,saveTarotResultParams);
        String lastInsertIdQuery = "select last_insert_id()";
        return this.jdbcTemplate.queryForObject(lastInsertIdQuery,int.class);
    }
    public int insertInventory(int pickId, PostTarotResultReq postTarotResultReq){
        String selectTarotResultQuery = "SELECT\n" +
                "       user_id,\n" +
                "       pick_date,\n" +
                "       (SELECT inventory_id FROM user WHERE user_id = t.user_id ) as inventory_id\n" +
                "FROM tarot_by_user AS t\n" +
                "WHERE pick_id =?";
        int selectTarotResultParam = pickId;
        Inventory inventory = this.jdbcTemplate.queryForObject(selectTarotResultQuery,
                (rs, rowNum) -> new Inventory(
                        rs.getInt("user_id"),
                        rs.getDate("pick_date"),
                        rs.getInt("inventory_id")
                        ),selectTarotResultParam);

        String insertInventoryQuery =
                "INSERT INTO inventory\n" +
                "(inventory_id, user_id, pick_id, pick_date) VALUES\n" +
                "(?,?,?,?)";
        Object[] insertInventoryParams =
                new Object[]{
                        inventory.getInventoryId(),
                        inventory.getUserId(),
                        pickId,
                        inventory.getPickDate()
                };

        return this.jdbcTemplate.update(insertInventoryQuery,insertInventoryParams);
    }
    public List<GetTarotsPickedByUser> getInventoryRes(int userId){
        String getInventoryQuery = "select pick_id, user_id, question_id, one_or_set, tarot_id, set_id, pick_date " +
                "from tarot_by_user where user_id = ? and status='ACTIVE'";
        int getInventoryParam = userId;
        return this.jdbcTemplate.query(getInventoryQuery,
                (rs,rowNums) -> new GetTarotsPickedByUser(
                        rs.getInt("pick_id"),
                        rs.getInt("user_id"),
                        rs.getInt("question_id"),
                        rs.getString("one_or_set"),
                        rs.getInt("tarot_id"),
                        rs.getInt("set_id"),
                        rs.getDate("pick_date")
                ), getInventoryParam);
    }
    public int getUserIdByPickId(int pickId) {
        String getUserIdByPickIdQuery = "SELECT user_id FROM tarot_by_user WHERE pick_id=?";
        int getUserIdByPickIdParam = pickId;
        int userId = this.jdbcTemplate.queryForObject(getUserIdByPickIdQuery,int.class,getUserIdByPickIdParam);
        System.out.println("userId = " + userId);
        return userId;
    }
    public int deleteTarotResult(int pickId){
        String deleteTarotResultQuery = "UPDATE tarot_by_user SET status='INACTIVE' WHERE pick_id=?";
        int deleteTarotResultParams = pickId;
        return this.jdbcTemplate.update(deleteTarotResultQuery, deleteTarotResultParams);
    }
    public int checkTarotResultExists(int pickId){
        String checkTarotResultExistQuery = "select exists(select pick_id from tarot_by_user where pick_id = ?)";
        int checkTarotResultExistParams = pickId;
        return this.jdbcTemplate.queryForObject(checkTarotResultExistQuery,
                int.class,
                checkTarotResultExistParams);

    }
}
