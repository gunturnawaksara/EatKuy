/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.eatkuyprojects;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author ASUS
 */
public class AkunDB {

    AkunDB() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * @return the id
     */
    public int getId() {
        return id.get();
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id.set(id);
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email.get();
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email.set(email);
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username.get();
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username.set(username);
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password.get();
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password.set(password);
    }

    /**
     * @return the usia
     */
    public int getUsia() {
        return usia.get();
    }

    /**
     * @param usia the usia to set
     */
    public void setUsia(int usia) {
        this.usia.set(usia);
    }

    /**
     * @return the jeniskelamin
     */
    public String getJeniskelamin() {
        return jeniskelamin.get();
    }

    /**
     * @param jeniskelamin the jeniskelamin to set
     */
    public void setJeniskelamin(String jeniskelamin) {
        this.jeniskelamin.set(jeniskelamin);
    }

    /**
     * @return the beratbadan
     */
    public int getBeratbadan() {
        return beratbadan.get();
    }

    /**
     * @param beratbadan the beratbadan to set
     */
    public void setBeratbadan(int beratbadan) {
        this.beratbadan.set(beratbadan);
    }

    /**
     * @return the tinggibadan
     */
    public int getTinggibadan() {
        return tinggibadan.get();
    }

    /**
     * @param tinggibadan the tinggibadan to set
     */
    public void setTinggibadan(int tinggibadan) {
        this.tinggibadan.set(tinggibadan);
    }

    /**
     * @return the tingkatAktivitas
     */
    public int getTingkatAktivitas() {
        return tingkatAktivitas.get();
    }

    /**
     * @param tingkatAktivitas the tingkatAktivitas to set
     */
    public void setTingkatAktivitas(int tingkatAktivitas) {
        this.tingkatAktivitas.set(tingkatAktivitas);
    }

    /**
     * @return the status
     */
    public int getStatus() {
        return status.get();
    }

    /**
     * @param status the status to set
     */
    public void setStatus(int status) {
        this.status.set(status);
    }
    private SimpleIntegerProperty id;
    private SimpleStringProperty email;
    private SimpleStringProperty username;
    private SimpleStringProperty password;
    private SimpleIntegerProperty usia;
    private SimpleStringProperty jeniskelamin;
    private SimpleIntegerProperty beratbadan;
    private SimpleIntegerProperty tinggibadan;
    private SimpleIntegerProperty tingkatAktivitas;
    private SimpleIntegerProperty status;
    
//    public AkunDB(){
//        this(0,"","","",0,"",0,0,0,0);
//    }
    
    public AkunDB(int id, String email, String username, String password, 
            int usia, String jeniskelamin, int beratbadan, int tinggibadan, int tingkatAktivitas, int status){
        this.id = new SimpleIntegerProperty(id);
        this.email = new SimpleStringProperty(email);
        this.username = new SimpleStringProperty(username);
        this.password = new SimpleStringProperty(password);
        this.usia = new SimpleIntegerProperty(usia);
        this.jeniskelamin = new SimpleStringProperty(jeniskelamin);
        this.beratbadan = new SimpleIntegerProperty(beratbadan);
        this.tinggibadan = new SimpleIntegerProperty(tinggibadan);
        this.tingkatAktivitas = new SimpleIntegerProperty(tingkatAktivitas);
        this.status = new SimpleIntegerProperty(status);
    }

    void setJenisKelamin(String string) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    void setBeratBadan(int aInt) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    void setTinggiBadan(int aInt) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}