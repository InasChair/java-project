/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author ultra
 */
public class DataBase {
    public final static String USER = "root";
    public final static String PWD = "";
    public final static String DRIVER = "com.mysql.jdbc.Driver";
    public final static String URL = "jdbc:mysql://localhost:3306/java_inzy";
    
    public static Connection C;
    public static Statement S;
    public static ResultSet RS;
     
    public DataBase(){};
       public static void Connexion() throws ClassNotFoundException, SQLException {
        Class.forName(DRIVER);
        C = DriverManager.getConnection(URL, USER, PWD);
    }
         public static void close()  {
        
        if (RS != null) {
            try {
                RS.close();
            } catch (SQLException ex) {
                System.err.println("Erreur : fermeture de Resultset");
                excep(ex);
            }
        }
        
        if (S != null) {
            try {
                S.close();
            } catch (SQLException ex) {
                System.err.println("Erreur : fermeture de Statement");
                excep(ex);
            }
        }
        
        if (C != null) {
            try {
                C.close();
            } catch (SQLException ex) {
                System.err.println("Erreur : fermeture de Connection");
                excep(ex);
            }
        }
    }
    
    
     public static void excep(Exception e) {
        
        System.err.println("Error Message : " + e);
        
        if (e instanceof SQLException) {
            System.err.println("Error Code : " + ((SQLException)e).getErrorCode());
            System.err.println("SQL State : " + ((SQLException)e).getSQLState());
        }
        
    }
     
     
     
       public static ResultSet SELECT(String requete) {
        
        try {
            Connexion();
            S = C.createStatement();
            RS = S.executeQuery(requete);
            
        } catch (Exception e) {
            
            System.err.println("Error Message :problem in SELECT() methode.");
            excep(e);
        } 
            
        return RS;
    }
        public static int OPDML(String requete) {
        try {
            Connexion();
            S = C.createStatement();
            return S.executeUpdate(requete);
            
        } catch (Exception e) {
            
            System.err.println("Error Message :problem in OPDML() methode.");
            excep(e);
        } 
        return 0;
    }
     
}
