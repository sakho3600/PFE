/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import beans.Admin;
import dao.dao_Admin;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author HTOUM
 */
@ManagedBean
@SessionScoped

public class modele_Admin {
    Admin a= new Admin();
     dao_Admin service=new dao_Admin();

    public modele_Admin() {
    }

    public Admin getA() {
        return a;
    }

    public void setA(Admin a) {
        this.a = a;
    }

    public dao_Admin getService() {
        return service;
    }

    public void setService(dao_Admin service) {
        this.service = service;
    }

   
    
    public void ajouter(Admin d)
    {
        this.service.ajouter(d);
        
        FacesContext f=FacesContext.getCurrentInstance();
        f.addMessage(null,new FacesMessage("l'admin a bien ete ajouter"));
    }
          
}
