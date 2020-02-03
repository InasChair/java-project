/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import DAO.DaoBD;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Month;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;

/**
 *
 * @author TPvPro
 */
public class ChartModel {
        private Connection Con;
    private Statement St;
    private DaoBD dao;
    public ChartModel() {
        dao = new DaoBD();
        dao.setPilote("com.mysql.jdbc.Driver");
        dao.setUrl("jdbc:mysql://localhost:3306/java_inzy");
        dao.setLogin("root");
        dao.setPassword("");
        dao.Connect();
        Con = dao.getConnexion();
    }
    public ObservableList<PieChart.Data> getPieData(String from , String to) throws SQLException{
       ObservableList<PieChart.Data> PieData = FXCollections.observableArrayList();
       ResultSet Res = null;
       String Query  = "select services.nom as name, sum(detail_services.amount) as value from details , "
                + "detail_services,services where services.id = detail_services.service_id and"
                + " details.id = detail_services.detail_id and details.start_in >='"+from+"' and details.end_in <= '"+to+"' group by services.id";
       St = Con.createStatement(); 
        Res = St.executeQuery(Query);
       while(Res.next()){
              PieData.add(new PieChart.Data(Res.getString("name"),Res.getDouble("value")));
        }
     return PieData;
    }
    public XYChart.Series getBarData(String year) throws SQLException{
     XYChart.Series Data = new XYChart.Series<>();
       ResultSet Res = null;
       String Query  = "SELECT MONTH(reservation_date) as month , sum(reservations.total_amount) as total  from reservations where YEAR(reservation_date) = '"+year+"' GROUP BY MONTH(reservation_date)";
       St = Con.createStatement(); 
        Res = St.executeQuery(Query);
        int i = 1;
       while(i<13){
           if(Res.next() && i == Res.getInt("month")){
                Data.getData().add(new XYChart.Data(Month.of(i).name(),Res.getDouble("total")));
                Res.next();
           }   
           else {
               Data.getData().add(new XYChart.Data(Month.of(i).name(),0.0));
               Res.previous();
           }
           i++;
       }
       Data.setName("Income formonths of "+year);
     return Data;
    }
    public XYChart.Series getLineData(String fromDate , String toDate) throws SQLException{
     XYChart.Series Data = new XYChart.Series<>();
       ResultSet Res = null;
       String Query  = "select reservation_date as d , sum(total_amount) as total from reservations where reservation_date <= '"+toDate+"' and reservation_date >= '"+fromDate+"' GROUP by reservation_date";
       St = Con.createStatement(); 
        Res = St.executeQuery(Query);
       while(Res.next())
           Data.getData().add(new XYChart.Data(Res.getString("d"),Res.getDouble("total")));
     Data.setName("Income from "+fromDate+" to "+toDate);
     return Data;
    }
    public XYChart.Series getLineDataByMonth(String fromDate , String toDate) throws SQLException{
     XYChart.Series Data = new XYChart.Series<>();
       ResultSet Res = null;
       String Query  = "SELECT sum(reservations.total_amount) as total ,EXTRACT(YEAR FROM reservation_date) "
               + "as year ,EXTRACT(MONTH FROM reservation_date) as month FROM reservations"
               + "  where reservation_date <= '"+toDate+"' and reservation_date >= '"+fromDate+"' group by EXTRACT(YEAR_MONTH FROM reservation_date)";
       St = Con.createStatement(); 
        Res = St.executeQuery(Query);
       while(Res.next())
           Data.getData().add(new XYChart.Data(Res.getString("year")+"-"+Res.getString("month"),Res.getDouble("total")));
     Data.setName("Income from "+fromDate+" to "+toDate);
     return Data;
    }
    public  ObservableList<String> GetYears() throws SQLException{
          ObservableList<String> Years = FXCollections.observableArrayList();
       ResultSet Res = null;
       String Query  = "select DISTINCT EXTRACT(YEAR FROM reservation_date) as year FROM reservations";
       St = Con.createStatement(); 
        Res = St.executeQuery(Query);
       while(Res.next()){
              Years.add(Res.getString("year"));
        }
     return Years;
    }
}
