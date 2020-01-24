/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;
import java.sql.*;

/**
 * 
 * @author CHAIR Inas
 */
public class DaoBD {
    private String Url;
    private String Pilote;
    private String Login;
    private String Password;
    private Connection Connexion;
    
    public void Connect(){
        try{
            Class.forName(Pilote);
            Connexion=DriverManager.getConnection(Url,Login, Password);
        }catch(ClassNotFoundException ex){
            System.err.println("Problem de pilote...");
        }catch(SQLException ex ){
            System.err.println("Problem de connexion...");
        }
    }

    public String getUrl() {
        return Url;
    }

    public String getPilote() {
        return Pilote;
    }

    public String getLogin() {
        return Login;
    }

    public String getPassword() {
        return Password;
    }

    public Connection getConnexion() {
        return Connexion;
    }

    public void setUrl(String Url) {
        this.Url = Url;
    }

    public void setPilote(String Pilote) {
        this.Pilote = Pilote;
    }

    public void setLogin(String Login) {
        this.Login = Login;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public void setConnexion(Connection Connexion) {
        this.Connexion = Connexion;
    }

}
 