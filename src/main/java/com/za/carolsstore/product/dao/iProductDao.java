/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.za.carolsstore.product.dao;

/**
 *
 * @author patri
 */


import com.za.carolsstore.product.model.Category;
import com.za.carolsstore.product.model.InterBranchTransfer;
import com.za.carolsstore.product.model.KeepAside;
import com.za.carolsstore.product.model.Product;
import com.za.carolsstore.store.model.Store;
import java.util.List;

public interface iProductDao {
    //Create--------------------------------------------------------------------
    boolean addProduct(Product product);
    boolean addKeepAside(KeepAside keepAside);
    boolean addIBT(InterBranchTransfer transfer);
    boolean addCategory(Category category);
      List<Store> getStoresByProduct(String productID);
    //Read----------------------------------------------------------------------
    Product getProduct(String productID);
    List<Product> getProductsByStore(String storeID);
    List<Product> getProductsByCategory(String categoryID);
    List<Product> getAllProducts();
    KeepAside getKeepAside(String keepAsideID);
    List<KeepAside> getKeepAsidesByStore(String storeID);
    List<KeepAside> getAllKeepAsides();
    InterBranchTransfer getIbt(String transferID);
    List<InterBranchTransfer> getIbtsByStore(String storeID);
    List<InterBranchTransfer> getAllIbts();
    Category getCategory(String categoryID);
    List<Category> getAllCategories();
    
    //Update--------------------------------------------------------------------
    boolean updateProduct(Product product);
    boolean setProductOnSale(Product product, double onSale);
    boolean updateKeepAside(KeepAside keepAside);
    boolean updateIbt(InterBranchTransfer transfer);
    boolean updateCategory(Category category, String categoryID);

    //Delete--------------------------------------------------------------------
    boolean deleteProduct(String productID);
    boolean deleteKeepAside(String keepAsideID);
    boolean deleteIbt(String transferID);
    boolean deleteCategory(String categoryID);   
}
