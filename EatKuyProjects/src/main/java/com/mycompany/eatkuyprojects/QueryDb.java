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
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Kukuh
 */
public class QueryDb extends ConnectDb{    
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
    
    public ResultSet isExist(String username, String email){
        try{
            String query = "SELECT * from Akun WHERE Username=? OR Email=?";
            ps = con.prepareStatement(query);
            ps.setString(1, username);
            ps.setString(2, email);
            rs = ps.executeQuery();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "FAILED log");
        }
        return rs;
    }
    
    public ResultSet isEmpty(String username){
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
    
}

