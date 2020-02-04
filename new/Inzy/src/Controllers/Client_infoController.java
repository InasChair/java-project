/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers ;

import java.io.IOException;
import DAO.*;
import Model.*;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
/**
 * FXML Controller class
 *
 * @author PROBOOK
 */
public class Client_infoController implements Initializable {

    @FXML
    Label l1;
    @FXML
    Label l2;
    @FXML
    Label l3;
    @FXML
    Label l4;
    @FXML
    Label l5;
    @FXML
    Label l6;
    @FXML
    Label l7;
    @FXML
    Label l8;
    @FXML
    Label l9;
    @FXML
    Label l10;
    @FXML
    Label l11;
    @FXML
    ComboBox cmb1 ;
    @FXML
    AnchorPane anch;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }    
   public void afficher(ResultSet r1) throws SQLException 
    {
        l1.setText(r1.getString("nom"));
        l2.setText(r1.getString("prenom"));
        l3.setText(r1.getString("ci"));
        l4.setText(r1.getString("phone"));
        l5.setText(r1.getString("email"));
        l6.setText(r1.getString("author"));
        l7.setText(r1.getString("room_id"));
        l8.setText(r1.getString("start_in"));
        l9.setText(r1.getString("end_in"));
        l10.setText(r1.getString("total_amount"));
	ObservableList<String> lstc = FXCollections.observableArrayList();
	do
	{
        lstc.add(r1.getString("servicen"));
	}while(r1.next());
        cmb1.setItems(lstc);
    }
    @FXML 
     void back(ActionEvent event) throws IOException {
         FXMLLoader Loader = new FXMLLoader(getClass().getResource("/Cashier/clients.fxml"));
          AnchorPane root =Loader.load();
      
        
          anch.getChildren().setAll(root);
     }
    }
    
