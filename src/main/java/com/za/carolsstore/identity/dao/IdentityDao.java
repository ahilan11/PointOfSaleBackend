/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.za.carolsstore.identity.dao;


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
 * @author patri
 */
public class IdentityDao implements iIdentityDao {
    private JDBCConfig config = new JDBCConfig();
    private Connection con;
    private PreparedStatement ps;
    private ResultSet rs;
    private int rowsAffected;
    

    public IdentityDao() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(IdentityDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        String url = config.readURL() + "?ssl=false";
        try {
            con = DriverManager.getConnection(url, config.readPropertiesUser(), config.readPropertiesPassword());
        } catch (SQLException ex) {
            Logger.getLogger(IdentityDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    

    @Override
    public char [] getLastIDEmployee() {
        String lastID = null;
        if(con!=null){
            try {
                ps = con.prepareStatement("Select ID From employee Order By ID asc");
                rs = ps.executeQuery();
                while(rs.next()){
                    lastID = rs.getString("ID");
                }
            } catch (SQLException ex) {
                Logger.getLogger(IdentityDao.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                if(ps!=null){
                    try {
                        ps.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(IdentityDao.class.getName()).log(Level.SEVERE, null, ex);                    }
                }
                if(rs!=null){
                    try {
                        rs.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(IdentityDao.class.getName()).log(Level.SEVERE, null, ex);                    }
                }
            }
        }
        return lastID.toCharArray();
    }
    
     @Override
    public char [] getLastIDProduct() {
        String lastID = null;
        if(con!=null){
            try {
                ps = con.prepareStatement("Select ID From product Order By ID asc");
                rs = ps.executeQuery();
                while(rs.next()){
                    lastID = rs.getString("ID");
                }
            } catch (SQLException ex) {
                Logger.getLogger(IdentityDao.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                if(ps!=null){
                    try {
                        ps.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(IdentityDao.class.getName()).log(Level.SEVERE, null, ex);                    }
                }
                if(rs!=null){
                    try {
                        rs.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(IdentityDao.class.getName()).log(Level.SEVERE, null, ex);                    }
                }
            }
        }
        return lastID.toCharArray();
    }
    
     @Override
    public char [] getLastIDIbt() {
        String lastID = null;
        if(con!=null){
            try {
                ps = con.prepareStatement("Select ID From IbtItem Order By ID asc");
                rs = ps.executeQuery();
                while(rs.next()){
                    lastID = rs.getString("ID");
                }
            } catch (SQLException ex) {
                Logger.getLogger(IdentityDao.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                if(ps!=null){
                    try {
                        ps.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(IdentityDao.class.getName()).log(Level.SEVERE, null, ex);                    }
                }
                if(rs!=null){
                    try {
                        rs.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(IdentityDao.class.getName()).log(Level.SEVERE, null, ex);                    }
                }
            }
        }
        return lastID.toCharArray();
    }
    
     @Override
    public char [] getLastIDStore() {
        String lastID = null;
        if(con!=null){
            try {
                ps = con.prepareStatement("Select ID From store Order By ID asc");
                rs = ps.executeQuery();
                while(rs.next()){
                    lastID = rs.getString("ID");
                }
            } catch (SQLException ex) {
                Logger.getLogger(IdentityDao.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                if(ps!=null){
                    try {
                        ps.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(IdentityDao.class.getName()).log(Level.SEVERE, null, ex);                    }
                }
                if(rs!=null){
                    try {
                        rs.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(IdentityDao.class.getName()).log(Level.SEVERE, null, ex);                    }
                }
            }
        }
        return lastID.toCharArray();
    }
    
     @Override
    public char [] getLastIDCategory() {
        String lastID = null;
        if(con!=null){
            try {
                ps = con.prepareStatement("Select ID From category Order By ID asc");
                rs = ps.executeQuery();
                while(rs.next()){
                    lastID = rs.getString("ID");
                }
            } catch (SQLException ex) {
                Logger.getLogger(IdentityDao.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                if(ps!=null){
                    try {
                        ps.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(IdentityDao.class.getName()).log(Level.SEVERE, null, ex);                    }
                }
                if(rs!=null){
                    try {
                        rs.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(IdentityDao.class.getName()).log(Level.SEVERE, null, ex);                    }
                }
            }
        }
        return lastID.toCharArray();
    }
    
     @Override
    public char [] getLastIDKeepAside() {
        String lastID = null;
        if(con!=null){
            try {
                ps = con.prepareStatement("Select ID From keepaside Order By ID asc");
                rs = ps.executeQuery();
                while(rs.next()){
                    lastID = rs.getString("ID");
                }
            } catch (SQLException ex) {
                Logger.getLogger(IdentityDao.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                if(ps!=null){
                    try {
                        ps.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(IdentityDao.class.getName()).log(Level.SEVERE, null, ex);                    }
                }
                if(rs!=null){
                    try {
                        rs.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(IdentityDao.class.getName()).log(Level.SEVERE, null, ex);                    }
                }
            }
        }
        return lastID.toCharArray();
    }
    
     @Override
    public char [] getLastIDEmail() {
        String lastID = null;
        if(con!=null){
            try {
                ps = con.prepareStatement("Select ID From email Order By ID asc");
                rs = ps.executeQuery();
                while(rs.next()){
                    lastID = rs.getString("ID");
                }
            } catch (SQLException ex) {
                Logger.getLogger(IdentityDao.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                if(ps!=null){
                    try {
                        ps.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(IdentityDao.class.getName()).log(Level.SEVERE, null, ex);                    }
                }
                if(rs!=null){
                    try {
                        rs.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(IdentityDao.class.getName()).log(Level.SEVERE, null, ex);                    }
                }
            }
        }
        return lastID.toCharArray();
    }
    
     @Override
    public char [] getLastIDSale() {
        String lastID = null;
        if(con!=null){
            try {
                ps = con.prepareStatement("Select ID From sale Order By ID asc");
                rs = ps.executeQuery();
                while(rs.next()){
                    lastID = rs.getString("ID");
                }
            } catch (SQLException ex) {
                Logger.getLogger(IdentityDao.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                if(ps!=null){
                    try {
                        ps.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(IdentityDao.class.getName()).log(Level.SEVERE, null, ex);                    }
                }
                if(rs!=null){
                    try {
                        rs.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(IdentityDao.class.getName()).log(Level.SEVERE, null, ex);                    }
                }
            }
        }
        return lastID.toCharArray();
    }
    
}
