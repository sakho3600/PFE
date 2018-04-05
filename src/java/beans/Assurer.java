/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
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
     @OneToMany(fetch = FetchType.LAZY, mappedBy = "BulletinMensuel")
     private Set<BulletinMensuel> BulletinMensuel = new HashSet<BulletinMensuel>(
			0);
    public Assurer() {
    }

    public Assurer(int Matricule, String Pernom, String Nom, String MotDePasse) {
        super(Matricule, Pernom, Nom, MotDePasse);
    }

    public Set<BulletinMensuel> getBulletinMensuel() {
        return BulletinMensuel;
    }

    public void setBulletinMensuel(Set<BulletinMensuel> BulletinMensuel) {
        this.BulletinMensuel = BulletinMensuel;
    }

    
     
     
     
}
