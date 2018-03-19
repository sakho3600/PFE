/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Persistence;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

/**
 *
 * @author HTOUM
 */

@Entity
@Table(name = "Mission")
public class Mission {
    @Id
   @GeneratedValue (strategy=GenerationType.AUTO)
   @Column(name ="CodeMission" , unique = true) // valeur unique dans la BD 
    private int CodeMission ;
   
      @ManyToOne
    @JoinColumn(name="Matricule") 
    private Agent agent;
      
      @Column( name="Intitule_Mission")
      private String Intitule_Mission;
      
      @Column( name="Objectif")
      private String Objectif;
      
      @Column( name="DateDeh")
    @Temporal(javax.persistence.TemporalType.DATE)
      private Date DateDeb;
      
      
     @Column( name="DateFin")
    @Temporal(javax.persistence.TemporalType.DATE)
      private Date DateFin;
      
    @Column( name="NbrJours")
      private int NbrJours;
    @Column (name="Kilometrage")
     private float Kilometrage;
 
    @Column (name="Type")
     private String type;
 
    @Column (name="Etat")
    private int Etat;    
    
    // Date now = new Date();
     
    @Temporal(TemporalType.TIME)
    @Column (name="DateDeCreation" )
    private Date DateDeCreation = new Date() ;
    
 
    @ManyToMany(cascade = CascadeType.ALL )
	@JoinTable(name = "LesVilleMission", joinColumns = { @JoinColumn(name = "CodeMission") }, inverseJoinColumns = { @JoinColumn(name = "Code_Postal")})
	public Set<ville> Les_villes;

      @ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "LesHebergementMission", joinColumns = { @JoinColumn(name = "CodeMission") }, inverseJoinColumns = { @JoinColumn(name = "CodeHeb") })
	public Set<Hebergement> Les_Hebergement;

    
      public Mission(int CodeMission, Agent agent, String Intitule_Mission, String Objectif, Date DateDeb, Date DateFin, int NbrJours, float Kilometrage, Set<ville> Les_villes, Set<Hebergement> Les_Hebergement) {
        this.CodeMission = CodeMission;
        this.agent = agent;
        this.Intitule_Mission = Intitule_Mission;
        this.Objectif = Objectif;
        this.DateDeb = DateDeb;
        this.DateFin = DateFin;
        this.NbrJours = NbrJours;
        this.Kilometrage = Kilometrage;
        this.Les_villes = Les_villes;
        this.Les_Hebergement = Les_Hebergement;
    }

    
    public Mission(int CodeMission, Agent agent, String Intitule_Mission, String Objectif, Date DateDeb, Date DateFin, int NbrJours, float Kilometrage, Set<ville> Les_villes) {
        this.CodeMission = CodeMission;
        this.agent = agent;
        this.Intitule_Mission = Intitule_Mission;
        this.Objectif = Objectif;
        this.DateDeb = DateDeb;
        this.DateFin = DateFin;
        this.NbrJours = NbrJours;
        this.Kilometrage = Kilometrage;
        this.Les_villes = Les_villes;
    }
    public Mission() {
    }

    public Mission(int CodeMission, Agent agent, String Intitule_Mission, String Objectif, Date DateDeb, Date DateFin, int NbrJours, float Kilometrage) {
        this.CodeMission = CodeMission;
        this.agent = agent;
        this.Intitule_Mission = Intitule_Mission;
        this.Objectif = Objectif;
        this.DateDeb = DateDeb;
        this.DateFin = DateFin;
        this.NbrJours = NbrJours;
        this.Kilometrage = Kilometrage;
    }

    public Mission(int CodeMission, Agent agent, String Intitule_Mission, String Objectif, Date DateDeb, Date DateFin, int NbrJours, float Kilometrage, String type, int Etat, Set<ville> Les_villes, Set<Hebergement> Les_Hebergement) {
        this.CodeMission = CodeMission;
        this.agent = agent;
        this.Intitule_Mission = Intitule_Mission;
        this.Objectif = Objectif;
        this.DateDeb = DateDeb;
        this.DateFin = DateFin;
        this.NbrJours = NbrJours;
        this.Kilometrage = Kilometrage;
        this.type = type;
        this.Etat = Etat;
        this.Les_villes = Les_villes;
        this.Les_Hebergement = Les_Hebergement;
    }

    public int getCodeMission() {
        return CodeMission;
    }

    public void setCodeMission(int CodeMission) {
        this.CodeMission = CodeMission;
    }

    public Agent getAgent() {
        return agent;
    }

    public void setAgent(Agent agent) {
        this.agent = agent;
    }

    public String getIntitule_Mission() {
        return Intitule_Mission;
    }

    public void setIntitule_Mission(String Intitule_Mission) {
        this.Intitule_Mission = Intitule_Mission;
    }

    public String getObjectif() {
        return Objectif;
    }

    public void setObjectif(String Objectif) {
        this.Objectif = Objectif;
    }

    public Date getDateDeb() {
        return DateDeb;
    }

    public void setDateDeb(Date DateDeb) {
        this.DateDeb = DateDeb;
    }

    public Date getDateFin() {
        return DateFin;
    }

    public void setDateFin(Date DateFin) {
        this.DateFin = DateFin;
    }

    public int getNbrJours() {
        return NbrJours;
    }

    public void setNbrJours(int NbrJours) {
        this.NbrJours = NbrJours;
    }

    public float getKilometrage() {
        return Kilometrage;
    }

    public void setKilometrage(float Kilometrage) {
        this.Kilometrage = Kilometrage;
    }

    public Set<ville> getLes_villes() {
        return Les_villes;
    }

    public void setLes_villes(Set<ville> Les_villes) {
        this.Les_villes = Les_villes;
    }

    public Set<Hebergement> getLes_Hebergement() {
        return Les_Hebergement;
    }

    public void setLes_Hebergement(Set<Hebergement> Les_Hebergement) {
        this.Les_Hebergement = Les_Hebergement;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getEtat() {
       if (Etat==1)
           return "OUI";
       else 
           return "NON";
    }

    public void setEtat(int Etat) {
        this.Etat = Etat;
    }

    public Date getDateDeCreation() {
        return DateDeCreation;
    }

    public void setDateDeCreation(Date DateDeCreation) {
        this.DateDeCreation = DateDeCreation;
    }

   

    

   

   

 
    
   
    }
    
    
    
