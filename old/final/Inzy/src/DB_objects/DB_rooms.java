/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DB_objects;

/**
 *
 * @author PROBOOK
 */
public class DB_rooms {
    private String number;
    private String floor;
    private String type;
    private String beds;
    private String price;
    private String av;

    public String getAv() {
        return av;
    }

    public void setAv(String av) {
        this.av = av;
    }

    
    public DB_rooms(String n, String f, String t, String b, String p,String av)
    {
     this.number = n;
     this.floor = f;
     this.type = t;
     this.beds = b;
     this.price = p;
     this.av = av;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBeds() {
        return beds;
    }

    public void setBeds(String beds) {
        this.beds = beds;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
    
}
