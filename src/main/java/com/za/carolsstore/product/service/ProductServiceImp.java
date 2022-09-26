/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.za.carolsstore.product.service;


import com.za.carolsstore.product.dao.ProductDaoImp;
import com.za.carolsstore.product.dao.iProductDao;
import com.za.carolsstore.product.model.Category;
import com.za.carolsstore.product.model.InterBranchTransfer;
import com.za.carolsstore.product.model.KeepAside;
import com.za.carolsstore.product.model.Product;
import com.za.carolsstore.store.dao.StoreDao;
import com.za.carolsstore.store.dao.iStoreDao;
import com.za.carolsstore.store.model.Store;
import java.util.List;


/**
 *
 * @author nicad
 */
public class ProductServiceImp implements iProductService{
    private final iProductDao productDao;
    private final iStoreDao storeDao;

    public ProductServiceImp() {
        this.productDao = new ProductDaoImp();
        this.storeDao = new StoreDao();
    }

    @Override
    public String addProduct(Product product) {
        return (productDao.addProduct(product))?"Success":"Failure";
    }
    
    @Override
    public String addKeepAside(KeepAside keepAside) {
        return (productDao.addKeepAside(keepAside))?"Success":"Failure";
    }
    
    @Override
    public String addIBT(InterBranchTransfer transfer) {
        return (productDao.addIBT(transfer))?"Success":"Failure";
    }
    
    @Override
    public String addCategory(com.za.carolsstore.product.model.Category category) {
        return (productDao.addCategory( category))?"Success":"Failure";
    }

public List<Store> getStoresByProduct(String productID){
            return productDao.getStoresByProduct(productID);
        }
    @Override
    public Product getProduct(String productID) {
        return productDao.getProduct(productID);
    }

    @Override
    public List<Product> getProductsByStore(String storeID) {
        return productDao.getProductsByStore(storeID);
    }

    @Override
    public List<Product> getProductsByCategory(String categoryID) {
        return productDao.getProductsByCategory(categoryID);
    }

    @Override
    public List<Product> getAllProducts() {
        return productDao.getAllProducts();
    }
    
    @Override
    public KeepAside getKeepAside(String keepAsideID) {
        return productDao.getKeepAside(keepAsideID);
    }
    
    @Override
    public List<KeepAside> getKeepAsidesByStore(String storeID) {
        List<KeepAside> keepAsides = productDao.getKeepAsidesByStore(storeID);
        for (KeepAside ka: keepAsides) {
            ka.setProduct(productDao.getProduct(ka.getProductID()));
            ka.setStore(storeDao.getStore(ka.getStoreID()));
        }
        return keepAsides;
    }

    @Override
    public List<KeepAside> getAllKeepAsides() {
        List<KeepAside> keepAsides = productDao.getAllKeepAsides();
        for (KeepAside ka: keepAsides) {
            ka.setProduct(productDao.getProduct(ka.getProductID()));
            ka.setStore(storeDao.getStore(ka.getStoreID()));
        }
        return keepAsides;
    }
    
    @Override
    public InterBranchTransfer getIbt(String transferID) {
        InterBranchTransfer transfer = productDao.getIbt(transferID);
        transfer.setProduct(productDao.getProduct(transfer.getProductID()));
        transfer.setToStore(storeDao.getStore(transfer.getToStoreID()));
        transfer.setFromStore(storeDao.getStore(transfer.getFromStoreID()));
        return transfer;
    }

    @Override
    public List<InterBranchTransfer> getIbtsByStore(String storeID) {
        List<InterBranchTransfer> transfers = productDao.getIbtsByStore(storeID);
        for (InterBranchTransfer transfer: transfers) {
            transfer.setProduct(productDao.getProduct(transfer.getProductID()));
            transfer.setToStore(storeDao.getStore(transfer.getToStoreID()));
            transfer.setFromStore(storeDao.getStore(transfer.getFromStoreID()));
        }
        return transfers;
    }

    @Override
    public List<InterBranchTransfer> getAllIbts() {
        List<InterBranchTransfer> transfers = productDao.getAllIbts();
        for (InterBranchTransfer transfer: transfers) {
            transfer.setProduct(productDao.getProduct(transfer.getProductID()));
            transfer.setToStore(storeDao.getStore(transfer.getToStoreID()));
            transfer.setFromStore(storeDao.getStore(transfer.getFromStoreID()));
        }
        return transfers;
    }
    
    @Override
    public Category getCategory(String categoryID) {
        return productDao.getCategory(categoryID);
    }

    @Override
    public List<Category> getAllCategories() {
        return productDao.getAllCategories();
    }

    @Override
    public String updateProduct(Product product) {
        return (productDao.updateProduct(product))?"Success":"Failure";
    }
    
    @Override
    public String updateKeepAside(KeepAside keepAside) {
        return (productDao.updateKeepAside(keepAside))?"Success":"Failure";
    }
    
    @Override
    public String updateIbt(InterBranchTransfer transfer) {
        return (productDao.updateIbt(transfer))?"Success":"Failure";
    }
    
    @Override
    public String approveIbt(InterBranchTransfer transfer) {
        transfer.setApproved(true);
        return (productDao.updateIbt(transfer))?"Success":"Failure";
    }

    @Override
    public String receiveIbt(InterBranchTransfer transfer) {
        transfer.setReceived(true);
        return (productDao.updateIbt(transfer))?"Success":"Failure";
    }
    
    @Override
    public String updateCategory(Category category) {
        return (productDao.updateCategory(category ,category.getCategoryID()))?"Success":"Failure";
    }

    @Override
    public String deleteProduct(String productID) {
        return (productDao.deleteProduct(productID))?"Success":"Failure";
    }
    
    @Override
    public String deleteKeepAside(String keepAsideID) {
        return (productDao.deleteKeepAside(keepAsideID))?"Success":"Failure";
    }
    
    @Override
    public String deleteIbt(String transferID) {
        return (productDao.deleteIbt(transferID))?"Success":"Failure";
    }
    
    @Override
    public String deleteCategory(String categoryID) {
        return (productDao.deleteCategory(categoryID))?"Success":"Failure";
    }
}
