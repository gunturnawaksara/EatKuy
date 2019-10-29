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
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class MainController implements Initializable {
        
    Session s;
    private QueryDb db;
    @FXML
    private BorderPane borderPane;
    @FXML
    private Label navigasi;
    @FXML
    private Label username;
    @FXML
    private Button breakfast;
    @FXML
    private Button lunch;
    @FXML
    private Button dinner;
    @FXML
    private MenuItem profile;
    @FXML
    private MenuItem about;
    @FXML
    private MenuItem logout;
    @FXML
    private Label kalori;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ResultSet rs = db.isEmpty(s.getUsername());
        try {
            if(rs.next()){
                int bb = rs.getInt(7);
                if(bb == 0){
                    loadUI("Signup2");
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void loadUI(String title){
        Parent root =  null;
        try {
            root = FXMLLoader.load(getClass().getResource(title+".fxml"));
        } catch (IOException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }   
        borderPane.setCenter(root);
    }
    
}
