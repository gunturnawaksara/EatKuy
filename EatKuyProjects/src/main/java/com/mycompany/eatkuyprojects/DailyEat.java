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
public class DailyEat {
    private SimpleIntegerProperty ID_catat;
    private SimpleStringProperty NamaMakanan;
    private SimpleIntegerProperty Kalori;
    private SimpleStringProperty SesiMakan;

    /**
     * @return the ID_catat
     */
    public int getID_catat() {
        return ID_catat.get();
    }

    /**
     * @param ID_catat the ID_catat to set
     */
    public void setID_catat(int ID_catat) {
        this.ID_catat = new SimpleIntegerProperty(ID_catat);
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
     * @return the SesiMakan
     */
    public String getSesiMakan() {
        return SesiMakan.get();
    }

    /**
     * @param SesiMakan the SesiMakan to set
     */
    public void setSesiMakan(String SesiMakan) {
        this.SesiMakan = new SimpleStringProperty(SesiMakan);
    }
    
}
