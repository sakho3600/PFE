/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import com.sun.istack.internal.NotNull;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import modele.privs;
import java.util.Set;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlRootElement;


/**
 *
 * @author Mohammed Mehdi Sarray#
 */
@Entity
@Table(name = "Agent")
@PrimaryKeyJoinColumn(name = "Matricule")

public class Agent extends Personnel implements Serializable{
    
   private static final long serialVersionUID = 1L; 
   
   @Column(name = "MatriculeChef" )
   private int MatriculeChef; //matricule Chef hierarchique
   @NotNull
   @Column(name = "Departement")
   private String Departement; //departement de l'agent
   
        @OneToMany(mappedBy = "CodeMission")
            private Set<Mission> Missions;



    public Agent(int MatriculeChef, String Departement, int Matricule, String Pernom, String Nom , String mdp) {
        super(Matricule, Pernom, Nom , mdp);
        this.MatriculeChef = MatriculeChef;
        this.Departement = Departement;
       
        
    }

    public Set<Mission> getMissions() {
        return Missions;
    }

    public void setMissions(Set<Mission> Missions) {
        this.Missions = Missions;
    }

    public Agent(int MatriculeChef, String Departement, Set<Mission> Missions) {
        this.MatriculeChef = MatriculeChef;
        this.Departement = Departement;
        this.Missions = Missions;
    }

    public Agent(int MatriculeChef, String Departement, Set<Mission> Missions, int Matricule, String Pernom, String Nom, String MotDePasse) {
        super(Matricule, Pernom, Nom, MotDePasse);
        this.MatriculeChef = MatriculeChef;
        this.Departement = Departement;
        this.Missions = Missions;
    }

    public Agent() {
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

