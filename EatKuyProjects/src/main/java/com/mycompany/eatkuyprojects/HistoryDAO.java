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

/**
 *
 * @author ASUS
 */
public class HistoryDAO {
    private static History getHistoryFromResultSet(ResultSet rs) throws SQLException {
        History history = null;
        if (rs.next()) {
            history = new History();
            history.setID_History(rs.getInt("ID_History"));
            history.setUser(rs.getString("User"));
            history.setNamaMakanan(rs.getString("NamaMakanan"));
            history.setKalori(rs.getInt("Kalori"));
            history.setTanggal(rs.getString("Tanggal"));
        }
        return history;
    }
    
    public static ObservableList<History> searchHistorys() throws SQLException, ClassNotFoundException {
        String selectStmt = "SELECT * FROM MakananHistory";
        try {
            ResultSet rsMtk = DBUtil.getInstance().dbExecuteQuery(selectStmt);
            ObservableList<History> mtkList = getHistoryList(rsMtk);
            return mtkList;
        } catch (SQLException e) {
            System.out.println("SQL select operation has been failed: " + e); //Return exception
            throw e;
        }
    }
    
    private static ObservableList<History> getHistoryList(ResultSet rs) throws SQLException, ClassNotFoundException {
        ObservableList<History> historyList = FXCollections.observableArrayList();
        while (rs.next()) {
            History history = new History();
            history = new History();
            history.setID_History(rs.getInt("ID_History"));
            history.setUser(rs.getString("User"));
            history.setNamaMakanan(rs.getString("NamaMakanan"));
            history.setKalori(rs.getInt("Kalori"));
            history.setTanggal(rs.getString("Tanggal"));
            historyList.add(history);
        }
        return historyList;
    }
}
