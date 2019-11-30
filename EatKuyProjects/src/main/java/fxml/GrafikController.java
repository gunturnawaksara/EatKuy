/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fxml;

import com.mycompany.eatkuyprojects.DBUtil;
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
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class GrafikController implements Initializable {

    
    private String sessionUsername;
    private DBUtil db;
    
    @FXML
    private LineChart<?, ?> grafikID;
    @FXML
    private NumberAxis y;
    @FXML
    private CategoryAxis x;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        db = new DBUtil();
        try {
            hitungGrafik();
        } catch (SQLException ex) {
            Logger.getLogger(GrafikController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(GrafikController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void back_btn(ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
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
    
    public void hitungGrafik() throws SQLException, ClassNotFoundException{
        //masih belum bisa
        
        XYChart.Series series = new XYChart.Series();
        String query="select Tanggal,Kalori from MakananHarian where Username = '"+sessionUsername+"'";
        ResultSet rs=db.dbExecuteQuery(query);
        while(rs.next())
        series.getData().add(new XYChart.Data<>(rs.getDate(1), rs.getInt(2)));
        grafikID.getData().addAll(series);
        }
       
}

