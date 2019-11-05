/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fxml;

import com.mycompany.eatkuyprojects.QueryDb;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author Kukuh Nugroho
 */
public class HomeController implements Initializable {
    
    private QueryDb db;
    private String sessionUsername;
    private String sessionStatus;
    @FXML
    private TextField jenisKelamin;
    @FXML
    private TextField usia;
    @FXML
    private TextField beratBadan;
    @FXML
    private TextField tinggiBadan;
    @FXML
    private TextField tingkatAktivitas;
    @FXML
    private Label username;
    @FXML
    private Label kalori;
    @FXML
    private TextField search;
    @FXML
    private ImageView searchButton;
    @FXML
    private ImageView tambah;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        db = new QueryDb();
        db.connect();
    }    

    @FXML
    private void changeButton(ActionEvent event) {
        String usiaUser = usia.getText();
        String beratBadanUser = beratBadan.getText();
        String tinggiBadanUser = tinggiBadan.getText();
        String jenisKelaminUser = jenisKelamin.getText();
        db.UpdateAkun(jenisKelaminUser, usiaUser, Integer.parseInt(beratBadanUser), Integer.parseInt(tinggiBadanUser), sessionUsername);
    }

    @FXML
    private void logoutButton(ActionEvent event) {
    }
    
    public void GetUserLogin(String uName, String uStatus) {
        // TODO Auto-generated method stub
        this.sessionUsername = uName;
        this.sessionStatus = uStatus;
    }
    
}
