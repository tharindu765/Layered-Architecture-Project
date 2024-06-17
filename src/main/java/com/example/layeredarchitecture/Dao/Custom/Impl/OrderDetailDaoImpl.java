package com.example.layeredarchitecture.Dao.Custom.Impl;

import com.example.layeredarchitecture.Dao.Custom.Impl.ItemDaoImpl;
import com.example.layeredarchitecture.Dao.Custom.ItemDao;
import com.example.layeredarchitecture.Dao.Custom.OrderDetailDao;
import com.example.layeredarchitecture.db.DBConnection;
import com.example.layeredarchitecture.model.OrderDetailDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class OrderDetailDaoImpl implements OrderDetailDao {
    public boolean saveOrderDetail(String orderId, List<OrderDetailDTO> orderDetails) throws SQLException, ClassNotFoundException {
       Connection connection = DBConnection.getDbConnection().getConnection();
        PreparedStatement stm = connection.prepareStatement("INSERT INTO OrderDetails (oid, itemCode, unitPrice, qty) VALUES (?,?,?,?)");

        for (OrderDetailDTO detail : orderDetails) {
            stm.setString(1, orderId);
            stm.setString(2, detail.getItemCode());
            stm.setBigDecimal(3, detail.getUnitPrice());
            stm.setInt(4, detail.getQty());

            if (stm.executeUpdate() != 1) {
                connection.rollback();
                connection.setAutoCommit(true);
                return false;
            }

//                //Search & Update Item
            ItemDao itemDao = new ItemDaoImpl();
           return itemDao.SaveItemFromDetail(detail);
        }
        return false;
    }

    @Override
    public List<OrderDetailDTO> loadAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public void Save(OrderDetailDTO TM) throws SQLException, ClassNotFoundException {

    }

    @Override
    public void Update(OrderDetailDTO TM) throws SQLException, ClassNotFoundException {

    }

    @Override
    public boolean Exit(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public void Delete(String id) throws SQLException, ClassNotFoundException {

    }

    @Override
    public String GenarateNextID() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public List<String> loadAllIds() throws SQLException, ClassNotFoundException {
        return null;
    }
}
//this is OrderDetail