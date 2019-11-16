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
import java.text.SimpleDateFormat;
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
import javafx.scene.control.ComboBox;
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
        this.username.setText(uName);
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
    
    public void loadDB1(TableColumn<DailyEat, Integer> id_Catat, TableColumn<DailyEat, String> namaMakananCatat, TableColumn<DailyEat, Integer> kaloriCatat, TableColumn<DailyEat, String> sesiCatat, TableView<DailyEat> tableTab){
        id_Catat.setCellValueFactory(new PropertyValueFactory("ID_Catat"));
        namaMakananCatat.setCellValueFactory(new PropertyValueFactory("NamaMakanan"));
        kaloriCatat.setCellValueFactory(new PropertyValueFactory("Kalori"));
        sesiCatat.setCellValueFactory(new PropertyValueFactory("SesiMakan"));
        
        ObservableList<DailyEat> data;
        try {
            data = DailyEatDAO.searchDailyEats();
            tableTab.setItems(data);
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
    
    public void loadMakananDB(){
        loadDB2(col_idMakanan, col_namaMakanan, col_KaloriMakanan, makananTabel);
    }
    
    public void loadCatatDB(){
        loadDB1(col_idCatat, col_namaMakananCatat, col_kaloriCatat, col_sesiCatat, catatMakanan);
    }
    
    public void searchFiltered(TextField session, TableView<Makanan> tabSeacrh){
        ObservableList<Makanan> data;
        try {
            data = MakananDAO.searchMakanans();
            final FilteredList<Makanan> filteredList = new FilteredList<>(data);
            session.textProperty().addListener(new ChangeListener<String>() {
                @Override
                public void changed(ObservableValue<? extends String> observable, String oldValue, final String newValue) {
                    filteredList.setPredicate(new Predicate<Makanan>() {
                        @Override
                        public boolean test(Makanan t) {
                            if(newValue == null || newValue.isEmpty()){
                                return true;
                            }
                            String lowerCaseFilter = newValue.toLowerCase();
                            if(t.getNama_makanan().toLowerCase().indexOf(lowerCaseFilter) != -1){
                                return true;
                            } else if (String.valueOf(t.getKalori()).toLowerCase().indexOf(lowerCaseFilter) != -1){
                                return true;
                            }
                            return false;
                        }
                    });
                }
            });
            SortedList<Makanan> sortedData = new SortedList<>(filteredList);
            sortedData.comparatorProperty().bind(tabSeacrh.comparatorProperty());
            tabSeacrh.setItems(sortedData);
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
        loadMakananDB();
        loadCatatDB();
        try {
            pindahHistory();
        } catch (SQLException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 

    @FXML
    private void filterMakanan(ActionEvent event) {
        searchFiltered(searchField, makananTabel);
    }

    @FXML
    private void historyButton(ActionEvent event) throws IOException {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/History.fxml"));
                    Parent Main = (Parent) loader.load();
                    HistoryController his = (HistoryController)loader.getController();
                    his.GetUserLogin(this.sessionUsername);
                    Scene scene = new Scene(Main);
                    Stage Primarystage = (Stage) ((Node)event.getSource()).getScene().getWindow();
                    Primarystage.setResizable(false);
                    Primarystage.setScene(scene);
                    Primarystage.show();
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
        String pattern = "yyyy-mm-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String date = simpleDateFormat.format(new Date());
        try{
                String query = "INSERT INTO MakananHarian(NamaMakanan, Kalori, SesiMakan, Tanggal) VALUES('"+namaM+"','"+kaloriM+"','"+sesi+"','"+date+"')";
                db.dbExecuteUpdate(query);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("ADD FOOD SUCCESS");
                alert.setHeaderText("TAMBAH MAKANAN !");
                alert.setContentText("BERHASIL MENAMBAH MAKANAN !");
                alert.showAndWait();
                clearField();
                loadDB1(col_idCatat, col_namaMakananCatat, col_kaloriCatat, col_sesiCatat, catatMakanan);
        }catch(Exception e){
            System.out.print(e);
        }
    }
    
    public void pindahHistory() throws SQLException, ClassNotFoundException{
        String pattern = "yyyy-mm-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String date = simpleDateFormat.format(new Date());
        System.out.print(date);
        String query = "SELECT * FROM MakananHarian WHERE Username= '"+sessionUsername+"'";
        ResultSet rs2 = db.dbExecuteQuery(query);
        while(rs2.next()){
            if(String.valueOf(rs2.getDate("Tanggal")) != date){
                String queryInsert = "INSERT INTO MakananHistory(ID_History, User, NamaMakanan, Kalori, Tanggal VALUES('"+rs2.getString("ID_catat")+"','"+sessionUsername+"','"+rs2.getString("NamaMakanan")+"','"+rs2.getString("Kalori")+"','"+rs2.getString("Tanggal")+"')";
                db.dbExecuteUpdate(queryInsert);
            }
        }
        String queryReset = "DELETE FROM MakananHarian WHERE 1";
        db.dbExecuteUpdate(queryReset);
    }

}
