package com.example.layeredarchitecture.Dao;

import com.example.layeredarchitecture.db.DBConnection;
import com.example.layeredarchitecture.model.ItemDTO;
import com.example.layeredarchitecture.model.OrderDetailDTO;
import com.example.layeredarchitecture.view.tdm.ItemTM;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public interface ItemDao {
    List<ItemTM> loadAllItem() throws SQLException, ClassNotFoundException;
     void delete(String code) throws SQLException, ClassNotFoundException;
     void save(ItemTM itemTM) throws SQLException, ClassNotFoundException;
     void update(ItemTM itemTM) throws SQLException, ClassNotFoundException;
     boolean exitItem(String code) throws SQLException, ClassNotFoundException;

     String genarateNextId() throws SQLException, ClassNotFoundException;
     ItemDTO findTheItem(String newItemCode) throws SQLException, ClassNotFoundException;
     List<String> loadAllItems() throws SQLException, ClassNotFoundException;
    boolean SaveItemFromDetail(OrderDetailDTO detail) throws SQLException, ClassNotFoundException;
}
