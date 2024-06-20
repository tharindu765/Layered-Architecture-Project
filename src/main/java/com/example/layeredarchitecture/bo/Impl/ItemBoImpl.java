package com.example.layeredarchitecture.bo.Impl;

import com.example.layeredarchitecture.Dao.Custom.Impl.ItemDaoImpl;
import com.example.layeredarchitecture.Dao.Custom.ItemDao;
import com.example.layeredarchitecture.Dao.SQLUtil;
import com.example.layeredarchitecture.bo.ItemBo;
import com.example.layeredarchitecture.db.DBConnection;
import com.example.layeredarchitecture.model.ItemDTO;
import com.example.layeredarchitecture.model.OrderDetailDTO;
import com.example.layeredarchitecture.view.tdm.ItemTM;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemBoImpl implements ItemBo{
    public List<ItemTM> loadAll() throws SQLException, ClassNotFoundException {
        ItemDao itemDao = new ItemDaoImpl();
        return itemDao.loadAll();
    }

    public void Delete(String id) throws SQLException, ClassNotFoundException {
        ItemDao itemDao = new ItemDaoImpl();
        itemDao.Delete(id);
    }

    public void Save(ItemTM itemTM) throws SQLException, ClassNotFoundException {
        ItemDao itemDao = new ItemDaoImpl();
        itemDao.Save(itemTM);
    }

    public void Update(ItemTM itemTM) throws SQLException, ClassNotFoundException {
        ItemDao itemDao = new ItemDaoImpl();
        itemDao.Update(itemTM);
    }

    public boolean Exit(String code) throws SQLException, ClassNotFoundException {
        ItemDao itemDao = new ItemDaoImpl();
        return itemDao.Exit(code);
    }

    public String GenarateNextID() throws SQLException, ClassNotFoundException {
        ItemDao itemDao = new ItemDaoImpl();
        return itemDao.GenarateNextID();
    }

    public ItemDTO findTheItem(String newItemCode) throws SQLException, ClassNotFoundException {
        ItemDao itemDao = new ItemDaoImpl();
        return itemDao.findTheItem(newItemCode);
    }

    public List<String> loadAllIds() throws SQLException, ClassNotFoundException {
        ItemDao itemDao = new ItemDaoImpl();
        return itemDao.loadAllIds();
    }

    public boolean SaveItemFromDetail(OrderDetailDTO detail) throws SQLException, ClassNotFoundException {
        ItemDao itemDao = new ItemDaoImpl();
        return itemDao.SaveItemFromDetail(detail);
    }
}