/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.eatkuyprojects.model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author ASUS
 */
public class Akun {
    private SimpleIntegerProperty ID_User;
    private SimpleStringProperty Email;
    private SimpleStringProperty Username;
    private SimpleStringProperty Password;
    private SimpleIntegerProperty Usia;
    private SimpleStringProperty JenisKelamin;
    private SimpleIntegerProperty BeratBadan;
    private SimpleIntegerProperty TinggiBadan;
    private SimpleIntegerProperty TingkatAktivitas;
    private SimpleIntegerProperty Status;

    public int getID_User() {
        return ID_User.get();
    }

    public void setID_User(int ID_User) {
        this.ID_User = new SimpleIntegerProperty(ID_User);
    }

    public String getEmail() {
        return Email.get();
    }

    public void setEmail(String Email) {
        this.Email = new SimpleStringProperty(Email);
    }

    public String getUsername() {
        return Username.get();
    }

    public void setUsername(String Username) {
        this.Username = new SimpleStringProperty(Username);
    }

    public String getPassword() {
        return Password.get();
    }

    public void setPassword(String Password) {
        this.Password = new SimpleStringProperty(Password);
    }

    public int getUsia() {
        return Usia.get();
    }

    public void setUsia(int Usia) {
        this.Usia = new SimpleIntegerProperty(Usia);
    }

    public String getJenisKelamin() {
        return JenisKelamin.get();
    }

    public void setJenisKelamin(String JenisKelamin) {
        this.JenisKelamin = new SimpleStringProperty(JenisKelamin);
    }

    public int getBeratBadan() {
        return BeratBadan.get();
    }

    public void setBeratBadan(int BeratBadan) {
        this.BeratBadan = new SimpleIntegerProperty(BeratBadan);
    }

    public int getTinggiBadan() {
        return TinggiBadan.get();
    }

    public void setTinggiBadan(int TinggiBadan) {
        this.TinggiBadan = new SimpleIntegerProperty(TinggiBadan);
    }

    public int getTingkatAktivitas() {
        return TingkatAktivitas.get();
    }

    public void setTingkatAktivitas(int TingkatAktivitas) {
        this.TingkatAktivitas = new SimpleIntegerProperty(TingkatAktivitas);
    }

    public int getStatus() {
        return Status.get();
    }

    public void setStatus(int Status) {
        this.Status = new SimpleIntegerProperty(Status);
    }
}
