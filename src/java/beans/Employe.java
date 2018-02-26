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
@Table(name = "Employe")
@PrimaryKeyJoinColumn(name = "Matricule")

public class Employe extends Personnel implements Serializable{
   private int MatriculeChef;
   private String Departement;

    public Employe(int MatriculeChef, String Departement, int Matricule, String Pernom, String Nom, String MotDePasse) {
        super(Matricule, Pernom, Nom, MotDePasse);
        this.MatriculeChef = MatriculeChef;
        this.Departement = Departement;
    }

    public int getMatriculeChef() {
        return MatriculeChef;
    }

    public void setMatriculeChef(int MatriculeChef) {
        this.MatriculeChef = MatriculeChef;
    }

    public String getDepartement() {
        return Departement;
    }

    public void setDepartement(String Departement) {
        this.Departement = Departement;
    }
   
}
