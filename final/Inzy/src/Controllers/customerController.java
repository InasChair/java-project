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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
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

    @FXML
    CheckBox ck1;
    @FXML
    CheckBox ck2;
    @FXML
    CheckBox ck3;
    @FXML
    CheckBox ck4;
    @FXML
    TableView<DB_customers> liste;
     @FXML
    TableColumn<DB_customers,String> lname;
     @FXML
    private TableColumn<DB_customers,String> fname;
    @FXML
    private TableColumn<DB_customers,String> iddoc;
    @FXML
    private TableColumn<DB_customers,String> nroom;
    @FXML
    private TextField cekx;
    @FXML
    private TextField cek2;
    @FXML
    private TextField cek3;
    @FXML
    private TextField cek4;
    @FXML
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
         //To change body of generated methods, choose Tools | Templates.
    }
    @FXML
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
    @FXML
    private void handlemouseevent(ActionEvent event)
    {
        DB_customers c= liste.getSelectionModel().getSelectedItem();
        System.out.println(c.getCi());
    }
}
