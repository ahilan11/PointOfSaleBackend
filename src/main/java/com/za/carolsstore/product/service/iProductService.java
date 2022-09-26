/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.za.carolsstore.product.service;


import com.za.carolsstore.product.model.Category;
import com.za.carolsstore.product.model.InterBranchTransfer;
import com.za.carolsstore.product.model.KeepAside;
import com.za.carolsstore.product.model.Product;
import com.za.carolsstore.store.model.Store;
import java.util.List;

/**
 *
 * @author patri
 */
public interface iProductService {
    //Create--------------------------------------------------------------------
    String addProduct(Product product);
    String addKeepAside(KeepAside keepAside);
    String addIBT(InterBranchTransfer transfer);
    String addCategory(Category category);
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
    String updateProduct(Product product);
    String updateKeepAside(KeepAside keepAside);
    String updateIbt(InterBranchTransfer transfer);
    String approveIbt(InterBranchTransfer transfer);
    String receiveIbt(InterBranchTransfer transfer);
    String updateCategory(Category category);

    //Delete--------------------------------------------------------------------
    String deleteProduct(String productID);
    String deleteKeepAside(String keepAsideID);
    String deleteIbt(String transferID);
    String deleteCategory(String categoryID);
}
