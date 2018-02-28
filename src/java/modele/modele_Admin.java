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
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import java.io.Serializable;

/**
 *
 * @author HTOUM
 */
@ManagedBean
@SessionScoped

public class modele_Admin  {
  
    
        private String pwd;
	private String msg;
	private String user;
        
    
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

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

  

    
    public void ajouter()
    {
        this.service.ajouter(this.a);
        
        FacesContext f=FacesContext.getCurrentInstance();
        f.addMessage(null,new FacesMessage("l'admin a bien ete ajouter"));
    }
          
}
