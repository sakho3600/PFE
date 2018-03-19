/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author HTOUM
 */


@Entity
@Table(name = "Hebergement")

public class Hebergement {

    @Id    
    @GeneratedValue (strategy=GenerationType.AUTO)
    private Long CodeHeb;
    @Column(name="NomHeb")
    private String NomHeb;
    @Column (name="Prix")
    private float Prix;
    @ManyToOne
    @JoinColumn(name="Code_Postal") 
    private ville Ville ;
    





    
    public Long getCodeHeb() {
        return CodeHeb;
    }

    public void setCodeHeb(Long CodeHeb) {
        this.CodeHeb = CodeHeb;
    }

    public Hebergement(Long CodeHeb, String NomHeb, float Prix, ville Ville) {
        this.CodeHeb = CodeHeb;
        this.NomHeb = NomHeb;
        this.Prix = Prix;
        this.Ville = Ville;
    }

   
    public Hebergement(Long CodeHeb, String NomHeb, float Prix) {
        this.CodeHeb = CodeHeb;
        this.NomHeb = NomHeb;
        this.Prix = Prix;
    }

    public Hebergement() {
    }

    public String getNomHeb() {
        return NomHeb;
    }

    public void setNomHeb(String NomHeb) {
        this.NomHeb = NomHeb;
    }

    public float getPrix() {
        return Prix;
    }

    public void setPrix(float Prix) {
        this.Prix = Prix;
    }

    public ville getVille() {
        return Ville;
    }

    public void setVille(ville Ville) {
        this.Ville = Ville;
    }   
}