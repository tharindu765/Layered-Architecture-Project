package com.example.layeredarchitecture.Dao.Custom;

import com.example.layeredarchitecture.Dao.CrudDao;
import com.example.layeredarchitecture.db.DBConnection;
import com.example.layeredarchitecture.model.ItemDTO;
import com.example.layeredarchitecture.model.OrderDetailDTO;
import com.example.layeredarchitecture.view.tdm.ItemTM;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public interface ItemDao extends CrudDao<ItemTM> {
    public boolean SaveItemFromDetail(OrderDetailDTO detail) throws SQLException, ClassNotFoundException;

    public ItemDTO findTheItem(String newItemCode) throws SQLException, ClassNotFoundException;
}
