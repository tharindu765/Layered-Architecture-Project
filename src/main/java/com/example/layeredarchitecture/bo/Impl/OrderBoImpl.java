package com.example.layeredarchitecture.bo.Impl;

import com.example.layeredarchitecture.Dao.SQLUtil;
import com.example.layeredarchitecture.model.OrderDetailDTO;
import com.example.layeredarchitecture.view.tdm.OrderTM;
import javafx.scene.control.Alert;

import java.sql.*;
import java.time.LocalDate;
import java.util.List;

public class OrderBoImpl {
    public List<OrderTM> loadAll() throws SQLException, ClassNotFoundException {
        return null;
    }


    public void Save(OrderTM TM) throws SQLException, ClassNotFoundException {

    }


    public void Update(OrderTM TM) throws SQLException, ClassNotFoundException {

    }


    public boolean Exit(String id) throws SQLException, ClassNotFoundException {
        return false;
    }


    public void Delete(String id) throws SQLException, ClassNotFoundException {

    }

    public String GenarateNextID() {
        try {
        /*Connection connection = DBConnection.getDbConnection().getConnection();
        Statement stm = connection.createStatement();
        ResultSet rst = stm.executeQuery("SELECT oid FROM `Orders` ORDER BY oid DESC LIMIT 1;");
*/
            ResultSet rst = SQLUtil.excute("SELECT oid FROM `Orders` ORDER BY oid DESC LIMIT 1;");
            return rst.next() ? String.format("OID-%03d", (Integer.parseInt(rst.getString("oid").replace("OID-", "")) + 1)) : "OID-001";
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to generate a new order id").show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return "OID-001";
    }


    public List<String> loadAllIds() throws SQLException, ClassNotFoundException {
        return null;
    }

    public boolean orderSave(String orderId, LocalDate orderDate, String customerId, List<OrderDetailDTO> orderDetails) {
        OrderBoImpl orderBo = new OrderBoImpl();
        return orderBo.orderSave(orderId, orderDate, customerId, orderDetails);
    }
}