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
import java.io.Serializable;
import java.util.List;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
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
   
   
   
   @Column(name = "username", unique = true) //unique dans la BD
   @NotNull
    private String username ; //login

   
   
   /* le Mapping du Type Enum */ 
   
    public Admin( int Matricule, String Pernom, String Nom, String MotDePasse , String username ) {
        super(Matricule, Pernom, Nom , MotDePasse);
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

  

    
   
    
}
