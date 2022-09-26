/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.za.carolsstore.store.service;


import com.za.carolsstore.store.model.Review;
import com.za.carolsstore.store.model.Store;
import java.util.List;

/**
 *
 * @author patri
 */
public interface iStoreService {
    //Create--------------------------------------------------------------------
    String addStore(Store store);
    
    //Read----------------------------------------------------------------------
    Store getStore(String storeID);
    
    //Update--------------------------------------------------------------------
    String updateStore(Store store);
    
    //Delete--------------------------------------------------------------------
    String deleteStore(String storeID);
    
    //FOR REVIEW
    //Create--------------------------------------------------------------------
    String addReview(Review review);
    
    //Read----------------------------------------------------------------------
    Review getReview(String reviewID);
    List<Review> getReviewsByStore(String storeID);
    List<Review> getAllReviews();
  
    //Delete--------------------------------------------------------------------
    String deleteReview(String reviewID);
    
}
