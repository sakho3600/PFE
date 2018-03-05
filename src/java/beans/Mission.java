/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author HTOUM
 */

@Entity
@Table(name = "Mission")
public class Mission {
    @Id
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
      
   @Column (name="NombrePersAcc")
   private int NombrePersAcc;
   
      @Column( name="Destination")
      private String Destination;
   
         @Column( name="NbrJours")
      private int NbrJours;

    public Mission() {
    }

    public Mission(int CodeMission, Agent agent, String Intitule_Mission, String Objectif, Date DateDeb, Date DateFin, int NombrePersAcc, String Destination, int NbrJours) {
        this.CodeMission = CodeMission;
        this.agent = agent;
        this.Intitule_Mission = Intitule_Mission;
        this.Objectif = Objectif;
        this.DateDeb = DateDeb;
        this.DateFin = DateFin;
        this.NombrePersAcc = NombrePersAcc;
        this.Destination = Destination;
        this.NbrJours = NbrJours;
    }

    public Mission(int CodeMission, Agent agent, String Intitule_Mission) {
        this.CodeMission = CodeMission;
        this.agent = agent;
        this.Intitule_Mission = Intitule_Mission;
    }

    public Mission(int CodeMission, String Intitule_Mission) {
        this.CodeMission = CodeMission;
        this.Intitule_Mission = Intitule_Mission;
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

    public int getNombrePersAcc() {
        return NombrePersAcc;
    }

    public void setNombrePersAcc(int NombrePersAcc) {
        this.NombrePersAcc = NombrePersAcc;
    }

    public String getDestination() {
        return Destination;
    }

    public void setDestination(String Destination) {
        this.Destination = Destination;
    }

    public int getNbrJours() {
        return NbrJours;
    }

    public void setNbrJours(int NbrJours) {
        this.NbrJours = NbrJours;
    }

    @Override
    public String toString() {
        return "Mission{" + "CodeMission=" + CodeMission + ", Intitule_Mission=" + Intitule_Mission + ", Objectif=" + Objectif + ", DateDeb=" + DateDeb + ", DateFin=" + DateFin + ", NombrePersAcc=" + NombrePersAcc + ", Destination=" + Destination + ", NbrJours=" + NbrJours + '}';
    }
         
         
   
    }
