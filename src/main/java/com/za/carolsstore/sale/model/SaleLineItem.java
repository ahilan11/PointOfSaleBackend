/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.za.carolsstore.sale.model;

import com.za.carolsstore.product.model.Product;
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
public class SaleLineItem {
    private String saleID;
    private String product;
    private Integer quantity;
    private String storeID;
}
