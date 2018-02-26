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
 * @author HTOUM
 */
@Entity
@Table(name = "SuperAdmin")
@PrimaryKeyJoinColumn(name = "Matricule")

public class SuperAdmin extends Personnel implements Serializable{

    public SuperAdmin(int Matricule, String Pernom, String Nom, String MotDePasse) {
        super(Matricule, Pernom, Nom, MotDePasse);
    }

    public SuperAdmin() {
    }
    
    
}
