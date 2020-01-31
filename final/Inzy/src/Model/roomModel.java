
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
public class roomModel {
    private Connection Con;
    private Statement St;
    private DaoBD dao;
    public roomModel() {
       dao = new DaoBD();
        dao.setPilote("com.mysql.jdbc.Driver");
        dao.setUrl("jdbc:mysql://localhost:3306/java_inzy");
        dao.setLogin("root");
        dao.setPassword("");
        dao.Connect();
        Con = dao.getConnexion();
    }
   
    public void Create(room r,String desc) throws SQLException{
         St = Con.createStatement();
         String Req ; 
         Req = "Insert into rooms values ("+r.getFloor()+","
                 +r.getType()+","
                 +r.getBed()+","
                 +desc+","
                 +r.getPrice()+"'not available')";
         St.executeUpdate(Req);
    }
    
    public ObservableList<room> Rooms(Integer floor, String type, Integer bed, Double price, String State) throws SQLException{
       ObservableList<room> rooms = FXCollections.observableArrayList();
       ResultSet Res = null;
       String Query = "Select id,floor,beds,type,state,price from rooms where id ";
       St = Con.createStatement();
       if(!"".equals(type))Query += " and  type = '"+type+"'";
       if(floor != 0)
        Query += " and  floor = "+floor;
       
       if(bed != 0)
        Query += " and  beds = "+floor;
       
       if(price != 0)
        Query += " and price <= "+price;
       
        if(!State.equals(""))
            Query += " and  state = '"+State+"'";

        Res = St.executeQuery(Query);
        while (Res.next()) {
           rooms.add(new room(
           Res.getInt("id"),Res.getInt("floor"),
           Res.getString("type"),Res.getInt("beds"),
           Res.getDouble("price")
           ));
        }
        return rooms;
    }
        public void Update(room r) throws SQLException{
         St = Con.createStatement();
         String Req ;
        
         Req = "UPDATE rooms SET floor= "+r.getFloor()+",type= '"+r.getType()
                 +"',beds= "+r.getBed()+",price= "+r.getPrice()
                 +" WHERE id = "+r.getId();
         
        St.executeUpdate(Req);
    }
}
