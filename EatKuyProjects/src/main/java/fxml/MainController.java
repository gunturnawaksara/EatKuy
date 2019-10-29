/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fxml;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
    }    
    
}
