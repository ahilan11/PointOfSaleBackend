package com.za.carolsstore.product.model;

import com.za.carolsstore.store.model.Store;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author patri
 */

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class InterBranchTransfer {
    private String ibtID, productID, toStoreID, fromStoreID;
    private Product product;
    private Store toStore, fromStore;
    private boolean approved, received;
    private String customerPhoneNumber;
    private LocalDate dateRequested, dateApproved;
    private LocalTime timeRequested, timeApproved;
}
