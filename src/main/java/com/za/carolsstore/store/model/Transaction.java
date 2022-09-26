/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.za.carolsstore.store.model;


import com.za.carolsstore.product.model.Product;
import com.za.carolsstore.employee.model.Employee;
import java.sql.Date;
import java.sql.Time;
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
public class Transaction {
    private String transactionID, productID, employeeID;
    private int quantityLoggedIn, previousQuantity, newQuantity;
    private Date dateTransacted;
    private Time timeTransacted;
    private Product product;
    private Employee employee;
}
