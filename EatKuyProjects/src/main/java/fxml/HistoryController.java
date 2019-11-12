/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fxml;

import com.mycompany.eatkuyprojects.DailyEat;
import com.mycompany.eatkuyprojects.DailyEatDAO;
import com.mycompany.eatkuyprojects.History;
import com.mycompany.eatkuyprojects.HistoryDAO;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class HistoryController implements Initializable {

    private String sessionUsername;
    @FXML
    private TableView<History> tabelHistory;
    @FXML
    private TableColumn<History, Integer> col_idHistory;

    @FXML
    private TableColumn<History, String> col_userHistory;

    @FXML
    private TableColumn<History, String> col_makananHistory;

    @FXML
    private TableColumn<History, Integer> col_kaloriHistory;

    @FXML
    private TableColumn<History, String> col_tanggalHistory;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        loadHistory();
    }    

    @FXML
    private void backButton(ActionEvent event) throws SQLException, ClassNotFoundException, IOException {
           FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Home.fxml"));
            Parent Main = (Parent) loader.load();
            HomeController home = (HomeController)loader.getController();
            home.GetUserLogin(this.sessionUsername);
            home.GetKaloriUser();
            Scene scene = new Scene(Main);
            Stage Primarystage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            Primarystage.setResizable(false);
            Primarystage.setScene(scene);
            Primarystage.show();
    }
    
    public void GetUserLogin(String uName) {
        // TODO Auto-generated method stub
        this.sessionUsername = uName;
    }
    
    public void loadDBHistory(TableColumn<History, Integer> id_History, TableColumn<History, String> userHistory, TableColumn<History, String> makananHistory, TableColumn<History, Integer> kaloriHistory, TableColumn<History, String> tanggalHistory, TableView<History> tableView){
        id_History.setCellValueFactory(new PropertyValueFactory("ID_History"));
        userHistory.setCellValueFactory(new PropertyValueFactory("User"));
        makananHistory.setCellValueFactory(new PropertyValueFactory("NamaMakanan"));
        kaloriHistory.setCellValueFactory(new PropertyValueFactory("Kalori"));
        tanggalHistory.setCellValueFactory(new PropertyValueFactory("Tanggal"));
        
        ObservableList<History> data;
        try {
            data = HistoryDAO.searchHistorys();
            tabelHistory.setItems(data);
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(MakananManajemenController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void loadHistory(){
       loadDBHistory(col_idHistory, col_userHistory, col_makananHistory, col_kaloriHistory,col_tanggalHistory, tabelHistory);
    }
    
}
