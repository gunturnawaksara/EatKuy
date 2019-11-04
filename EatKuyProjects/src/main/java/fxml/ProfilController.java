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
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Kukuh Nugroho
 */
public class ProfilController implements Initializable {
    
    String sessionUsername;
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
    private void changeButton(ActionEvent event) {
        
    }
    
    public void GetUser(String username) {
        // TODO Auto-generated method stub
        sessionUsername = username;
        System.out.println(username);
    }
    
}
