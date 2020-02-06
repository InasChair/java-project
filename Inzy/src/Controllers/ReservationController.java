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
import java.util.ResourceBundle;
import javafx.fxml.Initializable;


import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import static com.sun.org.apache.regexp.internal.RETest.test;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
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
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import static jdk.nashorn.internal.objects.NativeRegExp.test;

/**
 * FXML Controller class
 *
 * @author ultra
 */


public class ReservationController implements Initializable {
          Model1 c = new Model1();
                  
public static int id=0;
     @FXML
    private JFXTextField nom;

    @FXML
    private JFXTextField prenom;

    @FXML
    private ComboBox<String> sexe;

    @FXML
    private JFXTextField ci;
    
    
    @FXML
    private JFXTextField ci1;

    @FXML
    private JFXTextField email;

    @FXML
    private JFXTextField id1;
    
     @FXML
    private JFXTextField id11;
     
      @FXML
    private JFXTextField id12;
        @FXML
    private AnchorPane test;

    @FXML
    private Label RFrom_D;

    @FXML
    private JFXDatePicker datedeb;

    @FXML
    private JFXDatePicker datefin;
        @FXML
    private JFXTextField phone;
            
    int idf;
    public static  DB_rooms sliver =null;
  

    @FXML
  
        void click(ActionEvent event) {
        if(nom.getText().trim().isEmpty() 
                ||prenom.getText().trim().isEmpty() 
                || ci.getText().trim().isEmpty()
                || ci1.getText().trim().isEmpty()
                || sexe.getValue()=="" 
                || id1.getText().trim().isEmpty() 
                || id11.getText().trim().isEmpty()
                ||id12.getText().trim().isEmpty()
                || phone.getText().trim().isEmpty()
                || email.getText().trim().isEmpty() 
                ||datedeb.getValue()==null || datefin.getValue()==null)
        {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText(null);
             alert.setContentText("Fill all the blancks,Please!");
            alert.showAndWait();
            
        }
        else{
                
                if(datedeb.getValue().compareTo(LocalDate.now())<0)
                {
                     Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText(null);
             alert.setContentText("Wrong date !");
            alert.showAndWait();
            return;
                }
               if(datedeb.getValue().compareTo(datefin.getValue())>0){
                   Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText(null);
             alert.setContentText("Wrong end reservation,fix it please!");
            alert.showAndWait();
            return;
               }
               else{
            
      //creer le client
       DB_customers costumer=new DB_customers(
               nom.getText(),
               prenom.getText(),
               ci.getText(),
               sexe.getValue().toString(),
               phone.getText(),
               email.getText());
          //tester si le client existe deja dans la base
       int teste= 0;
               Vector<DB_customers> costumer1 = c.selectclient();
        
            for(int i =0;i<costumer1.size();i++) 
            {
                DB_customers c = costumer1.get(i);
                if(c.getCi().compareToIgnoreCase(ci.getText())==0)//go back
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
           float duration= ChronoUnit.DAYS.between(
                   datedeb.getValue(),
                   datefin.getValue());
                   c.insertreservation(costumer, duration * c.selectprice(idf), id);
                   Details D= new Details(c.getidreservation(),
                   idf,
                   datedeb.getValue().toString(),
                   datefin.getValue().toString(),
                   duration*c.selectprice(idf));
       c.insertdetails(D);     
      
        ci.setText("");
        ci1.setText("");
        email.setText("");
        id1.setText("");
        phone.setText("");
        prenom.setText("");
        sexe.setValue("");       
        nom.setText("");
        id1.setText("");
        id11.setText("");
        id12.setText("");
        datedeb.setValue(null);
        datefin.setValue(null);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("the reservation was registered successfuly");
            alert.showAndWait();
    
        }}}
        

    @FXML
    void click2(ActionEvent event) throws Exception{
     if(nom.getText().trim().isEmpty() 
             ||prenom.getText().trim().isEmpty()
             || ci.getText().trim().isEmpty()
             || ci1.getText().trim().isEmpty()
             || sexe.getValue()=="" 
             || phone.getText().trim().isEmpty()
             || email.getText().trim().isEmpty() 
             || id1.getText().trim().isEmpty() 
             || id11.getText().trim().isEmpty()
             || id12.getText().trim().isEmpty()
             || datedeb.getValue()==null 
             || datefin.getValue()==null)
        {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText(null);
            alert.setContentText("Fill all the blancks,Please!");
            alert.showAndWait();
     }else{
         if(datedeb.getValue().compareTo(LocalDate.now())<0)
                {
                     Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText(null);
             alert.setContentText("Wrong date !");
            alert.showAndWait();
            return;}
         
        FXMLLoader Loader = new FXMLLoader(getClass().getResource("/Cashier/service.fxml"));
        
        AnchorPane root =Loader.load();
        
            ServiceController hkproto = Loader.getController();
          hkproto.transfert(
                    datedeb.getValue(),
                    datefin.getValue(), 
                    idf, nom.getText(),
                    prenom.getText(),
                   // ci.getText(), 
                    ci.getText(),//go back
                    sexe.getValue(),
                    phone.getText(),
                    email.getText(),
                    ChronoUnit.DAYS.between(datedeb.getValue(),
                    datefin.getValue()),id1.getText(),id11.getText(),id12.getText());
                    test.getChildren().setAll(root);
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
                       
                        this.ci1.setText(c.getCi());
                        this.email.setText(c.getEmail());
                        this.nom.setText(c.getNom());
                        this.phone.setText(c.getPhone());
                        this.sexe.setValue(c.getSexe());
                        this.prenom.setText(c.getPrenom());
                        //this.nom.setText(c.getNom());
                       return;
                }
                else {
                    this.ci1.setText(ci.getText());
                    email.clear();
                    nom.clear();
                    phone.clear();
                    sexe.setCache(false);
                    
                    prenom.clear();
                    
                    
                   
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
                   { "Male", "Female",  };
    sexe.setItems(FXCollections.observableArrayList(s));
    
}
 

 
        @FXML
    void check(ActionEvent event) throws IOException, InterruptedException {
  Stage stage =new Stage();

    FXMLLoader Loader  = new FXMLLoader();
                   Loader.setLocation(getClass().getResource("/Cashier/rooms.fxml"));
                     Parent root =(Parent)Loader.load();
              roomController turn= Loader.getController(); 
              if(datedeb.getValue()!=null && datedeb.getValue()!=null)
              if(datedeb.getValue().compareTo(datefin.getValue())>0){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText(null);
             alert.setContentText("Wrong end reservation,fix it please!");
            alert.showAndWait();
            return;
               }
      turn.sendin(datedeb.getValue(),datefin.getValue());
                    
                    
        Scene scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setScene(scene);
        stage.showAndWait();
       
    id1.setText(sliver.getType());
       id11.setText(sliver.getBeds());
        id12.setText(sliver.getPrice());
        idf=Integer.parseInt(sliver.getNumber());
    }
    void transfert2( LocalDate datedeb,LocalDate datefin, String nom, String prenom, String ci, String sexe, String phone, String email,String type,String beds,String price)
    {
         this.datedeb.setValue(datedeb); 
        this.datefin.setValue(datefin);
       
       this.nom.setText(nom);
         this.prenom.setText(prenom);
        this.ci.setText(ci);
        this.sexe.setValue(sexe);
        this.phone.setText(phone);
        this.email.setText(email);
        
        this.id1.setText(type);
        this.id11.setText(beds);
        this.id12.setText(price);
        this.ci1.setText(ci);
    }
}
