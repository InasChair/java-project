/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author CHAIR Inas
 */
public class StatisticsController implements Initializable {
    

    @FXML
    private AnchorPane sT;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            loadPage("/Administrator/Pie.fxml");
        } catch (IOException ex) {
            Logger.getLogger(StatisticsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void Pie(ActionEvent event) throws IOException {
        loadPage("/Administrator/Pie.fxml");
    }

    @FXML
    private void Bar(ActionEvent event) throws IOException {
        loadPage("/Administrator/Bar.fxml");
    }

    @FXML
    private void Line(ActionEvent event) throws IOException {
        loadPage("/Administrator/Line.fxml");
    }
    
     private void loadPage(String page) throws IOException{
     FXMLLoader Loader  = new FXMLLoader();
                   Loader.setLocation(getClass().getResource(page));
                   
     AnchorPane A=Loader.load();
    sT.getChildren().setAll(A);
    }
}
