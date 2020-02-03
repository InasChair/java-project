package Cashier;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author CHAIR Inas
 */
public class Cashier_panelController implements Initializable {
    double x,y;
    
    @FXML
 private AnchorPane bp; 
    private AnchorPane ap;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void home(MouseEvent event) {
        bp.setClip(ap);
    }

    @FXML
    private void reservation(MouseEvent event) {
        loadPage("reservation");
    }

    @FXML
    private void clients(MouseEvent event) {
        loadPage("clients");
    }

    @FXML
    private void rooms(MouseEvent event) {
    loadPage("rooms");
    }


    @FXML
    private void about_us(MouseEvent event) {
        loadPage("about_us");
    }
   
    private void loadPage(String page){
        Parent root =null;
        try{
            root=FXMLLoader.load(getClass().getResource(page+".fxml"));
      
        }catch(IOException ex ){
            Logger.getLogger(Cashier_panelController.class.getName()).log(Level.SEVERE,null,ex);
        }
        bp.setClip(ap);
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
    //stage.setMinHeight(true);
    }

    @FXML
    private void close(MouseEvent event) {
        Stage stage =(Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.close();
    }
    
}
