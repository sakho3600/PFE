/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

/**
 *
 * @author HTOUM
 */

@Entity
@Table(name = "Cloture")
@PrimaryKeyJoinColumn(name = "Matricule")
public class Cloture implements Serializable {
    
   
	@Id
          @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "CodeMission")
          private Mission Mission;
      
        @Column( name="fdiver")
        private Float fdiver;

        @Column( name="fhebergent")
        private Float fhebergent;

        @Column( name="ftransport")
        private Float ftransport;
        
        

    public Cloture(Mission Mission, Float fdiver, Float fhebergent, Float ftransport) {
        this.Mission = Mission;
        this.fdiver = fdiver;
        this.fhebergent = fhebergent;
        this.ftransport = ftransport;
    }

    public Cloture() {
    }

        
    public Mission getCodeMission() {
        return Mission;
    }

    public void setCodeMission(Mission Mission) {
        this.Mission = Mission;
    }

    public Float getFdiver() {
        return fdiver;
    }

    public void setFdiver(Float fdiver) {
        this.fdiver = fdiver;
    }

    public Float getFhebergent() {
        return fhebergent;
    }

    public void setFhebergent(Float fhebergent) {
        this.fhebergent = fhebergent;
    }

    public Float getFtransport() {
        return ftransport;
    }

    public void setFtransport(Float ftransport) {
        this.ftransport = ftransport;
    }

}
