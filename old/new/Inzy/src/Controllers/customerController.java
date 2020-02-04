/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import DAO.*;
import Model.*;
import DB_objects.*;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author PROBOOK
 */
public class customerController implements Initializable{

    CheckBox ck1;
    CheckBox ck2;
    CheckBox ck3;
    CheckBox ck4;
    TableView<DB_customers> liste;
     TableColumn<DB_customers,String> lname;
    private TableColumn<DB_customers,String> fname;
    private TableColumn<DB_customers,String> iddoc;
    private TableColumn<DB_customers,String> nroom;
    private TextField cekx;
    private TextField cek2;
    private TextField cek3;
    private TextField cek4;
    @FXML
    private Label labelName;
    @FXML
    private TextField Name;
    @FXML
    private Label labelPrice;
    @FXML
    private Label labelDesc;
    @FXML
    private Label labelName1;
    @FXML
    private TextField Name1;
    @FXML
    private Label labelPrice1;
    @FXML
    private Label labelDesc1;
    @FXML
    private TableView<?> serviceTable;
    @FXML
    private TableColumn<?, ?> serviceId;
    private void handleButtonAction(ActionEvent event){
        try{
            String rlname,rfiname,rroomnub,rci,param="";
            if(ck1.isSelected()) 
                { rlname= cekx.getText();
                if(rlname.isEmpty())
                {   Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("information Dialog");
                    alert.setHeaderText("Enter last name please");
                    alert.setContentText("!");
                    alert.showAndWait();
                }
                else
                {
                   param= " and nom = '"+rlname+"'"; 
                }
                }
            if(ck2.isSelected()) 
                { rfiname= cek2.getText();
                if(rfiname.isEmpty())
                {   Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("information Dialog");
                    alert.setHeaderText("Enter first name please");
                    alert.setContentText("!");
                    alert.showAndWait();
                }
                else
                {
                   param = " and prenom = '"+rfiname+"'"; 
                }
                }
            if(ck3.isSelected()) 
                { rci= cek3.getText();
                if(rci.isEmpty())
                {   Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("information Dialog");
                    alert.setHeaderText("Enter  cin or passeport number please");
                    alert.setContentText("!");
                    alert.showAndWait();
                }
                else
                {
                   param+= " and ci = '"+rci+"'"; 
                }
                }
            param += " group by nom,prenom,ci ";
           if(ck4.isSelected()) 
                { rroomnub= cek4.getText();
                if(rroomnub.isEmpty())
                {   Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("information Dialog");
                    alert.setHeaderText("Enter room number please");
                    alert.setContentText("!");
                    alert.showAndWait();
                }
                else
                {
                   param+= " having nr = "+rroomnub; 
                }
                }
           iddoc.setCellValueFactory(new PropertyValueFactory<>("ci"));    
           lname.setCellValueFactory(new PropertyValueFactory<>("nom"));
           fname.setCellValueFactory(new PropertyValueFactory<>("prenom"));
           nroom.setCellValueFactory(new PropertyValueFactory<>("roomid"));
           liste.setItems((new DAO_customers()).getcustomers(param));
        }
            catch(SQLException ex)
            {
                
            }
    }
   
    @Override
    public void initialize(URL location, ResourceBundle resources) {
       String param="select ci,nom,prenom,max((case when sysdate() between start_in and end_in then d.room_id else null end)) nr from customers c, reservations r ,details d where c.id=r.customer_id and r.id=d.reservation_id ";
         iddoc.setCellValueFactory(new PropertyValueFactory<>("ci"));    
           lname.setCellValueFactory(new PropertyValueFactory<>("nom"));
           fname.setCellValueFactory(new PropertyValueFactory<>("prenom"));
           nroom.setCellValueFactory(new PropertyValueFactory<>("roomid"));
        try {
            liste.setItems((new DAO_customers()).getcustomers(param));
        } catch (SQLException ex) {
            Logger.getLogger(customerController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void handleClearAction(ActionEvent event)  {
        ck1.setSelected(false);
        ck2.setSelected(false);
        ck3.setSelected(false);
        ck4.setSelected(false);
        cekx.clear(); 
        cek2.clear();
        cek3.clear();
        cek4.clear();
        liste.getItems().clear();
    }
    private void handlemouseevent(ActionEvent event)
    {
        DB_customers c= liste.getSelectionModel().getSelectedItem();
        System.out.println(c.getCi());
    }

    @FXML
    private void Add(ActionEvent event) {
    }
}
