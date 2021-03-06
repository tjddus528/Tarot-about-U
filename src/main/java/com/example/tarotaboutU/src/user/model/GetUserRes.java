package com.example.tarotaboutU.src.user.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GetUserRes {
    private int userId;
    private int inventoryId;
    private String name;
    private String birthday;
    private String gender;
    private String status;
}
