/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fxml;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class ProfilController implements Initializable {

    @FXML
    private Label navigasi;
    @FXML
    private Label username;
    @FXML
    private Button profile;
    @FXML
    private Button breakfast;
    @FXML
    private Button lunch;
    @FXML
    private Button dinner;
    @FXML
    private Button logout;
    @FXML
    private Label kalori;
    @FXML
    private TextField usia;
    @FXML
    private TextField beratBadan;
    @FXML
    private TextField tinggiBadan;
    @FXML
    private TextField jenisKelamin;
    @FXML
    private TextField tingkatAktivitas;
    @FXML
    private Button change;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void profileButton(ActionEvent event) {
    }

    @FXML
    private void breakfastButton(ActionEvent event) {
    }

    @FXML
    private void lunchButton(ActionEvent event) {
    }

    @FXML
    private void dinnerButton(ActionEvent event) {
    }

    @FXML
    private void logoutButton(ActionEvent event) {
    }

    @FXML
    private void changeButton(ActionEvent event) {
    }
    
}
