/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fxml;

import com.mycompany.eatkuyprojects.DBUtil;
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
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class LoginController implements Initializable {

    @FXML
    private Button loginButton;
    @FXML
    private Hyperlink signupHyperlink;
    @FXML
    private PasswordField passwordLogin;
    @FXML
    private TextField usernameLogin;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO;
    } 

    @FXML
    void loginActivity(ActionEvent event) throws SQLException, IOException, ClassNotFoundException {
        try{
            String username = usernameLogin.getText();
            String pass = passwordLogin.getText();
            DBUtil db = new DBUtil();
            String query = "SELECT * from Akun WHERE Username = '"+username+"' AND Password =  '"+pass+"'";
            ResultSet rs = db.dbExecuteQuery(query);
            if(rs.next()){
                int level = rs.getInt("Status");
                if(level == 1){
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Admin.fxml"));
                    Parent Main = (Parent) loader.load();
                    AdminController adm = (AdminController)loader.getController();
                    adm.onLoad();
                    Scene scene = new Scene(Main);
                    Stage Primarystage = (Stage) ((Node)event.getSource()).getScene().getWindow();
                    Primarystage.setResizable(false);
                    Primarystage.setScene(scene);
                    Primarystage.show();
                    rs.close();
                }else{
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Home.fxml"));
                    Parent Main = (Parent) loader.load();
                    HomeController home = (HomeController)loader.getController();
                    home.GetUserLogin(username);
                    home.GetKaloriUser();
                    Scene scene = new Scene(Main);
                    Stage Primarystage = (Stage) ((Node)event.getSource()).getScene().getWindow();
                    Primarystage.setResizable(false);
                    Primarystage.setScene(scene);
                    Primarystage.show();
                    rs.close();
                }
            }else{
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("LOGIN FAILED");
                alert.setHeaderText("LOGIN FAILED !");
                alert.setContentText("Username / Password is WRONG !");
                alert.showAndWait();
            }
        }catch(SQLException ex){
            //JOptionPane.showMessageDialog(null, "FAILED log");
            ex.printStackTrace();
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
       
    
}
