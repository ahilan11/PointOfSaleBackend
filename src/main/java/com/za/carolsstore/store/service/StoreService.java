/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.za.carolsstore.store.service;


import com.za.carolsstore.employee.dao.EmployeeDaoImp;
import com.za.carolsstore.employee.dao.iEmployeeDao;
import com.za.carolsstore.sale.dao.SaleDao;
import com.za.carolsstore.sale.dao.iSaleDao;
import com.za.carolsstore.store.dao.StoreDao;
import com.za.carolsstore.store.dao.iStoreDao;
import com.za.carolsstore.store.model.Review;
import com.za.carolsstore.store.model.Store;
import java.util.List;

/**
 *
 * @author nicad
 */
public class StoreService implements iStoreService{
    private final iStoreDao storeDao;
    private final iEmployeeDao employeeDao;
    private final iSaleDao saleDao;

    public StoreService() {
        this.storeDao = new StoreDao();
        this.employeeDao = new EmployeeDaoImp();
        this.saleDao = new SaleDao();
    }

    @Override
    public String addStore(Store store) {
        return (storeDao.addStore(store))?"Success":"Failure";
    }

    @Override
    public Store getStore(String storeID) {
        Store store = storeDao.getStore(storeID);
        store.setEmployees(employeeDao.getEmployeesByStore(storeID));
        store.setSales(saleDao.getSalesByStore(storeID));
        return store;
    }

    @Override
    public String updateStore(Store store) {
        return (storeDao.updateStore(store))?"Success":"Failure";
    }

    @Override
    public String deleteStore(String storeID) {
        return (storeDao.deleteStore(storeID))?"Success":"Failure";
    }

    @Override
    public String addReview(Review review) {
        return (storeDao.addReview(review))?"Success":"Failure";
    }

    @Override
    public Review getReview(String reviewID) {
        return storeDao.getReview(reviewID);
    }

    @Override
    public List<Review> getReviewsByStore(String storeID) {
        return storeDao.getReviewsByStore(storeID);
    }

    @Override
    public List<Review> getAllReviews() {
        return storeDao.getAllReviews();
    }

    @Override
    public String deleteReview(String reviewID) {
        return (storeDao.deleteReview(reviewID))?"Success":"Failure";
    }
}
