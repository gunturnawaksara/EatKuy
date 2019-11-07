/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.eatkuyprojects;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javax.swing.JOptionPane;

/**
 *
 * @author Kukuh
 */
public class QueryDb extends ConnectDb{   

    static Object getInstance() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public QueryDb(){
        this.connect();
    }

    public void connect(){
        
        try{
            try{
                Class.forName("org.sqlite.JDBC");
            }catch(ClassNotFoundException ex){
                Logger.getLogger(ConnectDb.class.getName()).log(Level.SEVERE, null, ex);
            }
            con = DriverManager.getConnection("jdbc:sqlite:EatKuyDB.db");
            st = con.createStatement();
            System.out.println("connect");
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void queryUp(String query){
        try {
            ps=con.prepareStatement(query);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ConnectDb.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public ResultSet queryResult(String query){
        try{
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
        }catch(SQLException E){
            JOptionPane.showMessageDialog(null, "ERROR");
        }
        return rs;
    }
    
    public ResultSet logquery(String username, String password){
        try{
            String query = "SELECT * from Akun WHERE Username=? AND Password=?";
            ps = con.prepareStatement(query);
            ps.setString(1, username);
            ps.setString(2, password);
            rs = ps.executeQuery();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "FAILED log");
        }
        return rs;
    }
    
    public ResultSet isEmailExist(String email){
        try{
            String query = "SELECT * from Akun WHERE Email=?";
            ps = con.prepareStatement(query);
            ps.setString(1, email);
            rs = ps.executeQuery();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "FAILED log");
        }
        return rs;
    }
    
    public ResultSet isUsernameExist(String username){
        try{
            String query = "SELECT * from Akun WHERE Username=?";
            ps = con.prepareStatement(query);
            ps.setString(1, username);
            rs = ps.executeQuery();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "FAILED log");
        }
        return rs;
    }
    
    public void InsertAkun (String Email, String Username, String Password){
        try{
            Statement statement;
            statement = con.createStatement();
            int Usia = 0;
            String JenisKelamin = "-";
            int BeratBadan = 0;
            int TinggiBadan = 0;
            int TingkatAktivitas = 0;
            int Status = 2;
            String query = "INSERT INTO Akun(Email,Username,Password,JenisKelamin,Usia,BeratBadan,TinggiBadan,TingkatAktivitas,Status) VALUES ('"+Email+"','"+Username+"','"+Password+"','"+JenisKelamin+"','"+Usia+"','"+BeratBadan+"','"+TinggiBadan+"','"+TingkatAktivitas+"','"+Status+"')";
            statement.executeUpdate(query);
            //System.out.println("success");
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "FAILED");
        }
    }
    
    public void UpdateAkun (String JenisKelamin, String Usia, int BeratBadan, int TinggiBadan, String Username){
        try{
            Statement statement;
            statement = con.createStatement();
            String query;
            query = "UPDATE Akun SET JenisKelamin = '"+JenisKelamin+"', Usia= '"+Usia+"', BeratBadan= '"+BeratBadan+"', TinggiBadan= '"+TinggiBadan+"' WHERE Username='"+Username+"'";
            statement.executeUpdate(query);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("UPDATE SUCCESSFULL");
            alert.setHeaderText("UPDATE SUCCESS FULL !");
            alert.setContentText("USER DATA UPDATED");
            alert.showAndWait();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "FAILED");
        }
    }
    
    public static ObservableList<AkunDB> getAkunDBList(ResultSet rs) throws SQLException{
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
    
}

