/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.eatkuyprojects;

import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 *
 * @author Kukuh Nugroho
 */
public class Session {
    String username = "";
    String password = "";
    
    public Session(String username, String password) {
        this.username = username;
        this.password = password;
    }
    
    public void destorySession(){
        this.username = null;
        this.password = null;
    }
    
    public String getUsername(){
        return this.username;
    }
}
