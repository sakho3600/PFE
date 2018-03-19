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
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapKey;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Mohammed Mehdi Sarray#
 */
@Entity
@Table(name = "Personnel")
@Inheritance(strategy = InheritanceType.JOINED) // Heritage

public class Personnel implements Serializable{
    
    
    private static final long serialVersionUID = 2L; 
    
   @Id
   @Column(name ="Matricule" , unique = true) // valeur unique dans la BD 
    private int Matricule;
    @Column(name ="Prenom")
   private String Pernom;
    @Column(name ="Nom")
    private String Nom;
    @Column(name ="MotDePasse")
    private String MotDePasse;
    @Column(name= "Directeur")
      private String Directeur;
    

        @OneToMany(mappedBy = "Matricule")
                  @MapKey(name="MatriculeChef")
            private Set<Agent> Agent;

    public Set<Agent> getAgent() {
        return Agent;
    }

    public void setAgent(Set<Agent> Agent) {
        this.Agent = Agent;
    }

         
         
    public Personnel() {
    }

    public Personnel(int Matricule, String Pernom, String Nom, String MotDePasse) {
        this.Matricule = Matricule;
        this.Pernom = Pernom;
        this.Nom = Nom;
        this.MotDePasse = MotDePasse;
    }

    public Personnel(int Matricule, String Pernom, String Nom, String MotDePasse, String Directeur) {
        this.Matricule = Matricule;
        this.Pernom = Pernom;
        this.Nom = Nom;
        this.MotDePasse = MotDePasse;
        this.Directeur = Directeur;
    }

    public String getMotDePasse() {
        return MotDePasse;
    }

    public void setMotDePasse(String MotDePasse) {
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

    public String getDirecteur() {
        return Directeur;
    }

    public void setDirecteur(String Directeur) {
        this.Directeur = Directeur;
    }

   
    
    
}
