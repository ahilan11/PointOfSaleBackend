/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.za.carolsstore.sale.service;


import com.za.carolsstore.product.dao.ProductDaoImp;
import com.za.carolsstore.product.dao.iProductDao;
import com.za.carolsstore.product.model.Product;
import com.za.carolsstore.employee.model.Employee;
import com.za.carolsstore.sale.dao.SaleDao;
import com.za.carolsstore.sale.dao.iSaleDao;
import com.za.carolsstore.sale.model.Promocode;
import com.za.carolsstore.sale.model.Sale;
import com.za.carolsstore.sale.model.SaleLineItem;
import java.util.List;

/**
 *
 * @author nicad
 */
public class SaleService implements iSaleService{
    private final iSaleDao dao;
    private final iProductDao dao2;
    private final iSaleDao dao3;

    public SaleService() {
        dao = new SaleDao();
        dao2 = new ProductDaoImp();
        dao3 = new SaleDao();
    }

    //Create--------------------------------------------------------------------
    @Override
    public String addSale(Sale sale){
       return (dao.addSale(sale))?"Success":"Failure";
    }
    
    //Read----------------------------------------------------------------------
    @Override
    public Sale getSale(String saleID){
        return dao.getSale(saleID);
    }
    @Override
    public List<Sale> getSalesByStore(String storeID){
        return dao.getSalesByStore(storeID);
    }
    @Override
    public List<Sale> getSalesByEmployee(String employeeID){
        return dao.getSalesByEmployee(employeeID);
    }
    @Override
    public List<Sale> getSalesOfProduct(String productID){
        return dao.getSalesOfProduct(productID);
    }
    @Override
    public List<Sale> getSalesOfProductByStore(String productID, String storeID){
        return dao.getSalesOfProductByStore(productID, storeID);
    }
    @Override
    public List<Sale> getSalesOfProductByEmployee(String productID, String employeeID){
        return dao.getSalesOfProductByEmployee(productID, employeeID);
    }
    @Override
    public List<Sale> getAllSales(){
        return dao.getAllSales();
    }
    
    //Update--------------------------------------------------------------------
    @Override
    public String updateSale(Sale sale){
        return (dao.updateSale(sale))?"Success":"Failure";
    }
    
    @Override
    public String addProductToLineItem(Product product, int quantity,String saleID) {
        return (dao.addProductToLineItem(product, quantity, saleID))?"Success":"Failure";
    }

    @Override
    public String removeProductFromLineItem(Product product, Employee manager) {
        
        return (dao.removeProductFromLineItem(product, manager))?"Success":"Failure";
    }
    
    @Override
    public String clearSaleLineItem(){
        return (dao.clearSaleLineItem())?"Success":"Failure";
    }
    
    //Delete--------------------------------------------------------------------
    @Override
    public String deleteSale(String saleID){
        return (dao.deleteSale(saleID))?"Success":"Failure";
    }

    //PromoCode
    //Create--------------------------------------------------------------------
    @Override
    public String addPromocode(Promocode promocode) {
        return (dao.addPromocode(promocode))?"Success":"Failure";
    }

    //Read----------------------------------------------------------------------
    @Override
    public Promocode getPromocode(String code) {
        return dao.getPromocode(code);
    }

    @Override
    public List<Promocode> getAllPromode() {
        return dao.getAllPromode();
    }

    //Update--------------------------------------------------------------------
    @Override
    public String updatePromocode(Promocode promocode) {
        return (dao.updatePromocode(promocode))?"Success":"Failure";
    }

    //Delete--------------------------------------------------------------------
    @Override
    public String deletePromocode(String code) {
        return (dao.deletePromocode(code))?"Success":"Failure";
    }
    
    //Inventory-----------------------------------------------------------------
    
    @Override
    public int checkInventory(String storeID, String productID){
        return dao.checkInventory(storeID, productID);
    }
    
    @Override
    public String decreaseInventory(String storeID, String productID, int amount){
        return (dao.decreaseInventory(storeID, productID, amount))?"Success":"Failure";
    }
    
    @Override
    public String increaseInventory(String storeID, String productID, int amount){
        return (dao.increaseInventory(storeID, productID, amount))?"Success":"Failure";
    }

    @Override
    public List<SaleLineItem> getSaleLineItems(String saleID) {
        List<SaleLineItem> lineItems = dao.getSaleLineItems(saleID);
//        for (SaleLineItem lineItem : lineItems) {
//            lineItem.setProd(dao2.getProduct(lineItem.getProduct()));
//            lineItem.setSale(dao3.getSale(lineItem.getSaleID()));
//        }
        return lineItems;
    }
}
