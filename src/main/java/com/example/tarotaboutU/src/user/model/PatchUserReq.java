package com.example.tarotaboutU.src.user.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PatchUserReq {
    private String name;
    private String birthday;
    private String gender;
}
