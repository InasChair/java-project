package Cashier;

import java.awt.BorderLayout;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author CHAIR Inas
 */
public class Cashier_panelController implements Initializable {
    double x,y;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            //To change body of generated methods, choose Tools | Templates.
            loadPage("home.fxml");
        } catch (IOException ex) {
            Logger.getLogger(Cashier_panelController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
 private AnchorPane bp; 
    @FXML
    private AnchorPane ap;
     @FXML
  private AnchorPane xD;
   
     @FXML
    void reservation(ActionEvent event) throws IOException {
loadPage("reservation.fxml");
    }

    @FXML
    void rooms(ActionEvent event) throws IOException {
loadPage("rooms.fxml");
    }
      @FXML
    void about_us(ActionEvent event) throws IOException {
         loadPage("about_us.fxml");
    }

    @FXML
    void clients(ActionEvent event) throws IOException {
loadPage("clients.fxml");
    }

   

   

    @FXML
    void home(ActionEvent event) throws IOException {
       loadPage("home.fxml");

    }
   
    private void loadPage(String page) throws IOException{
     FXMLLoader Loader  = new FXMLLoader();
                   Loader.setLocation(getClass().getResource(page));
                   
     AnchorPane A=Loader.load();
    xD.getChildren().setAll(A);
    }

    @FXML
    private void dragged(MouseEvent event) {
           Stage stage =(Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setX(event.getSceneX()-x);
        stage.setY(event.getSceneX()-y);
    }

    @FXML
    private void pressed(MouseEvent event) {
        x=event.getSceneX();
        y=event.getSceneY();
    }

    @FXML
    private void min(MouseEvent event) {
    Stage stage =(Stage) ((Node) event.getSource()).getScene().getWindow();
    stage.setIconified(true);
    }

    @FXML
    private void close(MouseEvent event) {
        Stage stage =(Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.close();
    }
      @FXML
    void logout(ActionEvent event) throws IOException {
 
        
        Stage s=new Stage();
              FXMLLoader Loader  = new FXMLLoader();
                   Loader.setLocation(getClass().getResource("/login/Main.fxml"));
                  
       Parent root=(Parent)Loader.load();
      
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