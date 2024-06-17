package com.example.layeredarchitecture.Dao;

import com.example.layeredarchitecture.db.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SQLUtil{
    public static <T>T excute(String sql, Object... obj) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getDbConnection().getConnection();
        PreparedStatement pst = connection.prepareStatement(sql);
        for (int i = 0; i < obj.length; i++) {
            pst.setObject((i + 1), obj[i]);
        }
        if(sql.startsWith("SELECT")){
            ResultSet resultSet = pst.executeQuery();
            return (T) resultSet;
        }else{
            return (T) (Boolean) (pst.executeUpdate() >0);
            }
        }
}
