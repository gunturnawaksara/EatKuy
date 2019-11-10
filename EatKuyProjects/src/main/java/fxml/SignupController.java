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
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Kukuh Nugroho
 */
public class SignupController implements Initializable {

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
        try{
            DBUtil db = new DBUtil();
            String username = usernameSignup.getText();
            String email = emailSignup.getText();
            String pass = passwordSignup.getText();
            String query1 = "SELECT * from Akun WHERE Email='"+email+"'";
            String query2 = "SELECT * from Akun WHERE Username='"+username+"'";
            ResultSet rs = db.dbExecuteQuery(query1);
            ResultSet rs2 = db.dbExecuteQuery(query2);
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
                    int Usia = 0;
                    String JenisKelamin = "-";
                    int BeratBadan = 0;
                    int TinggiBadan = 0;
                    int TingkatAktivitas = 0;
                    int Status = 2;
                    String query3 = "INSERT INTO Akun(Email,Username,Password,JenisKelamin,Usia,BeratBadan,TinggiBadan,TingkatAktivitas,Status) VALUES ('"+email+"','"+username+"','"+pass+"','"+JenisKelamin+"','"+Usia+"','"+BeratBadan+"','"+TinggiBadan+"','"+TingkatAktivitas+"','"+Status+"')";
                    ResultSet rs3 = db.dbExecuteQuery(query3);
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Main.fxml"));
                    Parent Main = (Parent) loader.load();
                    HomeController home = (HomeController)loader.getController();
                    home.GetUserLogin(username, "Member");
                    Scene scene = new Scene(Main);
                    Stage Primarystage = (Stage) ((Node)event.getSource()).getScene().getWindow();
                    Primarystage.setResizable(false);
                    Primarystage.setScene(scene);
                    Primarystage.show();
                    rs3.close();
                }
            }
        } catch(Exception e){
            JOptionPane.showMessageDialog(null, "FAILED log");
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
