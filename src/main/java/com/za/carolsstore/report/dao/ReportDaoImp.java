/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.za.carolsstore.report.dao;

import com.za.carolsstore.product.model.Product;
import com.za.carolsstore.config.JDBCConfig;
import com.za.carolsstore.report.model.Report;
import com.za.carolsstore.sale.dao.SaleDao;
import com.za.carolsstore.store.model.Store;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

/**
 *
 * @author patri
 */
public class ReportDaoImp implements iReportDao {

    private JDBCConfig config = new JDBCConfig();
    private Connection con;
    private PreparedStatement ps;
    private ResultSet rs;

    public ReportDaoImp() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ReportDaoImp.class.getName()).log(Level.SEVERE, null, ex);
        }
        String url = config.readURL();
        try {
            con = DriverManager.getConnection(url, config.readPropertiesUser(), config.readPropertiesPassword());
        } catch (SQLException ex) {
            Logger.getLogger(ReportDaoImp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<Report> getStoreDailyTargetBar() {
        List<Report> reports = new ArrayList();
        List<Double> numbers = new ArrayList();
        try {
            ps = con.prepareStatement("Select storeID, branchName, dailyTarget, sum(price*quantity) as totalSales, "
                    + "(100 - sum(price*quantity)/dailyTarget*100) as percentageAway, "
                    + "day(dateSold) as today from product inner join(salelineitem inner join "
                    + "(sale inner join store on sale.storeID = store.ID) on salelineitem.saleID = sale.ID) "
                    + "on product.ID = salelineitem.productID group by storeID,day(dateSold) "
                    + "having today = day(now())");
            rs = ps.executeQuery();
            while (rs.next()) {
                            Report report = new Report();

                double totalSales = Math.round(rs.getDouble("totalSales") * 1.00);
                double dailyTarget = rs.getDouble("dailyTarget");
                double percentSold = Math.round((totalSales / dailyTarget * 100) * 100.0);
                numbers.add(totalSales);
                numbers.add(dailyTarget);
                numbers.add(percentSold);
                report.setValues(numbers);
                report.setNames(List.of(rs.getString("storeID"),rs.getString("branchName")));
reports.add(report);

            }
        } catch (SQLException ex) {
            Logger.getLogger(ReportDaoImp.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ReportDaoImp.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ReportDaoImp.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return reports;
    }


    @Override
    public List<Report> getStoresAchievedTargetBar(Month month) {
List<Report> reports = new ArrayList();
        List<Double> numbers = new ArrayList();    
        try {
            ps = con.prepareStatement("select storeID,branchName, sum(price*quantity) as totalSales, "
                    + "month(dateSold) as chosenMonth, monthlyTarget from product "
                    + "inner join(salelineitem inner join(sale inner join store on sale.storeID = store.ID) "
                    + "on salelineitem.saleID = sale.ID) on product.ID = salelineitem.productID "
                    + "group by storeID, month(dateSold) having chosenMonth = ? "
                    + "order by sum(price*quantity) desc");
            ps.setInt(1, month.getValue());
            rs = ps.executeQuery();
            while (rs.next()) {
                
                 Report report = new Report();

                double totalSales = Math.round(rs.getDouble("totalSales") * 1.00);
                double dailyTarget = rs.getDouble("monthlyTarget");
                double percentSold = Math.round((totalSales / dailyTarget * 100) * 100.0);
                numbers.add(totalSales);
                numbers.add(dailyTarget);
                numbers.add(percentSold);
                report.setValues(numbers);
                report.setNames(List.of(rs.getString("storeID"),rs.getString("branchName")));
reports.add(report);
            }
           
        } catch (SQLException ex) {
            Logger.getLogger(ReportDaoImp.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ReportDaoImp.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ReportDaoImp.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return reports;
    }

 
    @Override
    public List<Report> getStoresLeastSellingBar(int amount, int months) {
List<Report> reports = new ArrayList();
        List<Double> numbers = new ArrayList();
        try {
            ps = con.prepareStatement("Select branchName, sum(price*quantity)/? as totalSales, storeID, dateSold "
                    + "from product inner join(salelineitem inner join (sale inner join store on store.ID=sale.storeID) on salelineitem.saleID = sale.ID) "
                    + "on product.ID = salelineitem.productID "
                    + "group by storeID having year(now()) = year(dateSold) "
                    + "and month(dateSold) between month(dateSold)-? and month(now()) order by totalSales asc limit ?");
            ps.setInt(1, months);
            ps.setInt(2, months);
            ps.setInt(3, amount);
            rs = ps.executeQuery();
            while (rs.next()) {

                 Report report = new Report();

                double totalSales = Math.round(rs.getDouble("totalSales") * 1.00);
                numbers.add(totalSales);
                report.setValues(numbers);
                report.setNames(List.of(rs.getString("storeID"),rs.getString("branchName")));
reports.add(report);            }
        } catch (SQLException ex) {
            Logger.getLogger(ReportDaoImp.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ReportDaoImp.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ReportDaoImp.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return reports;
    }

    @Override
    public List<Report> getStoresTopRatedBar(Month month, int amount) {
List<Report> reports = new ArrayList();
        List<Double> numbers = new ArrayList();
        if (con != null) {
            try {
                ps = con.prepareStatement("Select storeID, branchName, month(date) as months, avg(rating) as ratings "
                        + "From review inner join store on review.storeID = store.ID group by storeID, months having months= ? order by avg(rating) desc limit ?");
                ps.setInt(1, month.getValue());
                ps.setInt(2, amount);
                rs = ps.executeQuery();
                while (rs.next()) {
 Report report = new Report();

                double totalSales = Math.round(rs.getDouble("totalSales") * 1.00);
                numbers.add(totalSales);
                report.setValues(numbers);
                report.setNames(List.of(rs.getString("storeID"),rs.getString("branchName")));
reports.add(report);                   }
            } catch (SQLException ex) {
                Logger.getLogger(SaleDao.class.getName()).log(Level.SEVERE, null, ex);
            } finally {

                if (ps != null) {
                    try {
                        ps.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(SaleDao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                if (rs != null) {
                    try {
                        rs.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(SaleDao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
        return reports;
    }

    @Override
    public List<Report> getEmployeesTopSellingBar(Store store, int amount) {
List<Report> reports = new ArrayList();
        List<Double> numbers = new ArrayList();        try {
            ps = con.prepareStatement("Select sale.storeID, employeeID, employee.name, employee.surname, "
                    + "sum(price*quantity) as totalSales from product inner join(salelineitem inner join "
                    + "(sale inner join employee on employee.ID = sale.employeeID) on salelineitem.saleID = sale.ID) "
                    + "on product.ID = salelineitem.productID group by employeeID having sale.storeID=? "
                    + "order by sum(price*quantity) desc limit ?");
            ps.setString(1, store.getStoreId());
            ps.setInt(2, amount);
            rs = ps.executeQuery();
            while (rs.next()) {
 Report report = new Report();

                double totalSales = Math.round(rs.getDouble("totalSales") * 1.00);
                numbers.add(totalSales);
                report.setValues(numbers);
                report.setNames(List.of(rs.getString("storeID"),rs.getString("branchName")));
reports.add(report);               }
        } catch (SQLException ex) {
            Logger.getLogger(ReportDaoImp.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ReportDaoImp.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ReportDaoImp.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return reports;
    }

    @Override
    public List<Report> getEmployeesTopSellingBar(int amount) {
List<Report> reports = new ArrayList();
        List<Double> numbers = new ArrayList();        try {
            ps = con.prepareStatement("Select sale.storeID, employeeID, employee.name as name, employee.surname, "
                    + "sum(price*quantity) as totalSales from product inner join(salelineitem inner join "
                    + "(sale inner join employee on employee.ID = sale.employeeID) on salelineitem.saleID = sale.ID) "
                    + "on product.ID = salelineitem.productID group by employeeID "
                    + "order by sum(price*quantity) desc");
            rs = ps.executeQuery();
            while (rs.next()) {
 Report report = new Report();

                double totalSales = Math.round(rs.getDouble("totalSales") * 1.00);
                numbers.add(totalSales);
                report.setValues(numbers);
                report.setNames(List.of(rs.getString("storeID"),rs.getString("employeeID"),rs.getString("name")));
reports.add(report);               }
        } catch (SQLException ex) {
            Logger.getLogger(ReportDaoImp.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ReportDaoImp.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ReportDaoImp.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return reports;
    }

    @Override
    public List<Report> getProductBar(Product product) {
List<Report> reports = new ArrayList();
        List<Double> numbers = new ArrayList();        try {
            ps = con.prepareStatement("select employeeID, name, surname, productID, sum(quantity) as amountSold "
                    + "from salelineitem inner join (sale inner join employee on sale.employeeID = employee.ID) "
                    + "on salelineitem.saleID = sale.ID "
                    + "group by employeeID, productID having productID=? "
                    + "order by amountSold desc");
            ps.setString(1, product.getId());
            rs = ps.executeQuery();
            while (rs.next()) {
 Report report = new Report();

                double totalSales = Math.round(rs.getDouble("amountSold") * 1.00);
                numbers.add(totalSales);
                report.setValues(numbers);
                report.setNames(List.of(rs.getString("employeeId"),rs.getString("name")));
reports.add(report);               }
        } catch (SQLException ex) {
            Logger.getLogger(ReportDaoImp.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ReportDaoImp.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ReportDaoImp.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return reports;
    }

    @Override
    public List<Report> getProductsTop40SellingBar() {
List<Report> reports = new ArrayList();
        List<Double> numbers = new ArrayList();        try {
            ps = con.prepareStatement("select productID, name, size, storeID, sum(quantity) as totalSales "
                    + "from product inner join(salelineitem inner join sale on salelineitem.saleID = sale.ID) "
                    + "on product.ID = salelineitem.productID group by storeID, productID "
                    + "order by totalSales desc limit 40");
            rs = ps.executeQuery();
            while (rs.next()) {
 Report report = new Report();

                double totalSales = Math.round(rs.getDouble("totalSales") * 1.00);
                numbers.add(totalSales);
                report.setValues(numbers);
                report.setNames(List.of(rs.getString("storeID"),rs.getString("productId"),rs.getString("name")));
reports.add(report);               }

        } catch (SQLException ex) {
            Logger.getLogger(ReportDaoImp.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ReportDaoImp.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ReportDaoImp.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return reports;
    }

    @Override
    public List<Report> getSalesBar(Store store, Month month) {
List<Report> reports = new ArrayList();
        List<Double> numbers = new ArrayList();        try {
            ps = con.prepareStatement("select name, size, sum(quantity) as totalSales, month(dateSold) as month "
                    + "from product inner join(salelineitem inner join sale on salelineitem.saleID = sale.ID) "
                    + "on product.ID = salelineitem.productID group by productID, month, storeID having storeID=? and month=? "
                    + "order by totalSales desc");
            ps.setString(1, store.getStoreId());
            ps.setInt(2, month.getValue());
            rs = ps.executeQuery();
            while (rs.next()) {
 Report report = new Report();

                double totalSales = Math.round(rs.getDouble("totalSales") * 1.00);
                numbers.add(totalSales);
                report.setValues(numbers);
                report.setNames(List.of(rs.getString("storeID"),rs.getString("branchName")));
reports.add(report);               }
        } catch (SQLException ex) {
            Logger.getLogger(ReportDaoImp.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ReportDaoImp.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ReportDaoImp.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return reports;
    }

    @Override
    public List<Report> getStoresTopSellingBar(int amount) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
