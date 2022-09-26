/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.za.carolsstore.sale.dao;


import com.za.carolsstore.product.model.Product;
import com.za.carolsstore.config.JDBCConfig;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.za.carolsstore.employee.model.Employee;
import com.za.carolsstore.sale.model.Promocode;
import com.za.carolsstore.sale.model.Sale;
import com.za.carolsstore.sale.model.SaleLineItem;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalTime;

/**
 *
 * @author nicad
 */
public class SaleDao implements iSaleDao {

    private JDBCConfig config = new JDBCConfig();
    private Connection con;
    private PreparedStatement ps;
    private ResultSet rs;
    private int rowsAffected;

    public SaleDao() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SaleDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        String url = config.readURL();
        try {
            con = DriverManager.getConnection(url, config.readPropertiesUser(), config.readPropertiesPassword());
        } catch (SQLException ex) {
            Logger.getLogger(SaleDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public boolean addSale(Sale sale) {
        if (con != null) {
            try {
                ps = con.prepareStatement("Insert Into sale(ID, dateSold, timeSold, storeID, employeeID) Values(?,date(now()), time(now()),?,?)");
                ps.setString(1, sale.getSaleID());
                ps.setString(2, sale.getStoreID());
                ps.setString(3, sale.getEmployeeID());
                rowsAffected = ps.executeUpdate();
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
            }
        }
        return rowsAffected == 1;
    }

    @Override
    public Sale getSale(String saleID) {
        Sale sale = null;
        if (con != null) {
            try {
                ps = con.prepareStatement("Select ID, dateSold, timeSold, storeID, employeeID "
                        + "From sale Where ID = ?");
                ps.setString(1, saleID);
                rs = ps.executeQuery();
                while (rs.next()) {
                    sale = new Sale(
                            rs.getString("ID"),
                            rs.getDate("dateSold").toLocalDate().toString(),
                            rs.getTime("timeSold").toLocalTime().toString(),
                            rs.getString("storeID"),
                            rs.getString("employeeID")
                    );
                }
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
        return sale;
    }

    @Override
    public List<Sale> getSalesByStore(String storeID) {
        List<Sale> saleList = new ArrayList<>();
        if (con != null) {
            try {
                ps = con.prepareStatement("Select ID, dateSold, timeSold, storeID, employeeID From sale Where storeID = ?");
                ps.setString(1, storeID);
                rs = ps.executeQuery();
                while (rs.next()) {
                    saleList.add(new Sale(
                            rs.getString("ID"),
                            rs.getDate("dateSold").toLocalDate().toString(),
                            rs.getTime("timeSold").toLocalTime().toString(),
                            rs.getString("storeID"),
                            rs.getString("employeeID")
                    ));
                }
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
        return saleList;
    }

    @Override
    public List<Sale> getSalesByEmployee(String employeeID) {
        List<Sale> saleList = new ArrayList<>();
        if (con != null) {
            try {
                ps = con.prepareStatement("Select ID, dateSold, timeSold, storeID, employeeID From sale Where employeeID = ?");
                ps.setString(1, employeeID);
                rs = ps.executeQuery();
                while (rs.next()) {
                    saleList.add(new Sale(
                            rs.getString("ID"),
                            rs.getDate("dateSold").toLocalDate().toString(),
                            rs.getTime("timeSold").toLocalTime().toString(),
                            rs.getString("storeID"),
                            rs.getString("employeeID")
                    ));
                }
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
        return saleList;
    }

    @Override
    public List<Sale> getSalesOfProduct(String productID) {
        List<Sale> saleList = new ArrayList<>();
        if (con != null) {
            try {
                ps = con.prepareStatement("Select ID, dateSold, timeSold, storeID, employeeID From sale inner join sale_lineitem on sale.ID = sale_lineitem.saleID"
                        + " Where sale_lineitem.productID = ?");
                ps.setString(1, productID);
                rs = ps.executeQuery();
                while (rs.next()) {
                    saleList.add(new Sale(
                            rs.getString("ID"),
                            rs.getDate("dateSold").toLocalDate().toString(),
                            rs.getTime("timeSold").toLocalTime().toString(),
                            rs.getString("storeID"),
                            rs.getString("employeeID")
                    ));
                }
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
        return saleList;
    }

    @Override
    public List<Sale> getSalesOfProductByStore(String productID, String storeID) {
        List<Sale> saleList = new ArrayList<>();
        if (con != null) {
            try {
                ps = con.prepareStatement("Select ID, dateSold, timeSold, storeID, "
                        + "employeeID From sale inner join sale_lineitem on sale.ID = sale_lineitem.saleID"
                        + " Where sale_lineitem.productID = ? AND sale.storeID = ?");
                ps.setString(1, productID);
                ps.setString(2, storeID);
                rs = ps.executeQuery();
                while (rs.next()) {
                    saleList.add(new Sale(
                            rs.getString("ID"),
                            rs.getDate("dateSold").toLocalDate().toString(),
                            rs.getTime("timeSold").toLocalTime().toString(),
                            rs.getString("storeID"),
                            rs.getString("employeeID")
                    ));
                }
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
        return saleList;
    }

    @Override
    public List<Sale> getSalesOfProductByEmployee(String productID, String employeeID) {
        List<Sale> saleList = new ArrayList<>();
        if (con != null) {
            try {
                ps = con.prepareStatement("Select ID, dateSold, timeSold, storeID, "
                        + "employeeID From sale Inner Join sale_lineitem "
                        + "On sale.ID = sale_lineitem.saleID"
                        + " Where sale_lineitem.productID = ? AND sale.employeeID = ?");
                ps.setString(1, productID);
                ps.setString(2, employeeID);
                rs = ps.executeQuery();
                while (rs.next()) {
                    saleList.add(new Sale(
                            rs.getString("ID"),
                            rs.getDate("dateSold").toLocalDate().toString(),
                            rs.getTime("timeSold").toLocalTime().toString(),
                            rs.getString("storeID"),
                            rs.getString("employeeID")
                    ));
                }
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
        return saleList;
    }

    @Override
    public List<Sale> getAllSales() {
        List<Sale> saleList = new ArrayList<>();
        if (con != null) {
            try {
                ps = con.prepareStatement("Select ID, dateSold, timeSold, storeID, employeeID From sale");
                rs = ps.executeQuery();
                while (rs.next()) {
                    saleList.add(new Sale(
                            rs.getString("ID"),
                            rs.getDate("dateSold").toLocalDate().toString(),
                            rs.getTime("timeSold").toLocalTime().toString(),
                            rs.getString("storeID"),
                            rs.getString("employeeID")
                    ));
                }
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
        return saleList;
    }

    @Override
    public boolean updateSale(Sale sale) {
        if (con != null) {
            try {
                ps = con.prepareStatement("Update sale Set dateSold = ?, "
                        + "timeSold = ?, storeID = ? ,employeeID = ? Where ID = ?");
                ps.setDate(1, Date.valueOf(sale.getDateSold().toString()));
                ps.setTime(2, Time.valueOf(sale.getTimeSold().toString()));
                ps.setString(3, sale.getStoreID());
                ps.setString(4, sale.getEmployeeID());
                ps.setString(5, sale.getSaleID());
                rowsAffected = ps.executeUpdate();
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
            }
        }
        return rowsAffected == 1;
    }

    @Override
    public boolean deleteSale(String saleID) {
        if (con != null) {
            try {
                ps = con.prepareStatement("Delete From sale Where ID = ?");
                ps.setString(1, saleID);
                rowsAffected = ps.executeUpdate();
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
            }
        }
        return rowsAffected == 1;
    }

    @Override
    public boolean addProductToLineItem(Product product, int quantity,String saleID) {
        try {
            ps = con.prepareStatement("Insert Into salelineitem(saleID, productID, quantity) values(?,?,?)");
            ps.setString(1, saleID);
            ps.setString(2, product.getId());
            ps.setInt(3, quantity);
            rowsAffected = ps.executeUpdate();
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
        }
        return rowsAffected == 1;
    }

    @Override
    public boolean removeProductFromLineItem(Product product, Employee empl) {
        if (empl.getRole() == 2) {
            try {
                ps = con.prepareStatement("delete from salelineitem where productID = ?");
                ps.setString(1, product.getId());
                rowsAffected = ps.executeUpdate();
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
            }
        }
        return rowsAffected == 1;
    }

    @Override
    public boolean clearSaleLineItem(){
        int noOfRows = getNumberOfRows();
        try {
            ps = con.prepareStatement("delete from salelineitem");
            rowsAffected = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(SaleDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rowsAffected==noOfRows;
    }

    private String getLastSale(){
        String lastID = null;
        try {
            ps = con.prepareStatement("SELECT * FROM sale ORDER BY ID DESC LIMIT 1");
            rs = ps.executeQuery();
            if (rs.next()) {
                lastID = rs.getString("ID");
            }
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
        return lastID;
    }

    @Override
    public boolean addPromocode(Promocode promocde) {
        if (con != null) {
            try {
                ps = con.prepareStatement("Insert Into promocode(code, discount) Values(?,?)");
                ps.setString(1, promocde.getCode());
                ps.setDouble(2, promocde.getDiscount());
                rowsAffected = ps.executeUpdate();
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
            }
        }
        return rowsAffected == 1;
    }

    @Override
    public Promocode getPromocode(String code) {
        Promocode promocode = null;
        if (con != null) {
            try {
                ps = con.prepareStatement("Select * From promocode Where code = ?");
                ps.setString(1, code);
                rs = ps.executeQuery();
                while (rs.next()) {
                    promocode = new Promocode(
                            rs.getString("code"),
                            rs.getDouble("discount")
                    );
                }
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
        return promocode;
    }
    
    private int getNumberOfRows(){
        int noOfRows = 0;
        try {
            ps.getConnection().prepareStatement("Select count(quantity) As count_rows From salelineitem");
            rs = ps.executeQuery();
            while(rs.next()){
                noOfRows = rs.getInt("count_rows");
            }
        } catch (SQLException ex) {
            Logger.getLogger(SaleDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return noOfRows;
    }

    @Override
    public List<Promocode> getAllPromode() {
        List<Promocode> promocodes = new ArrayList<>();
        if (con != null) {
            try {
                ps = con.prepareStatement("Select * From promocode");
                rs = ps.executeQuery();
                while (rs.next()) {
                    promocodes.add(new Promocode(
                            rs.getString("code"),
                            rs.getDouble("discount")
                    ));
                }
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
        return promocodes;
    }

    @Override
    public boolean updatePromocode(Promocode promocode) {
        if (con != null) {
            try {
                ps = con.prepareStatement("Update promocode Set code = ?, "
                        + "discount = ? Where code = ?");
                ps.setString(1, promocode.getCode());
                ps.setDouble(2, promocode.getDiscount());
                ps.setString(3, promocode.getCode());
                rowsAffected = ps.executeUpdate();
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
            }
        }
        return rowsAffected == 1;
    }

    @Override
    public boolean deletePromocode(String code) {
        if (con != null) {
            try {
                ps = con.prepareStatement("Delete From promocode Where code = ?");
                ps.setString(1, code);
                rowsAffected = ps.executeUpdate();
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
            }
        }
        return rowsAffected == 1;
    }

    @Override
    public int checkInventory(String storeID, String productID) {
        int inventoryAmount = 0;
        if (con != null) {
            try {
                ps = con.prepareStatement("Select Quantity"
                        + "From inventory Where storeID = ? and productID = ?");
                ps.setString(1, storeID);
                ps.setString(2, productID);
                rs = ps.executeQuery();
                while (rs.next()) {
                    inventoryAmount = rs.getInt("Quantity");
                }
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
        return inventoryAmount;
    }

    @Override
    public boolean increaseInventory(String storeID, String productID, int amount) {
        if (con != null) {
            try {
                ps = con.prepareStatement("Update inventory set quantity = Quantity+?"
                        + "Where storeID = ? and productID = ?");
                ps.setInt(1, amount);
                ps.setString(2, storeID);
                ps.setString(3, productID);
                rowsAffected = ps.executeUpdate();
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
        return rowsAffected==1;
    }

    @Override
    public boolean decreaseInventory(String storeID, String productID, int amount) {
        if (con != null) {
            try {
                ps = con.prepareStatement("Update inventory set quantity = Quantity-?"
                        + "Where storeID = ? and productID = ?");
                ps.setInt(1, amount);
                ps.setString(2, storeID);
                ps.setString(3, productID);
                rowsAffected = ps.executeUpdate();
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
        return rowsAffected==1;
    }

    @Override
    public List<SaleLineItem> getSaleLineItems(String saleID) {
        List<SaleLineItem> lineItems = new ArrayList<>();
        if(con!=null){
            try {
                ps = con.prepareStatement("Select * From sale_lineitem where saleID = ?");
                ps.setString(1, saleID);
                rs = ps.executeQuery();
                while(rs.next()){
                    lineItems.add(new SaleLineItem(rs.getString("saleID"), rs.getString("productID"), rs.getInt("quantity"),rs.getString("")));
                }
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
        return lineItems;
    }
    
}
