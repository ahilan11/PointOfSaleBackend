/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.za.carolsstore.sale.dao;

import com.za.carolsstore.product.model.Product;
import com.za.carolsstore.employee.model.Employee;
import com.za.carolsstore.sale.model.Promocode;
import com.za.carolsstore.sale.model.Sale;
import com.za.carolsstore.sale.model.SaleLineItem;
import java.util.List;

/**
 *
 * @author patri
 */
public interface iSaleDao {
    //Create--------------------------------------------------------------------
    boolean addSale(Sale sale);
    boolean addProductToLineItem(Product product, int quantity,String saleID);
    
    //Read----------------------------------------------------------------------
    Sale getSale(String saleID);
    List<Sale> getSalesByStore(String storeID);
    List<Sale> getSalesByEmployee(String employeeID);
    List<Sale> getSalesOfProduct(String productID);
    List<Sale> getSalesOfProductByStore(String productID, String storeID);
    List<Sale> getSalesOfProductByEmployee(String productID, String employeeID);
    List<Sale> getAllSales();
    List<SaleLineItem> getSaleLineItems(String saleID);
    
    //Update--------------------------------------------------------------------
    boolean updateSale(Sale sale);
    
    //Delete--------------------------------------------------------------------
    boolean deleteSale(String saleID);
    boolean clearSaleLineItem();
    boolean removeProductFromLineItem(Product product, Employee manager);
    
    //PromoCode
    //Create--------------------------------------------------------------------
    boolean addPromocode(Promocode promocode);
    
    //Read----------------------------------------------------------------------
    Promocode getPromocode(String code);
    List<Promocode> getAllPromode();
    
    //Update--------------------------------------------------------------------
    boolean updatePromocode(Promocode promocode);
    
    //Delete--------------------------------------------------------------------
    boolean deletePromocode(String code);
    
    //Inventory-----------------------------------------------------------------
    //Read----------------------------------------------------------------------
    int checkInventory(String storeID, String productID);
    
    //Update--------------------------------------------------------------------
    boolean increaseInventory(String storeID, String productID, int amount);
    boolean decreaseInventory(String storeID, String productID, int amount);
}
