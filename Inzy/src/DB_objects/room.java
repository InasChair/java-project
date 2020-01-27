/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DB_objects;


import Administrator.*;
import Model.*;
//import com.sun.org.glassfish.gmbal.Description;
import java.sql.SQLException;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;

/**
 *Price
 * @author TPvPro
 */
public class room {
    Integer id  ;
    Integer floor;
    String type;
    Integer bed;
    Double price;
   String description;
 String state;

    Button Update;
    private Iterable<room> RoomsController;
    public room(Integer id ,Integer floor, String type, Integer bed, Double price) {
        this.id = id;
        this.floor = floor;
        this.type = type;
        this.bed = bed;
        this.price = price;
      
        this.Update = new Button("Update");
        this.Update.setOnAction((ActionEvent e)->{
           ObservableList<room> rooms_ = Administrator.RoomsController.roomsTable_.getSelectionModel().getSelectedItems();
           rooms_.forEach((room r) -> {
               roomModel rm;
               rm = new roomModel();
               try {
                   rm.Update(r);
               } catch (SQLException ex) {
              System.err.println(ex.toString());               }
            });
        }); 
       
        
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setState(String state) {
        this.state = state;
    }

    public room(Integer floor, String type, Integer bed, Double price, String description, String state) {
        this.floor = floor;
        this.type = type;
        this.bed = bed;
        this.price = price;
        this.description = description;
        this.state = state;
    }

    public Integer getBed() {
        return bed;
    }


    public Integer getFloor() {
        return floor;
    }

    public Double getPrice() {
        return price;
    }

 public String getDescription() {
        return description;
    }
  public String getState() {
        return state;
    }
 

    public String getType() {
        return type;
    }

    public int getId() {
        return id;
    }

    public void setBed(Integer bed) {
        this.bed = bed;
    }

    public void setFloor(Integer floor) {
        this.floor = floor;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setPrice(Double price) {
        this.price = price;
    }


    public void setType(String type) {
        this.type = type;
    }
    public Button getUpdate() {
        return Update;
    }

    public void setUpdate(Button Update) {
        this.Update = Update;
    }
 public void changerstatus()
    {
        if (state.compareToIgnoreCase("reserve")==0)
        {
            this.state="libre";
        }
        if (state.compareToIgnoreCase("libre")==0)
               
        {
           this.state="reserve";
        }
    }
}
