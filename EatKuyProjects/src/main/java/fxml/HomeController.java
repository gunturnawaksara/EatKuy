/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fxml;

import com.mycompany.eatkuyprojects.DBUtil;
import com.mycompany.eatkuyprojects.DailyEat;
import com.mycompany.eatkuyprojects.DailyEatDAO;
import com.mycompany.eatkuyprojects.Makanan;
import com.mycompany.eatkuyprojects.MakananDAO;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.MouseEvent;
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
    private Label kalori;
    @FXML
    private TextField tinggiBadan;
    @FXML
    private Label username;
    @FXML
    private ComboBox<String> comboJK;
    @FXML
    private ComboBox<String> comboAktivitas;
    @FXML
    private ComboBox<String> comboSesi;
    
    ObservableList<String> JK = FXCollections.observableArrayList("Laki-laki","Perempuan");
    ObservableList<String> aktivitas = FXCollections.observableArrayList("Sangat jarang olahraga","Jarang olahraga","Normal olahraga","Sering olahraga","Sangat sering olahraga");
    ObservableList<String> sesi = FXCollections.observableArrayList("Breakfast","Lunch","Dinner");
    @FXML
    private TableView<Makanan> makananTabel;
    @FXML
    private TextField searchField;
    @FXML
    private TableView<DailyEat> catatMakanan;

    @FXML
    private TableColumn<Makanan, Integer> col_idMakanan;
    @FXML
    private TableColumn<Makanan, String> col_namaMakanan;
    @FXML
    private TableColumn<Makanan, Integer> col_KaloriMakanan;
    @FXML
    private TableColumn<DailyEat, Integer> col_idCatat;
    @FXML
    private TableColumn<DailyEat, String> col_namaMakananCatat;
    @FXML
    private TableColumn<DailyEat, Integer> col_kaloriCatat;
    @FXML
    private TableColumn<DailyEat, String> col_sesiCatat;
    @FXML
    private TextField makananTxt;
    @FXML
    private TextField kaloriTxt;
    @FXML
    private ImageView tambahBtn;
    @FXML
    private Button changeBtn;
    @FXML
    private DatePicker tanggalHistory;
    
    private String tanggal;

    /**
     * Initializes the controller class.
     */  
    
    
    
    @FXML
    private void changeButton(ActionEvent event) {
        if(changeBtn.getText().equals("Change")){
            comboJK.setDisable(false);
            beratBadan.setDisable(false);
            tinggiBadan.setDisable(false);
            usia.setDisable(false);
            comboAktivitas.setDisable(false);
            changeBtn.setText("SAVE");
        }else{
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
            changeBtn.setText("Change");
            getDisableChanger();
        }
    }

    @FXML
    private void logoutButton(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Login.fxml"));
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.setScene(new Scene(root));
    }
    
    public void GetUserLogin(String uName){
        // TODO Auto-generated method stub
        this.sessionUsername = uName;
        this.username.setText(uName);
        getDisableChanger();
    }
    
    public void getDisableChanger(){
        String query = "SELECT * FROM Akun Where Username = '"+sessionUsername+"'";
        ResultSet rs;
        try {
            rs = db.dbExecuteQuery(query);
            if(rs.next()){
                comboJK.setValue(rs.getString("JenisKelamin"));
                beratBadan.setText(String.valueOf(rs.getInt("BeratBadan")));
                tinggiBadan.setText(String.valueOf(rs.getInt("TinggiBadan")));
                usia.setText(String.valueOf(rs.getInt("Usia")));
                comboAktivitas.setValue(rs.getString("TingkatAktivitas"));
            }
            comboJK.setDisable(true);
            beratBadan.setDisable(true);
            tinggiBadan.setDisable(true);
            usia.setDisable(true);
            comboAktivitas.setDisable(true);
        } catch (SQLException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        } 
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
        if(rs.next()){
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
               this.kalori.setText(kal);

            } else if(jk.equals("Laki-laki")&& ta.equals("Jarang olahraga")){
                tamp= (int) (1.375*this.hitungKaloriL());
                kal =String.valueOf(tamp);
                this.kalori.setText(kal);

            }  else if(jk.equals("Laki-laki")&& ta.equals("Normal olahraga")){
                tamp= (int) (1.55*this.hitungKaloriL());
                kal =String.valueOf(tamp);
                this.kalori.setText(kal);

            }  else if(jk.equals("Laki-laki")&& ta.equals("Sering olahraga")){
                tamp= (int) (1.725*this.hitungKaloriL());
                kal =String.valueOf(tamp);
                this.kalori.setText(kal);

            }  else if(jk.equals("Laki-laki")&& ta.equals("Sangat sering olahraga")){
                tamp= (int) (1.9*this.hitungKaloriL());
                kal =String.valueOf(tamp);
                this.kalori.setText(kal);

            }  else if(jk.equals("Perempuan")&& ta.equals("Sangat jarang olahraga")){
                tamp= (int) (1.2*this.hitungKaloriP());
                kal =String.valueOf(tamp);
                this.kalori.setText(kal);

            } else if(jk.equals("Perempuan")&& ta.equals("Jarang olahraga")){
                tamp= (int) (1.375*this.hitungKaloriP());
                kal =String.valueOf(tamp);
                this.kalori.setText(kal);

            } else if(jk.equals("Perempuan")&& ta.equals("Normal olahraga")){
                tamp= (int) (1.55*this.hitungKaloriP());
                kal =String.valueOf(tamp);
                this.kalori.setText(kal);

            } else if(jk.equals("Perempuan")&& ta.equals("Sering olahraga")){
                tamp= (int) (1.725*this.hitungKaloriP());
                kal =String.valueOf(tamp);
                this.kalori.setText(kal);

            } else if(jk.equals("Perempuan")&& ta.equals("Sangat sering olahraga")){
                tamp= (int) (1.9*this.hitungKaloriP());
                kal =String.valueOf(tamp);
                this.kalori.setText(kal);
            }
        }
        rs.close();
    }
    
    public void loadDB1(TableColumn<DailyEat, Integer> id_Catat, TableColumn<DailyEat, String> namaMakananCatat, TableColumn<DailyEat, Integer> kaloriCatat, TableColumn<DailyEat, String> sesiCatat, TableView<DailyEat> tableTab, String date){
        id_Catat.setCellValueFactory(new PropertyValueFactory("ID_Catat"));
        namaMakananCatat.setCellValueFactory(new PropertyValueFactory("NamaMakanan"));
        kaloriCatat.setCellValueFactory(new PropertyValueFactory("Kalori"));
        sesiCatat.setCellValueFactory(new PropertyValueFactory("SesiMakan"));
        ObservableList<DailyEat> data;
        try {    
            data = DailyEatDAO.searchDailyEats(date);
            tableTab.setItems(data);
            hitungPogressKalori(tanggal);
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(MakananManajemenController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
      
      
    public void loadDB2(TableColumn<Makanan, Integer> idMakanan, TableColumn<Makanan, String> namaMakanan, TableColumn<Makanan, Integer> kaloriMakanan, TableView<Makanan> tableTab){
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
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        db = new DBUtil();
        comboJK.setItems(JK);
        comboAktivitas.setItems(aktivitas);
        comboSesi.setItems(sesi);
        tanggalHariIni();
        loadDB1(col_idCatat, col_namaMakananCatat, col_kaloriCatat, col_sesiCatat, catatMakanan, String.valueOf(LocalDate.now()));
        loadDB2(col_idMakanan, col_namaMakanan, col_KaloriMakanan, makananTabel);
    } 

    @FXML
    private void filterMakanan(ActionEvent event) {
        ObservableList<DailyEat> data;
        try {
            data = DailyEatDAO.searchDailyEats(tanggal);
            final FilteredList<DailyEat> filteredList = new FilteredList<>(data);
            searchField.textProperty().addListener(new ChangeListener<String>() {
                @Override
                public void changed(ObservableValue<? extends String> observable, String oldValue, final String newValue) {
                    filteredList.setPredicate(new Predicate<DailyEat>() {
                        @Override
                        public boolean test(DailyEat t) {
                            if(newValue == null || newValue.isEmpty()){
                                return true;
                            }
                            String lowerCaseFilter = newValue.toLowerCase();
                            if(t.getNamaMakanan().toLowerCase().indexOf(lowerCaseFilter) != -1){
                                return true;
                            } else if (String.valueOf(t.getKalori()).toLowerCase().indexOf(lowerCaseFilter) != -1){
                                return true;
                            }
                            return false;
                        }
                    });
                }
            });
            SortedList<DailyEat> sortedData = new SortedList<>(filteredList);
            sortedData.comparatorProperty().bind(catatMakanan.comparatorProperty());
            catatMakanan.setItems(sortedData);
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(MakananManajemenController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
 

    @FXML
    private void getMakanan(MouseEvent event) {
        try{
            makananTxt.setText(makananTabel.getSelectionModel().getSelectedItem().getNama_makanan());
            kaloriTxt.setText(makananTabel.getSelectionModel().getSelectedItem().getKalori());
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public void clearField(){
        makananTxt.setText(null);
        kaloriTxt.setText(null);
        comboSesi.setValue(null);
    }

    @FXML
    private void buttonTambah(MouseEvent event) {
        String namaM = makananTxt.getText();
        String kaloriM = kaloriTxt.getText();
        String sesi = comboSesi.getValue();
        if(sesi != null){
            try{
                String query = "INSERT INTO MakananHarian(NamaMakanan, Kalori, SesiMakan, Tanggal, Username) VALUES('"+namaM+"','"+kaloriM+"','"+sesi+"','"+tanggal+"','"+sessionUsername+"')";
                db.dbExecuteUpdate(query);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("ADD FOOD SUCCESS");
                alert.setHeaderText("TAMBAH MAKANAN !");
                alert.setContentText("BERHASIL MENAMBAH MAKANAN !");
                alert.showAndWait();
                clearField();
                loadDB1(col_idCatat, col_namaMakananCatat, col_kaloriCatat, col_sesiCatat, catatMakanan, tanggal);
            }catch(Exception e){
                System.out.print(e);
            }
        }
    }
    
    
    public void tanggalHariIni(){
        tanggalHistory.setValue(LocalDate.now());
        tanggal = String.valueOf(tanggalHistory.getValue());
    }

    @FXML
    private void getNewDate(ActionEvent event) {
        tanggal = String.valueOf(tanggalHistory.getValue());
        loadDB1(col_idCatat, col_namaMakananCatat, col_kaloriCatat, col_sesiCatat, catatMakanan, tanggal);
        
    }
    
    public void hitungPogressKalori(String kalori) throws ClassNotFoundException, SQLException{
        GetKaloriUser();
        try {
            String selectStmt = "SELECT * FROM MakananHarian WHERE Tanggal = '"+tanggal+"'";
            ResultSet rsMtk = DBUtil.getInstance().dbExecuteQuery(selectStmt);
            while(rsMtk.next()){
                int jmlhKalori = Integer.parseInt(this.kalori.getText());
                int kalMakanan = Integer.parseInt(rsMtk.getString("Kalori"));
                this.kalori.setText(String.valueOf(jmlhKalori-kalMakanan));
            }
        } catch (SQLException e) {
            System.out.println("SQL select operation has been failed: " + e); //Return exception
            throw e;
        }
    }
    
}
