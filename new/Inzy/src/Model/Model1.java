/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;
import DAO.*;
import DB_objects.*;
import java.sql.ResultSet;
import java.util.Vector;

 import java.sql.ResultSet;
import java.util.Date;
import java.util.Vector;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
/**
 *
 * @author ultra
 */
public class Model1 {
    
    public Model1(){}
    DataBase data =new DataBase();
    //////customer
  public boolean insertclient(DB_customers C) {
        
        String insertQuery = "INSERT INTO customers (nom, prenom, ci,sexe, phone, email)  VALUES("
                            + "'" + C.getNom()     + "', "
                            + "'" + C.getPrenom()     + "', "
                            + "'" + C.getCi()  + "', "
                          
                + "'" + C.getSexe()  + "',"
                + "'" + C.getPhone()  + "',"
                + "'" + C.getEmail()  + "' );";
       
        return (data.OPDML(insertQuery) == 0) ? false : true;
         
                
      
    }
  public int getidcustomer(DB_customers c)
  {
      int a=0;
      String requete = "select id from customers where ci='"+c.getCi()+ "' ;";
       ResultSet rs=data.SELECT(requete);
       try {
            rs.next();
          a=   rs.getInt("id");
            
        
            
        } catch (Exception ex) {
            data.excep(ex);
        } 
        
        return a;
    }
  public Vector<DB_customers> selectclient()
  {
      Vector<DB_customers> ss =new Vector<DB_customers>();
      String requete = "select * from customers  ";
       ResultSet rs=data.SELECT(requete);
       try {
            
            while(rs.next()) {
                
               DB_customers C= new DB_customers(rs.getString("nom"),rs.getString("prenom"),
                       rs.getString("ci"),rs.getString("sexe"),
                       rs.getString("phone"),rs.getString("email"));
              
                
                ss.add(C);
            }
            
            data.close();
            
        } catch (Exception ex) {
            data.excep(ex);
        }
       return ss;
  }
  ///////reservation
  public boolean insertreservation(DB_customers C,float amount,int b) 
  { 

      
        int a =getidcustomer(C);
        String insertQuery = "INSERT INTO reservations ( `employee_id`, `customer_id`, `reservation_date`, `total_amount`, `state`) VALUES("
                            +  b + ", "
                            +   a   + ", "
                            + "CURRENT_DATE() ,"+ amount+ ",'not payed' );";
                
        return (data.OPDML(insertQuery) == 0) ? false : true;
      
    }
  
  
  public int getidreservation()
  {
      int a=0;
      String requete = "select Max(id) max_id from reservations ";
       ResultSet rs=data.SELECT(requete);
       try {
            rs.next();
          a=   rs.getInt("max_id");
            
            data.close();
            
        } catch (Exception ex) {
            data.excep(ex);
        } 
        
        return a;
  }
  
  ///////details
  public boolean insertdetails(Details d)
  {
      int id =getidreservation();
              String insertQuery = "INSERT INTO details (`reservation_id`, `room_id`, `start_in`, `end_in`, `amount`) VALUES("
                            + + id+ ", "
                            +  + d.getRoom_id()     + ", "
                            + "'"+ d.getStart_in()+"', "
                            + "'"+ d.getEnd_in()+"', "
                      + "'"+d.getAmount()  + "');";
              
        return (data.OPDML(insertQuery) == 0) ? false : true;     

  }
  public int getiddetails()
  {
      int a=0;
      String requete = "select Max(id) max_id from details ";
       ResultSet rs=data.SELECT(requete);
       try {
            rs.next();
          a=   rs.getInt("max_id");
            
            data.close();
            
        } catch (Exception ex) {
            data.excep(ex);
        } 
        
        return a;
  }
  
  
  /////room
  public float selectprice(int room)
  {
      float a=0;
      String requete = "select price from rooms where id="+room+ " ;";
       ResultSet rs=data.SELECT(requete);
       try {
            rs.next();
          a=   rs.getFloat("price");
            
          
            
        } catch (Exception ex) {
            data.excep(ex);
        } 
        
        return a;
  }
//  public void updateroom(room M)
//  {
//       
//        
//        String updateQuery = "UPDATE rooms "
//                           + "SET floor = " + M.getFloor()+ ", "
//                             + " type = " + "'" +M.getType()+ "', "
//                             + " Beds = " +  M.getBed()+ ", "
//                             + " description = " + "'" +M.getDescription() + "', "
//                             + " price = " +  +M.getPrice()+ ", "
//                              + " state = " + "'" +M.getState()+"'"
//                           + "WHERE rooms.id = " + M.getId()+ ";";
//        
//      System.out.println(M.getState());
//       
//        
//        
//        data.OPDML(updateQuery);
//        data.close();
//    }
//  public room findRoom(int id)
//  {
//      room M=null;
//      String requete = "select * from  rooms where id='"+id+ "' ;";
//       ResultSet rs=null;
//              rs= data.SELECT(requete);
//       try {
//           if( rs.next()==true){
//         M=new room(rs.getInt("floor"),rs.getString("type"),rs.getInt("beds"),rs.getDouble("price"),rs.getString("description"),rs.getString("state"));
//            
//        M.setId(id);}
//            
//        } catch (Exception ex) {
//            data.excep(ex);
//        } 
//        
//        return M;
//  }
  ////detail_service
  public boolean insererdetailsservice(detail_services d)
  {
            int id =getidreservation();
              String insertQuery = "INSERT INTO detail_services (`service_id`, `detail_id`, `start_in`, `H_deb`, `amount`) VALUES("
                            + + d.getService_id()+ ", "
                            +  + d.getDetail_id()     + ", "
                            + "'"+ d.getStart_in()+"', "
                            + "'"+ d.getH_deb()+"', "
                      + "'"+d.getAmount()  + "');";
              
        return (data.OPDML(insertQuery) == 0) ? false : true; 
  }
  /////auths
  public int id_auths(String username)
  {
     int a=0;
      String requete = "select id from auths where username='"+username+ "' ;";
       ResultSet rs=data.SELECT(requete);
       try {
            rs.next();
          a=   rs.getInt("id");
            
        
            
        } catch (Exception ex) {
            data.excep(ex);
        } 
        
        return a;  
  }
      public float selectservice(String var,int id)
  {
      
        float a=0;
      String requete = "select "+var+" from services where id='"+id+"' ;";
       ResultSet rs=data.SELECT(requete);
       try {
            rs.next();
          a=   rs.getFloat("price");
            
        
            
        } catch (Exception ex) {
            data.excep(ex);
        } 
        
        return a;  
  }
      ///auths
  public ObservableList<db> usern()
  {
      
ObservableList<db> tableau = FXCollections.observableArrayList();

      String requete = "select username from auths  ;";
       ResultSet rs=data.SELECT(requete);
       try {
         while(rs.next())
         {
          tableau.add(new db(rs.getString("username")));
                          

         }
        
            
        
            
        } catch (Exception ex) {
            data.excep(ex);
        } 
        
       return tableau;
  }
       public boolean insertemp(String username,String password)
       {
           String insertQuery = "INSERT INTO auths (`username`, `password`) VALUES("
                            + "'"+username+"', "
                      + "'"+password + "');";
              
        return (data.OPDML(insertQuery) == 0) ? false : true;     

       }
       public void updatepwd(String username,String password)
       {
              String updateQuery = "UPDATE auths "
                           + "SET password = '"+password+ "' "
                           + "WHERE username='"+username+"';";
        
 
       
        
        
        data.OPDML(updateQuery);
        data.close();
       }
      
  }




