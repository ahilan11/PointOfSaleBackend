/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.za.carolsstore.sale.service;


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
public interface iSaleService {  
    //Create--------------------------------------------------------------------
    public String addSale(Sale sale);
    public String addProductToLineItem(Product product, int quantity, String saleID);
    
    //Read----------------------------------------------------------------------
    public Sale getSale(String saleID);
    public List<Sale> getSalesByStore(String storeID);
    public List<Sale> getSalesByEmployee(String employeeID);
    public List<Sale> getSalesOfProduct(String productID);
    public List<Sale> getSalesOfProductByStore(String productID, String storeID);
    public List<Sale> getSalesOfProductByEmployee(String productID, String employeeID);
    public List<Sale> getAllSales();
    public List<SaleLineItem>getSaleLineItems(String saleID);
    
    //Update--------------------------------------------------------------------
    public String updateSale(Sale sale);
    
    //Delete--------------------------------------------------------------------
    public String deleteSale(String saleID);
    public String clearSaleLineItem();
    public String removeProductFromLineItem(Product product, Employee manager);

    //PromoCode
    //Create--------------------------------------------------------------------
    public String addPromocode(Promocode promocde);
    
    //Read----------------------------------------------------------------------
    public Promocode getPromocode(String code);
    public List<Promocode> getAllPromode();
    
    //Update--------------------------------------------------------------------
    public String updatePromocode(Promocode promocode);
    
    //Delete--------------------------------------------------------------------
    public String deletePromocode(String code);
    
    //Inventory
    //Update--------------------------------------------------------------------
    int checkInventory(String storeID, String productID);
    String increaseInventory(String storeID, String productID, int amount);
    String decreaseInventory(String storeID, String productID, int amount);
}
