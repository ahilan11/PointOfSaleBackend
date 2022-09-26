package com.za.carolsstore.message.dao;

import com.za.carolsstore.config.JDBCConfig;
import com.za.carolsstore.message.model.Email;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EmailDaoImp implements iEmailDao{
    private Connection con;
    private PreparedStatement ps;
    private ResultSet rs;
    private int rowsAffected;
    private JDBCConfig config = new JDBCConfig();
    
    public EmailDaoImp(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(EmailDaoImp.class.getName()).log(Level.SEVERE, null, ex);
        }
        String url = config.readURL();
        try {
            con = DriverManager.getConnection(url, config.readPropertiesUser(), config.readPropertiesPassword());
        } catch (SQLException ex) {
            Logger.getLogger(EmailDaoImp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public boolean addEmail(Email email) {
        if(con!=null){
            try {
                ps = con.prepareStatement("Insert into email(ID, address, message, sentDate, sentTime) values (?,?,?,date(now()),time(now()))");
                ps.setString(1, email.getId());
                ps.setString(2, email.getAddress());
                ps.setString(3, email.getMessage());
           
                rowsAffected = ps.executeUpdate();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            finally{
                if(ps!=null){
                    try {
                        ps.close();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        }
        return rowsAffected == 1;
    }

    @Override
    public Email getEmail(String emailID) {
        Email email = null;
        if(con!=null){
            try {
                ps = con.prepareStatement("Select * from email where ID = ?");
                ps.setString(1, emailID);
                rs = ps.executeQuery();
                while(rs.next()){
                    email = new Email();
                    email.setId(rs.getString("ID"));
                    email.setMessage(rs.getString("message"));
                    email.setAddress(rs.getString("address"));
                    email.setDateSent(rs.getDate("sentDate").toLocalDate());
                    email.setTimeSent(rs.getTime("sentTime").toLocalTime());
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            finally{
                if(ps!=null){
                    try {
                        ps.close();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
                if(rs!=null){
                    try {
                        rs.close();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        }
        return email;
    }

    @Override
    public boolean deleteEmail(String emailID) {
        if(con!=null){
            try {
                ps = con.prepareStatement("Delete from email where ID = ?");
                ps.setString(1, emailID);
                rowsAffected = ps.executeUpdate();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            finally{
                if(ps!=null){
                    try {
                        ps.close();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        }
        return rowsAffected == 1;
    }
}
