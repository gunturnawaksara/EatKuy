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
        private static ObservableList<AkunDB> getAkunDBList(ResultSet rs) throws SQLException, ClassNotFoundException {
        ObservableList<AkunDB> AkunDBList = FXCollections.observableArrayList();
        while (rs.next()) {
            AkunDB akun = new AkunDB();
            akun.setId(rs.getInt("id"));
            akun.setEmail(rs.getString("email"));
            akun.setUsername(rs.getString("username"));
            akun.setPassword(rs.getString("password"));
            akun.setUsia(rs.getInt("usia"));
            akun.setJenisKelamin(rs.getString("jeniskelamin"));
            akun.setBeratBadan(rs.getInt("beratbadan"));
            akun.setTinggiBadan(rs.getInt("tinggibadan"));
            akun.setTingkatAktivitas(rs.getInt("tingkatAktivitas"));
            akun.setStatus(rs.getInt("status"));
            AkunDBList.add(akun);
        }
        return AkunDBList;
    }
}
