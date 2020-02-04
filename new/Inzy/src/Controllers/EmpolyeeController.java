/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import Model.*;
import DB_objects.*;

import java.sql.SQLException;
import java.util.Vector;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;
/**
 * FXML Controller class
 *
 * @author ultra
 */
public class EmpolyeeController implements Initializable {

    @FXML
    private Label labelName;
    @FXML
    private TextField Name;
    @FXML
    private Label labelPrice;
    @FXML
    private PasswordField Password;
    @FXML
    private Label labelDesc;
    @FXML
    private PasswordField cpass;
    @FXML
    private Label labelName1;
    @FXML
    private TextField Name1;
    @FXML
    private Label labelPrice1;
    @FXML
    private PasswordField Password1;
    @FXML
    private Label labelDesc1;
    @FXML
    private PasswordField cpass1;
    @FXML
    private TableView<db> serviceTable;
    @FXML
    private TableColumn<db,String> serviceId;

    Auth a=new Auth();
    Model1 b=new Model1();
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        a.Rreservation();
    initTable();
    }    

    @FXML
    private void Add(ActionEvent event) throws SQLException {
        if(a.User1(Name.getText()).first()==true)
        {
               Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText(null);
             alert.setContentText("This username exist already!");
            alert.showAndWait();
            return;
        }
        if(Password.getText().compareTo(cpass.getText())!=0)
        {
                  Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText(null);
             alert.setContentText("Wrong confirmation password!");
            alert.showAndWait();
            return;
        }
        b.insertemp(Name.getText(), Password.getText());
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("the cashier was added successfuly");
            alert.showAndWait();
               initTable();
        
       
    }

    @FXML
    private void change(ActionEvent event) throws SQLException {
         if(a.User1(Name1.getText()).first()==false)
        {
               Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText(null);
             alert.setContentText("This username doesn't exist!");
            alert.showAndWait();
            return;
        }
         if(Password1.getText().compareTo(cpass1.getText())!=0)
        {
                  Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText(null);
             alert.setContentText("Wrong confirmation password!");
            alert.showAndWait();
            return;
        }
         b.updatepwd(Name1.getText(),Password1.getText());
         
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("the password was updated!");
            alert.showAndWait();
    }
    void remplissage()
    {ObservableList<db> FX;
       FX =b.usern();
       serviceTable.setItems( FX);
   }
    private void initTable() {
        initcol();
    }
         private void initcol() {
       
        serviceId.setCellValueFactory(new PropertyValueFactory<db,String>("Username"));
        ObservableList<db> FX;
       FX =b.usern();
          
       serviceTable.setItems(FX);
    }
    
 
   
}
