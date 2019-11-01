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
public class AkunQuery {
        public static ObservableList<AkunDB> getAkunDBList(ResultSet rs) throws SQLException, ClassNotFoundException {
        ObservableList<AkunDB> AkunDBList = FXCollections.observableArrayList();
        while (rs.next()) {
            AkunDB akun = new AkunDB();
            akun.setId(rs.getInt("ID_User"));
            akun.setEmail(rs.getString("Email"));
            akun.setUsername(rs.getString("Username"));
            akun.setPassword(rs.getString("Password"));
            akun.setUsia(rs.getInt("Usia"));
            akun.setJeniskelamin(rs.getString("JenisKelamin"));
            akun.setBeratbadan(rs.getInt("BeratBadan"));
            akun.setTinggibadan(rs.getInt("TinggiBadan"));
            akun.setTingkatAktivitas(rs.getInt("TingkatAktivitas"));
            akun.setStatus(rs.getInt("Status"));
            AkunDBList.add(akun);
        }
        return AkunDBList;
    }
        
        private static AkunDB getAkunFromResultSet(ResultSet rs) throws SQLException {
        AkunDB akun = null;
        if (rs.next()) {
            akun = new AkunDB();
            akun.setId(rs.getInt("ID_User"));
            akun.setEmail(rs.getString("Email"));
            akun.setUsername(rs.getString("Username"));
            akun.setPassword(rs.getString("Password"));
            akun.setUsia(rs.getInt("Usia"));
            akun.setJeniskelamin(rs.getString("JenisKelamin"));
            akun.setBeratbadan(rs.getInt("BeratBadan"));
            akun.setTinggibadan(rs.getInt("TinggiBadan"));
            akun.setTingkatAktivitas(rs.getInt("TingkatAktivitas"));
            akun.setStatus(rs.getInt("Status"));
        }
            return akun;
        }
}
