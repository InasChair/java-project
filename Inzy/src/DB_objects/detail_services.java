/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DB_objects;

/**
 *
 * @author ultra
 */
public class detail_services {
    private int id;
    private int service_id;
    private int detail_id;
    private String Start_in;
    private  String H_deb;
    private float amount;

    public detail_services(int service_id, int detail_id, String Start_in, String H_deb, float amount) {
        this.service_id = service_id;
        this.detail_id = detail_id;
        this.Start_in = Start_in;
        this.H_deb = H_deb;
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public int getService_id() {
        return service_id;
    }

    public int getDetail_id() {
        return detail_id;
    }

    public String getStart_in() {
        return Start_in;
    }

    public String getH_deb() {
        return H_deb;
    }

    public float getAmount() {
        return amount;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setService_id(int service_id) {
        this.service_id = service_id;
    }

    public void setDetail_id(int detail_id) {
        this.detail_id = detail_id;
    }

    public void setStart_in(String Start_in) {
        this.Start_in = Start_in;
    }

    public void setH_deb(String H_deb) {
        this.H_deb = H_deb;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }
    
    
}
