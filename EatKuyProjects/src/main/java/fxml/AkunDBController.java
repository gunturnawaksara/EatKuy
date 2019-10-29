/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fxml;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
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
import javafx.stage.Stage;

/**
 *
 * @author ASUS
 */
public class AkunDBController implements Initializable{

    @FXML
    private Button logout;
    @FXML
    private TableView<?> userTableView;
    @FXML
    private TableColumn<?, ?> col_id;
    @FXML
    private TableColumn<?, ?> col_email;
    @FXML
    private TableColumn<?, ?> col_uname;
    @FXML
    private TableColumn<?, ?> col_pass;
    @FXML
    private TableColumn<?, ?> col_usia;
    @FXML
    private TableColumn<?, ?> col_jkelamin;
    @FXML
    private TableColumn<?, ?> col_bb;
    @FXML
    private TableColumn<?, ?> col_tb;
    @FXML
    private TableColumn<?, ?> col_ta;
    @FXML
    private TableColumn<?, ?> col_status;

    @FXML
    void logoutButton(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Login.fxml"));
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.setScene(new Scene(root));
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    
    
}
