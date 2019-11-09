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
public class MakalDAO {
    private static MakananKalori getMakananKaloriFromResultSet(ResultSet rs) throws SQLException{
        MakananKalori makal = null;
        if (rs.next()) {
            makal = new MakananKalori();
            makal.setId_makanan(rs.getInt("id_makanan"));
            makal.setNama_makanan(rs.getString("Nama_makanan"));
            makal.setKalori(rs.getInt("Kalori"));
        } 
        return makal;
    }
    
    public static ObservableList<MakananKalori> searchMakananKaloris() throws SQLException, ClassNotFoundException {
        String selectStmt = "SELECT * FROM makal";
        try {
            ResultSet rsMtk = DBUtil.getInstance().dbExecuteQuery(selectStmt);
            ObservableList<MakananKalori> mtkList = getMakananKaloriList(rsMtk);
            return mtkList;
        } catch (SQLException e) {
            System.out.println("SQL select operation has been failed: " + e); //Return exception
            throw e;
        }
    }
    
    private static ObservableList<MakananKalori> getMakananKaloriList(ResultSet rs) throws SQLException, ClassNotFoundException {
        ObservableList<MakananKalori> makalList = FXCollections.observableArrayList();
        while (rs.next()) {
            MakananKalori makal = new MakananKalori();
            makal.setId_makanan(rs.getInt("id_makanan"));
            makal.setNama_makanan(rs.getString("Nama_makanan"));
            makal.setKalori(rs.getInt("Kalori"));
            makalList.add(makal);
        }
        return makalList;
    }
}
