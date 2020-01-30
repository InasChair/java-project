/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Administrator;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Side;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.stage.Stage;

/**
 *
 * @author TPvPro
 */
public class Piechart extends Application {
 
//    private final ObservableList<PieChart.Data> detaills =  FXCollections.observableArrayList();
//    private PieChart piechart;
    @Override
    public void start(Stage stage) throws Exception {
     
        Parent root = FXMLLoader.load(getClass().getResource("Line.fxml"));
         
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
