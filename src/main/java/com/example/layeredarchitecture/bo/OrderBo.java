package com.example.layeredarchitecture.bo;

import com.example.layeredarchitecture.Dao.SQLUtil;
import com.example.layeredarchitecture.bo.Impl.OrderBoImpl;
import com.example.layeredarchitecture.model.OrderDetailDTO;
import com.example.layeredarchitecture.view.tdm.OrderTM;
import javafx.scene.control.Alert;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public interface OrderBo {
    public List<OrderTM> loadAll() throws SQLException, ClassNotFoundException;

    public void Save(OrderTM TM) throws SQLException, ClassNotFoundException ;

    public void Update(OrderTM TM) throws SQLException, ClassNotFoundException ;

    public boolean Exit(String id) throws SQLException, ClassNotFoundException ;

    public void Delete(String id) throws SQLException, ClassNotFoundException ;
    public String GenarateNextID() ;


    public List<String> loadAllIds() throws SQLException, ClassNotFoundException ;
    public boolean orderSave(String orderId, LocalDate orderDate, String customerId, List<OrderDetailDTO> orderDetails) ;
}
