/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.za.carolsstore.employee.dao;


import com.za.carolsstore.employee.model.Employee;
import java.util.List;

/**
 *
 * @author patri
 */
public interface iEmployeeDao {
    //Create--------------------------------------------------------------------
    public boolean addEmployee(Employee employee);
    
    //Read----------------------------------------------------------------------
    public Employee getEmployee(String employeeID);
    public List<Employee> getAllEmployees();
    public List<Employee> getEmployeesByStore(String storeID);
    
    //Update--------------------------------------------------------------------
    public boolean updateEmployee(Employee employee);
    
    //Delete--------------------------------------------------------------------
    public boolean deleteEmployee(String employeeID);
    
}
