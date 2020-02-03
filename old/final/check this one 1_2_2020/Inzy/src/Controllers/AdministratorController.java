/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Model.*;
import com.jfoenix.controls.JFXPasswordField;
import javafx.scene.control.TextField;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

/**
 * FXML Controller class
 *
 * @author ultra
 */
public class AdministratorController implements Initializable {
 @FXML
    private TextField admsername;

    @FXML
    private TextField aserial;

        @FXML
    private JFXPasswordField apassword;
//    
//    
   
       public void AdminLogin(Event e) throws SQLException, IOException{
    
    
      String user,pass,ser;
        Auth auth = new Auth();
        user = admsername.getText();
        pass = apassword.getText();
        ser = aserial.getText();
      
        
        if (auth.Admin(user,pass,ser).first()==true) {
           
    Stage s=new Stage();
              Parent root = FXMLLoader.load(getClass().getResource("/Administrator/admin_panel.fxml"));
                 Scene scene = new Scene(root);
           scene.setFill(Color.TRANSPARENT);
          s.initStyle(StageStyle.TRANSPARENT);
        s.setScene(scene);
        s.show();
  Stage window =(Stage)((Node)e.getSource()).getScene().getWindow();
  window.close();
        }
}
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }    
    
}
