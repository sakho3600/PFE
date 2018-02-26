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
      Personnel a= new Personnel();
     dao_Personnel service=new dao_Personnel();
 public modele_Personnel() {
    }

    public Personnel getA() {
        return a;
    }

    public void setA(Personnel a) {
        this.a = a;
    }

    public dao_Personnel getService() {
        return service;
    }

    public void setService(dao_Personnel service) {
        this.service = service;
    }

   
    
    public void ajouter()
    {
        this.service.ajouter(this.a);
        
        FacesContext f=FacesContext.getCurrentInstance();
        f.addMessage(null,new FacesMessage("le personnel a bien ete ajouter"));
    }
          
    
    public void loginAdmin() throws IOException{
    Personnel p=this.service.lister(this.a.getMatricule());
     FacesContext f=FacesContext.getCurrentInstance();
     if (p.getMotDePasse().equals(a.getMotDePasse())){
        switch (this.service.verifGrade(this.a.getMatricule())) {
            case "SuperAdmin":
                {
                    ExternalContext extContext = f.getExternalContext();
                    extContext.redirect(extContext.getRequestContextPath() + "/faces/index.xhtml");
                    break;
                }
            case "Admin":
                {
                    ExternalContext extContext = f.getExternalContext();
                    extContext.redirect(extContext.getRequestContextPath() + "/faces/inde.xhtml");
                    break;
                }
            default:
                f.addMessage(null,new FacesMessage("vous n'êtes pas autorisé à utiliser ce service "));
                break;
        }
         
       }else
     {        f.addMessage(null,new FacesMessage("Mot de Passe incorrect"));

     }
    }
}

