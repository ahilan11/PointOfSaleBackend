/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.za.carolsstore.sale.service;

/**
 *
 * @author Vijay
 */
public interface InventoryServiceInt {
    String checkAvailableStock(String productID,String storeID, String quantity);
    String addStock(String productID, String storeID, int quantity);
    String decreaseStock(String productID, String storeID, int quantity);
}
