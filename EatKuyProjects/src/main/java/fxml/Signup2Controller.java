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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class Signup2Controller implements Initializable {
    
    private QueryDb db;
    private Session s;
    @FXML
    private Label username;
    @FXML
    private TextField jeniskelamin;
    @FXML
    private TextField usia;
    @FXML
    private TextField bb;
    @FXML
    private TextField tb;
    @FXML
    private Button doneButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        db = new QueryDb();
        db.connect();
    }    

    @FXML
    private void doneActivity(ActionEvent event) throws IOException {
        String Jeniskelamin = jeniskelamin.getText();
        String Usia = usia.getText();
        int Beratbadan = Integer.parseInt(bb.getText());
        int Tinggibadan = Integer.parseInt(tb.getText());
        String tamp;
        tamp=s.getUsername();
        db.UpdateAkun(Jeniskelamin, Usia, Beratbadan, Tinggibadan, tamp);
        System.out.println(s.getUsername());
        if(tamp == ""){
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/fxml/Main.fxml"));
            Parent Main = loader.load();
            Scene scene = new Scene(Main);
            Stage Primarystage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            Primarystage.setResizable(false);
            Primarystage.setScene(scene);
            Primarystage.show();
       }else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("SIGN UP FAILED");
            alert.setHeaderText("SIGN UP FAILED !");
            alert.setContentText("Email already used !");
            alert.showAndWait();
                    
        }
    }
    
    
    
}
