/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fxml;

import com.mycompany.eatkuyprojects.DBUtil;
import com.mycompany.eatkuyprojects.Makanan;
import com.mycompany.eatkuyprojects.MakananDAO;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Kukuh Nugroho
 */
public class MakananManajemenController implements Initializable {

    @FXML
    private TableView<Makanan> makananTableView;
    @FXML
    private TableColumn<Makanan, Integer> col_idm;
    @FXML
    private TableColumn<Makanan, String> col_namam;
    @FXML
    private TableColumn<Makanan, String> col_kalorim;
    @FXML
    private Button tambahMakananButton;
    @FXML
    private Button hapusMakanButton;
    @FXML
    private TextField namaMakananBaru;
    @FXML
    private TextField kaloriMakananBaru;
    @FXML
    private TextField namaMakananLama;
    @FXML
    private TextField kaloriMakananLama;
    @FXML
    private Button updateMakanButton1;
    String namaTmp;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        loadDB();
    }

    public void loadDB(){
        col_idm.setCellValueFactory(new PropertyValueFactory("id_makanan"));
        col_namam.setCellValueFactory(new PropertyValueFactory("Nama_makanan"));
        col_kalorim.setCellValueFactory(new PropertyValueFactory("Kalori"));
        
        ObservableList<Makanan> data;
        try {
            data = MakananDAO.searchMakanans();
            makananTableView.setItems(data);
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(MakananManajemenController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void tambahMakanAction(ActionEvent event) {
        DBUtil db = new DBUtil();
        String namaM = namaMakananBaru.getText();
        String KalM =  kaloriMakananBaru.getText();
        try{
            String queryCek = "SELECT * FROM Makanan WHERE Nama_makanan = '"+namaM+"'";
            ResultSet rsCek = db.dbExecuteQuery(queryCek);
            String namaMakananDB = rsCek.getString("Nama_makanan");
            if(rsCek.next()){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("ADD FOOD FAILED");
                alert.setHeaderText("GAGAL MENAMBAH MAKANAN !");
                alert.setContentText("MAKANAN SUDAH TERDAFTAR !");
                alert.showAndWait();
            }else{
                String query = "INSERT INTO Makanan(Nama_makanan, Kalori) VALUES('"+namaM+"','"+KalM+"')";
                ResultSet rs = db.dbExecuteQuery(query);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("ADD FOOD SUCCESS");
                alert.setHeaderText("TAMBAH MAKANAN !");
                alert.setContentText("BERHASIL MENAMBAH MAKANAN !");
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
    private void hapusMakananAction(ActionEvent event) {
        DBUtil db = new DBUtil();
        try{
            String query = "DELETE FROM Makanan WHERE Nama_makanan = '"+namaMakananLama.getText()+"'";
            db.dbExecuteUpdate(query);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("DELETE SUCCESS");
            alert.setHeaderText("DELETE MAKANAN !");
            alert.setContentText("BERHASIL MENGHAPUS MAKANAN !");
            alert.showAndWait();
            loadDB();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }

    @FXML
    private void updateMakananAction(ActionEvent event) {
        DBUtil db = new DBUtil();
        try{
            if(makananTableView.getSelectionModel().getSelectedItem().getNama_makanan() != namaMakananLama.getText() || 
                    makananTableView.getSelectionModel().getSelectedItem().getKalori() != kaloriMakananLama.getText()){
                String query = "UPDATE Makanan SET Nama_makanan = '"
                        +namaMakananLama.getText()+"', Kalori = '"+kaloriMakananLama.getText()
                        +"'WHERE Nama_makanan='"+namaTmp+"'";
                db.dbExecuteUpdate(query);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("UPDATE SUCCESS");
                alert.setHeaderText("UPDATE MAKANAN !");
                alert.setContentText("BERHASIL MENGUPDATE MAKANAN !");
                alert.showAndWait();   
            }
            else{
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("UPDATE FAILED");
                alert.setHeaderText("UPDATE MAKANAN !");
                alert.setContentText("GAGAL MENGUPDATE MAKANAN !");
                alert.showAndWait();  
            }
            loadDB();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }

    @FXML
    private void getMakananValue(MouseEvent event) {
        try{
            namaMakananLama.setText(makananTableView.getSelectionModel().getSelectedItem().getNama_makanan());
            kaloriMakananLama.setText(makananTableView.getSelectionModel().getSelectedItem().getKalori());
            namaTmp = makananTableView.getSelectionModel().getSelectedItem().getNama_makanan();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
}
