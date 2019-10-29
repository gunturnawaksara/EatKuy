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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Kukuh Nugroho
 */
public class SignupController implements Initializable {

    private QueryDb db;
    Session s;
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
    void signupActivity(ActionEvent event) throws SQLException, IOException {
        String username = usernameSignup.getText();
        String email = emailSignup.getText();
        String pass = passwordSignup.getText();
        ResultSet rs = db.isExist(username, email);
        if(rs.next()){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                 alert.setTitle("SIGN UP FAILED");
                 alert.setHeaderText("SIGN UP FAILED !");
                 alert.setContentText("Username / Email already used !");
                 alert.showAndWait();
        }else{
            s = new Session(usernameSignup, passwordSignup);
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/fxml/Main.fxml"));
            Parent Main = loader.load();
            Scene scene = new Scene(Main);
            Stage Primarystage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            Primarystage.setResizable(false);
            Primarystage.setScene(scene);
            Primarystage.show();
        }
    }

    @FXML
    void loginFrame(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml/Login.fxml"));
        Parent Main = loader.load();
        Scene scene = new Scene(Main);
        Stage Primarystage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Primarystage.setResizable(false);
        Primarystage.setScene(scene);
        Primarystage.show();
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
}
