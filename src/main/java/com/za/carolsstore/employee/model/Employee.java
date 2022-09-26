package com.za.carolsstore.employee.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Employee {
    private String employeeID;
    private String name, surname;
    private int role;
    private String password;
    private String storeID;
}
