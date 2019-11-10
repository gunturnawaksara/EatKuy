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
 * @author Kukuh Nugroho
 */
public class Makanan {
    private SimpleIntegerProperty id_makanan;
    private SimpleStringProperty Nama_makanan;
    private SimpleStringProperty Kalori;

    public String getNama_makanan() {
        return Nama_makanan.get();
    }

    public void setNama_makanan(String Nama_makanan) {
        this.Nama_makanan = new SimpleStringProperty(Nama_makanan);
    }

    public String getKalori() {
        return Kalori.get();
    }

    public void setKalori(String Kalori) {
        this.Kalori = new SimpleStringProperty(Kalori);
    }

    public int getId_makanan() {
        return id_makanan.get();
    }

    public void setId_makanan(int id_makanan) {
        this.id_makanan = new SimpleIntegerProperty(id_makanan);
    }
    
    
    
}
