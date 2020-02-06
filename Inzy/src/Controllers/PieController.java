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
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;

/**
 *
 * @author TPvPro
 */
public class PieController implements Initializable {
    
    @FXML
    private PieChart mypie;
   
  
    @FXML
    private DatePicker fromDate;
    @FXML
    private DatePicker toDate;
    
    
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
            mypie.setData(new ChartModel().getPieData(from, to));
        } catch (SQLException ex) {
            Logger.getLogger(PieController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    @FXML
       private void getPie(ActionEvent event) throws SQLException {
           if(toDate.getValue().compareTo(LocalDate.now())>0)
                {
                     Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText(null);
             alert.setContentText("Wrong date !");
            alert.showAndWait();
            return;
                }
        try {     
            mypie.setData(new ChartModel().getPieData(fromDate.getValue().toString(), toDate.getValue().toString()));
        } catch (SQLException ex) {
            Logger.getLogger(PieController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
