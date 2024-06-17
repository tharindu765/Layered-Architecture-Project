package com.example.layeredarchitecture.Dao.Custom.Impl;

import com.example.layeredarchitecture.Dao.Custom.OrderDao;
import com.example.layeredarchitecture.Dao.Custom.OrderDetailDao;
import com.example.layeredarchitecture.Dao.SQLUtil;
import com.example.layeredarchitecture.db.DBConnection;
import com.example.layeredarchitecture.model.OrderDetailDTO;
import com.example.layeredarchitecture.view.tdm.OrderTM;
import javafx.scene.control.Alert;

import java.sql.*;
import java.time.LocalDate;
import java.util.List;

public class OrderDaoImpl implements OrderDao {
    @Override
    public List<OrderTM> loadAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public void Save(OrderTM TM) throws SQLException, ClassNotFoundException {

    }

    @Override
    public void Update(OrderTM TM) throws SQLException, ClassNotFoundException {

    }

    @Override
    public boolean Exit(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public void Delete(String id) throws SQLException, ClassNotFoundException {

    }

    public String GenarateNextID(){
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

    @Override
    public List<String> loadAllIds() throws SQLException, ClassNotFoundException {
        return null;
    }

    public boolean orderSave(String orderId, LocalDate orderDate, String customerId, List<OrderDetailDTO> orderDetails) {
    Connection connection = null;
    try {
        connection = DBConnection.getDbConnection().getConnection();
        PreparedStatement stm = connection.prepareStatement("SELECT oid FROM `Orders` WHERE oid=?");
        stm.setString(1, orderId);
        /*if order id already exist*/
        if (stm.executeQuery().next()) {

        }

        connection.setAutoCommit(false);
        stm = connection.prepareStatement("INSERT INTO `Orders` (oid, date, customerID) VALUES (?,?,?)");
        stm.setString(1, orderId);
        stm.setDate(2, Date.valueOf(orderDate));
        stm.setString(3, customerId);

        if (stm.executeUpdate() != 1) {
            connection.rollback();
            connection.setAutoCommit(true);
            return false;
        }
        OrderDetailDao orderDetailDao = new OrderDetailDaoImpl();
        if (orderDetailDao.saveOrderDetail(orderId, orderDetails)){
            connection.commit();
            connection.setAutoCommit(true);
            return false;
        }
        connection.commit();
        connection.setAutoCommit(true);
        return true;

    } catch (SQLException throwables) {
        throwables.printStackTrace();
    } catch (ClassNotFoundException e) {
        e.printStackTrace();
    }
    return false;

}
}
