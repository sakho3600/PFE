/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import beans.Admin;
import dao.dao_Admin;
import utilitaire.SessionKeyGen;
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
  
    /** variables **/
        private String pwd; // Password
	private String msg; // Message
	private String user; // Username
        private String userKey ; /* unique key pour la session */
        
        
    /** Objects **/
    Admin admin= new Admin(); // Admin Object
    dao_Admin service=new dao_Admin(); // dao_Admin to acces the dao
    SessionKeyGen sessionId= new SessionKeyGen() ; // generateur d'id de session (UUID)

    public modele_Admin() {
    }

    public Admin getA() {
        return admin;
    }

    public void setA(Admin admin) {
        this.admin = admin;
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
      if (service.ifcanbelogged(admin.getUsername(), admin.getMotDePasse())) // verification de l'authentification
      {
          admin = service.ifAdmin(admin.getUsername()); // verification si c'est un Administrateur
          
          this.userKey = this.sessionId.getRandomUUIDString() ; // affectation de valeur uuid 
          FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("userkey", userKey); // Ajout de id de session
          FacesContext.getCurrentInstance().getExternalContext().redirect("welcome.xhtml"); // redirection vers la page d'acceuil apré une verification de l'utilisateur
         
          
      }else{ // retour a la page de Connexion
	FacesContext context=FacesContext.getCurrentInstance();
	context.addMessage(null, new FacesMessage("Username or Password is invalid")); // Message d'erreur
	context.addMessage(null, new FacesMessage("+ Check Capslock in keyboard :)"));
         }
      
  
  }
  
  public void logout() throws IOException // deconnexion
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
