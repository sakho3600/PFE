/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Mohammed Mehdi Sarray#
 */
@Entity
@Table(name = "Assurer")
@PrimaryKeyJoinColumn(name = "Matricule")
@XmlRootElement
public class Assurer extends Personnel implements Serializable{
     private static final long serialVersionUID = 13L; 
     
     @OneToMany(targetEntity=BulletinMensuel.class, cascade=CascadeType.ALL,mappedBy = "assurer")
     private List<BulletinMensuel> BulletinMensuel ;
    public Assurer() {
    }

    public Assurer(int Matricule, String Pernom, String Nom, String MotDePasse , String Email) {
        super(Matricule, Pernom, Nom, MotDePasse,Email);
    }

    public List<BulletinMensuel> getBulletinMensuel() {
        return BulletinMensuel;
    }

    public void setBulletinMensuel(List<BulletinMensuel> BulletinMensuel) {
        this.BulletinMensuel = BulletinMensuel;
    }

    
     
     
     
}
