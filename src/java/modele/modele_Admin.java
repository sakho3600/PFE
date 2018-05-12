/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import beans.Admin;
import beans.Agent;
import beans.Cloture;
import beans.Departement;
import beans.Mission;
import beans.prevision;
import beans.privileges;
import beans.vehicule;
import dao.dao_Admin;
import dao.dao_Agent;
import dao.dao_Cloture;
import dao.dao_Departement;
import dao.dao_Mission;
import dao.dao_Vehicule;
import utilitaire.SessionKeyGen;
import java.io.IOException;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedProperty;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;
import org.primefaces.event.SelectEvent;
import utilitaire.cryptpasswords;

/**
 *
 * @author HTOUM
 */
@ManagedBean
@SessionScoped

public class modele_Admin  {
  
    /** variables **/
       
        private String Messageupdate1; // update/delete mission message
        private String pwd; // Password
	private String msg; // Message
	private String user; // Username
        private String userKey ; /* unique key pour la session */
        private int matricule ; // matricule utiliser pour l'update
        private String choixprevision; // choix du prevision a modifier
        private int matriculeMission; //matricule de mission a modifiers/Supprimer
        private String Stat;
        private String Departements;
        private String TypeRejet;
        private String Rejet;
        private vehicule vehicule=new vehicule();
         //Les dates
    private Date date1;
    private Date date2;
         // <editor-fold desc="acces block" defaultstate="collapsed">
        /** privileges panelgrid block **/
        private boolean addagent;
        private boolean adduseradmin;
        private boolean editagent;
        private boolean gestmission;
        private boolean gestassurance;
        private boolean updateuseradmin;
       
        //</editor-fold>
        
    /** Tables **/
          private List<String> privilege ; // les privileges
        private List<String> selectedPrivs; // les privileges qui seront choisit seront affecter dans se tableau
        private List<privileges> mesprivileges;
        private List<Mission>MissionStat;
     
  private List<String> previsions ; //   
        private List<Admin> admins;
        
    /** Objects **/
        String nomDepartement;
        Departement Departement=new Departement();
        dao_Departement serviceDepartement= new dao_Departement();
        String villes=new String();
        Mission mission=new Mission();
    Admin admin= new Admin(); // Admin Object connexion admin
    Admin nouvelleadmin=new Admin(); // pour la création des administrateurs
    dao_Admin service=new dao_Admin(); // dao_Admin to acces the dao
    dao_Agent serviceagent=new dao_Agent(); // dao_Admin to acces the dao
    dao_Mission serviceMission=new dao_Mission();
    dao_Cloture serviceCloture=new dao_Cloture();
    SessionKeyGen sessionId= new SessionKeyGen() ; // generateur d'id de session (UUID)
    cryptpasswords encrypt=new cryptpasswords() ;
    prevision previs=new prevision();
    prevision updateprev = new prevision();
    Cloture cloture=new Cloture();
     Mission modifMission = new Mission();
     cryptpasswords encryption = new cryptpasswords() ; // SHA256 ENCRYPTION
         private List<vehicule> cars2;
         
    @ManagedProperty("#{carService}")
    dao_Vehicule serviceVehicule=new dao_Vehicule();

    public modele_Admin() {  
    }
    
    @PostConstruct // implementation des privileges a afficher
    public void init()
    {   
        addagent=false;
        adduseradmin=false;
        editagent=false;
        gestmission=false;
        gestassurance=false;
        updateuseradmin=false;
           selectedPrivs = new ArrayList<String>() ;
        privilege = new ArrayList<String>() ;
        this.mesprivileges = service.listpriv();
        
        for(int i=0 ; i < mesprivileges.size() ; i++)
        {
            privilege.add(mesprivileges.get(i).getLibelle()) ;
        }
       
        
    
        
       this.updateprev = service.getprevision() ;
           cars2 = serviceVehicule.listevehicule();

    }

    public List<vehicule> getCars2() {
        return cars2;
    }     
    public void setService(dao_Vehicule serviceVehicule) {
        this.serviceVehicule = serviceVehicule;
    }
     
    public void onRowEdit(RowEditEvent event) {
        Date d=new Date();
        d=((vehicule) event.getObject()).getDate_de_mise_en_circulation();
vehicule v=new vehicule(((vehicule) event.getObject()).getId(),((vehicule) event.getObject()).getImmatriculation(),((vehicule) event.getObject()).getModel(),((vehicule) event.getObject()).getConsommation(),((vehicule) event.getObject()).getNumchasis(),((vehicule) event.getObject()).getNumcarte_grise(),d,((vehicule) event.getObject()).getNom(),((vehicule) event.getObject()).getCarburant());
               this.serviceVehicule.MajVehicule(v);
        FacesMessage msg = new FacesMessage("Car Edited", ((vehicule) event.getObject()).getImmatriculation());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
     
    public void onRowCancel(RowEditEvent event) {
        String s= ((vehicule) event.getObject()).getCarburant()+ ((vehicule) event.getObject()).getImmatriculation();
        FacesMessage msg = new FacesMessage("Edit Cancelled", s);
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
     
    public void onCellEdit(CellEditEvent event) {
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();
         
        if(newValue != null && !newValue.equals(oldValue)) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed", "Old: " + oldValue + ", New:" + newValue);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }
    public String getDepartements() {
        return Departements;
    }

    public dao_Vehicule getServiceVehicule() {
        return serviceVehicule;
    }

    public void setServiceVehicule(dao_Vehicule serviceVehicule) {
        this.serviceVehicule = serviceVehicule;
    }

    public void setDepartements(String Departements) {
        this.Departements = Departements;
    }

    public String getRejet() {
        return Rejet;
    }

    public void setRejet(String Rejet) {
        this.Rejet = Rejet;
    }

    public String getStat() {
        return Stat;
    }

    public void setStat(String Stat) {
        this.Stat = Stat;
    }

    public String getTypeRejet() {
        return TypeRejet;
    }

    public void setTypeRejet(String TypeRejet) {
        this.TypeRejet = TypeRejet;
    }

   
    public Date getDate1() { 
        return date1;
    }

    public void setDate1(Date date1) {
        this.date1 = date1;
    }

    public Date getDate2() {
        return date2;
    }
   public void onDateSelect1(SelectEvent event) { 
        this.date1 = (Date) event.getObject();
      
        }
     public void onDateSelect2(SelectEvent event) {
        this.date2 = (Date) event.getObject();
       }
  
   
         public vehicule getVehicule() {
        return vehicule;
    }

    // <editor-fold desc="getters and setters" defaultstate="collapsed">
    /** start of getters and setters **/
    public void setVehicule(vehicule vehicule) {
        this.vehicule = vehicule;
    } 

    public void setDate2(Date date2) {
        this.date2 = date2;
    }

    public void setServiceagent(dao_Agent serviceagent) {
        this.serviceagent = serviceagent;
    }

    public boolean isUpdateuseradmin() {
        return updateuseradmin;
    }

    public List<Admin> getAdmins() {
        return admins;
    }

    public void setAdmins(List<Admin> admins) {
        this.admins = admins;
    }
    
    public List<String> getPrivilege() {
        return privilege;
    }

    public void setPrivilege(List<String> privilege) {
        this.privilege = privilege;
    }

    public List<String> getSelectedPrivs() {
        return selectedPrivs;
    }

    public void setSelectedPrivs(List<String> selectedPrivs) {
        this.selectedPrivs = selectedPrivs;
    }

    public List<privileges> getMesprivileges() {
        return mesprivileges;
    }

    public void setMesprivileges(List<privileges> mesprivileges) {
        this.mesprivileges = mesprivileges;
    }

    public dao_Agent getServiceagent() {
        return serviceagent;
    }
    public void setUpdateuseradmin(boolean updateuseradmin) {
        this.updateuseradmin = updateuseradmin;
    }

    public cryptpasswords getEncryption() {
        return encryption;
    }

    public void setEncryption(cryptpasswords encryption) {
        this.encryption = encryption;
    }

    public String getUserKey() {
        return userKey;
    }

    public void setUserKey(String userKey) {
        this.userKey = userKey;
    }

    public List<Mission> getMissionStat() {
        return MissionStat;
    }

    public void setMissionStat(List<Mission> MissionStat) {
        this.MissionStat = MissionStat;
    }

    public String getNomDepartement() {
  
String dep=this.serviceagent.chefoupas(this.mission.getAgent()).getNomDep();
if (dep==null){
    
Departement d2 =this.mission.getAgent().getAgentAffecter();
  return d2.getNomDep();//pas chef
}else 
{  return dep;//chef

}  
    }

    public void setNomDepartement(String nomDepartement) {
        this.nomDepartement = nomDepartement;
    }

    public Departement getDepartement() {
        return Departement;
    }

    public void setDepartement(Departement Departement) {
        this.Departement = Departement;
    }

    public dao_Departement getServiceDepartement() {
        return serviceDepartement;
    }

    public void setServiceDepartement(dao_Departement serviceDepartement) {
        this.serviceDepartement = serviceDepartement;
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

    public String getMessageupdate1() {
        return Messageupdate1;
    }

    public void setMessageupdate1(String Messageupdate1) {
        this.Messageupdate1 = Messageupdate1;
    }

    public Mission getModifMission() {
        return modifMission;
    }

    public void setModifMission(Mission modifMission) {
        this.modifMission = modifMission;
    }

   
    public String getVilles() {
        return villes;
    }

    public void setVilles(String villes) {
        this.villes = villes;
    }

    public int getMatriculeMission() {
        return matriculeMission;
    }

    public void setMatriculeMission(int matriculeMission) {
        this.matriculeMission = matriculeMission;
    }
    
    

    public dao_Cloture getServiceCloture() {
        return serviceCloture;
    }

    public void setServiceCloture(dao_Cloture serviceCloture) {
        this.serviceCloture = serviceCloture;
    }

    public Cloture getCloture() {
        return cloture;
    }

    public void setCloture(Cloture cloture) {
        this.cloture = cloture;
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
    public Mission getMission() {
        return mission;
    }

    public void setMission(Mission mission) {
        this.mission = mission;
    }

    public dao_Mission getServiceMission() {
        return serviceMission;
    }

    public void setServiceMission(dao_Mission serviceMission) {
        this.serviceMission = serviceMission;
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
          
          this.userKey = this.sessionId.getRandomUUIDString()+"administrateur" ; // affectation de valeur uuid 
                    checkprivs() ; /** verification des privileges **/

          
          
          
          FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("userk", userKey); // Ajout de id de session
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
       context.getExternalContext().getSessionMap().remove("userk") ;
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
             this.selectedPrivs = new ArrayList<String>() ; 
             this.nouvelleadmin = new Admin();
            
             } 
       
       // verifier si
       else if (service.ifExistsAdmin(this.nouvelleadmin.getMatricule()) == true && service.ifAdminvalid(this.nouvelleadmin.getUsername()) == false )
       {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erreur!", "Matricule Existant."));
            this.selectedPrivs = new ArrayList<String>() ;  
            this.nouvelleadmin = new Admin();
            
       }
       else if (service.ifExistsAdmin(this.nouvelleadmin.getMatricule()) == false && service.ifAdminvalid(this.nouvelleadmin.getUsername()) == true ) 
       {
           FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erreur!", "Username Existant."));
           this.selectedPrivs = new ArrayList<String>() ;  
           this.nouvelleadmin = new Admin();
           
       }
       else {
           if (this.selectedPrivs.contains("ALL")){ // si le privilege du super admin a ete choisit donc les autres privileges ne seront pas necessaire
               this.selectedPrivs = new ArrayList<String>() ;
               this.selectedPrivs.add("ALL") ;
           }
           if (this.selectedPrivs.contains("GA") && this.selectedPrivs.contains("GM"))
           {
               this.selectedPrivs.remove("GA") ;
               this.selectedPrivs.remove("GM") ;
               this.selectedPrivs.add("GMA") ;
           }
       
         
           this.nouvelleadmin.setLes_privileges(affectprivileges()); // ajout des privileges
           this.nouvelleadmin.setMotDePasse(encrypt.cryptme(this.nouvelleadmin.getMotDePasse())) ;
           this.service.ajouter(this.nouvelleadmin);
        
        FacesContext f=FacesContext.getCurrentInstance();
        f.addMessage(null,new FacesMessage("Ajout effectué"));
        
        
        this.nouvelleadmin = new Admin(); // pour l'initialisation du formulaire apré l'ajout  
        this.selectedPrivs = new ArrayList<String>() ; // initialisation du checkbox    
       
                      }              
        }
    // </editor-fold>
    
    // <editor-fold desc="allow privileges Method" defaultstate="collapsed">
    //activer les privileges 
   public void allow(List<privileges> privileges)
    {
        for(int i = 0 ; i < privileges.size() ; i++)
        {
            if ( privileges.get(i).getLibelle().equals("ADDuser") )
            {
                this.addagent = true;
                
            }
            if ( privileges.get(i).getLibelle().equals("UPuser") )
            {
                this.editagent = true;
                
            }
           
            if ( privileges.get(i).getLibelle().equals("GA") )
            {
                this.gestassurance = true;
                
            }
            if ( privileges.get(i).getLibelle().equals("GM") )
            {
                this.gestmission = true;
                
            }
            if ( privileges.get(i).getLibelle().equals("UPuseradmin") )
            {
                this.updateuseradmin = true;
                
            }
            if ( privileges.get(i).getLibelle().equals("GMA") )
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
 
    
        
        List<privileges> newList = new ArrayList<privileges>()  ;
             newList = this.admin.getLes_privileges() ;
                
        for (int i= 0 ; i<newList.size() ; i++){
            
            if (newList.get(i).getLibelle().equals("ALL")) //superAdmin
            {
            this.addagent = true;
            this.adduseradmin = true;
            this.editagent = true;
            this.gestmission = true;
            this.gestassurance = true ;
            this.updateuseradmin = true ;
            stop = true ;
            }
        }
        
        
        if ( stop == false)
        {
            allow(newList) ; // allow privileges access
        }
       
    }
    
    
    
    public List<privileges> affectprivileges() //compare les privileges qui ont étais choisit et les privileges qui sont dans la tables BD
    {
         privileges priv = new privileges();
          List<privileges> prv = new ArrayList();
         for(int i = 0 ; i<mesprivileges.size(); i++)
         {
           if( this.selectedPrivs.contains(mesprivileges.get(i).getLibelle()) )
           {
             
               priv.setCode_privilege(mesprivileges.get(i).getCode_privilege());
               priv.setLibelle(mesprivileges.get(i).getLibelle());
               prv.add(priv) ;
               priv = new privileges();
             
           }
            
         }
         return prv;
    }
     //</editor-fold>     
   
    //<editor-fold desc="Ajout prevision Method" defaultstate="collapsed" >
    
    public void ajouterprevision()
    {
        prevision p=new prevision();
            p.setTotal(this.updateprev.getFdiver()
                                   +this.updateprev.getFtransport()
                                  +this.updateprev.getFhebergement());
            p.setFdiver(this.updateprev.getFdiver());
            p.setFhebergement(this.updateprev.getFhebergement());
           p.setFtransport(this.updateprev.getFtransport());
           p.setPrixEssence(this.updateprev.getPrixEssence());
            service.addprevision(p);
    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Terminer!", "prevision Ajouter."));

          
        
    }
    
    
    
    
     
    
    
    
    
    
    //</editor-fold>
    
    //<editor-fold desc="update prevision" defaultstate="collapsed" >
       public void updateprevis()  throws IOException {
       
           // calcul total 
           
              this.updateprev.setTotal(this.updateprev.getFdiver()
                                   +this.updateprev.getFtransport()
                                  +this.updateprev.getFhebergement());
              service.updateprevision(this.updateprev); 
          
         FacesContext f=FacesContext.getCurrentInstance();
        f.addMessage(null,new FacesMessage("Ajout effectuer"));
               
       }
          
       
             //</editor-fold>
       
    // <editor-fold desc="Cloture" defaultstate="collapsed">    
     public List<Mission> ListerMissionNonCloturer(){
         return this.serviceMission.listMissionnoncloturer();
         }
          
         public void CloturerMission(Mission mission) throws IOException{
             this.mission=mission;
          
        this.villes= this.serviceMission.StringVille(this.mission);
                     FacesContext.getCurrentInstance().getExternalContext().redirect("FinMission.xhtml");             
         }

           
    public void AjoutCloture (){
        this.cloture.setCodeMission(this.mission);
        this.serviceCloture.ajoutCloture(cloture);
        this.serviceMission.terminerMission(mission);
    } 
     public void AjoutVehicule(){
     this.serviceVehicule.AjoutVehicule(this.vehicule);
   FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Terminer!", "Vehicule Ajouter."));
}
    
    //</editor-fold>
    
    // <editor-fold desc="Update Delete Mission Methods" defaultstate="collapsed">
    public void leadstoupdateMission() throws IOException
       {
    
            this.modifMission = this.serviceMission.RetourMission(this.matriculeMission) ;
            
             if (this.modifMission != null){
     
          FacesContext.getCurrentInstance().getExternalContext().redirect("missiondetail.xhtml");}
          else{
          FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erreur!", "Matricule Mission inexistante."));
                  }
                  }
             
       public void updateMission() throws IOException
       {
           this.serviceMission.updateMission(this.modifMission);
           this.Messageupdate1 = "Mission Modifier!" ;
           FacesContext.getCurrentInstance().getExternalContext().redirect("deletemission.xhtml");
       }
       
                  
       public void deleteMission() throws IOException
       {
           this.serviceMission.deleteMission(this.modifMission);
           this.Messageupdate1 = "Mission Supprimer!" ;
             FacesContext.getCurrentInstance().getExternalContext().redirect("deletemisson.xhtml");
       }
       //</editor-fold>
    
    // <editor-fold desc="Update Delete Super Admin" defaultstate="collapsed">
    public void leadstoupdate() throws IOException {
       
      if(
          this.service.ifExistsAdmin(this.matricule)){
          this.nouvelleadmin = this.service.ifExistsAdminobject(this.matricule) ;
          
          FacesContext.getCurrentInstance().getExternalContext().redirect("admindetail.xhtml");
      }else{
          FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erreur!", "Admin inexistante."));
      }
          
      
       } 
      public void updateAdmin() throws IOException, NoSuchAlgorithmException
      {
          
          this.Messageupdate1 = "Operation effectuer , Admin modifier" ;
          if ( !this.pwd.isEmpty()){
              this.nouvelleadmin.setMotDePasse(encryption.cryptme(this.pwd))  ;
              this.pwd = "";
          }
          
          if ( !this.selectedPrivs.isEmpty())
          {
             
              this.nouvelleadmin.setLes_privileges(affectprivileges()); 
              this.selectedPrivs.clear(); ;
          } 
          
          this.service.updateadmin(this.nouvelleadmin);
           this.nouvelleadmin = new Admin();
           
          FacesContext.getCurrentInstance().getExternalContext().redirect("listeadministrateur.xhtml");
      }
  
   public List<Admin> tableAdmin()
     {
        return  this.admins = service.listerAdmin() ;
     }
     
  public void deleteAdmin() throws IOException
     {
         this.Messageupdate1 = "Operation effectuer , Admin supprimer" ;
         this.service.delete(this.nouvelleadmin);
          this.nouvelleadmin = new Admin();
           FacesContext.getCurrentInstance().getExternalContext().redirect("listeadministrateur.xhtml");
     }
   //</editor-fold>
 
    // <editor-fold desc="add dep , listeMission , imprimer" defaultstate="collapsed">
  public void AjoutDepartement(){
   this.serviceDepartement.AjoutParNom(this.Departement.getNomDep());
   FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Terminer!", "Departement Ajouter."));

  }
 public List<Mission> ListerMission(){
 return this.serviceMission.listerLesMission();}
 
 
 public void imprimer(Mission mission) throws IOException{
 this.mission=mission;
         this.villes= this.serviceMission.StringVille(this.mission);

 FacesContext.getCurrentInstance().getExternalContext().redirect("FormPrint.xhtml");             

 
 }

// </editor-fold> 
 
public void AnnulerMission() throws IOException{
    this.mission=this.serviceMission.RetourMission(matriculeMission);
this.service.AnnulerMission(this.mission,this.admin);
this.Messageupdate1 = "Operation effectuer ,Mission Annuler" ;
FacesContext.getCurrentInstance().getExternalContext().redirect("operationmission.xhtml");
this.mission=new Mission();

}
public List<Mission> ListerlesMissionsAValiderRH(){
 return this.service.ListerlesMissionsNonValiderRH();}
 
 public void ValiderMission(){
 this.service.ValiderMissionRH(mission);}
 
 public void FormValid(Mission m) throws IOException{
        this.mission=m;
             FacesContext.getCurrentInstance().getExternalContext().redirect("FormValid.xhtml");
    }
 

 
    public List<String> ListerDep(){
    return this.serviceDepartement.ListerLesDepartement();
    }

public void StatDate() throws IOException, ParseException{
          FacesContext.getCurrentInstance().getExternalContext().redirect("MissionStat.xhtml"); // redirection vers la page d'acceuil apré une verification de l'utilisateur
this.MissionStat=this.serviceagent.ListerLesMissionClotureParDate(this.date1,this.date2);
this.Stat=this.serviceagent.LesFraixdesMissionClotureParAgent(this.MissionStat);

}
public void StatAgent() throws IOException, ParseException{
FacesContext.getCurrentInstance().getExternalContext().redirect("MissionStat.xhtml"); // redirection vers la page d'acceuil apré une verification de l'utilisateur
if (this.date1==null || this.date2==null){
this.MissionStat=this.serviceagent.ListerlesMissionClotureParAgent(this.matricule);
this.Stat=this.serviceagent.LesFraixdesMissionClotureParAgent(this.MissionStat);
}else{
this.MissionStat=this.serviceagent.ListerlesMissionClotureParAgentParDate(this.matricule, this.date1, this.date2);
this.Stat=this.serviceagent.LesFraixdesMissionClotureParAgent(this.MissionStat);

}
}

public void StatDepartement() throws IOException, ParseException{
    
FacesContext.getCurrentInstance().getExternalContext().redirect("MissionStat.xhtml"); // redirection vers la page d'acceuil apré une verification de l'utilisateur
if (this.date1==null || this.date2==null){
this.MissionStat=this.serviceagent.ListerLesMissionClotureDesAgents(this.serviceDepartement.TousLesAgentDepartement(this.serviceDepartement.RechercheDepParNom(this.Departements)));
this.Stat=this.serviceagent.LesFraixdesMissionClotureParAgent(this.MissionStat);}
else{
this.MissionStat=this.serviceagent.ListerLesMissionClotureDesAgentsParDate(this.serviceDepartement.TousLesAgentDepartement(this.serviceDepartement.RechercheDepParNom(this.Departements)),this.date1,this.date2);
this.Stat=this.serviceagent.LesFraixdesMissionClotureParAgent(this.MissionStat);
        }
}



public void rejet(){
this.mission.setRejet(this.Rejet);
this.service.AffecterRejet(mission);
    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Terminer!", "prevision Ajouter."));

}


}
