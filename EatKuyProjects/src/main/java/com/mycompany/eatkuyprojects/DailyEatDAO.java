/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.eatkuyprojects;

import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.DatePicker;

/**
 *
 * @author ASUS
 */
public class DailyEatDAO {
    private static DailyEat getDailyEatFromResultSet(ResultSet rs) throws SQLException {
        DailyEat de = null;
        if (rs.next()) {
            de = new DailyEat();
            de.setID_catat(rs.getInt("ID_catat"));
            de.setNamaMakanan(rs.getString("NamaMakanan"));
            de.setKalori(rs.getInt("Kalori"));
            de.setSesiMakan(rs.getString("SesiMakan"));
        }
        return de;
    }
    
    public static ObservableList<DailyEat> searchDailyEats(String date) throws SQLException, ClassNotFoundException {
        String selectStmt = "SELECT * FROM MakananHarian WHERE Tanggal = '"+date+"'";
        try {
            ResultSet rsMtk = DBUtil.getInstance().dbExecuteQuery(selectStmt);
            ObservableList<DailyEat> mtkList = getDailyEatList(rsMtk);
            return mtkList;
        } catch (SQLException e) {
            System.out.println("SQL select operation has been failed: " + e); //Return exception
            throw e;
        }
    }
    
    private static ObservableList<DailyEat> getDailyEatList(ResultSet rs) throws SQLException, ClassNotFoundException {
        ObservableList<DailyEat> deList = FXCollections.observableArrayList();
        while (rs.next()) {
            DailyEat de = new DailyEat();
            de.setID_catat(rs.getInt("ID_catat"));
            de.setNamaMakanan(rs.getString("NamaMakanan"));
            de.setKalori(rs.getInt("Kalori"));
            de.setSesiMakan(rs.getString("SesiMakan"));
            deList.add(de);
        }
        return deList;
    }
}
