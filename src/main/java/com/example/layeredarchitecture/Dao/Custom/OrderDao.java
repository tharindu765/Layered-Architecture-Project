package com.example.layeredarchitecture.Dao.Custom;

import com.example.layeredarchitecture.Dao.CrudDao;
import com.example.layeredarchitecture.db.DBConnection;
import com.example.layeredarchitecture.model.ItemDTO;
import com.example.layeredarchitecture.model.OrderDetailDTO;
import com.example.layeredarchitecture.view.tdm.OrderTM;
import javafx.scene.control.Alert;

import java.sql.*;
import java.time.LocalDate;
import java.util.List;

public interface OrderDao extends CrudDao<OrderTM> {
     boolean orderSave(String orderId, LocalDate orderDate, String customerId, List<OrderDetailDTO> orderDetails);
}
