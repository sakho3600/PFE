/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import beans.Agent;
import dao.dao_Agent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import utilitaire.SessionKeyGen;
import javax.faces.context.FacesContext;
import javax.faces.application.FacesMessage;

/**
 *
 * @author Mohammed Mehdi Sarray#
 */
@ManagedBean
@SessionScoped
public class modele_agent {
    
    /** variables **/
     private int matricule;
     private String password;
     private String SessionKey ;
   
     /** Tables **/ 
     List<String> Departement  ;
     List<String> selectedDepartements ;
    /** Objects **/
    dao_Agent service = new dao_Agent();
    Agent agent = new Agent(); // connected 
    Agent nouvelleagent = new Agent(); // the new one 
    
    
    SessionKeyGen sessionId= new SessionKeyGen() ; // generateur d'id de session (UUID)
    
    public modele_agent() {
    }
    
    @PostConstruct
    public void modele_agent() {
        
        this.Departement = new ArrayList<String>() ;
        this.selectedDepartements = new ArrayList<String>() ;
        this.Departement.add("Resource Humaine") ; 
       
                
    }

    public int getMatricule() {
        return matricule;
    }

    public void setMatricule(int matricule) {
        this.matricule = matricule;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSessionKey() {
        return SessionKey;
    }

    public void setSessionKey(String SessionKey) {
        this.SessionKey = SessionKey;
    }

    public dao_Agent getService() {
        return service;
    }

    public void setService(dao_Agent service) {
        this.service = service;
    }

    public Agent getAgent() {
        return agent;
    }

    public void setAgent(Agent agent) {
        this.agent = agent;
    }

    public Agent getNouvelleagent() {
        return nouvelleagent;
    }

    public void setNouvelleagent(Agent nouvelleagent) {
        this.nouvelleagent = nouvelleagent;
    }

    public SessionKeyGen getSessionId() {
        return sessionId;
    }

    public void setSessionId(SessionKeyGen sessionId) {
        this.sessionId = sessionId;
    }
    
    
    public void logmein() throws IOException
  {
      if (service.ifcanbelogged(agent.getMatricule(),agent.getMotDePasse())) // verification de l'authentification
      {
      
          this.SessionKey = this.sessionId.getRandomUUIDString() ; // affectation de valeur uuid 
          FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("userkey", SessionKey); // Ajout de id de session
          FacesContext.getCurrentInstance().getExternalContext().redirect("mission/welcome.xhtml"); // redirection vers la page d'acceuil apré une verification de l'utilisateur
         
          
      }else{ // retour a la page INDEX
	FacesContext context=FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Matricule ou Mot de passe incorrect")); // Message d'erreur
         }
     
      
  
  }
    
     public void logout() throws IOException // deconnexion
  {
      FacesContext context = FacesContext.getCurrentInstance(); 
       context.getExternalContext().getSessionMap().remove("userkey") ;
       FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
       
  }
     
     public void ajouter() {
         
         if(!service.ifExists(this.nouvelleagent.getMatricule()))
         {
             service.ajouter(this.nouvelleagent);
             this.nouvelleagent = new Agent();
             FacesContext f=FacesContext.getCurrentInstance();
             f.addMessage(null,new FacesMessage("Ajout effectuer"));
             
         }else{
              FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erreur!", "Matricule Existant."));
              this.nouvelleagent = new Agent();
              
         }
     }
    
    
    
    
}
