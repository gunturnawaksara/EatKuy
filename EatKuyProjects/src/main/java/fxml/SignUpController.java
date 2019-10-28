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
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class SignUpController implements Initializable {

     @FXML
    private TextField emailSignup;

    @FXML
    private Button signupButton;

    @FXML
    private PasswordField passwordSignup;

    @FXML
    private TextField usernameSignup;

    @FXML
    private Hyperlink loginHyperlink;

    @FXML
    void signupActivity(ActionEvent event) {

    }

    @FXML
    void loginFrame(ActionEvent event) {

    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}