
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
 * @author TPvPro
 */
public class serviceModel {
    private Connection Con;
    private Statement St;
    private DaoBD dao;
    public serviceModel() {
        dao = new DaoBD();
        dao.setPilote("com.mysql.jdbc.Driver");
        dao.setUrl("jdbc:mysql://localhost:3306/java_inzy");
        dao.setLogin("root");
        dao.setPassword("");
        dao.Connect();
        Con = dao.getConnexion();
    }
   
    public void Create(service s) throws SQLException{
         St = Con.createStatement();
         String Req ;
      
         Req = "Insert into services values (NULL,'"+s.getName()+"',"
                 +s.getPrice()+",'available','"
                 +s.getDesc() +"') ";
        
         St.executeUpdate(Req);
    }
    public ObservableList<service> Services() throws SQLException{
       ObservableList<service> services = FXCollections.observableArrayList();
       ResultSet Res = null;
       String Query = "Select * from services where id ";
       St = Con.createStatement();
        Res = St.executeQuery(Query);
        while (Res.next()) {
           services.add(new service(
                   Res.getInt("id"),
                   Res.getString("nom"),
                  
                   Res.getString("description"),
                    Res.getDouble("price")
           ));
        }
        return services;
    }  
    public void Update(service s) throws SQLException{
         St = Con.createStatement();
         String Req="";
         Req = "UPDATE services SET nom= '"+s.getName()+"',price= "+s.getPrice()
               +",description = '"+s.getDesc()+"' WHERE id = "+s.getId();
        St.executeUpdate(Req);
    }
}
