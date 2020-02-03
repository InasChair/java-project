/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DB_objects;

import java.util.Date;

/**
 *
 * @author ultra
 */
public class Details {
    private int id;
    private int reservation_id;
    private int room_id;
    private String start_in;
          private String  end_in;
          	 private float amount;

    public Details(int reservation_id, int room_id, String start, String end, float amount) {
        this.reservation_id = reservation_id;
        this.room_id = room_id;
        this.start_in = start;
        this.end_in = end;
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public int getReservation_id() {
        return reservation_id;
    }

    public int getRoom_id() {
        return room_id;
    }

    public String getStart_in() {
        return start_in;
    }

    public String getEnd_in() {
        return end_in;
    }

    public float getAmount() {
        return amount;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setReservation_id(int reservation_id) {
        this.reservation_id = reservation_id;
    }

    public void setRoom_id(int room_id) {
        this.room_id = room_id;
    }

    public void setStart_in(String start) {
        this.start_in = start;
    }

    public void setEnd_in(String end) {
        this.end_in = end;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }
    

 
                 

         
    
}
