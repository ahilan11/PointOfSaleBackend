/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.za.carolsstore.report.dao;

import com.za.carolsstore.product.model.Product;
import com.za.carolsstore.report.model.Report;
import com.za.carolsstore.store.model.Store;
import java.time.Month;
import java.util.List;
import javax.swing.JTable;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

/**
 *
 * @author patri
 */
public interface iReportDao {

    List<Report> getStoreDailyTargetBar();

    List<Report> getStoresAchievedTargetBar(Month month);

    List<Report> getStoresTopSellingBar(int amount);

    List<Report> getStoresLeastSellingBar(int months, int amount);

    List<Report> getStoresTopRatedBar(Month month, int amount);

    List<Report> getEmployeesTopSellingBar(Store store, int amount);

    List<Report> getEmployeesTopSellingBar(int amount);

    List<Report> getProductBar(Product product);

    List<Report> getProductsTop40SellingBar();

    List<Report> getSalesBar(Store store, Month month);
}
