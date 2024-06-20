package com.example.layeredarchitecture.bo.Impl;

import com.example.layeredarchitecture.model.OrderDetailDTO;

import java.sql.SQLException;
import java.util.List;

public class OrderDetailBoImpl {
    public boolean saveOrderDetail(String orderId, List<OrderDetailDTO> orderDetails) throws SQLException, ClassNotFoundException {
        OrderDetailBoImpl orderDetailBo = new OrderDetailBoImpl();
        return orderDetailBo.saveOrderDetail(orderId, orderDetails);
    }

    public List<OrderDetailDTO> loadAll() throws SQLException, ClassNotFoundException {
        return null;
    }


    public void Save(OrderDetailDTO TM) throws SQLException, ClassNotFoundException {

    }


    public void Update(OrderDetailDTO TM) throws SQLException, ClassNotFoundException {

    }


    public boolean Exit(String id) throws SQLException, ClassNotFoundException {
        return false;
    }


    public void Delete(String id) throws SQLException, ClassNotFoundException {

    }


    public String GenarateNextID() throws SQLException, ClassNotFoundException {
        return null;
    }


    public List<String> loadAllIds() throws SQLException, ClassNotFoundException {
        return null;
    }
}
