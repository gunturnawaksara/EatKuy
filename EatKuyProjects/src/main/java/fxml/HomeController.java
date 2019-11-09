/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fxml;

import com.mycompany.eatkuyprojects.DBUtil;
import com.mycompany.eatkuyprojects.MakalDAO;
import com.mycompany.eatkuyprojects.MakananKalori;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
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
    private TextField jenisKelamin;
    @FXML
    private TextField usia;
    @FXML
    private TextField beratBadan;
    @FXML
    private TextField tinggiBadan;
    @FXML
    private TextField tingkatAktivitas;
    @FXML
    private Label username;
    @FXML
    private Label kalori;
    @FXML
    private TextField search;
    @FXML
    private ImageView searchButton;
    @FXML
    private ImageView tambah;
    @FXML
    private TableView<MakananKalori> tabel_makananKal;
    @FXML
    private TableColumn<MakananKalori, Integer> col_idmakal;
    @FXML
    private TableColumn<MakananKalori, String> col_namaMakanan;
    @FXML
    private TableColumn<MakananKalori, Integer> col_kalori;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //TODO
        db = new DBUtil();
        col_idmakal.setCellValueFactory(new PropertyValueFactory("id_makanan"));
        col_namaMakanan.setCellValueFactory(new PropertyValueFactory("Nama_makanan"));
        col_kalori.setCellValueFactory(new PropertyValueFactory("Kalori"));
        
        ObservableList<MakananKalori> data;
        try {
            data = MakalDAO.searchMakananKaloris();
            tabel_makananKal.setItems(data);
        } catch (SQLException | ClassNotFoundException ex) {

        }
    }    

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
    
}
