/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.za.carolsstore.store.model;

import java.sql.Date;
import java.sql.Time;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author nicad
 */

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Review {
    private String id;
    private String storeID;
    private String comment;
    private int rating;//out of 10
    private Date date;
    private Time time;
}
