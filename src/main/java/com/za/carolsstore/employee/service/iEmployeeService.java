/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.za.carolsstore.employee.service;

import com.za.carolsstore.employee.model.Employee;
import java.util.List;

/**
 *
 * @author patri
 */
public interface iEmployeeService {
    //Create--------------------------------------------------------------------
    public String addEmployee(Employee employee);
    
    //Read----------------------------------------------------------------------
    public Employee getEmployee(String employeeID);
    public List<Employee> getAllEmployees();
    public List<Employee> getEmployeesByStore(String StoreID);
    
    //Update--------------------------------------------------------------------
    public String updateEmployee(Employee employee);
    public String updateEmployeeRole(Employee employee);
    
    //Delete--------------------------------------------------------------------
    public String deleteEmployee(String employeeID);
}
