/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.za.carolsstore.report.service;


import com.za.carolsstore.product.model.Product;
import com.za.carolsstore.report.model.Report;
import com.za.carolsstore.store.model.Store;
import java.time.Month;
import java.util.List;

/**
 *
 * @author patri
 */
public interface iReportGenerator {
    List<Report> dailyTarget();

    List<Report> monthlyTarget(int month);

    List<Report> topSellingStores(int amount);

    List<Report> leastSellingStores(int months, int amount);

    List<Report> topRatedStores(int month, int amount);

    List<Report> topStoreEmployees(Store store, int amount);

    List<Report> topEmployees(int amount);

    List<Report> topProducts(Product product);

    List<Report> topSellingProducts();

    List<Report> topSales(Store store, int month);
}
