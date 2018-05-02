/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author Mohammed Mehdi Sarray#
 */
@Entity
@Table(name = "vehicule")
public class vehicule implements Serializable{
    private static final long serialVersionUID = 95L;
    
    @Id
   @GeneratedValue (strategy=GenerationType.AUTO)
   @Column(name ="id")
    private int id;
    
   @Column(name = "immatriculation", unique = true) //unique dans la BD
    private String immatriculation;
   
   @Column(name = "Model")
    private String Model;
   
   @Column(name = "Consommation")
   private Float consommation;
   
   @Column(name = "numchasis")
   private String numchasis ;
   
   @Column(name = "numcarte_grise")
   private String numcarte_grise ;
   
   @Column(name = "Date_de_mise_en_circulation")
    @Temporal(javax.persistence.TemporalType.DATE)
   private Date Date_de_mise_en_circulation ;
   
   @Column(name = "proprietaire")
   private String proprietaire ;
   
   @Column(name = "Nom")
   private String Nom ;
   
   

    public vehicule() {
    }

    public int getId() {
        return id;
    }

    public void setId(int Id) {
        this.id = Id;
    }

    public String getImmatriculation() {
        return immatriculation;
    }

    public void setImmatriculation(String immatriculation) {
        this.immatriculation = immatriculation;
    }

    public String getModel() {
        return Model;
    }

    public void setModel(String Model) {
        this.Model = Model;
    }

    public Float getConsommation() {
        return consommation;
    }

    public void setConsommation(Float consommation) {
        this.consommation = consommation;
    }

    public String getNumchasis() {
        return numchasis;
    }

    public void setNumchasis(String numchasis) {
        this.numchasis = numchasis;
    }

    public String getNumcarte_grise() {
        return numcarte_grise;
    }

    public void setNumcarte_grise(String numcarte_grise) {
        this.numcarte_grise = numcarte_grise;
    }

    public Date getDate_de_mise_en_circulation() {
        return Date_de_mise_en_circulation;
    }

    public void setDate_de_mise_en_circulation(Date Date_de_mise_en_circulation) {
        this.Date_de_mise_en_circulation = Date_de_mise_en_circulation;
    }

    public String getProprietaire() {
        return proprietaire;
    }

    public void setProprietaire(String proprietaire) {
        this.proprietaire = proprietaire;
    }

    public String getNom() {
        return Nom;
    }

    public void setNom(String Nom) {
        this.Nom = Nom;
    }

    

   
   
   
}

