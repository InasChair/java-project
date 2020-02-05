/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;
import DAO.*;
import DB_objects.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author PROBOOK
 */
public class DAO_customers {
    Connection Maconnexion ; 
       DaoBD dao ;
    public DAO_customers() {
        dao = new DaoBD(); 
        dao.setPilote("com.mysql.jdbc.Driver");
        dao.setUrl("jdbc:mysql://localhost:3306/java_inzy");
        dao.setLogin("root");
        dao.setPassword("");
        dao.Connect();
        Maconnexion = dao.getConnexion();
        }
    public ObservableList<DB_customers> getcustomers(String param3) throws SQLException
     {
      System.out.println(param3);
      ObservableList<DB_customers> dt = FXCollections.observableArrayList(); 
      ResultSet rs ;
      Statement statement=Maconnexion.createStatement();
          String  qr = "select distinct nom, prenom, ci,d.room_id ";
                  qr+= " from customers c left join actualreservations r on c.id=r.customer_id left"; 
                  qr+= " join actualdetails d on r.id=d.reservation_id where 1 ";
		  qr+=param3;
         System.out.println(qr);
         rs = statement.executeQuery(qr);
        while(rs.next()){
            DB_customers c = new DB_customers(rs.getString("ci"),rs.getString("prenom"),rs.getString("nom"),rs.getString("d.room_id"));
            dt.add(c); 
         } 
          return dt;
      }
    public ObservableList<DB_customers> All() throws SQLException
     {
      
      ObservableList<DB_customers> dt = FXCollections.observableArrayList(); 
      ResultSet rs ;
      Statement statement=Maconnexion.createStatement();
          String  qr = "select distinct nom, prenom, ci,d.room_id ";
                  qr+= " from customers c left join actualreservations r on c.id=r.customer_id left"; 
                  qr+= " join actualdetails d on r.id=d.reservation_id where 1 ";
		
         System.out.println(qr);
         rs = statement.executeQuery(qr);
        while(rs.next()){
            DB_customers c = new DB_customers(rs.getString("ci"),rs.getString("prenom"),rs.getString("nom"),rs.getString("d.room_id"));
            dt.add(c); 
         } 
          return dt;
      }
}
    