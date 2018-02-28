/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

/**
 *
 * @author Mohammed Mehdi Sarray#
 */
@Entity
@Table(name = "Personnel")
@Inheritance(strategy = InheritanceType.JOINED)

public class Personnel implements Serializable{
   @Id
    private int Matricule;
    @Column(name ="Prenom")
   private String Pernom;
     @Column(name ="Nom")
    private String Nom;
      @Column(name ="MotDePasse")
    private String MotDePasse;

    public Personnel() {
    }

    public Personnel(int Matricule, String Pernom, String Nom, String MotDePasse) {
        this.Matricule = Matricule;
        this.Pernom = Pernom;
        this.Nom = Nom;
        this.MotDePasse = MotDePasse;
    }

    public int getMatricule() {
        return Matricule;
    }

    public void setMatricule(int Matricule) {
        this.Matricule = Matricule;
    }

    public String getPernom() {
        return Pernom;
    }

    public void setPernom(String Pernom) {
        this.Pernom = Pernom;
    }

    public String getNom() {
        return Nom;
    }

    public void setNom(String Nom) {
        this.Nom = Nom;
    }

    public String getMotDePasse() {
        return MotDePasse;
    }

    public void setMotDePasse(String MotDePasse) {
        this.MotDePasse = MotDePasse;
    }
    
    
}
