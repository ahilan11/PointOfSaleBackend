/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.za.carolsstore.report.service;


import com.za.carolsstore.product.model.Product;
import com.za.carolsstore.report.dao.ReportDaoImp;
import com.za.carolsstore.report.dao.iReportDao;
import com.za.carolsstore.report.model.Report;
import com.za.carolsstore.store.model.Store;
import java.awt.geom.Rectangle2D;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.graphics2d.svg.SVGGraphics2D;

/**
 *
 * @author patri
 */
public class ReportGeneratorImp implements iReportGenerator{
    private Report report;
    private final iReportDao reportDao;

    public ReportGeneratorImp() {
        this.reportDao = new ReportDaoImp();
    }

    public List<Report> dailyTarget(){
        return reportDao.getStoreDailyTargetBar();
    }
    public List<Report> monthlyTarget(int month){
        
        return reportDao.getStoresAchievedTargetBar(Month.of(month));
    }
    public List<Report> topEmployees(int limit){
        return reportDao.getEmployeesTopSellingBar(limit);
    }
    public List<Report> topStoreEmployees(Store store, int limit){
        return reportDao.getEmployeesTopSellingBar(store, limit);
    }
    public List<Report> topProducts(Product product){
        return reportDao.getProductBar(product);
    }
    public List<Report> topSellingProducts(){
        return reportDao.getProductsTop40SellingBar();
    }
    public List<Report> topSales(Store store, int month){
        return reportDao.getSalesBar(store, Month.of(month));
    }
    public List<Report> leastSellingStores(int month, int limit){
        return reportDao.getStoresLeastSellingBar(month, limit);
    }
    public List<Report> topSellingStores(int limit){
        return reportDao.getStoresTopSellingBar( limit);
    }
    public List<Report> topRatedStores(int month, int limit){
        return reportDao.getStoresTopRatedBar(Month.of(month), limit);
    }

  
  
}