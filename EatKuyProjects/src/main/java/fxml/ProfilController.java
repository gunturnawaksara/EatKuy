/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fxml;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

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
        String navProfile="Profile";
        this.navigasi.setText(navProfile);
    }

    @FXML
    private void breakfastButton(ActionEvent event) {
        String navBreakfast="Breakfast";
        this.navigasi.setText(navBreakfast);
    }

    @FXML
    private void lunchButton(ActionEvent event) {
        String navLunch="Lunch";
        this.navigasi.setText(navLunch);
    }

    @FXML
    private void dinnerButton(ActionEvent event) {
        String navDinner="Dinner";
        this.navigasi.setText(navDinner);
    }

    @FXML
    private void logoutButton(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Login.fxml"));
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.setScene(new Scene(root));
    }

    @FXML
    private void changeButton(ActionEvent event) {
    }
    
}
