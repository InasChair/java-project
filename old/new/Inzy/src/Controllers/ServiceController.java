/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import com.jfoenix.controls.JFXDatePicker;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Vector;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import DB_objects.*;
import Model.*;
import DB_objects.DB_customers;
import DB_objects.Details;
import DB_objects.detail_services;
import DB_objects.room;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXTimePicker;
import java.awt.BorderLayout;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author ultra
 */
public class ServiceController implements Initializable {

 Controlleur c = new Controlleur();

    private LocalDate datedeb;

    private LocalDate  datefin;

    private int id1;
            
    String beds;
    
    String price ;
    
    String type;
    
    private String nom;

    private String prenom;

    private String ci;

    private String sexe;

    private String phone;

    private String email;
    private float duration;
        @FXML
    private JFXCheckBox ch1;

          @FXML
    private AnchorPane test;
          
    @FXML
    private Label RFrom_D1;

    @FXML
    private JFXDatePicker datedeb1=null;

    @FXML
    private JFXCheckBox ch2;

    @FXML
    private Label RFrom_D11;

    @FXML
    private JFXDatePicker datedeb2;

    @FXML
    private JFXCheckBox ch3;

    @FXML
    private Label RFrom_D12;

    @FXML
    private JFXDatePicker datedeb3;

    @FXML
    private JFXCheckBox ch4;

    @FXML
    private JFXTimePicker T4;
        @FXML
    private JFXTimePicker T1;
            @FXML
    private JFXTimePicker T3;
                @FXML
    private JFXTimePicker T2;

    @FXML
    private JFXDatePicker datedeb4;

    @FXML
    void add_service(MouseEvent event) {

    }



   


    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    void clck3(ActionEvent event) throws IOException {
        
        if (ch1.isSelected() == false && ch2.isSelected() == false && ch3.isSelected() == false && ch4.isSelected() == false) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText(null);
             alert.setContentText("choose at least 1 service,please!");
            alert.showAndWait();
            return;
        } 
        if(( T1.getValue()!=null && datedeb1.getValue()!=null && ch1.isSelected()==false) ||
                     (T2.getValue()!=null && datedeb2.getValue()!=null && ch2.isSelected()==false) ||
                     (T3.getValue()!=null && datedeb3.getValue()!=null && ch3.isSelected()==false)||
                     (T4.getValue()!=null && datedeb4.getValue()!=null && ch4.isSelected()==false))
             {
                  Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText(null);
             alert.setContentText("Select a service ,please!");
            alert.showAndWait();
            return ;
             }
        if(( T1.getValue()==null && datedeb1.getValue()!=null) ||
                     (T2.getValue()==null && datedeb2.getValue()!=null) ||
                     (T3.getValue()==null && datedeb3.getValue()!=null)||
                     (T4.getValue()==null && datedeb4.getValue()!=null)){
                 Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText(null);
             alert.setContentText("choose the correspondant time,please!");
            alert.showAndWait();
            return ;
             }
             if (( T1.getValue()!=null && datedeb1.getValue()==null) ||
                     (T2.getValue()!=null && datedeb2.getValue()==null) ||
                     (T3.getValue()!=null && datedeb3.getValue()==null)||
                     (T4.getValue()!=null && datedeb4.getValue()==null) ) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText(null);
             alert.setContentText("choose the correspondant day,please!");
            alert.showAndWait();
            return ;
        }

                if (ch1.isSelected() == true) {
               if (datedeb1.getValue().compareTo(datefin)>0|| datedeb1.getValue().compareTo(datedeb)<0 )
               {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText(null);
             alert.setContentText("date service isn't compatible with reservation room date");
            alert.showAndWait();
             
             return ;
               }}
                if (ch2.isSelected() == true) {
                if((datedeb2.getValue().compareTo(datefin)>0|| datedeb2.getValue().compareTo(datedeb)<0 ))
                {
                 Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText(null);
             alert.setContentText("date service isn't compatible with reservation room date");
            alert.showAndWait();
             
             return ;}}
             if (ch3.isSelected() == true) {
                if((datedeb3.getValue().compareTo(datefin)>0|| datedeb3.getValue().compareTo(datedeb)<0 ))
                {
                 Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText(null);
             alert.setContentText("date service isn't compatible with reservation room date");
            alert.showAndWait();
             
             return ;}
             }
              if (ch4.isSelected() == true) {
                if((datedeb4.getValue().compareTo(datefin)>0|| datedeb4.getValue().compareTo(datedeb)<0 ))
                {
                 Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText(null);
             alert.setContentText("date service isn't compatible with reservation room date");
            alert.showAndWait();
             
             return ;}}
            int total = 0;
            float v1,v2,v3,v4 ;
            
            if (ch1.isSelected() == true) {
                v1= c.selectservice("price", 1);
                total += v1;
            }
            if (ch2.isSelected() == true) {
             v2= c.selectservice("price", 2);
                total += v2;
            }
            if (ch3.isSelected() == true) {
              v3= c.selectservice("price", 3);
                total += v3;
            }
            if (ch4.isSelected() == true) {
              v4= c.selectservice("price", 4);
                total += v4;
            }
            DB_customers costumer = new DB_customers(nom, prenom,
                     ci, sexe,
                    phone, email);
            int teste = 0;
            Vector<DB_customers> costumer1 = c.selectclient();

            for (int i = 0; i < costumer1.size(); i++) {
                DB_customers c = costumer1.get(i);
                if (c.getCi().compareToIgnoreCase(ci) == 0) {
                    teste = 1;
                    break;

                }
            }
////            ///inserer si il n'existe pas
            if (teste == 0) {
                c.insertclient(costumer);
            }
////
////            //insrer dans les autre table
            c.insertreservation(costumer, total + duration * c.selectprice(id1), ReservationController.id);
            Details D = new Details(c.getidreservation(), id1,
                     datedeb.toString(),
                     datefin.toString(),
                    duration * c.selectprice(id1));
            c.insertdetails(D);

            
            if (ch1.isSelected() == true) {
         detail_services DS1 = new detail_services(1,c.getiddetails(),datedeb1.getValue().toString(),T1.getValue().toString(),100);
                c.insererdetailsservice(DS1);
            }
            if (ch2.isSelected() == true) {
                
               
               detail_services DS2 = new detail_services(2,c.getiddetails(),datedeb2.getValue().toString(),T2.getValue().toString(),100);
                c.insererdetailsservice(DS2);
            }
            if (ch3.isSelected() == true) {
                
                 
                  detail_services DS3 = new detail_services(3,c.getiddetails(),datedeb3.getValue().toString(),T3.getValue().toString(),100);
                c.insererdetailsservice(DS3);
            }
            if (ch4.isSelected() == true) {
                
                  detail_services DS4 = new detail_services(4,c.getiddetails(),datedeb4.getValue().toString(),T4.getValue().toString(),100);
                c.insererdetailsservice(DS4);

            }
                         Alert alert = new Alert(Alert.AlertType.INFORMATION);
          alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
           alert.setContentText("the reservation was registered successfuly");

           alert.showAndWait();
                   FXMLLoader Loader = new FXMLLoader(getClass().getResource("/Cashier/reservation.fxml"));
    AnchorPane root =Loader.load();
        
            
            test.getChildren().setAll(root);
           
        
        
        

    }

    public void transfert(LocalDate datedeb, LocalDate datefin, int id1, String nom, String prenom, String ci, String sexe, String phone, String email, float duration,String type,String beds,String price) {
        this.datedeb = datedeb;
        this.datefin = datefin;
        this.id1 = id1;
        this.nom = nom;
        this.prenom = prenom;
        this.ci = ci;
        this.sexe = sexe;
        this.phone = phone;
        this.email = email;
        this.duration = duration;
        this.beds=beds;
        this.price=price;
        this.type=type;
    }
      @FXML
    void back(ActionEvent event) throws IOException {
       FXMLLoader Loader  = new FXMLLoader();
                   Loader.setLocation(getClass().getResource("/Cashier/reservation.fxml"));
                     AnchorPane root =Loader.load();
                   ReservationController ss =Loader.getController();
                  
      ss.transfert2(datedeb,datefin,nom,
              prenom
              , ci
              ,sexe
              ,phone
              ,email
              ,type,
              beds,
              price);
      
    
        test.getChildren().setAll(root);
    }



   
    
}
