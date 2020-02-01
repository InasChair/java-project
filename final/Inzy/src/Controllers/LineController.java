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
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.DatePicker;

/**
 * FXML Controller class
 *
 * @author TPvPro
 */
public class LineController implements Initializable {

    @FXML
    private LineChart<String, Double> linechart;
    @FXML
    private NumberAxis y;
    @FXML
    private CategoryAxis x;
    @FXML
    private DatePicker fromDate;
    @FXML
    private DatePicker toDate;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          try {
           Date date = new Date();
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        int year  = localDate.getYear();
        int month = localDate.getMonthValue();
        int day   = localDate.getDayOfMonth();
        String from,to;
        to = ""+year+"-"+month+"-"+day;
        year--;
        from = ""+year+"-"+month+"-"+day;
        linechart.setTitle("Income by days");
         x.setLabel("Days");
         y.setLabel("Income (DH)");
        
            linechart.getData().addAll(new ChartModel().getLineData(from,to));
        } catch (SQLException ex) {
              System.out.println(ex);
        } 
    }    

    @FXML
    private void getLine(ActionEvent event) {
        try {
           
            linechart.getData().addAll(new ChartModel().getLineData(fromDate.getValue().toString(),toDate.getValue().toString()));
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    @FXML
    private void getLineClean(ActionEvent event) {
        try {
            linechart.getData().clear();
            linechart.getData().addAll(new ChartModel().getLineData(fromDate.getValue().toString(),toDate.getValue().toString()));
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
       

}
