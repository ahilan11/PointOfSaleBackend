/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.za.carolsstore.sale.service;

import com.za.carolsstore.sale.dao.InventoryDao;
import com.za.carolsstore.sale.dao.InventoryDaoInt;

/**
 *
 * @author Vijay
 */
public class InventoryService implements InventoryServiceInt{
private InventoryDaoInt dao;
    public InventoryService(){
        dao = new InventoryDao();
    }
    @Override
    public String checkAvailableStock(String productID, String storeID, String quantity) {
        int quan = Integer.parseInt(quantity);
        return (quan < dao.checkStock(storeID, productID))?"success":"failure";
            
        
    }

    @Override
    public String addStock(String productID, String storeID, int quantity) {
        if(quantity > 0){
            return dao.increaseInventory(storeID, productID, quantity)?"success":"failure";
        }
        return "incorrectNumber";
    }

    @Override
    public String decreaseStock(String productID, String storeID, int quantity) {
        if(quantity > 0){
            return dao.decreaseInventory(storeID, productID, quantity)?"success":"failure";
        }
        return "incorrectNumber";
    }
    
}
