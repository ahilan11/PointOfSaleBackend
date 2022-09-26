/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.za.carolsstore.sale.model;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author nicad
 */

@AllArgsConstructor
@NoArgsConstructor
@Setter
@ToString
@Getter
public class Sale {
    private String saleID;
    private String dateSold;
    private String timeSold;
    private String storeID;
    private String employeeID;
    private List<SaleLineItem> lineItems;
    private Promocode promocode;
    

    public Sale(String saleID, String dateSold, String timeSold, String storeID, String employeeID) {
        this.saleID = saleID;
        this.dateSold = dateSold;
        this.timeSold = timeSold;
        this.storeID = storeID;
        this.employeeID = employeeID;
    }
}
