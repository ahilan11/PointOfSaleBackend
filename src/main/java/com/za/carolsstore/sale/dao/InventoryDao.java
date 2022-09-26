/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.za.carolsstore.sale.dao;

import com.za.carolsstore.config.JDBCConfig;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Vijay
 */
public class InventoryDao implements InventoryDaoInt{
     private JDBCConfig config = new JDBCConfig();
    private Connection con;
    private PreparedStatement ps;
    private ResultSet rs;
    private int rowsAffected;

    public InventoryDao() {
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
    public boolean decreaseInventory(String storeID, String productID, int quantity) {
         try {
             ps = con.prepareStatement("update inventory set quantity = quantity-? where storeID = ? and productID = ?");
             ps.setInt(1,quantity);
             ps.setString(2, storeID);
             ps.setString(3,productID);
            rowsAffected =  ps.executeUpdate();
             System.out.println("This is rows affected " + rowsAffected);
         } catch (SQLException ex) {
             Logger.getLogger(InventoryDao.class.getName()).log(Level.SEVERE, null, ex);
         }
         finally{
             if(ps != null){
                 try {
                     ps.close();
                 } catch (SQLException ex) {
                     Logger.getLogger(InventoryDao.class.getName()).log(Level.SEVERE, null, ex);
                 }
             }
         }
         return rowsAffected==1;
    }

    @Override
    public boolean increaseInventory(String storeID, String productID, int quantity) {
try {
             ps = con.prepareStatement("update inventory set quantity = quantity+? where storeID = ? and productID = ?");
             ps.setInt(1,quantity);
             ps.setString(2, storeID);
             ps.setString(3,productID);
            rowsAffected =  ps.executeUpdate();
         
         } catch (SQLException ex) {
             Logger.getLogger(InventoryDao.class.getName()).log(Level.SEVERE, null, ex);
         }
         finally{
             if(ps != null){
                 try {
                     ps.close();
                 } catch (SQLException ex) {
                     Logger.getLogger(InventoryDao.class.getName()).log(Level.SEVERE, null, ex);
                 }
             }
         }
         return rowsAffected==1;    }

    @Override
    public int checkStock(String storeID, String productID) {
        int stock = 0;
         try {
             ps = con.prepareStatement("select * from inventory where productID = ? and storeID = ?");
             ps.setString(1,productID);
             ps.setString(2, storeID);
            rs =  ps.executeQuery();
             if(rs.next()){
                 stock = rs.getInt("quantity");
             }
         } catch (SQLException ex) {
             Logger.getLogger(InventoryDao.class.getName()).log(Level.SEVERE, null, ex);
         }
         finally{
             if(ps != null){
                 try {
                     ps.close();
                 } catch (SQLException ex) {
                     Logger.getLogger(InventoryDao.class.getName()).log(Level.SEVERE, null, ex);
                 }
             }
             if(rs!= null){
                 try {
                     rs.close();
                 } catch (SQLException ex) {
                     Logger.getLogger(InventoryDao.class.getName()).log(Level.SEVERE, null, ex);
                 }
             }
         }
         return stock;
    }

}
