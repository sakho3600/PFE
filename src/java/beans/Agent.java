/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;


import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
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
    
   @Column(name="Fonction")
   private String Fonction;
      @OneToMany(mappedBy = "NumDep")
            private Set<Departement> agentDirige;

      
       @ManyToOne
    @JoinColumn(name="NumDep") 
    private Departement AgentAffecter;

       
        @OneToMany(mappedBy = "CodeMission")
            private Set<Mission> Missions;

    public Agent(int Matricule, String Pernom, String Nom, String MotDePasse) {
        super(Matricule, Pernom, Nom, MotDePasse);
    }

       
       
    public Agent(Set<Departement> agentDirige, Departement AgentAffecter, int Matricule, String Pernom, String Nom, String MotDePasse) {
        super(Matricule, Pernom, Nom, MotDePasse);
        this.agentDirige = agentDirige;
        this.AgentAffecter = AgentAffecter;
    }

    public Agent(Set<Departement> agentDirige, Departement AgentAffecter) {
        this.agentDirige = agentDirige;
        this.AgentAffecter = AgentAffecter;
    }

    public Agent() {
    }

    public Set<Departement> getAgentDirige() {
        return agentDirige;
    }

    public void setAgentDirige(Set<Departement> agentDirige) {
        this.agentDirige = agentDirige;
    }

    public Departement getAgentAffecter() {
        return AgentAffecter;
    }

    public void setAgentAffecter(Departement AgentAffecter) {
        this.AgentAffecter = AgentAffecter;
    }

    public Agent(Set<Departement> agentDirige, Departement AgentAffecter, Set<Mission> Missions, int Matricule, String Pernom, String Nom, String MotDePasse) {
        super(Matricule, Pernom, Nom, MotDePasse);
        this.agentDirige = agentDirige;
        this.AgentAffecter = AgentAffecter;
        this.Missions = Missions;
    }

    public Agent(Set<Departement> agentDirige, Departement AgentAffecter, Set<Mission> Missions) {
        this.agentDirige = agentDirige;
        this.AgentAffecter = AgentAffecter;
        this.Missions = Missions;
    }

    public Agent(String Fonction, Set<Departement> agentDirige, Departement AgentAffecter, Set<Mission> Missions) {
        this.Fonction = Fonction;
        this.agentDirige = agentDirige;
        this.AgentAffecter = AgentAffecter;
        this.Missions = Missions;
    }

    public Set<Mission> getMissions() {
        return Missions;
    }

    public void setMissions(Set<Mission> Missions) {
        this.Missions = Missions;
    }

    public String getFonction() {
        return Fonction;
    }

    public void setFonction(String Fonction) {
        this.Fonction = Fonction;
    }
   
}
