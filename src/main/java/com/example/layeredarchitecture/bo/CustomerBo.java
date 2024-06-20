package com.example.layeredarchitecture.bo;

import com.example.layeredarchitecture.Dao.Custom.CustomerDao;
import com.example.layeredarchitecture.Dao.Custom.Impl.CustomerDaoImpl;
import com.example.layeredarchitecture.model.CustomerDTO;
import com.example.layeredarchitecture.view.tdm.CustomerTM;

import java.sql.SQLException;
import java.util.List;

public interface CustomerBo {
    public List<CustomerTM> loadAll() throws SQLException, ClassNotFoundException ;
    public void Save(CustomerTM customerTM) throws SQLException, ClassNotFoundException ;
    public void Update(CustomerTM customerTM) throws SQLException, ClassNotFoundException ;
    public boolean Exit(String id) throws SQLException, ClassNotFoundException ;
    public void Delete(String id) throws SQLException, ClassNotFoundException ;
    public String GenarateNextID() throws SQLException, ClassNotFoundException ;
    public List<String> loadAllIds() throws SQLException, ClassNotFoundException ;
    public CustomerDTO search(String newValue) throws SQLException, ClassNotFoundException ;

}
