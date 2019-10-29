/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.eatkuyprojects;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author Kukuh
 */
public abstract class ConnectDb {
    Connection con;
    PreparedStatement ps;
    Statement st;
    ResultSet rs,sr,rs2;
    
    public abstract void connect();
}

