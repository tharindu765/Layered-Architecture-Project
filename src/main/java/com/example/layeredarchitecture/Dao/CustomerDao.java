package com.example.layeredarchitecture.Dao;

import com.example.layeredarchitecture.db.DBConnection;
import com.example.layeredarchitecture.model.CustomerDTO;
import com.example.layeredarchitecture.view.tdm.CustomerTM;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public interface CustomerDao {
     List<CustomerTM> loadAllCustomers() throws SQLException, ClassNotFoundException;
     void Save(CustomerTM customerTM) throws SQLException, ClassNotFoundException;
     void Update(CustomerTM customerTM) throws SQLException, ClassNotFoundException;
     boolean ExitCustomer(String id) throws SQLException, ClassNotFoundException;
     void Delete(String id) throws SQLException, ClassNotFoundException;
     String GenarateNextID() throws SQLException, ClassNotFoundException;
     List<String> loadAllCustomerIds() throws SQLException, ClassNotFoundException;
     CustomerDTO search(String newValue) throws SQLException, ClassNotFoundException;
}
