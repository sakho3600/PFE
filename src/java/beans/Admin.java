/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 *
 * @author Mohammed Mehdi Sarray#
 */
@Entity
@Table(name = "Admin")
@PrimaryKeyJoinColumn(name = "Matricule")

public class Admin extends Personnel implements Serializable{
private String Privileges;

    public Admin(String Privileges, int Matricule, String Pernom, String Nom, String MotDePasse) {
        super(Matricule, Pernom, Nom, MotDePasse);
        this.Privileges = Privileges;
    }

    public Admin() {
     
    }

    public Admin(int Matricule, String MotDePasse) {
        super(Matricule, MotDePasse);
    }
    

  

    public String getPrivileges() {
        return Privileges;
    }

    public void setPrivileges(String Privileges) {
        this.Privileges = Privileges;
    }

   
    
}
