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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
/**
 *
 * @author Mohammed Mehdi Sarray#
 */ 
@Entity
@Table(name = "prevision")
public class prevision implements Serializable{
    
    private static final long serialVersionUID = 7L;
    @Id
    @GeneratedValue (strategy=GenerationType.AUTO)
    private int numprevs ;
   

    @Column(name="fdiver")
    private Float fdiver;
    @Column(name="fhebergement")
    private Float fhebergement;
    @Column(name="ftransport")
    private Float ftransport;
    @Column (name="Total")
    private Float Total;
    @Column(name="time")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date Heure_ = new Date();
    @Column(name="prixEssence")
    private Float prixEssence;
    @Column(name="total")
    private Float total;
    
    
    
    
    
    
    public int getNumprevs() {
        return numprevs;
    }

    public void setNumprevs(int numprivs) {
        this.numprevs = numprivs;
    }

    public Float getFdiver() {
        return fdiver;
    }

    public void setFdiver(Float fdiver) {
        this.fdiver = fdiver;
    }

    public Float getFhebergement() {
        return fhebergement;
    }

    public void setFhebergement(Float fhebergement) {
        this.fhebergement = fhebergement;
    }

    public Float getFtransport() {
        return ftransport;
    }

    public void setFtransport(Float ftransport) {
        this.ftransport = ftransport;
    }

    public Date getHeure_() {
        return Heure_;
    }

    public void setHeure_(Date Heure_) {
        this.Heure_ = Heure_;
    }

    public Float getTotal() {
        return total;
    }

    public void setTotal(Float total) {
        this.total = total;
    }

    public Float getPrixEssence() {
        return prixEssence;
    }

    public void setPrixEssence(Float prixEssence) {
        this.prixEssence = prixEssence;
    }
    
    
    
}


