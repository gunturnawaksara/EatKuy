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
    private LineChart<String, Number> grafikID;
    
    XYChart.Series<String,Number> dataKalori;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        db = new DBUtil();
    }    

    @FXML
    private void back_btn(ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Home.fxml"));
            Parent Main = (Parent) loader.load();
            HomeController home = (HomeController)loader.getController();
            home.GetUserLogin(this.sessionUsername);
            home.GetKaloriUser();
            home.hitungPogressKalori(sessionUsername);
            Scene scene = new Scene(Main);
            Stage Primarystage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            Primarystage.setResizable(false);
            Primarystage.setScene(scene);
            Primarystage.show();
    }
    
    public void GetUserLogin(String uName) throws SQLException, ClassNotFoundException {
        // TODO Auto-generated method stub
        this.sessionUsername = uName;
        hitungGrafik();
    }
    
    public void hitungGrafik() throws SQLException, ClassNotFoundException{
        dataKalori=new XYChart.Series();
        dataKalori.setName("Kalori");
        String query="SELECT Tanggal,Kalori from MakananHarian WHERE Username='"+sessionUsername+"'";
        ResultSet rs=db.dbExecuteQuery(query);
        while(rs.next()){
            dataKalori.getData().add(new XYChart.Data(rs.getString("Tanggal"),rs.getInt("Kalori")));
        }
        grafikID.getData().add(dataKalori);
       
    }
}

