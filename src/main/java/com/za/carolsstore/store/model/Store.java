/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.za.carolsstore.store.model;


import com.za.carolsstore.product.model.Product;
import com.za.carolsstore.employee.model.Employee;
import com.za.carolsstore.sale.model.Sale;
import java.util.List;
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
public class Store {
    private String storeId;
    private String branchName;
    private float dailyTarget, monthlyTarget;
    private List<Employee> employees;
    private List<Sale> sales;
    private List<Product> inventory;
}
