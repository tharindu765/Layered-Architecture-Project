package com.example.layeredarchitecture.bo;

import com.example.layeredarchitecture.Dao.Custom.Impl.ItemDaoImpl;
import com.example.layeredarchitecture.Dao.Custom.ItemDao;
import com.example.layeredarchitecture.model.ItemDTO;
import com.example.layeredarchitecture.model.OrderDetailDTO;
import com.example.layeredarchitecture.view.tdm.ItemTM;

import java.sql.SQLException;
import java.util.List;

public interface ItemBo {
    public List<ItemTM> loadAll() throws SQLException, ClassNotFoundException ;
    public void Delete(String id) throws SQLException, ClassNotFoundException ;
    public void Save(ItemTM itemTM) throws SQLException, ClassNotFoundException ;
    public void Update(ItemTM itemTM) throws SQLException, ClassNotFoundException;

    public boolean Exit(String code) throws SQLException, ClassNotFoundException;

    public String GenarateNextID() throws SQLException, ClassNotFoundException ;
    public ItemDTO findTheItem(String newItemCode) throws SQLException, ClassNotFoundException ;
    public List<String> loadAllIds() throws SQLException, ClassNotFoundException ;
    public boolean SaveItemFromDetail(OrderDetailDTO detail) throws SQLException, ClassNotFoundException ;
}
