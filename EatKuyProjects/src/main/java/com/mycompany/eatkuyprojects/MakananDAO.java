package com.mycompany.eatkuyprojects;


import com.mycompany.eatkuyprojects.DBUtil;
import com.mycompany.eatkuyprojects.Makanan;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Kukuh Nugroho
 */
public class MakananDAO {
    private static Makanan getMakananFromResultSet(ResultSet rs) throws SQLException {
        Makanan makanan = null;
        if (rs.next()) {
            makanan = new Makanan();
            makanan.setId_makanan(rs.getInt("Id_makanan"));
            makanan.setNama_makanan(rs.getString("Nama_makanan"));
            makanan.setKalori(rs.getString("Kalori"));
        }
        return makanan;
    }
    
    public static ObservableList<Makanan> searchMakanans() throws SQLException, ClassNotFoundException {
        String selectStmt = "SELECT * FROM Makanan";
        try {
            ResultSet rsMtk = DBUtil.getInstance().dbExecuteQuery(selectStmt);
            ObservableList<Makanan> mtkList = getMakananList(rsMtk);
            return mtkList;
        } catch (SQLException e) {
            System.out.println("SQL select operation has been failed: " + e); //Return exception
            throw e;
        }
    }
    
    private static ObservableList<Makanan> getMakananList(ResultSet rs) throws SQLException, ClassNotFoundException {
        ObservableList<Makanan> makananList = FXCollections.observableArrayList();
        while (rs.next()) {
            Makanan makanan = new Makanan();
            makanan.setId_makanan(rs.getInt("Id_makanan"));
            makanan.setNama_makanan(rs.getString("Nama_makanan"));
            makanan.setKalori(rs.getString("Kalori"));
            makananList.add(makanan);
        }
        return makananList;
    }
}
