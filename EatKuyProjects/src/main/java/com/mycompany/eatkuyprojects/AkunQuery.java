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
            akun.setId(rs.getInt(1));
            akun.setEmail(rs.getString(2));
            akun.setUsername(rs.getString(3));
            akun.setPassword(rs.getString(4));
            akun.setUsia(rs.getInt(5));
            akun.setJenisKelamin(rs.getString(6));
            akun.setBeratBadan(rs.getInt(7));
            akun.setTinggiBadan(rs.getInt(8));
            akun.setTingkatAktivitas(rs.getInt(9));
            akun.setStatus(rs.getInt(10));
            AkunDBList.add(akun);
        }
        return AkunDBList;
    }
}
