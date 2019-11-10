/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fxml;

import com.mycompany.eatkuyprojects.Akun;
import com.mycompany.eatkuyprojects.AkunDAO;
import com.mycompany.eatkuyprojects.DBUtil;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Kukuh Nugroho
 */
public class UserManajemenController implements Initializable {

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
    private TableColumn<Akun, String> col_ta;
    @FXML
    private TableColumn<Akun, Integer> col_status; 
    @FXML
    private TextField emailUserDB;
    @FXML
    private TextField passwordUserDB;
    @FXML
    private TextField usernameUserDB;
    @FXML
    private TextField usiaUserDB;
    @FXML
    private TextField kelaminUserDB;
    @FXML
    private TextField bbUserDB;
    @FXML
    private TextField tbUserDB;
    @FXML
    private TextField aktivitasUSerDB;
    @FXML
    private Button tambahAkun;
    @FXML
    private Button updateAkun;
    @FXML
    private Button hapusAkun;
    @FXML
    private Button clear;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        loadDB();
    }
    
    public void loadDB(){
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

        ObservableList<Akun> data;
        try {
            data = AkunDAO.searchAkuns();
            userTableView.setItems(data);
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void clearField(){
        emailUserDB.setText(null);
        usernameUserDB.setText(null);
        passwordUserDB.setText(null);
        usiaUserDB.setText(null);
        kelaminUserDB.setText(null);
        bbUserDB.setText(null);
        tbUserDB.setText(null);
        aktivitasUSerDB.setText(null);
    }

    @FXML
    private void getUserValue(MouseEvent event) {
        try{
            emailUserDB.setText(userTableView.getSelectionModel().getSelectedItem().getEmail());
            usernameUserDB.setText(userTableView.getSelectionModel().getSelectedItem().getUsername());
            passwordUserDB.setText(userTableView.getSelectionModel().getSelectedItem().getPassword());
            usiaUserDB.setText(String.valueOf(userTableView.getSelectionModel().getSelectedItem().getUsia()));
            kelaminUserDB.setText(userTableView.getSelectionModel().getSelectedItem().getJenisKelamin());
            bbUserDB.setText(String.valueOf(userTableView.getSelectionModel().getSelectedItem().getBeratBadan()));
            tbUserDB.setText(String.valueOf(userTableView.getSelectionModel().getSelectedItem().getTinggiBadan()));
            aktivitasUSerDB.setText(userTableView.getSelectionModel().getSelectedItem().getTingkatAktivitas());
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }

    @FXML
    private void tambahAkunButton(ActionEvent event) {
        DBUtil db = new DBUtil();
        try{
            String queryCek = "SELECT * FROM Akun WHERE Email = '"+emailUserDB.getText()+"'";
            ResultSet rsCek = db.dbExecuteQuery(queryCek);
            if(rsCek.next()){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("TAMBAH AKUN GAGAL");
                alert.setHeaderText("GAGAL MENAMBAH AKUN !");
                alert.setContentText("AKUN SUDAH TERDAFTAR !");
                alert.showAndWait();
            }else{
                String query = "INSERT INTO Akun(Email,Username,Password,JenisKelamin,Usia,BeratBadan,TinggiBadan,TingkatAktivitas,Status) VALUES ('"+emailUserDB+"','"+usernameUserDB+"','"+passwordUserDB+"','"+kelaminUserDB+"','"+usiaUserDB+"','"+bbUserDB+"','"+tbUserDB+"','"+aktivitasUSerDB+"','2')";;
                ResultSet rs = db.dbExecuteQuery(query);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("TAMBAH AKUN BERHASIL");
                alert.setHeaderText("TAMBAH AKUN !");
                alert.setContentText("BERHASIL MENAMBAH AKUN !");
                alert.showAndWait();
                loadDB();
                rs.close();
            }
            rsCek.close();
        }catch(Exception e){
            System.out.print(e);
        }
    }

    @FXML
    private void updateAkunButton(ActionEvent event) {
        DBUtil db = new DBUtil();
        try{
            if(userTableView.getSelectionModel().getSelectedItem().getEmail() != emailUserDB.getText() && 
                    userTableView.getSelectionModel().getSelectedItem().getUsername() != usernameUserDB.getText()){
                String query = "UPDATE Akun SET JenisKelamin = '"+kelaminUserDB+"', Usia= '"+usiaUserDB+"', BeratBadan= '"
                        +bbUserDB+"', TinggiBadan= '"+tbUserDB+"', TingkatAktivitas= '"
                        +aktivitasUSerDB+"' WHERE Username='"+usernameUserDB+"'";
                db.dbExecuteUpdate(query);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("UPDATE SUCCESS");
                alert.setHeaderText("UPDATE AKUN !");
                alert.setContentText("BERHASIL MENGUPDATE AKUN !");
                alert.showAndWait();   
            }else{
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("UPDATE FAILED");
                alert.setHeaderText("UPDATE AKUN !");
                alert.setContentText("Email/Username Sudah Terdaftar !");
                alert.showAndWait();  
            }
            loadDB();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }

    @FXML
    private void hapusAkunButton(ActionEvent event) {
        DBUtil db = new DBUtil();
        try{
            String query = "DELETE FROM Akun WHERE Email = '"+emailUserDB.getText()+"'";
            db.dbExecuteUpdate(query);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("DELETE SUCCESS");
            alert.setHeaderText("DELETE AKUN !");
            alert.setContentText("BERHASIL MENGHAPUS AKUN !");
            alert.showAndWait();
            clearField();
            loadDB();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }

    @FXML
    private void clearBtn(ActionEvent event) {
        clearField();
    }
    
}
