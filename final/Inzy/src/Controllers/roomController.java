/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import DAO.*;
import DB_objects.*;
import Model.*;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 *
 * @author PROBOOK
 */
public class roomController implements Initializable {

    @FXML
    private CheckBox ck1;
    @FXML
    private CheckBox ck2;
    @FXML
    private CheckBox ck3;
    @FXML
    private CheckBox cck4;
    @FXML
    private TableView<DB_rooms> list;
    @FXML
    private DatePicker  dt1;
    @FXML
    private DatePicker dt2;
    static MouseEvent event;
    @FXML
    private TableColumn<DB_rooms,String> number;
    
    @FXML
    private TableColumn<DB_rooms,String> floor;
    
      @FXML
    private TableColumn<DB_rooms,String> type;
      
      @FXML
    private TableColumn<DB_rooms,String> price;
          @FXML
    private TableColumn<DB_rooms,String> beds;
          @FXML
    private TableColumn<DB_rooms,String> state;
          
    @FXML
    private TableColumn<DB_rooms,String> av;
    @FXML
    private ComboBox<String> cmb1;
    @FXML
    private ComboBox<String> cmb2;
    @FXML
    private ComboBox<String> cmb3;
    @FXML
    private TextField txtp;
    @FXML
   
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        
        ObservableList<String> lstc = FXCollections.observableArrayList("Single","Twin","Suite","Double");
        cmb1.setItems(lstc);
        ObservableList<String> lstb = FXCollections.observableArrayList("1","2","3");
        cmb2.setItems(lstb);
        ObservableList<String> lsta = FXCollections.observableArrayList("1","2","3");
        cmb3.setItems(lstb);
        dt1.setValue(LocalDate.now());
        dt2.setValue(LocalDate.now());

        

  
           ObservableList<DB_rooms> rooms;
      
            DAO_rooms drm = new DAO_rooms();
        try {
            rooms = drm.All();
                
            list.setItems(rooms);
         
           initTable();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
       
       
    }
    
    
    
    public void initTable(){
        intCols();
    }
    public void intCols(){  
        
       number.setCellValueFactory(new PropertyValueFactory<>("number"));
       floor.setCellValueFactory(new PropertyValueFactory<DB_rooms, String>("floor"));
       type.setCellValueFactory(new PropertyValueFactory<DB_rooms, String>("type"));
       price.setCellValueFactory(new PropertyValueFactory<DB_rooms, String>("price"));
       beds.setCellValueFactory(new PropertyValueFactory<DB_rooms, String>("beds"));
       av.setCellValueFactory(new PropertyValueFactory<DB_rooms, String>("av"));
       list.setRowFactory(tableView -> {
    final TableRow<DB_rooms> row = new TableRow<>();
      
    row.hoverProperty().addListener((observable) -> {
         DB_rooms rr = row.getItem();
       
        if (row.isFocused() && rr != null) {
             if(rr.getAv().compareToIgnoreCase("available")!=0)
             {Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText(null);
             alert.setContentText("this roon isn't available, chose another one please!!" );
            alert.showAndWait();
             }
             else{
               
         ReservationController.sliver=rr;
            Stage window =(Stage)((Node)this.event.getSource()).getScene().getWindow();
        window.close();
             }
        } 
    });

    return row;
});
    }

   
    
    @FXML
     private void handleButtonAction(ActionEvent event) {
       try
       {
       String rtype,rbeds,rprice,rfloor,param="";
       if(ck1.isSelected()) 
       { rtype = cmb1.getValue(); 
                if(rtype==null)
                {Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("information Dialog");
                alert.setHeaderText("Look, make a choice from the list");
                alert.setContentText("a type must be selected from the list!");
                alert.showAndWait();
                }
                else
                {param = " and type = '"+rtype+"'";
                }      
       }
       if(ck2.isSelected()) {rbeds = cmb2.getValue(); 
        if(rbeds==null)
                {Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("information Dialog");
                alert.setHeaderText("Look, make a choice from the list");
                alert.setContentText("a number of beds must be selected from the list!");
                alert.showAndWait();
                }
                else
                {param += " and beds = "+Integer.parseInt(rbeds);
                }
       }
       if(ck3.isSelected()) 
       {
           rprice = txtp.getText();
           if(rprice.isEmpty())
           {
               Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText("Information must be filled");
                alert.setContentText("please fill the maximal price!");
                alert.showAndWait();
           }
           else
           {rprice = txtp.getText(); param += " and price <="+Double.parseDouble(rprice);
              }
       }
           if(cck4.isSelected()) {
               rfloor = cmb3.getValue(); 
                if(rfloor==null)
                {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("information Dialog");
                    alert.setHeaderText("Look, make a choice from the list");
                    alert.setContentText("a number of beds must be selected from the list!");
                    alert.showAndWait();
                }
                else
                {param += " and floor = "+Integer.parseInt(rfloor);
                }
       }
 initTable();
      list.setItems((new DAO_rooms()).getrooms(dt1.getValue().toString(),dt2.getValue().toString(), param));
     
        
       }
       catch(SQLException ex)
       {
           
       }
       
    }
    
    @FXML
    private void handleClearAction(ActionEvent event) {
        ck1.setSelected(false);
        ck2.setSelected(false);
        ck3.setSelected(false);
        cck4.setSelected(false);
        dt1.setValue(LocalDate.now());
        dt2.setValue(LocalDate.now());
        cmb1.getSelectionModel().clearSelection();
        cmb2.getSelectionModel().clearSelection();
        cmb3.getSelectionModel().clearSelection();
        txtp.clear();
        
    }
    
    
 
    //////////////////////////////////////////////////////////////////////////////

    public void sendin(LocalDate kk,LocalDate rr){
        dt1.setValue(kk);
        dt2.setValue(rr);
      
}     
        @FXML
    void endless(MouseEvent event) {
        this.event = event;
      
    }

}


