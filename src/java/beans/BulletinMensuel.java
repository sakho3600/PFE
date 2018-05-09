/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author Mohammed Mehdi Sarray#
 */
@Entity
@Table(name = "BulletinMensuel")
public class BulletinMensuel implements Serializable{
    
     private static final long serialVersionUID = 13225L;
    
    
    @Id
   @GeneratedValue (strategy=GenerationType.AUTO)
   @Column(name ="id")
    private int Id; 
    
    @Column(name="Matricule_Assurer")
    private int Matricule ;   
    
    @Column(name ="Numero_de_bulletin")
    private int num_bulletin;
    @Column(name ="Montant_prescrit")
    private float Montant_prescrit;
    @Column(name ="Date_transmit")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date Date_transmit ;
    @Column(name ="Malade")
    private String Malade;
    @Column(name ="Date_Remboursement")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date Date_Remboursement;
    @Column(name ="Montant_remboursement")
    private float Montant_remboursement;
    @Column(name ="Observation")
    private String Observation;
    @Column (name="DateDeCreation" )
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)    
    private Date DatedeCreation = new Date();
     
    @ManyToOne
    @JoinColumn(name="assurer", nullable=false)
    private Assurer assurer;

    public int getMatricule() {
        return Matricule;
    }

    public void setMatricule(int Matricule) {
        this.Matricule = Matricule;
    }


    public int getNum_bulletin() {
        return num_bulletin;
    }

    public void setNum_bulletin(Integer num_bulletin) {
        this.num_bulletin = num_bulletin;
    }

    public float getMontant_prescrit() {
        return Montant_prescrit;
    }

    public void setMontant_prescrit(Float Montant_prescrit) {
        this.Montant_prescrit = Montant_prescrit;
    }

    public Date getDate_transmit() {
        return Date_transmit;
    }

    public void setDate_transmit(Date Date_transmit) {
        this.Date_transmit = Date_transmit;
    }

    public String getMalade() {
        return Malade;
    }

    public void setMalade(String Malade) {
        this.Malade = Malade;
    }

    public Date getDate_Remboursement() {
        return Date_Remboursement;
    }

    public void setDate_Remboursement(Date Date_Remboursement) {
        this.Date_Remboursement = Date_Remboursement;
    }

    public float getMontant_remboursement() {
        return Montant_remboursement;
    }

    public void setMontant_remboursement(Float Montant_remboursement) {
        this.Montant_remboursement = Montant_remboursement;
    }

    public String getObservation() {
        return Observation;
    }

    public void setObservation(String Observation) {
        this.Observation = Observation;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public Assurer getAssurer() {
        return assurer;
    }

    public void setAssurer(Assurer assurer) {
        this.assurer = assurer;
    }
            

    




}
