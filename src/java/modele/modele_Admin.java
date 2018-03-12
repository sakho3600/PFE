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
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;

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
        private int matricule ;
    /** Tables **/
        private List<privs> privilege ; // les privileges
        private List<privs> selectedPrivs; // les privileges qui seront choisit seront affecter dans se tableau
           
        
    /** Objects **/
    Admin admin= new Admin(); // Admin Object connexion admin
    Admin nouvelleadmin=new Admin(); // pour la création des administrateurs
    dao_Admin service=new dao_Admin(); // dao_Admin to acces the dao
    SessionKeyGen sessionId= new SessionKeyGen() ; // generateur d'id de session (UUID)

    public modele_Admin() {  
    }
    @PostConstruct // implementation des privileges a afficher
    public void init()
    {   
        selectedPrivs = new ArrayList<privs>() ;
        privilege = new ArrayList<privs>() ;
        privilege.add(privs.GA) ;
        privilege.add(privs.ADDuser) ; 
        privilege.add(privs.ALL);
        privilege.add(privs.GM) ;
        privilege.add(privs.DELuser);
        privilege.add(privs.GMA);
        privilege.add(privs.UPuser) ;
    }

    public List<privs> getSelectedPrivs() {
        return selectedPrivs;
    }

    public void setSelectedPrivs(List<privs> selectedPrivs) {
        this.selectedPrivs = selectedPrivs;
    }

    public String getUserKey() {
        return userKey;
    }

    public void setUserKey(String userKey) {
        this.userKey = userKey;
    }

    public int getMatricule() {
        return matricule;
    }

    public void setMatricule(int matricule) {
        this.matricule = matricule;
    }

    public Admin getNouvelleadmin() {
        return nouvelleadmin;
    }

    public void setNouvelleadmin(Admin nouvelleadmin) {
        this.nouvelleadmin = nouvelleadmin;
    }

    
    
    public List<privs> getPrivilege() {
        return privilege;
    }

    public void setPrivilege(List<privs> privilege) {
        this.privilege = privilege;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public SessionKeyGen getSessionId() {
        return sessionId;
    }

    public void setSessionId(SessionKeyGen sessionId) {
        this.sessionId = sessionId;
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

    /** verification de l'authentification **/
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

    
    public void ajouter() // ajout d'un nouvelle administrateur
    {
       
        
        
       if (service.ifExistsAdmin(this.nouvelleadmin.getMatricule()) && service.ifAdminvalid(this.nouvelleadmin.getUsername() ) )
            { 
             FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erreur!", "Matricule et Username Existant."));
             this.selectedPrivs = new ArrayList<privs>() ; 
             this.nouvelleadmin = new Admin();
             } 
       else if (service.ifExistsAdmin(this.nouvelleadmin.getMatricule()) == true && service.ifAdminvalid(this.nouvelleadmin.getUsername()) == false )
       {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erreur!", "Matricule Existant."));
            this.selectedPrivs = new ArrayList<privs>() ;  
            this.nouvelleadmin = new Admin();
       }
       else if (service.ifExistsAdmin(this.nouvelleadmin.getMatricule()) == false && service.ifAdminvalid(this.nouvelleadmin.getUsername()) == true ) 
       {
           FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erreur!", "Username Existant."));
           this.selectedPrivs = new ArrayList<privs>() ;  
           this.nouvelleadmin = new Admin();
       }
       else {
           if (this.selectedPrivs.contains(privs.ALL)){ // si le privilege du super admin a ete choisit donc les autres privileges ne seront pas necessaire
               this.selectedPrivs = new ArrayList<privs>() ;
               this.selectedPrivs.add(privs.ALL) ;
           }
           this.nouvelleadmin.setAdmin_privs(this.selectedPrivs); // ajout des privileges
           this.service.ajouter(this.nouvelleadmin);
        
        FacesContext f=FacesContext.getCurrentInstance();
        f.addMessage(null,new FacesMessage("Ajout effectuer"));
        
        
        this.nouvelleadmin = new Admin(); // pour l'initialisation du formulaire apré l'ajout  
        this.selectedPrivs = new ArrayList<privs>() ; // initialisation du checkbox           
                      }              
        }
          
}
