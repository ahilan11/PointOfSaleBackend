package com.za.carolsstore.product.model;

import com.za.carolsstore.store.model.Store;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class KeepAside {
    private String keepAsideID, productID, storeID;
    private String customerEmail;
    private Product product;
    private Store store;
    private LocalDate dateCreated;
    private LocalTime timeCreated;   
}
