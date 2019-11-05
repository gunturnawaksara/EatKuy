/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fxml;

import com.mycompany.eatkuyprojects.QueryDb;
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
    public void signupActivity(ActionEvent event) throws SQLException, IOException {
        String username = usernameSignup.getText();
        String email = emailSignup.getText();
        String pass = passwordSignup.getText();
        ResultSet rs = db.isUsernameExist(username);
        ResultSet rs2 = db.isEmailExist(email);
        if(rs.next()){
            String uName = rs.getString(3);
            if(uName.equalsIgnoreCase(username)){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("SIGN UP FAILED");
                alert.setHeaderText("SIGN UP FAILED !");
                alert.setContentText("Username already used !");
                alert.showAndWait();
                rs.close();
            }
        }else{
            if(rs2.next()){
                String uEmail = rs2.getString(2);
                if(uEmail.equals(email)){
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("SIGN UP FAILED");
                    alert.setHeaderText("SIGN UP FAILED !");
                    alert.setContentText("Email already used !");
                    alert.showAndWait();
                    rs2.close();
                }
            }else{                        
                db.InsertAkun(email, username, pass);
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Main.fxml"));
                Parent Main = (Parent) loader.load();
                MainController mainCon = (MainController)loader.getController();
                mainCon.GetUserSignUp(username);
                Scene scene = new Scene(Main);
                Stage Primarystage = (Stage) ((Node)event.getSource()).getScene().getWindow();
                Primarystage.setResizable(false);
                Primarystage.setScene(scene);
                Primarystage.show();
                rs.close();
            }
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
        db = new QueryDb();
        db.connect();
    }
    
}
