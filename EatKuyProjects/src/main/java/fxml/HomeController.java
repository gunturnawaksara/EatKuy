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
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Kukuh Nugroho
 */
public class HomeController implements Initializable {
    
    private DBUtil db;
    private String sessionUsername;
    private String sessionStatus;
    @FXML
    private TextField usia;

    @FXML
    private ImageView tambahL;

    @FXML
    private ImageView searchButtonL;

    @FXML
    private TextField beratBadan;

    @FXML
    private TextField search;

    @FXML
    private ImageView searchButtonB;

    @FXML
    private Label kaloriD;
    

    @FXML
    private Label kaloriB;

    @FXML
    private TextField jenisKelamin;

    @FXML
    private Label kaloriL;

    @FXML
    private TextField tingkatAktivitas;

    @FXML
    private TextField tinggiBadan;

    @FXML
    private ImageView tambahD;

    @FXML
    private Label username;

    @FXML
    private ImageView tambahB;

    /**
     * Initializes the controller class.
     */  
    
    
    
    
    @FXML
    private void changeButton(ActionEvent event) {
        String usiaUser = usia.getText();
        String beratBadanUser = beratBadan.getText();
        String tinggiBadanUser = tinggiBadan.getText();
        String jenisKelaminUser = jenisKelamin.getText();
        try{
            String query = "UPDATE Akun SET JenisKelamin = '"+jenisKelaminUser+"', Usia= '"+usiaUser+"', BeratBadan= '"+beratBadanUser+"', TinggiBadan= '"+tinggiBadanUser+"' WHERE Username='"+sessionUsername+"'";
            db.dbExecuteUpdate(query);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "FAILED log");
        }
    }

    @FXML
    private void logoutButton(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Login.fxml"));
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.setScene(new Scene(root));
    }
    
    public void GetUserLogin(String uName, String uStatus) {
        // TODO Auto-generated method stub
        this.sessionUsername = uName;
        this.sessionStatus = uStatus;
    }
    
 
    
    public void GetKaloriUser() throws SQLException, ClassNotFoundException{
        String query="SELECT Usia, JenisKelamin, BeratBadan, TinggiBadan from Akun WHERE Username='"+sessionUsername+"'";
        ResultSet rs=db.dbExecuteQuery(query);
        rs.next();        
        int usia= rs.getInt(1);
        String jk = rs.getString(2);
        int bb= rs.getInt(3);
        int tb= rs.getInt(4);
        int tamp1;
        int tamp2;
        String kal;
        if(jk.equals("Laki-laki")){
           tamp1 =  66 + (14 * bb) + (5 * tb) - (7 * usia);
           kal =String.valueOf(tamp1);
           this.kaloriB.setText(kal);
           this.kaloriD.setText(kal);
           this.kaloriL.setText(kal);
        } else if(jk.equals("Perempuan")){
            tamp2 = 655 + (10 * bb) + (2 * tb) - (5 * usia);
            kal =String.valueOf(tamp2);
            this.kaloriB.setText(kal);
            this.kaloriD.setText(kal);
            this.kaloriL.setText(kal);
        }
        rs.close();
    }
      
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        db = new DBUtil();
    } 
}
