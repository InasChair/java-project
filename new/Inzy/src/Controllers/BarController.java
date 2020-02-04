/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;
import Model.ChartModel;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;

/**
 * FXML Controller class
 *
 * @author TPvPro
 */
public class BarController implements Initializable {

    @FXML
    private BarChart mubar;
    @FXML 
    CategoryAxis x;
    @FXML 
    NumberAxis y;
   
    private DatePicker fromDate;
    private DatePicker toDate;
    @FXML
    private ComboBox<String> Year;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        try {

            Year.setItems(new ChartModel().GetYears());
            Date date = new Date();
            LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            int year  = localDate.getYear();
            
                mubar.getData().addAll(new ChartModel().getBarData((""+localDate.getYear())));
  
        } catch (SQLException ex) {
            Logger.getLogger(BarController.class.getName()).log(Level.SEVERE, null,ex);
        }
       
    }    
    @FXML
    private void getPie(ActionEvent event) {
          try {
              mubar.getData().clear();
            mubar.getData().addAll(new ChartModel().getBarData(Year.getValue()));
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    @FXML
    private void getNewPie(ActionEvent event) {
                  try {
             
            mubar.getData().addAll(new ChartModel().getBarData(Year.getValue()));
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    
}
