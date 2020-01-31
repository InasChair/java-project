/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DB_objects;

/**
 *
 * @author CHAIR Inas
 */
public class DB_customers {
    private int id ;

  
     private String nom ;
    private String prenom;
    private String ci;
    private String roomid;
private String sexe;
    private String phone;
    private String email;

    public DB_customers(String nom, String prenom, String ci, String roomid) {
        this.nom = nom;
        this.prenom = prenom;
        this.ci = ci;
        this.roomid = roomid;
    }
   
    public DB_customers(String nom, String prenom, String ci, String sexe, String phone, String email) {
        this.nom = nom;
        this.prenom = prenom;
        this.ci = ci;
 
        this.sexe = sexe;
        this.phone = phone;
        this.email = email;
    }
  public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
  

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getCi() {
        return ci;
    }

 
    public String getSexe() {
        return sexe;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    
    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setCi(String ci) {
        this.ci = ci;
    }

 

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }
            
    
}
