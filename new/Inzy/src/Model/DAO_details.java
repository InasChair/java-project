/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;


import DAO.*;
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
public class DAO_details {
    
     Connection Maconnexion ; 
       DaoBD dao ;
    public DAO_details() {
        dao = new DaoBD(); 
        dao.setPilote("com.mysql.jdbc.Driver");
        dao.setUrl("jdbc:mysql://localhost:3306/java_inzy");
        dao.setLogin("root");
        dao.setPassword("");
        dao.Connect();
        Maconnexion = dao.getConnexion();  
    }
  public ResultSet getdetails(String para)  throws SQLException
  {
  
  ResultSet rs = null;
  Statement statement=Maconnexion.createStatement();
  String  qr ="select r.id,nom,prenom,ci,phone,email,total_amount,max((case when sysdate() between start_in and end_in then d.room_id else null ";
          qr+=" end)) nr from customers c, reservations r ,details d where c.ci='"+para+"'AND c.id= r.customer_id and r.id=d.reservation_id";
  System.out.println(qr);
   rs= statement.executeQuery(qr);
   return rs;
  }
  public ResultSet getdetails2(String p )  throws SQLException
  {
   
  ResultSet rs = null;
  Statement statement=Maconnexion.createStatement();
  String  qr ="select detail_id,service_id,d.start_in,d.end_in,room_id from details d JOIN detail_services ds on";
          qr+=" d.id=ds.detail_id WHERE reservation_id="+Integer.parseInt(p);
  System.out.println(qr);
   rs = statement.executeQuery(qr);
   return rs;
  }
   public ResultSet getdetails3(String q)  throws SQLException
  {
   
   ResultSet rs = null;
   Statement statement=Maconnexion.createStatement();
   String  qr ="select a.id from auths a JOIN reservations r ON r.employee_id=a.id where r.id="+Integer.parseInt(q);
   System.out.println(qr);
   rs = statement.executeQuery(qr);
   return rs;
  }
    
     public ResultSet getdetails4(String d)  throws SQLException
  {
   
       ResultSet rs = null;
     Statement statement=Maconnexion.createStatement();
     String  qr ="select nom from detail_services ds join services s on ds.service_id=s.id where ds.detail_id="+Integer.parseInt(d);
     System.out.println(qr);
     rs = statement.executeQuery(qr);
     return rs;
  }
     public ResultSet getdetailss(String s) throws SQLException
  {
    ResultSet rs = null;
     Statement statement=Maconnexion.createStatement();
   String  qr = "select distinct r.id, c.nom, prenom, ci, phone ,total_amount,email, ";
           qr+= " d.id,ds.service_id,a.id as author,d.start_in,d.end_in, s.nom as servicen,d.room_id ";
           qr+= " from customers c left join actualreservations r on c.id=r.customer_id left"; 
           qr+= " join actualdetails d on r.id=d.reservation_id left join detail_services ds ";
           qr+= "on d.id=ds.detail_id left join services s on ds.service_id=s.id left join";
           qr+= " auths a on r.employee_id=a.id where ";
           qr+=" c.ci='"+s+"'";
     System.out.println(qr);
     rs = statement.executeQuery(qr);
     return rs;
  }
    
}
