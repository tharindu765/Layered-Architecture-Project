package com.example.layeredarchitecture.Dao;

import com.example.layeredarchitecture.db.DBConnection;
import com.example.layeredarchitecture.model.ItemDTO;
import com.example.layeredarchitecture.model.OrderDetailDTO;
import com.example.layeredarchitecture.view.tdm.ItemTM;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ItemDaoImpl implements ItemDao{
    public List<ItemTM> loadAllItem() throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getDbConnection().getConnection();
        Statement stm = connection.createStatement();
        ResultSet rst = stm.executeQuery("SELECT * FROM Item");
        List<ItemTM> itemTMS = new ArrayList<>();
        while (rst.next()) {
            ItemTM itemTM = new ItemTM(rst.getString("code"), rst.getString("description"), rst.getBigDecimal("unitPrice"), rst.getInt("qtyOnHand"));
            itemTMS.add(itemTM);
        }
return itemTMS;
    }
    public void delete(String code) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getDbConnection().getConnection();
        PreparedStatement pstm = connection.prepareStatement("DELETE FROM Item WHERE code=?");
        pstm.setString(1, code);
        pstm.executeUpdate();
    }
public void save(ItemTM itemTM) throws SQLException, ClassNotFoundException {
    Connection connection = DBConnection.getDbConnection().getConnection();
    PreparedStatement pstm = connection.prepareStatement("INSERT INTO Item (code, description, unitPrice, qtyOnHand) VALUES (?,?,?,?)");
    pstm.setString(1, itemTM.getCode());
    pstm.setString(2, itemTM.getDescription());
    pstm.setBigDecimal(3, itemTM.getUnitPrice());
    pstm.setInt(4, itemTM.getQtyOnHand());
    pstm.executeUpdate();
}
public void update(ItemTM itemTM) throws SQLException, ClassNotFoundException {
    Connection connection = DBConnection.getDbConnection().getConnection();
    PreparedStatement pstm = connection.prepareStatement("UPDATE Item SET description=?, unitPrice=?, qtyOnHand=? WHERE code=?");
    pstm.setString(1, itemTM.getDescription());
    pstm.setBigDecimal(2, itemTM.getUnitPrice());
    pstm.setInt(3, itemTM.getQtyOnHand());
    pstm.setString(4, itemTM.getCode());
    pstm.executeUpdate();
}
public boolean exitItem(String code) throws SQLException, ClassNotFoundException {
    Connection connection = DBConnection.getDbConnection().getConnection();
    PreparedStatement pstm = connection.prepareStatement("SELECT code FROM Item WHERE code=?");
    pstm.setString(1, code);
    return pstm.executeQuery().next();
}

public String genarateNextId() throws SQLException, ClassNotFoundException {
    Connection connection = DBConnection.getDbConnection().getConnection();
    ResultSet rst = connection.createStatement().executeQuery("SELECT code FROM Item ORDER BY code DESC LIMIT 1;");
    if (rst.next()) {
        String id = rst.getString("code");
        int newItemId = Integer.parseInt(id.replace("I00-", "")) + 1;
        return String.format("I00-%03d", newItemId);
    } else {
        return "I00-001";
    }
}
public ItemDTO findTheItem(String newItemCode) throws SQLException, ClassNotFoundException {
    Connection connection = DBConnection.getDbConnection().getConnection();
    PreparedStatement pstm = connection.prepareStatement("SELECT * FROM Item WHERE code=?");
    pstm.setString(1, newItemCode + "");
    ResultSet rst = pstm.executeQuery();
    rst.next();
    ItemDTO item = new ItemDTO(newItemCode + "", rst.getString("description"), rst.getBigDecimal("unitPrice"), rst.getInt("qtyOnHand"));
return item;
}
public List<String> loadAllItems() throws SQLException, ClassNotFoundException {
    Connection connection = DBConnection.getDbConnection().getConnection();
    Statement stm = connection.createStatement();
    ResultSet rst = stm.executeQuery("SELECT * FROM Item");
    List<String> st = new ArrayList<>();
    while (rst.next()) {
        st.add(rst.getString("code"));
    }
    return st;
}

public boolean SaveItemFromDetail(OrderDetailDTO detail) throws SQLException, ClassNotFoundException {
    Connection connection = DBConnection.getDbConnection().getConnection();
    ItemDaoImpl itemDao = new ItemDaoImpl();
    ItemDTO item = itemDao.findTheItem(detail.getItemCode());
    item.setQtyOnHand(item.getQtyOnHand() - detail.getQty());

    PreparedStatement pstm = connection.prepareStatement("UPDATE Item SET description=?, unitPrice=?, qtyOnHand=? WHERE code=?");
    pstm.setString(1, item.getDescription());
    pstm.setBigDecimal(2, item.getUnitPrice());
    pstm.setInt(3, item.getQtyOnHand());
    pstm.setString(4, item.getCode());

    if (!(pstm.executeUpdate() > 0)) {
        connection.rollback();
        connection.setAutoCommit(true);
        return false;
    }
    return false;
}
}
