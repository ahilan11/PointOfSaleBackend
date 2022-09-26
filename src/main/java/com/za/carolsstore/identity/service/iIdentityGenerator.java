package com.za.carolsstore.identity.service;

import com.za.carolsstore.product.model.Category;
import com.za.carolsstore.product.model.InterBranchTransfer;
import com.za.carolsstore.product.model.KeepAside;
import com.za.carolsstore.product.model.Product;
import com.za.carolsstore.employee.model.Employee;
import com.za.carolsstore.message.model.Email;
import com.za.carolsstore.sale.model.Sale;
import com.za.carolsstore.store.model.Review;
import com.za.carolsstore.store.model.Store;
import com.za.carolsstore.store.model.Transaction;


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */

/**
 *
 * @author patri
 */
public interface iIdentityGenerator {
   public String generateID(Category category);
    public String generateID(Email email);
    public String generateID(Employee employee);
    public String generateID(InterBranchTransfer ibt);
    public String generateID(KeepAside keepAside);
    public String generateID(Product product);
    public String generateID(Category category, Product product);
    public String generateID(Sale sale);
    public String generateID(Store store);
  //  public String generateID(Transaction transaction);
  //  public String generateID(Review review);
}
