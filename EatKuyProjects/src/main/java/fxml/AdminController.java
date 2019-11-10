/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fxml;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class AdminController implements Initializable {
    
    @FXML
    private BorderPane borderpane;
    @FXML
    private Button lihatAkun;
    @FXML
    private Button lihatMakanan;
    @FXML
    private Button logout;


    @FXML
    void logoutButton(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Login.fxml"));
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.setScene(new Scene(root));
    }
    
    @FXML
    void lihatAkunButton(ActionEvent event) {
       loadUI("UserManajemen");
    }
    
    
    
    private void loadUI(String title){
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource(title+".fxml"));
        } catch (IOException ex) {
            Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
        }   
        borderpane.setCenter(root);
    }
    
    public void onLoad(){
        loadUI("UserManajemen");
    }
    
    public void refreshMakanan(){
        loadUI("MakananManajemen");
    }

    @FXML
    public void lihatMakananButton(ActionEvent event) {
        loadUI("MakananManajemen");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }


}
