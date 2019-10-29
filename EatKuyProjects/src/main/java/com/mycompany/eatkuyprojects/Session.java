/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.eatkuyprojects;

/**
 *
 * @author ASUS
 */
public class Session {
    private String username="";
    private String password="";

    public Session(String username, String password) {
        this.username=username;
        this.password=password;
    }

    public String getUsername() {
        return username;
    }
    
    
    
}
