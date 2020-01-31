/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Administrator;

import Model.serviceModel;
import DB_objects.*;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.paint.Color;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.IntegerStringConverter;

/**
 * FXML Controller class
 *
 * @author TPvPro
 */
public class Add_serviceController implements Initializable {

     public static ObservableList<service> services;
    @FXML
    private TextField Name;
    @FXML
    private TextField Price;

    @FXML
    private TextField Desc;
    @FXML
    private TableView<service> serviceTable;
    public static TableView<service> serviceTable_;
    @FXML
    private TableColumn<service, Integer> serviceId;
    @FXML
    private TableColumn<service, String> serviceName;
    @FXML
    private TableColumn<service, Double> servicePrice;

    @FXML
    private TableColumn<service,String> serviceDesc;
    @FXML
    private TableColumn<service,Button> serviceUpdate;

         IntegerStringConverter toInt =  new IntegerStringConverter();
     DoubleStringConverter toDouble = new DoubleStringConverter();
    @FXML
    private Label labelName;
    @FXML
    private Label labelPrice;
        @FXML
    private Label labelDesc;
    @FXML
    private Label errorMesssage;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      serviceTable_ = serviceTable;
   
        try {
           services = new serviceModel().Services();
           serviceTable.setItems(services);
           initTable();
        } catch (SQLException ex) {
             System.err.println(ex.toString());
        }
    }
    @FXML
    private void Add(ActionEvent event){

        serviceModel sm = new serviceModel();
        String pr = Price.getText();


         try {
             if((Name.getText().equals("") || Desc.getText().equals("")) || "".equals(Price.getText())){
            
                 errorMesssage.setTextFill(Color.web("#FF0000"));
                 errorMesssage.setText("All the fields are required");
                 
             }
             else {
                 sm.Create(new service(
                         0,
                         Name.getText(),
                         Desc.getText(),
                         toDouble.fromString(Price.getText())
                 ));
                 errorMesssage.setTextFill(Color.web("#FF0000"));
                 errorMesssage.setText("");
                 serviceTable.setItems(null);
                 services = sm.Services();
                 serviceTable.setItems(services);
                 Name.setText("");
                 
                 Desc.setText("");
                 Price.setText("");
             }
         } catch (SQLException ex  ) {
               System.err.println(ex.toString());
         }
    }    
    
        
    
     public void initTable(){
        intCols();
    }
    public void intCols(){  
    serviceId.setCellValueFactory(new PropertyValueFactory<>("Id"));
    serviceName.setCellValueFactory(new PropertyValueFactory<>("Name"));
     
    serviceDesc.setCellValueFactory(new PropertyValueFactory<>("Desc"));
    servicePrice.setCellValueFactory(new PropertyValueFactory<>("Price"));
    serviceUpdate.setCellValueFactory(new PropertyValueFactory<>("Update"));
    UpdateCiols();
    }
    public void UpdateCiols(){
        
       serviceId.setCellFactory(TextFieldTableCell.forTableColumn(toInt));
       serviceId.setOnEditCommit(e->{
           e.getTableView().getItems().get(e.getTablePosition().getRow()).setId(e.getNewValue());
       });
       
       serviceDesc.setCellFactory(TextFieldTableCell.forTableColumn());
       serviceDesc.setOnEditCommit(e->{
           e.getTableView().getItems().get(e.getTablePosition().getRow()).setDesc(e.getNewValue());
       });
       serviceName.setCellFactory(TextFieldTableCell.forTableColumn());
       serviceName.setOnEditCommit(e->{
           e.getTableView().getItems().get(e.getTablePosition().getRow()).setName(e.getNewValue());
       });
     
         servicePrice.setCellFactory(TextFieldTableCell.forTableColumn(toDouble));
       servicePrice.setOnEditCommit(e->{
           e.getTableView().getItems().get(e.getTablePosition().getRow()).setPrice(e.getNewValue());
       });
    
       serviceTable.setEditable(true);
    }
}
