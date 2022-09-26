/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.za.carolsstore.product.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author patri
 */

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class InventoryLineItem {
    private String saleID;
    private Product product;
    private Integer quantity;
}
