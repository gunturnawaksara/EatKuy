/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fxml;

import com.mycompany.eatkuyprojects.Akun;
import com.mycompany.eatkuyprojects.AkunDAO;
import com.mycompany.eatkuyprojects.QueryDb;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class AdminController implements Initializable {
    
    private QueryDb db;
    @FXML
    private Button logout;
    @FXML
    private TableView<Akun> userTableView;
    @FXML
    private TableColumn<Akun, Integer> col_id;
    @FXML
    private TableColumn<Akun, String> col_email;
    @FXML
    private TableColumn<Akun, String> col_uname;
    @FXML
    private TableColumn<Akun, String> col_pass;
    @FXML
    private TableColumn<Akun, Integer> col_usia;
    @FXML
    private TableColumn<Akun, String> col_jkelamin;
    @FXML
    private TableColumn<Akun, Integer> col_bb;
    @FXML
    private TableColumn<Akun, Integer> col_tb;
    @FXML
    private TableColumn<Akun, Integer> col_ta;
    @FXML
    private TableColumn<Akun, Integer> col_status; 
    @FXML
    private Button lihatAkun;
    @FXML
    private TableColumn<Akun, Button> col_hapusBtn;


    @FXML
    void logoutButton(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Login.fxml"));
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.setScene(new Scene(root));
    }
    
     @FXML
    void lihatAkunButton(ActionEvent event) {
       
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        col_id.setCellValueFactory(new PropertyValueFactory("ID_User"));
        col_email.setCellValueFactory(new PropertyValueFactory("Email"));
        col_uname.setCellValueFactory(new PropertyValueFactory("Username"));
        col_pass.setCellValueFactory(new PropertyValueFactory("Password"));
        col_usia.setCellValueFactory(new PropertyValueFactory("Usia"));
        col_jkelamin.setCellValueFactory(new PropertyValueFactory("JenisKelamin"));
        col_bb.setCellValueFactory(new PropertyValueFactory("BeratBadan"));
        col_tb.setCellValueFactory(new PropertyValueFactory("TinggiBadan"));
        col_ta.setCellValueFactory(new PropertyValueFactory("TingkatAktivitas"));
        col_status.setCellValueFactory(new PropertyValueFactory("Status"));
        //ambil data dari db dan masukkan ke TableView
        db = new QueryDb();
        ObservableList<Akun> data;
        try {
            db.connect();
            ResultSet rs = null;
            data = AkunDAO.searchAkuns();
            userTableView.setItems(data);
<<<<<<< Updated upstream
        } catch (SQLException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
=======
        } catch (SQLException | ClassNotFoundException ex) {
            
>>>>>>> Stashed changes
        }
    }    
    
}
