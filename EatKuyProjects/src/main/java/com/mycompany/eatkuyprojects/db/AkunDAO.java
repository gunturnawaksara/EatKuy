/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.eatkuyprojects.db;

import com.mycompany.eatkuyprojects.model.Akun;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author ASUS
 */
public class AkunDAO {
    private static Akun getAkunFromResultSet(ResultSet rs) throws SQLException {
        Akun akun = null;
        if (rs.next()) {
            akun = new Akun();
            akun.setID_User(rs.getInt("ID_User"));
            akun.setEmail(rs.getString("Email"));
            akun.setUsername(rs.getString("Username"));
            akun.setPassword(rs.getString("Password"));
            akun.setUsia(rs.getInt("Usia"));
            akun.setJenisKelamin(rs.getString("JenisKelamin"));
            akun.setBeratBadan(rs.getInt("BeratBadan"));
            akun.setTinggiBadan(rs.getInt("TinggiBadan"));
            akun.setTingkatAktivitas(rs.getInt("TingkatAktivitas"));
            akun.setStatus(rs.getInt("Status"));
        }
        return akun;
    }
    
    public static ObservableList<Akun> searchAkuns() throws SQLException, ClassNotFoundException {
        String selectStmt = "SELECT * FROM akun";
        try {
            ResultSet rsMtk = DBUtil.getInstance().dbExecuteQuery(selectStmt);
            ObservableList<Akun> mtkList = getAkunList(rsMtk);
            return mtkList;
        } catch (SQLException e) {
            System.out.println("SQL select operation has been failed: " + e); //Return exception
            throw e;
        }
    }
    
    private static ObservableList<Akun> getAkunList(ResultSet rs) throws SQLException, ClassNotFoundException {
        ObservableList<Akun> akunList = FXCollections.observableArrayList();
        while (rs.next()) {
            Akun akun = new Akun();
            akun.setID_User(rs.getInt("ID_User"));
            akun.setEmail(rs.getString("Email"));
            akun.setUsername(rs.getString("Username"));
            akun.setPassword(rs.getString("Password"));
            akun.setUsia(rs.getInt("Usia"));
            akun.setJenisKelamin(rs.getString("JenisKelamin"));
            akun.setBeratBadan(rs.getInt("BeratBadan"));
            akun.setTinggiBadan(rs.getInt("TinggiBadan"));
            akun.setTingkatAktivitas(rs.getInt("TingkatAktivitas"));
            akun.setStatus(rs.getInt("Status"));
        }
        return akunList;
    }
}
