/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.eatkuyprojects;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.RowSetProvider;

/**
 *
 * @author Kukuh Nugroho
 */
public class DBUtil {
    private static DBUtil dbutil = null;
    private static Connection conn = null;
    
    public DBUtil(){
    }
    
    public static DBUtil getInstance(){
        if(dbutil == null) {
            dbutil = new DBUtil();
        }
        return dbutil;
    }
    
        private String getJDBC() {
        String urlDB = "EatKuyDB.db";
        String JDBC_DRIVER = null;
        if (urlDB != null) {
            JDBC_DRIVER = "jdbc:sqlite:" + urlDB;
        }

        return JDBC_DRIVER;
    }
        
    public void dbConnect() throws SQLException, ClassNotFoundException {
        try {
            conn = DriverManager.getConnection(getJDBC());
        } catch (SQLException e) {
            throw e;
        }
    }
    
    public void dbDisconnect() throws SQLException {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        } catch (SQLException e) {
            throw e;
        }
    }

    public ResultSet dbExecuteQuery(String queryStmt) throws SQLException,
            ClassNotFoundException {
        Statement stmt = null;
        ResultSet resultSet = null;
        CachedRowSet crs = null;

        try {
            dbConnect();
            stmt = conn.createStatement();
            resultSet = stmt.executeQuery(queryStmt);
            RowSetFactory factory = RowSetProvider.newFactory();
            crs = factory.createCachedRowSet();
            crs.populate(resultSet);
        } catch (SQLException e) {
            System.out.println("Problem occurred at executeQuery operation : " + e);
            throw e;
        } finally {
            if (resultSet != null) {
                //Close resultSet
                resultSet.close();
            }
            if (stmt != null) {
                //Close Statement
                stmt.close();
            }
            //Close connection
            dbDisconnect();
        }
        return crs;
    }

    public void dbExecuteUpdate(String sqlStmt) throws SQLException, ClassNotFoundException {
        Statement stmt = null;
        try {
            dbConnect();
            stmt = conn.createStatement();
            stmt.executeUpdate(sqlStmt);
        } catch (SQLException e) {
            throw e;
        } finally {
            if (stmt != null) {
                stmt.close();
            }
            dbDisconnect();
        }
    }
   
}
