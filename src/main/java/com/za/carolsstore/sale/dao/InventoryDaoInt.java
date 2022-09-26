/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.za.carolsstore.sale.dao;

/**
 *
 * @author Vijay
 */
public interface InventoryDaoInt {
    boolean decreaseInventory(String storeID, String productID, int quantity);
        boolean increaseInventory(String storeID, String productID, int quantity);
int checkStock(String storeID, String productID);
}
