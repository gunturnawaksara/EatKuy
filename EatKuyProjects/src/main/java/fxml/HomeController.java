/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fxml;

import com.mycompany.eatkuyprojects.DBUtil;
import com.mycompany.eatkuyprojects.Makanan;
import com.mycompany.eatkuyprojects.MakananDAO;
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
    @FXML
    private TextField usia;
    @FXML
    private TextField beratBadan;
    @FXML
    private ImageView searchButtonB;
    @FXML
    private Label kaloriD;
    @FXML
    private Label kaloriB;
    @FXML
    private Label kaloriL;
    @FXML
    private TextField tinggiBadan;
    @FXML
    private Label username;
    @FXML
    private ImageView tambahB;
    @FXML
    private ComboBox<String> comboJK;
    @FXML
    private ComboBox<String> comboAktivitas;
    
    ObservableList<String> JK = FXCollections.observableArrayList("Laki-laki","Perempuan");
    ObservableList<String> aktivitas = FXCollections.observableArrayList("Sangat jarang olahraga","Jarang olahraga","Normal olahraga","Sering olahraga","Sangat sering olahraga");
    @FXML
    private TableColumn<Makanan, Integer> col_idBreakfast;
    @FXML
    private TableColumn<Makanan, String> col_namaBreakfast;
    @FXML
    private TableColumn<Makanan, Integer> col_KaloriBreakfast;
    @FXML
    private TableView<Makanan> makananBreakfast;
    @FXML
    private TextField searchBreakfast;
    @FXML
    private TableView<Makanan> makananLunch;
    @FXML
    private TableColumn<Makanan, Integer> col_idLunch;
    @FXML
    private TableColumn<Makanan, String> col_namaLunch;
    @FXML
    private TableColumn<Makanan, Integer> col_KaloriLunch;
    @FXML
    private TextField searchLunch;
    @FXML
    private ImageView searchButtonB1;
    @FXML
    private TableView<Makanan> makananDinner;
    @FXML
    private TableColumn<Makanan, Integer> col_idDinner;
    @FXML
    private TableColumn<Makanan, String> col_namaDinner;
    @FXML
    private TableColumn<Makanan, Integer> col_KaloriDinner;
    @FXML
    private TextField searchLunch1;
    @FXML
    private ImageView searchButtonB11;
    @FXML
    private ImageView tambahL;
    @FXML
    private ImageView tambahD;

    /**
     * Initializes the controller class.
     */  
    
    
    
    @FXML
    private void changeButton(ActionEvent event) {
        String usiaUser = usia.getText();
        String beratBadanUser = beratBadan.getText();
        String tinggiBadanUser = tinggiBadan.getText();
        String jenisKelaminUser = comboJK.getValue();
        String tingkatAktivitasUser = comboAktivitas.getValue();
        try{
            String query = "UPDATE Akun SET JenisKelamin = '"+jenisKelaminUser+"', Usia= '"+usiaUser+"', BeratBadan= '"+beratBadanUser+"', TinggiBadan= '"+tinggiBadanUser+"', TingkatAktivitas= '"+tingkatAktivitasUser+"' WHERE Username='"+sessionUsername+"'";
            db.dbExecuteUpdate(query);
            JOptionPane.showMessageDialog(null, "Berhasil Update");
            this.GetKaloriUser();
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
    
    public void GetUserLogin(String uName) {
        // TODO Auto-generated method stub
        this.sessionUsername = uName;
    }
    
    public int hitungKaloriL() throws SQLException, ClassNotFoundException{
        String query="SELECT Usia, JenisKelamin, BeratBadan, TinggiBadan, TingkatAktivitas from Akun WHERE Username='"+sessionUsername+"'";
        ResultSet rs=db.dbExecuteQuery(query);
        rs.next();        
        int usia= rs.getInt(1);
        String jk = rs.getString(2);
        int bb= rs.getInt(3);
        int tb= rs.getInt(4);
        String ta = rs.getString(5);
        int tamp;
        
        return tamp =  66 + (14 * bb) + (5 * tb) - (7 * usia);
    }
    
    public int hitungKaloriP() throws SQLException, ClassNotFoundException{
        String query="SELECT Usia, JenisKelamin, BeratBadan, TinggiBadan, TingkatAktivitas from Akun WHERE Username='"+sessionUsername+"'";
        ResultSet rs=db.dbExecuteQuery(query);
        rs.next();        
        int usia= rs.getInt(1);
        String jk = rs.getString(2);
        int bb= rs.getInt(3);
        int tb= rs.getInt(4);
        String ta = rs.getString(5);
        int tamp;
        
        return tamp = 655 + (10 * bb) + (2 * tb) - (5 * usia);
    }
    
    public void GetKaloriUser() throws SQLException, ClassNotFoundException{
        String query="SELECT Usia, JenisKelamin, BeratBadan, TinggiBadan, TingkatAktivitas from Akun WHERE Username='"+sessionUsername+"'";
        ResultSet rs=db.dbExecuteQuery(query);
        rs.next();        
        int usia= rs.getInt(1);
        String jk = rs.getString(2);
        int bb= rs.getInt(3);
        int tb= rs.getInt(4);
        String ta = rs.getString(5);
        int tamp;
        String kal;
        if(jk.equals("Laki-laki")&& ta.equals("Sangat jarang olahraga")){
           tamp=(int) (1.2*this.hitungKaloriL());
           kal =String.valueOf(tamp);
           this.kaloriB.setText(kal);
           this.kaloriD.setText(kal);
           this.kaloriL.setText(kal);
           
        } else if(jk.equals("Laki-laki")&& ta.equals("Jarang olahraga")){
            tamp= (int) (1.375*this.hitungKaloriL());
            kal =String.valueOf(tamp);
            this.kaloriB.setText(kal);
            this.kaloriD.setText(kal);
            this.kaloriL.setText(kal);
            
        }  else if(jk.equals("Laki-laki")&& ta.equals("Normal olahraga")){
            tamp= (int) (1.55*this.hitungKaloriL());
            kal =String.valueOf(tamp);
            this.kaloriB.setText(kal);
            this.kaloriD.setText(kal);
            this.kaloriL.setText(kal);
            
        }  else if(jk.equals("Laki-laki")&& ta.equals("Sering olahraga")){
            tamp= (int) (1.725*this.hitungKaloriL());
            kal =String.valueOf(tamp);
            this.kaloriB.setText(kal);
            this.kaloriD.setText(kal);
            this.kaloriL.setText(kal);
            
        }  else if(jk.equals("Laki-laki")&& ta.equals("Sangat sering olahraga")){
            tamp= (int) (1.9*this.hitungKaloriL());
            kal =String.valueOf(tamp);
            this.kaloriB.setText(kal);
            this.kaloriD.setText(kal);
            this.kaloriL.setText(kal);
            
        }  else if(jk.equals("Perempuan")&& ta.equals("Sangat jarang olahraga")){
            tamp= (int) (1.2*this.hitungKaloriP());
            kal =String.valueOf(tamp);
            this.kaloriB.setText(kal);
            this.kaloriD.setText(kal);
            this.kaloriL.setText(kal);
            
        } else if(jk.equals("Perempuan")&& ta.equals("Jarang olahraga")){
            tamp= (int) (1.375*this.hitungKaloriP());
            kal =String.valueOf(tamp);
            this.kaloriB.setText(kal);
            this.kaloriD.setText(kal);
            this.kaloriL.setText(kal);
            
        } else if(jk.equals("Perempuan")&& ta.equals("Normal olahraga")){
            tamp= (int) (1.55*this.hitungKaloriP());
            kal =String.valueOf(tamp);
            this.kaloriB.setText(kal);
            this.kaloriD.setText(kal);
            this.kaloriL.setText(kal);
            
        } else if(jk.equals("Perempuan")&& ta.equals("Sering olahraga")){
            tamp= (int) (1.725*this.hitungKaloriP());
            kal =String.valueOf(tamp);
            this.kaloriB.setText(kal);
            this.kaloriD.setText(kal);
            this.kaloriL.setText(kal);
            
        } else if(jk.equals("Perempuan")&& ta.equals("Sangat sering olahraga")){
            tamp= (int) (1.9*this.hitungKaloriP());
            kal =String.valueOf(tamp);
            this.kaloriB.setText(kal);
            this.kaloriD.setText(kal);
            this.kaloriL.setText(kal);
        }
        rs.close();
    }
      
      
    public void loadDB(TableColumn<Makanan, Integer> idMakanan, TableColumn<Makanan, String> namaMakanan, TableColumn<Makanan, Integer> kaloriMakanan, TableView<Makanan> tableTab){
        idMakanan.setCellValueFactory(new PropertyValueFactory("id_makanan"));
        namaMakanan.setCellValueFactory(new PropertyValueFactory("Nama_makanan"));
        kaloriMakanan.setCellValueFactory(new PropertyValueFactory("Kalori"));
        
        ObservableList<Makanan> data;
        try {
            data = MakananDAO.searchMakanans();
            tableTab.setItems(data);
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(MakananManajemenController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void loadBreakfastDB(){
        loadDB(col_idBreakfast, col_namaBreakfast, col_KaloriBreakfast, makananBreakfast);
    }
    
    public void loadLunchDB(){
        loadDB(col_idLunch, col_namaLunch, col_KaloriLunch, makananLunch);
    }
    
    public void loadDinnerDB(){
        loadDB(col_idDinner, col_namaDinner, col_KaloriDinner, makananDinner);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        db = new DBUtil();
        comboJK.setItems(JK);
        comboAktivitas.setItems(aktivitas);
        loadBreakfastDB();
    } 
}
