package com.example.tarotaboutU.src.card;

import com.example.tarotaboutU.src.card.model.GetCardRes;
import com.example.tarotaboutU.src.card.model.GetCardSetRes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class CardDao {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<GetCardRes> getCardList(){
        String getCardsQuery = "select * from tarot_card";
        return this.jdbcTemplate.query(getCardsQuery,
                (rs,rowNum) -> new GetCardRes(
                        rs.getInt("tarot_id"),
                        rs.getString("tarot_name_e"),
                        rs.getString("tarot_name_k"),
                        rs.getString("tarot_url_image"),
                        rs.getString("meaning"),
                        rs.getString("yes_or_no"),
                        rs.getString("property"),
                        rs.getString("today_tarot"),
                        rs.getString("love_tarot")
                ));
    }
    public List<GetCardSetRes> getCardSetList(){
        String getCardSetQuery = "select set_id, set_info, mind_tarot from tarot_set";
        return this.jdbcTemplate.query(getCardSetQuery,
                (rs,rowNum) -> new GetCardSetRes(
                        rs.getString("set_id"),
                        rs.getString("set_info"),
                        rs.getString("mind_tarot")
                ));
    }
    public GetCardRes getOneCard(int tarotId){
        String getOneCardQuery = "select * from tarot_card where tarot_id = ?";
        int getOneCardParam = tarotId;
        return this.jdbcTemplate.queryForObject(getOneCardQuery,
                (rs,rowNum) -> new GetCardRes(
                        rs.getInt("tarot_id"),
                        rs.getString("tarot_name_e"),
                        rs.getString("tarot_name_k"),
                        rs.getString("tarot_url_image"),
                        rs.getString("meaning"),
                        rs.getString("yes_or_no"),
                        rs.getString("property"),
                        rs.getString("today_tarot"),
                        rs.getString("love_tarot")
                ), getOneCardParam);
    }
    public GetCardSetRes getThreeCard(String setId){
        String getThreeCardQuery = "select set_id, set_info, mind_tarot from tarot_set where set_id = ?";
        String getThreeCardParam = setId;
        return this.jdbcTemplate.queryForObject(getThreeCardQuery,
                (rs,rowNum) -> new GetCardSetRes(
                        rs.getString("set_id"),
                        rs.getObject("set_info"),
                        rs.getString("mind_tarot")
                ), getThreeCardParam);
    }
}
