package com.example.layeredarchitecture.Dao.Custom;

import com.example.layeredarchitecture.Dao.CrudDao;
import com.example.layeredarchitecture.db.DBConnection;
import com.example.layeredarchitecture.model.OrderDetailDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public interface OrderDetailDao extends CrudDao<OrderDetailDTO> {
   boolean saveOrderDetail(String orderId, List<OrderDetailDTO> orderDetails) throws SQLException, ClassNotFoundException;
}
