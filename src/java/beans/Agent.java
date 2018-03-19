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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapKey;
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
    
   /*@Column(name = "MatriculeChef")
   private int MatriculeChef; //matricule Chef hierarchique
   */@NotNull
   @Column(name = "Departement")
   private String Departement; //departement de l'agent
   
        @OneToMany(mappedBy = "CodeMission")
            private Set<Mission> Missions;

        /*@OneToMany(mappedBy = "Matricule")
                  @MapKey(name="MatriculeChef")
            private Set<Personnel> Chef;
  */
         @ManyToOne
    @JoinColumn(name="MatriculeChef",referencedColumnName="Matricule")
                           

    private Personnel Personnel;
   
    public Agent(String Departement, Set<Mission> Missions, int Matricule, String Pernom, String Nom, String MotDePasse, String Directeur) {
        super(Matricule, Pernom, Nom, MotDePasse, Directeur);
        this.Departement = Departement;
        this.Missions = Missions;
    }

    public Personnel getPersonnel() {
        return Personnel;
    }

    public void setPersonnel(Personnel Personnel) {
        this.Personnel = Personnel;
    }

    public Agent(String Departement, Set<Mission> Missions, Personnel Personnel) {
        this.Departement = Departement;
        this.Missions = Missions;
        this.Personnel = Personnel;
    }

    public Agent(String Departement, Set<Mission> Missions, Personnel Personnel, int Matricule, String Pernom, String Nom, String MotDePasse) {
        super(Matricule, Pernom, Nom, MotDePasse);
        this.Departement = Departement;
        this.Missions = Missions;
        this.Personnel = Personnel;
    }

    public Agent(String Departement, Set<Mission> Missions, Personnel Personnel, int Matricule, String Pernom, String Nom, String MotDePasse, String Directeur) {
        super(Matricule, Pernom, Nom, MotDePasse, Directeur);
        this.Departement = Departement;
        this.Missions = Missions;
        this.Personnel = Personnel;
    }



        
        

/*
    public Agent(int MatriculeChef, String Departement, int Matricule, String Pernom, String Nom , String mdp) {
        super(Matricule, Pernom, Nom , mdp);
        this.MatriculeChef = MatriculeChef;
        this.Departement = Departement;
       
        
    }
*/
    public Set<Mission> getMissions() {
        return Missions;
    }

    public void setMissions(Set<Mission> Missions) {
        this.Missions = Missions;
    }

  /*  public Agent(int MatriculeChef, String Departement, Set<Mission> Missions) {
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

    public Agent(int MatriculeChef, String Departement, int Matricule, String Pernom, String Nom, String MotDePasse, String Directeur) {
        super(Matricule, Pernom, Nom, MotDePasse, Directeur);
        this.MatriculeChef = MatriculeChef;
        this.Departement = Departement;
    }
*/
    public Agent(String Departement, int Matricule, String Pernom, String Nom, String MotDePasse) {
        super(Matricule, Pernom, Nom, MotDePasse);
        this.Departement = Departement;
    }

    public Agent() {
    }
    
/*
    public int getMatriculeChef() {
        return MatriculeChef;
    }

    public void setMatriculeChef(int MatriculeChef) {
        this.MatriculeChef = MatriculeChef;
    }
*/
    public String getDepartement() {
        return Departement;
    }

    public void setDepartement(String Departement) {
        this.Departement = Departement;
    }

    @Override
    public String toString() {
        return "Agent{" + "Matricule= " + super.getMatricule() + "Nom= "+ super.getNom() + '}';
    }

  


   
    
    
   
}

