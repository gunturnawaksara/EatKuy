/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fxml;

import com.mycompany.eatkuyprojects.QueryDb;
import com.sun.xml.internal.bind.v2.runtime.unmarshaller.Loader;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class MainController implements Initializable {
    
        
    private QueryDb db;
    public String sessionUsername;
    @FXML
    private BorderPane borderPane;

    @FXML
    private Label kalori;

    @FXML
    private Button lunch;

    @FXML
    private Button logout;

    @FXML
    private Button profile;

    @FXML
    private Label navigasi;

    @FXML
    private Button breakfast;

    @FXML
    private Button dinner;

    @FXML
    private Label username;
    

    /**
     * Initializes the controller class.
     */
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //TODO
    }

    private void loadUI(String title){
        Parent root =  null;
        try {
            if(title.equals("Profil")){
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Profil.fxml"));
                root = (Parent) loader.load();
                ProfilController profCon = (ProfilController)loader.getController();
                profCon.GetUser(sessionUsername);
            }else{
                root = FXMLLoader.load(getClass().getResource(title+".fxml"));
            }
        } catch (IOException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }   
        borderPane.setCenter(root);
    }
    
    
   @FXML
    void profileButton(ActionEvent event) {
        String navProfile="Profile";
        this.navigasi.setText(navProfile);
        loadUI("Profil");
    }

    @FXML
    void breakfastButton(ActionEvent event) {
        String navBreakfast="Breakfast";
        this.navigasi.setText(navBreakfast);
        loadUI("Breakfast");
    }

    @FXML
    void lunchButton(ActionEvent event) {
        String navLunch="Lunch";
        this.navigasi.setText(navLunch);
        loadUI("Lunch");
    }

    @FXML
    void dinnerButton(ActionEvent event) {
        String navDinner="Dinner";
        this.navigasi.setText(navDinner);
        loadUI("Dinner");
    }

    @FXML
    void logoutButton(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Login.fxml"));
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.setScene(new Scene(root));
    }

    private void doneActivity(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml/Main.fxml"));
        Parent Main = loader.load();
        Scene scene = new Scene(Main);
        Stage Primarystage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Primarystage.setResizable(false);
        Primarystage.setScene(scene);
        Primarystage.show();
    }
   
    public void GetUserLogin(String uName) {
        // TODO Auto-generated method stub
        sessionUsername = uName;
        this.username.setText(uName);
        this.navigasi.setText("Breakfast");
        loadUI("Breakfast");
    }
    
    public void GetUserSignUp(String uName) {
        // TODO Auto-generated method stub
        sessionUsername = uName;
        this.username.setText(uName);
        String navProfile="Profile";
        this.navigasi.setText(navProfile);
        loadUI("Profil");
    }
    
    public void UpdateAkunUser(String JenisKelamin, String Usia, String BeratBadan, String TinggiBadan){
        this.username.setText(sessionUsername);
        String navProfile="Profile";
        this.navigasi.setText(navProfile);
        loadUI("Profil");
        String usiaUser = Usia;
        String beratBadanUser = BeratBadan;
        String tinggiBadanUser = TinggiBadan;
        String jenisKelaminUser = JenisKelamin;
        db.UpdateAkun(jenisKelaminUser, usiaUser, Integer.parseInt(beratBadanUser), Integer.parseInt(tinggiBadanUser), sessionUsername);
    }
}
