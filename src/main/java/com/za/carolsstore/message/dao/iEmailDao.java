package com.za.carolsstore.message.dao;

import com.za.carolsstore.message.model.Email;


public interface iEmailDao {
    boolean addEmail(Email email);
    Email getEmail(String emailID);
    boolean deleteEmail(String emailID);
}
