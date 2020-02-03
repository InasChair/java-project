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

/**
 *
 * @author TPvPro
 */
public class Auth {
    private Connection Con;
    private Statement St;
    private DaoBD dao;

    public Auth() {
        dao = new DaoBD();
        dao.setPilote("com.mysql.jdbc.Driver");
        dao.setUrl("jdbc:mysql://localhost:3306/java_inzy");
        dao.setLogin("root");
        dao.setPassword("");
        dao.Connect();
        Con = dao.getConnexion();
    }
    public ResultSet User(String user,String pass){
          ResultSet Res = null;
          try {
           St = Con.createStatement();
            Res = St.executeQuery("SELECT * FROM `auths` WHERE username ='"+user +"' and password = '"+pass+"'");
        } catch (SQLException ex) {
             System.out.println("Erreur dans la requete select ou ST , " + ex.getMessage());
        }
         return   Res;
    }

    public ResultSet Admin(String user, String pass, String ser) {
       ResultSet Res = null;
          try {
            St = Con.createStatement();
            Res = St.executeQuery("SELECT * FROM `auths` WHERE username = '"+user +"'and password = '"+pass +"'and serial_num = '"+ser+"'");
        } catch (SQLException ex) {
             System.out.println("Erreur dans la requete select ou ST , " + ex.getMessage());
        }
         return   Res;
    }
    public void UpdateSerial(int id , String ser) throws SQLException{
         St = Con.createStatement();
         String Req ; 
         Req = "update auths set serial_num = "+ser+" where ID ="+id ;
         St.executeUpdate(Req);
    }
    public void UpdatePassword(int id , String oldP,String newP) throws SQLException{
         St = Con.createStatement();
        if(St.executeQuery("select * from auths where id = "+id+"and password = "+oldP).first()){
            String Req ; 
            Req = "update auths set password = "+newP+" where ID ="+id ;
            St.executeUpdate(Req);
        }
    }
        public void UpdateUsername(int id , String oldU,String newU) throws SQLException{
         St = Con.createStatement();
        if(St.executeQuery("select * from auths where id = "+id+"and username = "+oldU).first()){
            String Req ; 
            Req = "update auths set password = "+newU+" where ID ="+id ;
            St.executeUpdate(Req);
        }
    }
    
}