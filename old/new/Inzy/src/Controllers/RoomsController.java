/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Model.roomModel;
import DB_objects.*;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXToggleButton;
import java.awt.BorderLayout;
import java.net.URL;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.IntegerStringConverter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import jdk.nashorn.internal.objects.NativeArray;

/**
 * FXML Controller class
 *
 * @author TPvPro
 */
public class RoomsController implements Initializable {
    public static ObservableList<room> rooms;
    @FXML
    private JFXCheckBox byType;
    @FXML
    private ComboBox<String> Types;
    @FXML
    private JFXCheckBox byBeds;
    @FXML
    private JFXCheckBox byAcces;
    @FXML
    private ComboBox<Integer> Beds;
    @FXML
    private JFXCheckBox byFloor;
    @FXML
    private ComboBox<Integer> Floors;
    @FXML
    private JFXCheckBox byPrice;
    @FXML
    private TextField Price;
    @FXML
    private JFXToggleButton Access;
    @FXML
    private TableView<room> roomsTable;
    public static TableView<room> roomsTable_;
    @FXML
      private TableColumn<room, Integer> roomId;
    @FXML
    private TableColumn<room, Integer> roomFloor;
    @FXML
    private TableColumn<room, Integer> roomBeds;
    @FXML
    private TableColumn<room, String> roomType;
    @FXML
    private TableColumn<room, Double> roomPrice;
    @FXML
    private TableColumn<room, Button> roomUpdate;
     
    
   
     IntegerStringConverter toInt =  new IntegerStringConverter();
     DoubleStringConverter toDouble = new DoubleStringConverter();
        Integer b = 0;
          Integer f =0;
          String t="",s="";
          Double p = 0.0; 
        
   
   
    /**
     * Initializes the controller class.
     */
    @Override
    
    public void initialize(URL url, ResourceBundle rb) {
        roomsTable_ = roomsTable;
           ObservableList<String> types = FXCollections.observableArrayList("Type 1","type 2");
           ObservableList<Integer> floors = FXCollections.observableArrayList(1,2,3,4);
           ObservableList<Integer> beds = FXCollections.observableArrayList(1,2,3);
           Types.setItems(types);
           Floors.setItems(floors);
           Beds.setItems(beds);
        try {
           rooms = new roomModel().Rooms(0,"",0,0.0,"");
           roomsTable.setItems(rooms);
         
           initTable();
        } catch (SQLException ex) {
             System.err.println(ex.toString());
        }
    }    

    @FXML
    private void Search(ActionEvent event) {
      
        int temp = 1;
           if(byType.isSelected())
               if((t = Types.getValue())==null) temp = 0;
      
           if(byFloor.isSelected())if((f= Floors.getValue())==null)temp = 0;
     
           if(byBeds.isSelected())if((b = Beds.getValue())==null) temp = 0;
          
           if(byPrice.isSelected()){
               String pr = Price.getText();

            if("".equals(pr)) temp = 0;
            else   p = toDouble.fromString(pr);
           }

            if(byAcces.isSelected()){
           if(s.equals(Access.getText())) s= "not available";
           else s = Access.getText(); 
        }
      
        if(temp == 0){
               Alert alert = new Alert(Alert.AlertType.INFORMATION);
            
                alert.setTitle("information Dialog");
                alert.setHeaderText("Connetion problems ");
              //  alert.setContentText("");
                alert.showAndWait();
          }
         else {
             roomsTable.setItems(null);
        try {
            rooms = new roomModel().Rooms(f,t,b,p,s);
        } catch (SQLException ex) {
            {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
            
                alert.setTitle("information Dialog");
                alert.setHeaderText("You should make a choice from the list ");
              // alert.setContentText("");
                alert.showAndWait();
        }
        }
          roomsTable.setItems(rooms);
          initTable();     
          }
    
    }
    
    public void initTable(){
        intCols();
    }
    public void intCols(){  
    roomId.setCellValueFactory(new PropertyValueFactory<>("id"));
    roomFloor.setCellValueFactory(new PropertyValueFactory<>("floor"));
    roomType.setCellValueFactory(new PropertyValueFactory<>("type"));
    roomBeds.setCellValueFactory(new PropertyValueFactory<>("bed"));
    roomPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
 
    roomUpdate.setCellValueFactory(new PropertyValueFactory<>("Update"));
    UpdateCiols();
    }
    public void UpdateCiols(){
        
       roomId.setCellFactory(TextFieldTableCell.forTableColumn(toInt));
       roomId.setOnEditCommit(e->{
           e.getTableView().getItems().get(e.getTablePosition().getRow()).setId(e.getNewValue());
       });
       
       roomFloor.setCellFactory(TextFieldTableCell.forTableColumn(toInt));
       roomFloor.setOnEditCommit(e->{
           e.getTableView().getItems().get(e.getTablePosition().getRow()).setFloor(e.getNewValue());
       });
       roomType.setCellFactory(TextFieldTableCell.forTableColumn());
       roomType.setOnEditCommit(e->{
           e.getTableView().getItems().get(e.getTablePosition().getRow()).setType(e.getNewValue());
       });
         roomBeds.setCellFactory(TextFieldTableCell.forTableColumn(toInt));
       roomBeds.setOnEditCommit(e->{
           e.getTableView().getItems().get(e.getTablePosition().getRow()).setBed(e.getNewValue());
       });
         roomPrice.setCellFactory(TextFieldTableCell.forTableColumn(toDouble));
       roomPrice.setOnEditCommit(e->{
           e.getTableView().getItems().get(e.getTablePosition().getRow()).setPrice(e.getNewValue());
       });
       roomsTable.setEditable(true);
    }


    
}
