/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;
import DAO.DaoBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


/**
 *
 * @author CHAIR Inas
 */
public class DB_customers {
    

    private Connection Con;
    private Statement St;
    private DaoBD dao;

    public  DB_customers() {
        dao = new DaoBD();
        dao.setPilote("com.mysql.jdbc.Driver");
        dao.setUrl("jdbc:mysql://localhost:3306/java_inzy");
        dao.setLogin("root");
        dao.setPassword("");
        dao.Connect();
        Con = dao.getConnexion();
    }

    public void InsererCustomers(int id, String nom,String prenom ,String ci ,String sexe,String phone,String email) {

        try {

            PreparedStatement Pst = Con.prepareStatement("insert into customers(id,  nom, prenom ,ci , sexe, phone, email) values (?,?,?,?,?,?,?)");
            Pst.setInt(1,id);
            Pst.setString(2, nom);
            Pst.setString(3, prenom);
            Pst.setString(4, ci);
            Pst.setString(5, sexe);
            Pst.setString(6, phone);
            Pst.setString(7, email);
            Pst.executeUpdate();
        } catch (SQLException ex) {
            System.err.println("Erreur dans la requete InsererDetailCommande " + ex.getMessage());
        }
    }

    public int CheckCreation(int Idcst) {
        try {
            PreparedStatement Pst = Con.prepareStatement("select count(*) from customers where id =" + Idcst);
            ResultSet res = Pst.executeQuery();
            if (res.next()) {
                int count = res.getInt(1);
                return count;
            }
        } catch (SQLException ex) {
            System.err.println("Erreur dans la requete CheckCreation " + ex.getMessage());
        }
        return 0;
    }

    public void Supprimercustomers(int id) {
        try {
            PreparedStatement Pst = Con.prepareStatement("delete from customers where id=?");
            Pst.setInt(1, id);
            Pst.executeUpdate();
        } catch (SQLException ex) {
            System.err.println("Erreur dans la requete Supprimer" + ex.getMessage());
        }
    }

 
}
