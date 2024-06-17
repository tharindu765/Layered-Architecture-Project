package com.example.layeredarchitecture.Dao.Custom.Impl;

import com.example.layeredarchitecture.Dao.Custom.ItemDao;
import com.example.layeredarchitecture.Dao.SQLUtil;
import com.example.layeredarchitecture.db.DBConnection;
import com.example.layeredarchitecture.model.ItemDTO;
import com.example.layeredarchitecture.model.OrderDetailDTO;
import com.example.layeredarchitecture.view.tdm.ItemTM;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ItemDaoImpl implements ItemDao {
    public List<ItemTM> loadAll() throws SQLException, ClassNotFoundException {
      /*  Connection connection = DBConnection.getDbConnection().getConnection();
        Statement stm = connection.createStatement();
        ResultSet rst = stm.executeQuery("SELECT * FROM Item");
       */
        ResultSet rst = SQLUtil.excute("SELECT * FROM Item");
        List<ItemTM> itemTMS = new ArrayList<>();

        while (rst.next()) {
            ItemTM itemTM = new ItemTM(rst.getString("code"), rst.getString("description"), rst.getBigDecimal("unitPrice"), rst.getInt("qtyOnHand"));
            itemTMS.add(itemTM);
        }
return itemTMS;
    }
    public void Delete(String id) throws SQLException, ClassNotFoundException {
        /*Connection connection = DBConnection.getDbConnection().getConnection();
        PreparedStatement pstm = connection.prepareStatement("DELETE FROM Item WHERE code=?");
        pstm.setString(1, code);
        pstm.executeUpdate();*/
        SQLUtil.excute("DELETE FROM Item WHERE code=?",id);
    }
public void Save(ItemTM itemTM) throws SQLException, ClassNotFoundException {
   /* Connection connection = DBConnection.getDbConnection().getConnection();
    PreparedStatement pstm = connection.prepareStatement("INSERT INTO Item (code, description, unitPrice, qtyOnHand) VALUES (?,?,?,?)");
    pstm.setString(1, itemTM.getCode());
    pstm.setString(2, itemTM.getDescription());
    pstm.setBigDecimal(3, itemTM.getUnitPrice());
    pstm.setInt(4, itemTM.getQtyOnHand());
    pstm.executeUpdate();*/
    SQLUtil.excute("INSERT INTO Item (code, description, unitPrice, qtyOnHand) VALUES (?,?,?,?)",itemTM.getCode(),itemTM.getDescription(),itemTM.getUnitPrice(),itemTM.getQtyOnHand());
}
public void Update(ItemTM itemTM) throws SQLException, ClassNotFoundException {
   /* Connection connection = DBConnection.getDbConnection().getConnection();
    PreparedStatement pstm = connection.prepareStatement("UPDATE Item SET description=?, unitPrice=?, qtyOnHand=? WHERE code=?");
    pstm.setString(1, itemTM.getDescription());
    pstm.setBigDecimal(2, itemTM.getUnitPrice());
    pstm.setInt(3, itemTM.getQtyOnHand());
    pstm.setString(4, itemTM.getCode());
    pstm.executeUpdate();*/
    SQLUtil.excute("UPDATE Item SET description=?, unitPrice=?, qtyOnHand=? WHERE code=?",itemTM.getDescription(),itemTM.getUnitPrice(),itemTM.getQtyOnHand(),itemTM.getCode());
}
public boolean Exit(String code) throws SQLException, ClassNotFoundException {
 /*   Connection connection = DBConnection.getDbConnection().getConnection();
    PreparedStatement pstm = connection.prepareStatement("SELECT code FROM Item WHERE code=?");
    pstm.setString(1, code);
    return pstm.executeQuery().next();*/
    ResultSet resultSet = SQLUtil.excute("SELECT code FROM Item WHERE code=?",code);
return resultSet.next();
    }

public String GenarateNextID() throws SQLException, ClassNotFoundException {
   /* Connection connection = DBConnection.getDbConnection().getConnection();

    ResultSet rst = connection.createStatement().executeQuery("SELECT code FROM Item ORDER BY code DESC LIMIT 1;");
    */
    ResultSet rst = SQLUtil.excute("SELECT code FROM Item ORDER BY code DESC LIMIT 1;");
    if (rst.next()) {
        String id = rst.getString("code");
        int newItemId = Integer.parseInt(id.replace("I00-", "")) + 1;
        return String.format("I00-%03d", newItemId);
    } else {
        return "I00-001";
    }
}
public ItemDTO findTheItem(String newItemCode) throws SQLException, ClassNotFoundException {
   /* Connection connection = DBConnection.getDbConnection().getConnection();
    PreparedStatement pstm = connection.prepareStatement("SELECT * FROM Item WHERE code=?");
    pstm.setString(1, newItemCode + "");
    ResultSet rst = pstm.executeQuery();

    */
    ResultSet rst = SQLUtil.excute("SELECT * FROM Item WHERE code=?",newItemCode);
    rst.next();
    ItemDTO item = new ItemDTO(newItemCode + "", rst.getString("description"), rst.getBigDecimal("unitPrice"), rst.getInt("qtyOnHand"));
return item;
}
public List<String> loadAllIds() throws SQLException, ClassNotFoundException {
   /* Connection connection = DBConnection.getDbConnection().getConnection();
    Statement stm = connection.createStatement();
    ResultSet rst = stm.executeQuery("SELECT * FROM Item");

    */
    ResultSet rst = SQLUtil.excute("SELECT * FROM Item");
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
