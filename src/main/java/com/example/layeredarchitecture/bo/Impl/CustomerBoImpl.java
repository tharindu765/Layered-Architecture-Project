package com.example.layeredarchitecture.bo.Impl;

import com.example.layeredarchitecture.Dao.Custom.CustomerDao;
import com.example.layeredarchitecture.Dao.Custom.Impl.CustomerDaoImpl;
import com.example.layeredarchitecture.Dao.SQLUtil;
import com.example.layeredarchitecture.bo.CustomerBo;
import com.example.layeredarchitecture.model.CustomerDTO;
import com.example.layeredarchitecture.view.tdm.CustomerTM;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerBoImpl implements CustomerBo {
    public List<CustomerTM> loadAll() throws SQLException, ClassNotFoundException {
        CustomerDao customerDao = new CustomerDaoImpl();
        return customerDao.loadAll();
    }
    public void Save(CustomerTM customerTM) throws SQLException, ClassNotFoundException {
        CustomerDao customerDao = new CustomerDaoImpl();
        customerDao.Save(customerTM);
    }
    public void Update(CustomerTM customerTM) throws SQLException, ClassNotFoundException {
        CustomerDao customerDao = new CustomerDaoImpl();
        customerDao.Update(customerTM);
    }
    public boolean Exit(String id) throws SQLException, ClassNotFoundException {
        CustomerDao customerDao = new CustomerDaoImpl();
       return customerDao.Exit(id);
    }
    public void Delete(String id) throws SQLException, ClassNotFoundException {
        CustomerDao customerDao = new CustomerDaoImpl();
        customerDao.Delete(id);
    }
    public String GenarateNextID() throws SQLException, ClassNotFoundException {
        CustomerDao customerDao = new CustomerDaoImpl();
       return customerDao.GenarateNextID();
    }
    public List<String> loadAllIds() throws SQLException, ClassNotFoundException {
        CustomerDao customerDao = new CustomerDaoImpl();
      return customerDao.loadAllIds();
    }

    public CustomerDTO search(String newValue) throws SQLException, ClassNotFoundException {
        CustomerDao customerDao = new CustomerDaoImpl();
        return customerDao.search(newValue);
    }

}
