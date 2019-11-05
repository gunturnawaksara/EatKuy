/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fxml;

import com.mycompany.eatkuyprojects.QueryDb;
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
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Kukuh Nugroho
 */
public class ProfilController implements Initializable {
    
    private QueryDb db;
    public String sessionUsername;
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
    
    MainController mc;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void changeButton(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Main.fxml"));
        Parent Main = (Parent) loader.load();
        MainController mainCon = (MainController)loader.getController();
        mainCon.UpdateAkunUser(jenisKelamin.getText(), usia.getText(), beratBadan.getText(), tinggiBadan.getText());
    }
    
    public void GetUser(String uName) {
        // TODO Auto-generated method stub
        sessionUsername = uName;
    }
    
}
