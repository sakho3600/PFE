/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;


import beans.Personnel;
import beans.Personnel;
import dao.dao_Personnel;
import dao.dao_Personnel;
import java.io.IOException;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

/**
 *
 * @author HTOUM
 */

@ManagedBean
@SessionScoped

public class modele_Personnel {
      Personnel personnel= new Personnel();
     dao_Personnel service=new dao_Personnel();
 public modele_Personnel() {
    }

    public Personnel getA() {
        return personnel;
    }

    public void setA(Personnel a) {
        this.personnel = personnel;
    }

    public dao_Personnel getService() {
        return service;
    }

    public void setService(dao_Personnel service) {
        this.service = service;
    }

   
    /** Add Personnel **/
    public void ajouter()
    {
        this.service.ajouter(this.personnel);
        
        FacesContext f=FacesContext.getCurrentInstance();
        f.addMessage(null,new FacesMessage("le personnel a bien ete ajouter"));
    }
          
    
    public void loginAdmin() throws IOException{
        
   // Personnel personne=this.service.lister(this.personnel.getMatricule());
    
    
    
     FacesContext f=FacesContext.getCurrentInstance();
     
    
    }
}

