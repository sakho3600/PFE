/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import beans.Admin;
import beans.prevision;
import dao.dao_Admin;
import utilitaire.SessionKeyGen;
import java.io.IOException;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import utilitaire.cryptpasswords;

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
        private int matricule ; // matricule utiliser pour l'update
        private String choixprevision; // choix du prevision a modifier
         // <editor-fold desc="acces block" defaultstate="collapsed">
        /** privileges panelgrid block **/
        private boolean addagent;
        private boolean adduseradmin;
        private boolean editagent;
        private boolean gestmission;
        private boolean gestassurance;
         //</editor-fold>
        
    /** Tables **/
        private List<privs> privilege ; // les privileges
        private List<privs> selectedPrivs; // les privileges qui seront choisit seront affecter dans se tableau
        private List<String> previsions ; //    
        
    /** Objects **/
    Admin admin= new Admin(); // Admin Object connexion admin
    Admin nouvelleadmin=new Admin(); // pour la création des administrateurs
    dao_Admin service=new dao_Admin(); // dao_Admin to acces the dao
    SessionKeyGen sessionId= new SessionKeyGen() ; // generateur d'id de session (UUID)
    cryptpasswords encrypt=new cryptpasswords() ;
    prevision previs=new prevision();
    prevision updateprev = new prevision();
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
        privilege.add(privs.GMA);
        privilege.add(privs.UPuser) ;
        
        addagent=false;
        adduseradmin=false;
        editagent=false;
        gestmission=false;
        gestassurance=false;
        
        this.previsions = service.toutlesprevisions();
    }
   
    // <editor-fold desc="getters and setters" defaultstate="collapsed">
    /** start of getters and setters **/
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

    public String getChoixprevision() {
        return choixprevision;
    }

    public void setChoixprevision(String choixprevision) {
        this.choixprevision = choixprevision;
    }

    public List<String> getPrevisions() {
        return previsions;
    }

    public void setPrevisions(List<String> previsions) {
        this.previsions = previsions;
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

    public prevision getPrevis() {
        return previs;
    }

    public void setPrevis(prevision previs) {
        this.previs = previs;
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

    public boolean isAddagent() {
        return addagent;
    }

    public void setAddagent(boolean addagent) {
        this.addagent = addagent;
    }

    public boolean isAdduseradmin() {
        return adduseradmin;
    }

    public void setAdduseradmin(boolean adduseradmin) {
        this.adduseradmin = adduseradmin;
    }

    public boolean isEditagent() {
        return editagent;
    }

    public void setEditagent(boolean editagent) {
        this.editagent = editagent;
    }

    public boolean isGestmission() {
        return gestmission;
    }

    public void setGestmission(boolean gestmission) {
        this.gestmission = gestmission;
    }

    public cryptpasswords getEncrypt() {
        return encrypt;
    }

    public void setEncrypt(cryptpasswords encrypt) {
        this.encrypt = encrypt;
    }

    public boolean isGestassurance() {
        return gestassurance;
    }

    public void setGestassurance(boolean gestassurance) {
        this.gestassurance = gestassurance;
    }

    public prevision getUpdateprev() {
        return updateprev;
    }

    public void setUpdateprev(prevision updateprev) {
        this.updateprev = updateprev;
    }
    
    
    /** end of getter and setter **/
    
    // </editor-fold>
    
    // <editor-fold desc="Login Method" defaultstate="collapsed" >
    /** verification de l'authentification **/
    
  public void logmein() throws IOException, NoSuchAlgorithmException
  {
      if (service.ifcanbelogged(admin.getUsername(), encrypt.cryptme(admin.getMotDePasse()))) // verification de l'authentification
      {
          admin = service.ifAdmin(admin.getUsername()); // detail de  l'Administrateur  ~
          
          this.userKey = this.sessionId.getRandomUUIDString() ; // affectation de valeur uuid 
          
          checkprivs() ; /** verification des privileges **/
          
          FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("userkey", userKey); // Ajout de id de session
          FacesContext.getCurrentInstance().getExternalContext().redirect("welcome.xhtml"); // redirection vers la page d'acceuil apré une verification de l'utilisateur
         
          
      }else{ // retour a la page de Connexion
	FacesContext context=FacesContext.getCurrentInstance();
	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning!", "Username or Password Invalid."));
         }
  
      
    
  
  }
  // </editor-fold>
    // <editor-fold desc="Logout Method"  defaultstate="collapsed" >
  public void logout() throws IOException // deconnexion
  {
      FacesContext context = FacesContext.getCurrentInstance(); 
       context.getExternalContext().getSessionMap().remove("userkey") ;
       FacesContext.getCurrentInstance().getExternalContext().invalidateSession(); /*destroying the session context */
       FacesContext.getCurrentInstance().getExternalContext().redirect("login.xhtml");
       
  }
// </editor-fold >
  
    // <editor-fold desc="add administrator Method" defaultstate="collapsed">
    public void ajouter() throws NoSuchAlgorithmException // ajout d'un nouvelle administrateur
    {
       
        
        // verifier la matricule et le username de l'admin existe deja
       if (service.ifExistsAdmin(this.nouvelleadmin.getMatricule()) && service.ifAdminvalid(this.nouvelleadmin.getUsername() ) )
            { 
             FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erreur!", "Matricule et Username Existant."));
             this.selectedPrivs = new ArrayList<privs>() ; 
             this.nouvelleadmin = new Admin();
            
             } 
       
       // verifier si
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
           if (this.selectedPrivs.contains(privs.GA) && this.selectedPrivs.contains(privs.GM))
           {
               this.selectedPrivs.remove(privs.GA) ;
               this.selectedPrivs.remove(privs.GM) ;
               this.selectedPrivs.add(privs.GM) ;
           }
          
           this.nouvelleadmin.setAdmin_privs(this.selectedPrivs); // ajout des privileges
           this.nouvelleadmin.setMotDePasse(encrypt.cryptme(this.nouvelleadmin.getMotDePasse())) ;
           this.service.ajouter(this.nouvelleadmin);
        
        FacesContext f=FacesContext.getCurrentInstance();
        f.addMessage(null,new FacesMessage("Ajout effectuer"));
        
        
        this.nouvelleadmin = new Admin(); // pour l'initialisation du formulaire apré l'ajout  
        this.selectedPrivs = new ArrayList<privs>() ; // initialisation du checkbox    
        
                      }              
        }
    // </editor-fold>
    
    // <editor-fold desc="allow privileges Method" defaultstate="collapsed">
    //activer les privileges 
    public void allow(List<privs> privileges)
    {
        for(int i = 0 ; i < privileges.size() ; i++)
        {
            if ( privileges.get(i).equals(privs.ADDuser) )
            {
                this.addagent = true;
                
            }
            if ( privileges.get(i).equals(privs.UPuser) )
            {
                this.editagent = true;
                
            }
           
            if ( privileges.get(i).equals(privs.GA) )
            {
                this.gestassurance = true;
                
            }
            if ( privileges.get(i).equals(privs.GM) )
            {
                this.gestmission = true;
                
            }
            if ( privileges.get(i).equals(privs.GMA) )
            {
                this.gestassurance = true;
                this.gestmission = true;
            }
            
        }
    }
   // </editor-fold>  
    
    // <editor-fold desc="verification des privileges Method" defaultstate="collapsed">
    public void checkprivs()
    {
        boolean stop = false ;
        List<privs> loggedprivs = new ArrayList<>() ;
        loggedprivs = this.admin.getAdmin_privs() ;
        
        for (int i= 0 ; i<loggedprivs.size() ; i++){
            if (loggedprivs.get(i) == privs.ALL) //superAdmin
            {
            this.addagent = true;
            this.adduseradmin = true;
            this.editagent = true;
            this.gestmission = true;
            this.gestassurance = true ;
            stop = true ;
            }
        }
        
        
        if ( stop == false)
        {
            allow(loggedprivs) ; // allow privileges access
        }
       
    }
     //</editor-fold>     
    
    //<editor-fold desc="Ajout prevision Method" defaultstate="collapsed" >
    
    public void ajouterprevision()
    {
        
        if (service.verifprevision(this.previs.getType()))
        
        { 
            this.service.addprevision(this.previs); //verification de l'existance du prevision
             this.previsions = service.toutlesprevisions();
          FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "terminer!", "prevision Ajouter."));
        this.previs = new prevision() ;  }
        else{
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erreur!", "prevision deja existante veuiller modifier l'ancienne."));
        } 
        
    }
    
    
    
    //</editor-fold>
    
    //<editor-fold desc="Ajout prevision Method" defaultstate="collapsed" >
      
     public void leadstoupprevision() throws IOException {
       
      
        this.updateprev = service.getprev(choixprevision) ;
         
          FacesContext.getCurrentInstance().getExternalContext().redirect("previsiondetail.xhtml");
    
         
     }
          
      
         

    //</editor-fold>
    
     
     public void updateprevis()
     {
         service.updateprevision(this.updateprev);
         this.updateprev = new prevision();
  FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "terminer!", "prevision Modifier."));

     }
     
     
     public void deleteprevision()
     {
         service.delete(this.updateprev);
         this.updateprev = new prevision();
         FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "terminer!", "prevision Supprimer."));

     }
    
}
