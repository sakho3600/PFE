/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 *
 * @author Mohammed Mehdi Sarray#
 */


@Entity
@Table(name = "user_privs")

public class user_privs implements Serializable {
  
    @Id
   private int user_id;
    
   

    public user_privs() {
    }

    
  
    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

  
   
   
    

    
}

    

