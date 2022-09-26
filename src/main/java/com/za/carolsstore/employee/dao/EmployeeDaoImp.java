/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.za.carolsstore.employee.dao;


import com.za.carolsstore.config.JDBCConfig;
import com.za.carolsstore.employee.model.Employee;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author patri
 */
public class EmployeeDaoImp implements iEmployeeDao{
    private JDBCConfig config = new JDBCConfig();
    private Connection con;
    private PreparedStatement ps;
    private ResultSet rs;
    private int rowsAffected;
        
    public EmployeeDaoImp(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(EmployeeDaoImp.class.getName()).log(Level.SEVERE, null, ex);        }
        String url = config.readURL();
        try {
            con = DriverManager.getConnection(url, config.readPropertiesUser(), config.readPropertiesPassword());
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeDaoImp.class.getName()).log(Level.SEVERE, null, ex);        }
    }
    
    @Override
    public boolean addEmployee(Employee employee) {
        if(con!=null){
            try{
                ps = con.prepareStatement("Insert Into Employee(ID, name, surname, role, password, storeID) values(?,?,?,?,?,?)");
                ps.setString(1, employee.getEmployeeID());
                ps.setString(2, employee.getName());
                ps.setString(3, employee.getSurname());
                ps.setInt(4, employee.getRole());
                ps.setString(5, employee.getPassword());
                ps.setString(6, employee.getStoreID());
                rowsAffected = ps.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(EmployeeDaoImp.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return rowsAffected==1;
    }
    
    @Override
    public Employee getEmployee(String employeeID) {
        Employee employee = null;
        if(con!=null){
            try {
                ps = con.prepareStatement("Select ID, name, surname, role, password, storeID From employee Where ID = ?");
                ps.setString(1, employeeID);
                rs = ps.executeQuery();
                while(rs.next()){
                    employee = new Employee(
                            rs.getString("ID"),
                            rs.getString("name"),
                            rs.getString("surname"),
                            rs.getInt("role"),
                            rs.getString("password"),
                            rs.getString("storeID")    
                    );
                }
            } catch (SQLException ex) {
                Logger.getLogger(EmployeeDaoImp.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                if(ps!=null){
                    try {
                        ps.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(EmployeeDaoImp.class.getName()).log(Level.SEVERE, null, ex);                    }
                }
                if(rs!=null){
                    try {
                        rs.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(EmployeeDaoImp.class.getName()).log(Level.SEVERE, null, ex);                    }
                }
            }
        }
        return employee;
    }
    
    @Override
    public boolean updateEmployee(Employee employee) {
        if(con!=null){
            try{
                ps = con.prepareStatement("Update employee Set ID = ?, name = ?, surname = ?, role = ?, password = ?, storeID = ? Where ID = ?");
                ps.setString(1, employee.getEmployeeID());
                ps.setString(2, employee.getName());
                ps.setString(3, employee.getSurname());
                ps.setInt(4, employee.getRole());
                ps.setString(5, employee.getPassword());
                ps.setString(6, employee.getStoreID());
                ps.setString(7, employee.getEmployeeID());
                rowsAffected = ps.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(EmployeeDaoImp.class.getName()).log(Level.SEVERE, null, ex);
            }finally{
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(EmployeeDaoImp.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return rowsAffected==1;
    }
    
    @Override
    public boolean deleteEmployee(String employeeID) {
        if(con!=null){
            try{
                ps = con.prepareStatement("Delete From employee Where ID=?");
                ps.setString(1, employeeID);
                rowsAffected = ps.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(EmployeeDaoImp.class.getName()).log(Level.SEVERE, null, ex);
            }finally{
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(EmployeeDaoImp.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return rowsAffected==1;
    }

    @Override
    public List<Employee> getAllEmployees() {
        List<Employee> employees = new ArrayList<>();
        if(con!=null){
            try {
                ps = con.prepareStatement("Select ID, name, surname, role, password, storeID From employee");
                rs = ps.executeQuery();
                while(rs.next()){
                    Employee employee = new Employee(
                            rs.getString("ID"),
                            rs.getString("name"),
                            rs.getString("surname"),
                            rs.getInt("role"),
                            rs.getString("password"),
                            rs.getString("storeID")    
                    );
                    employees.add(employee);
                }
            } catch (SQLException ex) {
                Logger.getLogger(EmployeeDaoImp.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                if(ps!=null){
                    try {
                        ps.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(EmployeeDaoImp.class.getName()).log(Level.SEVERE, null, ex);                    }
                }
                if(rs!=null){
                    try {
                        rs.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(EmployeeDaoImp.class.getName()).log(Level.SEVERE, null, ex);                    }
                }
            }
        }
        return employees;
    }

    @Override
    public List<Employee> getEmployeesByStore(String storeID) {
        List<Employee> employees = new ArrayList<>();
        if(con!=null){
            try {
                ps = con.prepareStatement("Select ID, name, surname, role, password, storeID From employee where storeID = ?");
                ps.setString(1, storeID);
                rs = ps.executeQuery();
                while(rs.next()){
                    Employee employee = new Employee(
                            rs.getString("ID"),
                            rs.getString("name"),
                            rs.getString("surname"),
                            rs.getInt("role"),
                            rs.getString("password"),
                            rs.getString("storeID")    
                    );
                    employees.add(employee);
                }
            } catch (SQLException ex) {
                Logger.getLogger(EmployeeDaoImp.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                if(ps!=null){
                    try {
                        ps.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(EmployeeDaoImp.class.getName()).log(Level.SEVERE, null, ex);                    }
                }
                if(rs!=null){
                    try {
                        rs.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(EmployeeDaoImp.class.getName()).log(Level.SEVERE, null, ex);                    }
                }
            }
        }
        return employees;
    }
   
}

    