package com.example.layeredarchitecture.Dao;

import com.example.layeredarchitecture.model.CustomerDTO;
import com.example.layeredarchitecture.view.tdm.CustomerTM;

import java.sql.SQLException;
import java.util.List;

public interface CrudDao <T>{
    List<T> loadAll() throws SQLException, ClassNotFoundException;
    void Save(T TM) throws SQLException, ClassNotFoundException;
    void Update(T TM) throws SQLException, ClassNotFoundException;
    boolean Exit(String id) throws SQLException, ClassNotFoundException;
    void Delete(String id) throws SQLException, ClassNotFoundException;
    String GenarateNextID() throws SQLException, ClassNotFoundException;
    List<String> loadAllIds() throws SQLException, ClassNotFoundException;
   // <T> search(String newValue) throws SQLException, ClassNotFoundException;
}
