/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package login;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import Model.*;
import java.io.IOException;
import java.sql.SQLException;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;

/**
 * FXML Controller class
 *
 * @author ultra
 */
public class CashierController   implements Initializable {

   @FXML
    void CashierLogin(ActionEvent event) throws SQLException, IOException, Exception {
        String user,pass;
        Auth auth = new Auth();
        user = cusername.getText();
        pass = cpassword.getText();
      
        
        if (auth.User(user,pass).first()==true) {
            System.out.println("yassine");
    Stage s=new Stage();
  FXMLLoader Loader  = new FXMLLoader();
                   Loader.setLocation(getClass().getResource("/Cashier/reservation.fxml"));
                    Loader.getController();
       Parent root=(Parent)Loader.load();
          int id =C.id_auths(user);
          
          //ici
                 Scene scene = new Scene(root);
           scene.setFill(Color.TRANSPARENT);
          s.initStyle(StageStyle.TRANSPARENT);
        s.setScene(scene);
        s.show();
  Stage window =(Stage)((Node)event.getSource()).getScene().getWindow();
  window.close();
        }
        
        

    }
    Controlleur C = new Controlleur();
        @FXML
    private TextField cusername;

    @FXML
    private TextField cpassword;
 

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }   


    }
    
    