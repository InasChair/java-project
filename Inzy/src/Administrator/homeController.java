/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Administrator;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 *
 * @author CHAIR Inas
 */
public class homeController implements Initializable {

    private AnchorPane bp; 
    private AnchorPane ap;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    private void home(MouseEvent event) {
        bp.setClip(ap);
    }

    private void reservation(MouseEvent event) {
        loadPage("reservation");
    }

    private void clients(MouseEvent event) {
        loadPage("clients");
    }

    private void rooms(MouseEvent event) {
    loadPage("rooms");
    }

    private void add_service(MouseEvent event) {
        loadPage("add_service");
    }

    private void statistics(MouseEvent event) {
        loadPage("statistics");
    }

    private void about_us(MouseEvent event) {
        loadPage("about_us");
    }
   
    private void loadPage(String page){
        Parent root =null;
        try{
            root=FXMLLoader.load(getClass().getResource(page+".fxml"));
      
        }catch(IOException ex ){
            Logger.getLogger(homeController.class.getName()).log(Level.SEVERE,null,ex);
        }
        bp.setClip(ap);
    }
}
