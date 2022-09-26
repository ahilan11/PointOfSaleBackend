/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.za.carolsstore.employee.service;


import com.za.carolsstore.employee.dao.EmployeeDaoImp;
import com.za.carolsstore.employee.dao.iEmployeeDao;
import com.za.carolsstore.employee.model.Employee;
import java.util.List;

/**
 *
 * @author nicad
 */
public class EmployeeServiceImp implements iEmployeeService{
    private final iEmployeeDao dao;

    public EmployeeServiceImp() {
        this.dao = new EmployeeDaoImp();
    }

    @Override
    public String addEmployee(Employee employee) {          
        return (dao.addEmployee(employee))?"Success":"Failure";
    }

    @Override
    public Employee getEmployee(String employeeID) {
        return dao.getEmployee(employeeID);
     }

    @Override
    public List<Employee> getAllEmployees() {
        return dao.getAllEmployees();
    }

    @Override
    public List<Employee> getEmployeesByStore(String storeID) {
        return dao.getEmployeesByStore(storeID);
    }

    @Override
    public String updateEmployee(Employee employee) {
        return (dao.updateEmployee(employee))?"Success":"Failure";
    }

    @Override
    public String updateEmployeeRole(Employee employee) {
        return (dao.updateEmployee(employee))?"Success":"Failure";
    }

    @Override
    public String deleteEmployee(String employeeID) {
        return (dao.deleteEmployee(employeeID))?"Success":"Failure";
    }
    
}
