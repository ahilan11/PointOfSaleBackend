/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.za.carolsstore.store.dao;


import com.za.carolsstore.store.model.Review;
import com.za.carolsstore.store.model.Store;
import java.util.List;

/**
 *
 * @author patri
 */
public interface iStoreDao {
    //Create--------------------------------------------------------------------
    public boolean addStore(Store store);
    
    //Read----------------------------------------------------------------------
    public Store getStore(String storeID);
    
    //Update--------------------------------------------------------------------
    public boolean updateStore(Store store);
    
    //Delete--------------------------------------------------------------------
    public  boolean deleteStore(String storeID);
    
    //FOR REVIEW
    //Create--------------------------------------------------------------------
    public boolean addReview(Review review);
    
    //Read----------------------------------------------------------------------
    public Review getReview(String reviewID);
    public List<Review> getReviewsByStore(String storeID);
    public List<Review> getAllReviews();
    
    //Delete--------------------------------------------------------------------
    public  boolean deleteReview(String reviewID);
}
