package com.example.layeredarchitecture.Dao.Custom.Impl;

import com.example.layeredarchitecture.Dao.Custom.CustomerDao;
import com.example.layeredarchitecture.Dao.SQLUtil;
import com.example.layeredarchitecture.model.CustomerDTO;
import com.example.layeredarchitecture.view.tdm.CustomerTM;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDaoImpl implements CustomerDao {
public List<CustomerTM> loadAll() throws SQLException, ClassNotFoundException {
    /*Connection connection = DBConnection.getDbConnection().getConnection();
    Statement stm = connection.createStatement();
    ResultSet rst = stm.executeQuery("SELECT * FROM Customer");*/
    ResultSet rst =  SQLUtil.excute("SELECT * FROM Customer");
    List<CustomerTM> AllCustomer = new ArrayList<>();
    while (rst.next()) {
        CustomerTM customerTM = new CustomerTM(rst.getString("id"), rst.getString("name"), rst.getString("address"));
        AllCustomer.add(customerTM);
    }
    return AllCustomer;
}
public void Save(CustomerTM customerTM) throws SQLException, ClassNotFoundException {
   /* Connection connection = DBConnection.getDbConnection().getConnection();
    PreparedStatement pstm = connection.prepareStatement("INSERT INTO Customer (id,name, address) VALUES (?,?,?)");
    pstm.setString(1, customerTM.getId());
    pstm.setString(2, customerTM.getName());
    pstm.setString(3, customerTM.getAddress());*/
    SQLUtil.excute("INSERT INTO Customer (id,name,address) VALUES (?,?,?)",customerTM.getId(),customerTM.getName(),customerTM.getAddress());
    //pstm.executeUpdate(
}
public void Update(CustomerTM customerTM) throws SQLException, ClassNotFoundException {
    /*Connection connection = DBConnection.getDbConnection().getConnection();
    PreparedStatement pstm = connection.prepareStatement("UPDATE Customer SET name=?, address=? WHERE id=?");
    pstm.setString(1, customerTM.getName());
    pstm.setString(2, customerTM.getAddress());
    pstm.setString(3, customerTM.getId());
    pstm.executeUpdate();*/
    SQLUtil.excute("UPDATE Customer SET name=?, address=? WHERE id=?",customerTM.getName(),customerTM.getAddress(),customerTM.getId());
}
public boolean Exit(String id) throws SQLException, ClassNotFoundException {
    /*   Connection connection = DBConnection.getDbConnection().getConnection();
    PreparedStatement pstm = connection.prepareStatement("SELECT id FROM Customer WHERE id=?");
    pstm.setString(1, id);
    return pstm.executeQuery().next();*/
   ResultSet resultSet = SQLUtil.excute("SELECT id FROM Customer WHERE id=?",id);
   return resultSet.next();
}
public void Delete(String id) throws SQLException, ClassNotFoundException {
   /* Connection connection = DBConnection.getDbConnection().getConnection();
    PreparedStatement pstm = connection.prepareStatement("DELETE FROM Customer WHERE id=?");
    pstm.setString(1, id);
    pstm.executeUpdate();*/
    SQLUtil.excute("DELETE FROM Customer WHERE id=?",id);
}
public String GenarateNextID() throws SQLException, ClassNotFoundException {
    /*Connection connection = DBConnection.getDbConnection().getConnection();

    ResultSet rst = connection.createStatement().executeQuery("SELECT id FROM Customer ORDER BY id DESC LIMIT 1;");
     */
    ResultSet rst = SQLUtil.excute("SELECT id FROM Customer ORDER BY id DESC LIMIT 1;");
    if (rst.next()) {
        String id = rst.getString("id");
        int newCustomerId = Integer.parseInt(id.replace("C00-", "")) + 1;
        return String.format("C00-%03d", newCustomerId);
    } else {
        return "C00-001";
    }
}
public List<String> loadAllIds() throws SQLException, ClassNotFoundException {
/*    Connection connection = DBConnection.getDbConnection().getConnection();
    Statement stm = connection.createStatement();

    ResultSet rst = stm.executeQuery("SELECT * FROM Customer");

 */
    ResultSet rst = SQLUtil.excute("SELECT * FROM Customer");
List<String> list = new ArrayList<>();
    while (rst.next()) {
        list.add(rst.getString("id"));
    }
return list;
}

public CustomerDTO search(String newValue) throws SQLException, ClassNotFoundException {
   /* Connection connection = DBConnection.getDbConnection().getConnection();
    PreparedStatement pstm = connection.prepareStatement("SELECT * FROM Customer WHERE id=?");
    pstm.setString(1, newValue + "");
    ResultSet rst = pstm.executeQuery();
    */
    ResultSet rst = SQLUtil.excute("SELECT * FROM Customer WHERE id=?",newValue);
    rst.next();
    CustomerDTO customerDTO = new CustomerDTO(newValue + "", rst.getString("name"), rst.getString("address"));
  return customerDTO;
}
}

