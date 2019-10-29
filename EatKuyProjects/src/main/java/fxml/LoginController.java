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
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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
 * @author ASUS
 */
public class LoginController implements Initializable {

    Session s;
    private QueryDb db;
    @FXML
    private Button loginButton;
    @FXML
    private Hyperlink signupHyperlink;
    @FXML
    private PasswordField passwordLogin;
    @FXML
    private TextField usernameLogin;

    @FXML
    void loginActivity(ActionEvent event) throws SQLException, IOException {
        String username = usernameLogin.getText();
        String pass = passwordLogin.getText();
        ResultSet rs = db.logquery(username, pass);
        if(rs.next()){
            int level = rs.getInt(10);
            if(level == 1){
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/fxml/Admin.fxml"));
                Parent Main = loader.load();
                Scene scene = new Scene(Main);
                Stage Primarystage = (Stage) ((Node)event.getSource()).getScene().getWindow();
                Primarystage.setResizable(false);
                Primarystage.setScene(scene);
                Primarystage.show();
            }else{
                s = new Session(username, pass);
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/fxml/Main.fxml"));
                Parent Main = loader.load();
                Scene scene = new Scene(Main);
                Stage Primarystage = (Stage) ((Node)event.getSource()).getScene().getWindow();
                Primarystage.setResizable(false);
                Primarystage.setScene(scene);
                Primarystage.show();
            }
        }else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("LOGIN FAILED");
            alert.setHeaderText("LOGIN FAILED !");
            alert.setContentText("Username / Password is WRONG !");
            alert.showAndWait();
        }
    }
    
        

    @FXML
    void signupFrame(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml/Signup.fxml"));
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
        db = new QueryDb();
        db.connect();
    }    
    
}
