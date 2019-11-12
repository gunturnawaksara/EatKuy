/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.eatkuyprojects;

import java.util.Date;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author ASUS
 */
public class History {
    private SimpleIntegerProperty ID_History;
    private SimpleStringProperty User;
    private SimpleStringProperty NamaMakanan;
    private SimpleIntegerProperty Kalori;
    private SimpleStringProperty Tanggal;

    /**
     * @return the ID_History
     */
    public int getID_History() {
        return ID_History.get();
    }

    /**
     * @param ID_History the ID_History to set
     */
    public void setID_History(int ID_History) {
        this.ID_History = new SimpleIntegerProperty(ID_History);
    }

    /**
     * @return the User
     */
    public String getUser() {
        return User.get();
    }

    /**
     * @param User the User to set
     */
    public void setUser(String User) {
        this.User = new SimpleStringProperty(User);
    }

    /**
     * @return the NamaMakanan
     */
    public String getNamaMakanan() {
        return NamaMakanan.get();
    }

    /**
     * @param NamaMakanan the NamaMakanan to set
     */
    public void setNamaMakanan(String NamaMakanan) {
        this.NamaMakanan = new SimpleStringProperty(NamaMakanan);
    }

    /**
     * @return the Kalori
     */
    public int getKalori() {
        return Kalori.get();
    }

    /**
     * @param Kalori the Kalori to set
     */
    public void setKalori(int Kalori) {
        this.Kalori = new SimpleIntegerProperty(Kalori);
    }

    /**
     * @return the Tanggal
     */
    public String getTanggal() {
        return Tanggal.get();
    }

    /**
     * @param Tanggal the Tanggal to set
     */
    public void setTanggal(String Tanggal) {
        this.Tanggal = new SimpleStringProperty(Tanggal);
    }
}
