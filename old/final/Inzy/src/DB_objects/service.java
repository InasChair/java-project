/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DB_objects;

import Model.*;
import Administrator.*;
import java.sql.SQLException;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;

/**
 *
 * @author TPvPro
 */
public class service {
   
   Integer Id;
   String Name,Desc;
   Double Price;
   Button Update;

    public service(Integer id,String Name, String Desc, Double Price) {
        this.Id = id;
        this.Name = Name;

        this.Desc = Desc;
        this.Price = Price;
        this.Update = new Button("Update");
        this.Update.setOnAction((ActionEvent e)->{
           ObservableList<service> services_ = Controllers.Add_serviceController.serviceTable_.getSelectionModel().getSelectedItems();
           services_.forEach((service s) -> {
               serviceModel sm;
               sm = new serviceModel();
               try {
                   sm.Update(s);
               } catch (SQLException ex) {
              System.err.println(ex.toString());               }
            });
        }); 
    }

    public void setId(Integer Id) {
        this.Id = Id;
    }

    public void setName(String Name) {
        this.Name = Name;
    }


    public void setDesc(String Desc) {
        this.Desc = Desc;
    }

    public void setPrice(Double Price) {
        this.Price = Price;
    }

    public Integer getId() {
        return Id;
    }

    public String getName() {
        return Name;
    }



    public String getDesc() {
        return Desc;
    }

    public Double getPrice() {
        return Price;
    }

    public void setUpdate(Button Update) {
        this.Update = Update;
    }

    public Button getUpdate() {
        return Update;
    }
   
   
}
