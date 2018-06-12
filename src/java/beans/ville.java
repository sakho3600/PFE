/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 *
 * @author HTOUM
 */

@Entity
@Table(name = "ville")
public class ville implements Serializable {
@Id     
 @Column(name ="Code_Postal" ) // valeur unique dans la BD 
    private int Code_Postal;
    @Column(name ="Nom")
   private String Nom;
 
  
    public ville(int Code_Postal, String Nom) {
        this.Code_Postal = Code_Postal;
        this.Nom = Nom;
    }
    
  
  
    public ville() {
    }

    public int getCode_Postal() {
        return Code_Postal;
    }

    public void setCode_Postal(int Code_Postal) {
        this.Code_Postal = Code_Postal;
    }

    public String getNom() {
        return Nom;
    }

    public void setNom(String Nom) {
        this.Nom = Nom;
    }

    @Override
    public String toString() {
        return Nom+" Code_Postal: " + Code_Postal  ;
    }

    
      
}
