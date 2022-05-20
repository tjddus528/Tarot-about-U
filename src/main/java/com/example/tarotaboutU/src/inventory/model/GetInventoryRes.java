package com.example.tarotaboutU.src.inventory.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class GetInventoryRes {
    private int inventoryId;
    private int userId;
    private String status;
    private List<GetTarotsPickedByUser> tarotList;
}
