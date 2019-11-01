/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fxml;

import com.mycompany.eatkuyprojects.QueryDb;
import com.mycompany.eatkuyprojects.Session;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
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
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class MainController implements Initializable {
    
        
    private QueryDb db;
    private Session s;
    
    @FXML
    private BorderPane borderPane;

    @FXML
    private Label kalori;

    @FXML
    private Button lunch;

    @FXML
    private Button logout;

    @FXML
    private Button profile;

    @FXML
    private Label navigasi;

    @FXML
    private Button breakfast;

    @FXML
    private Button dinner;

    @FXML
    private Label username;

    /**
     * Initializes the controller class.
     */
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //TODO
        this.navigasi.setText("Breakfast");
    }
//    username.setText(s.getUsername());
    private void loadUI(String title){
        Parent root =  null;
        try {
            root = FXMLLoader.load(getClass().getResource(title+".fxml"));
        } catch (IOException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }   
        borderPane.setCenter(root);
    }
    
    
   @FXML
    void profileButton(ActionEvent event) {
        String navProfile="Profile";
        this.navigasi.setText(navProfile);
    }

    @FXML
    void breakfastButton(ActionEvent event) {
        String navBreakfast="Breakfast";
        this.navigasi.setText(navBreakfast);
    }

    @FXML
    void lunchButton(ActionEvent event) {
        String navLunch="Lunch";
        this.navigasi.setText(navLunch);
    }

    @FXML
    void dinnerButton(ActionEvent event) {
        String navDinner="Dinner";
        this.navigasi.setText(navDinner);
    }

    @FXML
    void logoutButton(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Login.fxml"));
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.setScene(new Scene(root));
    }
    
}
