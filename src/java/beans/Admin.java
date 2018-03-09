/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

/**
 *
 * @author Mohammed Mehdi Sarray#
 */
import com.sun.istack.internal.NotNull;
import modele.privs;
import java.io.Serializable;
import java.util.List;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Mohammed Mehdi Sarray#
 */
@Entity
@Table(name = "Admin")
@PrimaryKeyJoinColumn(name = "Matricule")
@XmlRootElement
public class Admin extends Personnel implements Serializable{
    
   private static final long serialVersionUID = 1L; 
    
   @Column(name = "privs_id")
   @NotNull
    private int admin_ID ; // for the privileges 
      // privileges d'acces
   
   @Column(name = "username", unique = true) //unique dans la BD
   @NotNull
    private String username ; //login

   
   
   /* le Mapping du Type Enum */ 
@ElementCollection(targetClass=privs.class, fetch=FetchType.EAGER)
@Enumerated(EnumType.STRING)
@CollectionTable(name="user_privs", joinColumns={@JoinColumn(name="id")})
@Column(name="admin_privs")
   private List<privs> admin_privs ;  // privileges d'acces
   
    public Admin( int Matricule, String Pernom, String Nom, String MotDePasse , String username , List<privs> roles) {
        super(Matricule, Pernom, Nom , MotDePasse);
        this.admin_privs = roles ;
        this.username = username ;
        
    }

    public Admin() {
     
    }

    

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getAdmin_ID() {
        return admin_ID;
    }

    public void setAdmin_ID(int admin_ID) {
        this.admin_ID = admin_ID;
    }

    public List<privs> getAdmin_privs() {
        return admin_privs;
    }

    public void setAdmin_privs(List<privs> admin_privs) {
        this.admin_privs = admin_privs;
    }

  

    
   
    
}
