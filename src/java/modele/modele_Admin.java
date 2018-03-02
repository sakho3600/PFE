/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import beans.Admin;
import dao.dao_Admin;
import java.io.IOException;
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
        private static final String userKey="myd" ; /* TODO KEY (randomize)*/
        
        
    
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

  public void logmein() throws IOException
  {
      if (service.ifcanbelogged(a.getUsername(), a.getMotDePasse()))
      {
          a= service.ifAdmin(a.getUsername());
          FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("userkey", userKey);
          FacesContext.getCurrentInstance().getExternalContext().redirect("welcome.xhtml");
         
          
      }else{
	FacesContext context=FacesContext.getCurrentInstance();
	context.addMessage(null, new FacesMessage("Username or Password is invalid"));
	context.addMessage(null, new FacesMessage("+ Check Capslock in keyboard :)"));
         }
      
  
  }
  
  public void logout() throws IOException
  {
      FacesContext context = FacesContext.getCurrentInstance(); 
       context.getExternalContext().getSessionMap().remove("userkey") ;
       FacesContext.getCurrentInstance().getExternalContext().redirect("login.xhtml");
       
  }

    
    public void ajouter()
    {
       // this.service.ajouter(this.a);
        
        FacesContext f=FacesContext.getCurrentInstance();
        f.addMessage(null,new FacesMessage("l'admin a bien ete ajouter"));
    }
          
}
