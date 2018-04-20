package beans;


import com.sun.istack.internal.NotNull;
import java.io.Serializable;
import java.util.List;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author HTOUM
 */

@Entity
@Table(name = "Departement")
public class Departement implements Serializable {
       private static final long serialVersionUID = 1L; 

     @Id
       @GeneratedValue (strategy=GenerationType.AUTO)
     @Column(name ="NumDep" , unique = true) // valeur unique dans la BD 
       private int NumDep;
     
     
     @Column (name="NomDep")
     private String NomDep;
     
        @OneToMany(mappedBy = "Matricule")
            private List<Agent> AgentAffecter;

      
       @ManyToOne
    @JoinColumn(name="Matriculed") 
    private Agent agentDirige;

    public Departement(int NumDep, String NomDep) {
        this.NumDep = NumDep;
        this.NomDep = NomDep;
    }

    public Departement(String NomDep) {
        this.NomDep = NomDep;
    }

    public Departement(int NumDep, String NomDep, List<Agent> AgentAffecter, Agent agentDirige) {
        this.NumDep = NumDep;
        this.NomDep = NomDep;
        this.AgentAffecter = AgentAffecter;
        this.agentDirige = agentDirige;
    }

    public Departement() {
    }

    public int getNumDep() {
        return NumDep;
    }

    public void setNumDep(int NumDep) {
        this.NumDep = NumDep;
    }

    public String getNomDep() {
        return NomDep;
    }

    public void setNomDep(String NomDep) {
        this.NomDep = NomDep;
    }

    public List<Agent> getAgentAffecter() {
        return AgentAffecter;
    }

    public void setAgentAffecter(List<Agent> AgentAffecter) {
        this.AgentAffecter = AgentAffecter;
    }

    public Agent getAgentDirige() {
        return agentDirige;
    }

    public void setAgentDirige(Agent agentDirige) {
        this.agentDirige = agentDirige;
    }

    public Departement(int NumDep, String NomDep, Agent agentDirige) {
        this.NumDep = NumDep;
        this.NomDep = NomDep;
        this.agentDirige = agentDirige;
    }
        
                           

}
