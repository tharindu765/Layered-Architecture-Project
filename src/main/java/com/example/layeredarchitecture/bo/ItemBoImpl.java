package com.example.layeredarchitecture.bo;

import com.example.layeredarchitecture.Dao.Custom.Impl.ItemDaoImpl;
import com.example.layeredarchitecture.Dao.Custom.ItemDao;
import com.example.layeredarchitecture.Dao.SQLUtil;
import com.example.layeredarchitecture.db.DBConnection;
import com.example.layeredarchitecture.model.ItemDTO;
import com.example.layeredarchitecture.model.OrderDetailDTO;
import com.example.layeredarchitecture.view.tdm.ItemTM;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemBoImpl {
    public List<ItemTM> loadAll() throws SQLException, ClassNotFoundException {
        ItemDao itemDao = new ItemDaoImpl();
        return itemDao.loadAll();
    }
    public void Delete(String id) throws SQLException, ClassNotFoundException {
        ItemDao itemDao = new ItemDaoImpl();
        itemDao.Delete(id);
    }
    public void Save(ItemTM itemTM) throws SQLException, ClassNotFoundException {
        ItemDao itemDao = new ItemDaoImpl();
        itemDao.Save(itemTM);
    }
    public void Update(ItemTM itemTM) throws SQLException, ClassNotFoundException {
        ItemDao itemDao = new ItemDaoImpl();
        itemDao.Update(itemTM);
    }
    public boolean Exit(String code) throws SQLException, ClassNotFoundException {
        ItemDao itemDao = new ItemDaoImpl();
        return itemDao.Exit(code);
    }

    public String GenarateNextID() throws SQLException, ClassNotFoundException {
        ItemDao itemDao = new ItemDaoImpl();
      return itemDao.GenarateNextID();
    }
    public ItemDTO findTheItem(String newItemCode) throws SQLException, ClassNotFoundException {
        ItemDao itemDao = new ItemDaoImpl();
        return itemDao.findTheItem(newItemCode);
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
