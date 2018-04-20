/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
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
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author Mohammed Mehdi Sarray#
 */
@Entity
@Table(name="privilege")
public class privileges implements Serializable {
    
     
    private static final long serialVersionUID = 77L; 
    
   @Id
   @GeneratedValue (strategy=GenerationType.AUTO)
   @Column(name="code_privilege")
   private int code_privilege;
     
        
   @Column(name="privileges_libelle")
   private String libelle;
   
   
   @ManyToMany(fetch = FetchType.EAGER)
        @JoinTable(name = "Affectation_privilege"
                ,joinColumns = @JoinColumn(name = "code_privilege")
                ,inverseJoinColumns = @JoinColumn(name = "Matricule")
        )
        private List<Admin> privilege_admin ;

    public privileges(int id ,String libelle) {
        this.code_privilege=id ;
        this.libelle = libelle;
    }

   
   public privileges()
   {
       
   }

    public privileges(String libelle) {
        this.libelle = libelle;
    }
   
   
    public int getCode_privilege() {
        return code_privilege;
    }

    public void setCode_privilege(int code_privilege) {
        this.code_privilege = code_privilege;
    }

    public List<Admin> getPrivilege_admin() {
        return privilege_admin;
    }

    public void setPrivilege_admin(List<Admin> privilege_admin) {
        this.privilege_admin = privilege_admin;
    }

  

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }
     
        
     
}
