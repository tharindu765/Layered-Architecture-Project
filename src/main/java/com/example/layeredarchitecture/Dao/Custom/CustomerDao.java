package com.example.layeredarchitecture.Dao.Custom;

import com.example.layeredarchitecture.Dao.CrudDao;
import com.example.layeredarchitecture.db.DBConnection;
import com.example.layeredarchitecture.model.CustomerDTO;
import com.example.layeredarchitecture.view.tdm.CustomerTM;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public interface CustomerDao extends CrudDao<CustomerTM> {
    public CustomerDTO search(String newValue) throws SQLException, ClassNotFoundException;
}
