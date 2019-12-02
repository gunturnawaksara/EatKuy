/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fxml;

import com.mycompany.eatkuyprojects.DBUtil;
import com.mycompany.eatkuyprojects.DBUtil;
import java.net.URL;
import java.util.ResourceBundle;
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
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Kukuh Nugroho
 */
public class SignUp2Controller implements Initializable {
    
    String sessionUsername;
    @FXML
    private TextField usia;
    @FXML
    private TextField beratBadan;
    @FXML
    private TextField tinggiBadan;
    @FXML
    private ComboBox<String> comboJK;
    @FXML
    private ComboBox<String> comboAktivitas;
    
    ObservableList<String> JK = FXCollections.observableArrayList("Laki-laki","Perempuan");
    ObservableList<String> aktivitas = FXCollections.observableArrayList("Sangat jarang olahraga","Jarang olahraga","Normal olahraga","Sering olahraga","Sangat sering olahraga");

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        comboJK.setItems(JK);
        comboAktivitas.setItems(aktivitas);
    }    

    @FXML
    private void doneButton(ActionEvent event) {
        DBUtil db = new DBUtil();
        String usiaUser = usia.getText();
        String beratBadanUser = beratBadan.getText();
        String tinggiBadanUser = tinggiBadan.getText();
        String jenisKelaminUser = comboJK.getValue();
        String tingkatAktivitasUser = comboAktivitas.getValue();
        try{
            String query = "UPDATE Akun SET JenisKelamin = '"+jenisKelaminUser+"', Usia= '"+usiaUser+"', BeratBadan= '"+beratBadanUser+"', TinggiBadan= '"+tinggiBadanUser+"', TingkatAktivitas= '"+tingkatAktivitasUser+"' WHERE Username='"+sessionUsername+"'";
            db.dbExecuteUpdate(query);
            JOptionPane.showMessageDialog(null, "Berhasil Signup");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Home.fxml"));
            Parent Main = (Parent) loader.load();
            HomeController home = (HomeController)loader.getController();
            home.GetUserLogin(this.sessionUsername);
            home.GetKaloriUser();
            home.hitungPogressKalori();
            Scene scene = new Scene(Main);
            Stage Primarystage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            Primarystage.setResizable(false);
            Primarystage.setScene(scene);
            Primarystage.show();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "FAILED log");
        }
    }
    
    public void GetUserLogin(String uName) {
        // TODO Auto-generated method stub
        this.sessionUsername = uName;
    }
    
}
