/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cashier;

import DAO.*;
import Model.*;
import DB_objects.*;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;


import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.time.temporal.ChronoUnit;
import java.util.ResourceBundle;
import java.util.Vector;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ultra
 */


public class ReservationController implements Initializable {
          Controlleur c = new Controlleur();
     @FXML
    private JFXTextField nom;

    @FXML
    private JFXTextField prenom;

    @FXML
    private ComboBox<String> sexe;

    @FXML
    private JFXTextField ci;

    @FXML
    private JFXTextField email;

    @FXML
    private JFXTextField id1;

    @FXML
    private Label RFrom_D;

    @FXML
    private JFXDatePicker datedeb;

    @FXML
    private JFXDatePicker datefin;
        @FXML
    private JFXTextField phone;
            

  

    @FXML
    void click(ActionEvent event) {
        
         
if(nom.getText().trim().isEmpty() ||
                prenom.getText().trim().isEmpty() || ci.getText().trim().isEmpty()
                || sexe.getValue()==""
                ||  phone.getText().trim().isEmpty()
                    || email.getText().trim().isEmpty() || id1.getText().trim().isEmpty()
                ||datedeb.getValue()==null || datefin.getValue()==null)
        {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText(null);
             alert.setContentText("Fill all the blancks,Please!");
            alert.showAndWait();
            
        }
        else{
               if(datedeb.getValue().compareTo(datefin.getValue())<0){
                   Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText(null);
             alert.setContentText("Wrong end reservation.fix it please!");
            alert.showAndWait();
            return;
               }
               else{
                   
               
              room M=null;
                M=  c.findRoom(Integer.parseInt(id1.getText()));
                if(M==null || M.getState().compareTo("libre")!=0)
                {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText(null);
             alert.setContentText("The room  isn't available,Please chose another one!");
            alert.showAndWait();
            return;
                    
                }
                else
         
                {
        
         
      //creer le client
       DB_customers costumer=new DB_customers(nom.getText(),prenom.getText()
               ,ci.getText(),sexe.getValue().toString(),
               phone.getText(),email.getText());
          //tester si le client existe deja dans la base
       int teste= 0;
               Vector<DB_customers> costumer1 = c.selectclient();
        
            for(int i =0;i<costumer1.size();i++) 
            {
                DB_customers c = costumer1.get(i);
                if(c.getCi().compareToIgnoreCase(ci.getText())==0)
                {
                       teste=1;
                       break;
                        
                }
            }
       ///inserer si il n'existe pas
      if(teste==0)
      {
           c.insertclient(costumer);}
              
           //insrer dans les autre table
           float duration= ChronoUnit.DAYS.between(datedeb.getValue(),datefin.getValue());
          
          
           c.insertreservation(costumer, duration * c.selectprice(Integer.parseInt(id1.getText())), 1);
           Details D= new Details(c.getidreservation(),Integer.parseInt(id1.getText())
                   ,datedeb.getValue().toString()
                   ,datefin.getValue().toString(),
                   duration*c.selectprice(Integer.parseInt(id1.getText())));
           c.insertdetails(D);
         room ss=c.findRoom(Integer.parseInt(id1.getText()));
        
         ss.changerstatus();
        
         c.updateroom(ss);
         ci.setText("");
        
        email.setText("");
        id1.setText("");
        phone.setText("");
        prenom.setText("");
           sexe.setValue("");       
             nom.setText("");
             
             Alert alert = new Alert(Alert.AlertType.INFORMATION);
alert.setTitle("Information Dialog");
alert.setHeaderText(null);
alert.setContentText("the reservation was registered successfuly");

alert.showAndWait();
    
        }}}
        
        
    }
    @FXML
    void click2(ActionEvent event) throws Exception{
     if(nom.getText().trim().isEmpty() ||
                prenom.getText().trim().isEmpty() || ci.getText().trim().isEmpty()
                
                || sexe.getValue()=="" ||  phone.getText().trim().isEmpty()
                    || email.getText().trim().isEmpty() || id1.getText().trim().isEmpty()
                ||datedeb.getValue()==null || datefin.getValue()==null)
        {
             Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText(null);
             alert.setContentText("Fill all the blancks,Please!");
            alert.showAndWait();
     }else{
         
         FXMLLoader Loader = new FXMLLoader(getClass().getResource("service.fxml"));
         Parent root =(Parent)Loader.load();
        
            ServiceController hkproto = Loader.getController();
           hkproto.transfert(datedeb.getValue().toString(), datefin.getValue().toString(), 
                   Integer.parseInt(id1.getText()), nom.getText(),
                   prenom.getText(), ci.getText(), sexe.getValue(),
                   phone.getText(), email.getText(),ChronoUnit.DAYS.between(datedeb.getValue(),datefin.getValue()));
                Scene scene1 =new Scene(root);
        Stage window =(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene1);
        window.show();
        
     }
    
    }
          @FXML
    void clientexiste1(MouseEvent event) {
        Vector<DB_customers> costumer = c.selectclient();
        
            for(int i =0;i<costumer.size();i++) 
            {
                DB_customers c = costumer.get(i);
                if(c.getCi().compareToIgnoreCase(ci.getText())==0)
                {
                       
                       
                        this.email.setText(c.getEmail());
                        this.nom.setText(c.getNom());
                        this.phone.setText(c.getPhone());
                        this.sexe.setValue(c.getSexe());
                        this.prenom.setText(c.getPrenom());
                        this.nom.setText(c.getNom());
                        return ;
                }
            }
            

    }


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        sexe.setValue("");
              String s[] = 
                   { "Male", "Femel",  };
    sexe.setItems(FXCollections.observableArrayList(s));
    
}
}