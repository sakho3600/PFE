/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import beans.Agent;
import beans.Departement;
import beans.Mission;
import beans.prevision;
import beans.vehicule;
import beans.ville;
import dao.dao_Admin;
import dao.dao_Agent;
import dao.dao_Departement;
import dao.dao_Mission;
import dao.dao_Vehicule;
import dao.dao_ville;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import utilitaire.SessionKeyGen;
import javax.faces.context.FacesContext;
import javax.faces.application.FacesMessage;
import javax.faces.event.AjaxBehaviorEvent; 
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

 

import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;


import utilitaire.cryptpasswords ;
/**
 * 
 * @author Mohammed Mehdi Sarray#
 */
@ManagedBean
@SessionScoped
public class modele_agent {
    
    //Les villes
    private String[] selectedCities2;
    private List<String> cities;
    dao_ville d=new dao_ville();
     
    //Les dates
    private Date date1;
    private Date date2;
    
    
    // type de la mission
      private String type;
      private int matriculeMission ;
      private String Message; // update/delete agent message
     
      private String Rejet;
    
    /** variables **/
     private int matricule;
     private String password;
     private String SessionKey ;
     private String Departement  ;
     private String grade;
     private Float fdiver;
     private Float fhebergement;
     private Float ftransport;
     private Float prixEssance ;
     private Float ftotal;
     private Float prixGasoil;
     private String rejet;
     private int vehiculeMatricule ;
     private String LesFaisreel;
     
     /** Tables **/ 
    private List<Agent> agents ;
    private List<Mission> notification ;
    private List<vehicule> vehicule ;
    private int notif = 0 ;
   
    /** Objects **/
    dao_Vehicule serviceVehicule=new dao_Vehicule();
    dao_Departement serviceDepartement=new dao_Departement();
    dao_Admin serviceadmin = new dao_Admin() ;
    dao_Agent service = new dao_Agent();
    Agent agent = new Agent(); // connected 
    Agent nouvelleagent = new Agent(); // the new one 
    Mission mission= new Mission();
    dao_Mission serviceMission= new dao_Mission();
    dao_ville serviceville= new dao_ville();
    SessionKeyGen sessionId= new SessionKeyGen() ; // generateur d'id de session (UUID)
    cryptpasswords encryption = new cryptpasswords() ; // SHA256 ENCRYPTION
    String Villes=new String();
    prevision previsions = new prevision() ;
    vehicule  vh = new vehicule();
    
    
    public modele_agent() {
    }
    
    @PostConstruct
    public void modele_agent() {
 
         cities = new ArrayList<String>();
      List<ville> l=new ArrayList<>();
      
      l=d.ListerVille();
      
      for (ville str:l)
      {  cities.add(str.toString());}
      
       insertprevision();
       vehicule = serviceVehicule.listevehiculeDisponible() ;
   
    }
  
  

    // <editor-fold desc="getters and setters" defaultstate="collapsed">
    public void setServiceDepartement(dao_Departement serviceDepartement) {
        this.serviceDepartement = serviceDepartement;
    }

    public vehicule getVh() {
        return vh;
    }

    public void setVh(vehicule vh) {
        this.vh = vh;
    }
    
    
    public String getVilles() {
        return Villes;
    }
    public void setVilles(String Villes) {
        this.Villes = Villes;
    }

    public String[] getSelectedCities2() {
        return selectedCities2;
    }

        public String getRejet() {
        return Rejet;
    }

    public void setRejet(String Rejet) {
        this.Rejet = Rejet;
    }

    public dao_Departement getServiceDepartement() {
        return serviceDepartement;
    }

    public int getVehiculeMatricule() {
        return vehiculeMatricule;
    }

    public void setVehiculeMatricule(int vehiculeMatricule) {
        this.vehiculeMatricule = vehiculeMatricule;
    }

    public List<vehicule> getVehicule() {
        return vehicule;
    }

    public void setVehicule(List<vehicule> vehicule) {
        this.vehicule = vehicule;
    }

    public Float getPrixGasoil() {
        return prixGasoil;
    }

    public void setPrixGasoil(Float prixGasoil) {
        this.prixGasoil = prixGasoil;
    }

    public dao_Vehicule getServiceVehicule() {
        return serviceVehicule;
    }

    public void setServiceVehicule(dao_Vehicule serviceVehicule) {
        this.serviceVehicule = serviceVehicule;
    }
    
    
    
    public List<Mission> getNotification() {
        return notification;
    }

    public void setNotification(List<Mission> notification) {
        this.notification = notification;
    }
 
    public void setSelectedCities2(String[] selectedCities2) {
        this.selectedCities2 = selectedCities2;
    }
 
    public List<String> getCities() {
        return cities;
    }
    
     public String getType() {
        return type;
    }
 
    public void setType(String type) {
        this.type = type;
    }

    public Float getFdiver() {
        return fdiver;
    }

    public void setFdiver(Float fdiver) {
        this.fdiver = fdiver;
    }

    public Float getPrixEssance() {
        return prixEssance;
    }

    public void setPrixEssance(Float prixEssance) {
        this.prixEssance = prixEssance;
    }
    
    public Float getFhebergement() {
        return fhebergement;
    }

    public int getNotif() {
        return notif;
    }

    public void setNotif(int notif) {
        this.notif = notif;
    }

    public void setFhebergement(Float fhebergement) {
        this.fhebergement = fhebergement;
    }

    public Float getFtransport() {
        return ftransport;
    }

    public void setFtransport(Float ftransport) {
        this.ftransport = ftransport;
    }

    public Float getFtotal() {
        return ftotal;
    }

    public void setFtotal(Float ftotal) {
        this.ftotal = ftotal;
    }

   
    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }


    public int getMatricule() {
        return matricule;
    }

    public String getDepartement() {
/*  */
    return this.Departement;}

    public void setDepartement(String Departement) {
        this.Departement = Departement;
    }

    public int getMatriculeMission() {
        return matriculeMission;
    }

    public void setMatriculeMission(int matriculeMission) {
        this.matriculeMission = matriculeMission;
    }

    public String getLesFaisreel() {
        return LesFaisreel;
    }

    public void setLesFaisreel(String LesFaisreel) {
        this.LesFaisreel = LesFaisreel;
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
 public Date getDate1() {
        return date1;
    }
 
    public void setDate1(Date date1) {
        this.date1 = date1;
    }
 
    public Date getDate2() {
        return date2;
    }
 
    public void setDate2(Date date2) {
        this.date2 = date2;
    }

    public dao_ville getD() {
        return d;
    }

    public void setD(dao_ville d) {
        this.d = d;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String Message) {
        this.Message = Message;
    }

    public dao_ville getServiceville() {
        return serviceville;
    }

    public void setServiceville(dao_ville serviceville) {
        this.serviceville = serviceville;
    }

    public List<Agent> getAgents() {
        return agents;
    }

    public void setAgents(List<Agent> agents) {
        this.agents = agents;
    }

    public dao_Admin getServiceadmin() {
        return serviceadmin;
    }

    public void setServiceadmin(dao_Admin serviceadmin) {
        this.serviceadmin = serviceadmin;
    }

    public cryptpasswords getEncryption() {
        return encryption;
    }

    public void setEncryption(cryptpasswords encryption) {
        this.encryption = encryption;
    }

    public prevision getPrevisions() {
        return previsions;
    }

    public void setPrevisions(prevision previsions) {
        this.previsions = previsions;
    }
    
    public String DepAgent (){
    String dep=this.service.chefoupas(this.agent).getNomDep();
if (dep==null){
    
Departement d2 =this.agent.getAgentAffecter();
  return d2.getNomDep();//pas chef
}else 
{  return dep;//chef

}}
 
    public List<String> ListerDep(){
    return this.serviceDepartement.ListerLesDepartement();
    }
    //</editor-fold>
    
     // <editor-fold desc=" Events Methods" defaultstate="collapsed">   
     public void onDateSelect1(SelectEvent event) { 
        this.date1 = (Date) event.getObject();
        if (this.date2 != null ){ // le cas si on a changer la date debut mais pas la date fin
             calculMontant();
        }
        }
     public void onDateSelect2(SelectEvent event) {
        this.date2 = (Date) event.getObject();
        calculMontant();
       }
     
     public void onRowSelectutilisateur(SelectEvent event) {
         Agent id = (Agent) event.getObject() ;
         this.matricule = id.getMatricule() ;
        FacesMessage msg = new FacesMessage("Utilisateur choisit" );
        FacesContext.getCurrentInstance().addMessage(null, msg);
        
        
        
    }
     
     public void onRowSelect(SelectEvent event) {
         vehicule id = (vehicule) event.getObject() ;
        this.vh = fromVehiculeMatriculeToConsommation(id.getId()) ;
        FacesMessage msg = new FacesMessage("Vehicule choisit" );
        FacesContext.getCurrentInstance().addMessage(null, msg);
       
        onBlurkilometrage() ;
        
       /*
        if (this.mission.getKilometrage() != 0.0f) // verifier si un float est null
        {
            onBlurkilometrage() ;
        }*/
        
    }
       public void onBlurkilometrage() { 
          
         //  this.vh = fromVehiculeMatriculeToConsommation(this.vehiculeMatricule) ;
          if (this.vh != null) {
           if (vh.getCarburant().equals("Essence")) {
        this.ftransport = (((vh.getConsommation() / 100)  * this.mission.getKilometrage())  * this.prixEssance) ; 
                
         this.ftotal = this.fdiver + this.fhebergement + this.ftransport ;
           }
           else {
               this.ftransport = (((vh.getConsommation() / 100)  * this.mission.getKilometrage())  * this.prixGasoil) ; 
                
         this.ftotal = this.fdiver + this.fhebergement + this.ftransport ;
           }
          }
    }
       
      public vehicule fromVehiculeMatriculeToConsommation(int imMatricule) // getting the consommation from car matricule
      {
          vehicule result = new vehicule();
          
          for (int i = 0 ; i < this.vehicule.size() ; i++ )
          {
              if (this.vehicule.get(i).getId() == imMatricule) 
              { 
                  result = this.vehicule.get(i) ; 
                  i = this.vehicule.size();
              }
          }
          
          return result ;
      }
       
     //</editor-fold>
      
       // <editor-fold desc="Login Agent" defaultstate="collapsed">
    public void logmein() throws IOException, NoSuchAlgorithmException
  {
      if (service.ifcanbelogged(agent.getMatricule(),encryption.cryptme(agent.getMotDePasse()))) // verification de l'authentification
      {
      agent=service.ifExistsAgent(agent.getMatricule());
          this.SessionKey = this.sessionId.getRandomUUIDString() ; // affectation de valeur uuid 
          FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("userke", SessionKey); // Ajout de id de session
          FacesContext.getCurrentInstance().getExternalContext().redirect("mission/welcome.xhtml"); // redirection vers la page d'acceuil apré une verification de l'utilisateur
         
          
      }else{ // retour a la page INDEX
	FacesContext context=FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Matricule ou Mot de passe incorrect")); // Message d'erreur
         }
     
      
  
  }
    //</editor-fold>
    
       // <editor-fold desc="logout" defaultstate="collapsed">
     public void logout() throws IOException // deconnexion
  {
      FacesContext context = FacesContext.getCurrentInstance(); 
       context.getExternalContext().getSessionMap().remove("userke") ;
       FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
       
  }
     //</editor-fold>
     
       // <editor-fold desc="Ajout Agent" defaultstate="collapsed">
     /**creation d'un nouvelle agent
     * @throws java.security.NoSuchAlgorithmExceptiont**/
     public void ajouter() throws NoSuchAlgorithmException {
         
         if(!service.ifExists(this.nouvelleagent.getMatricule()) )
         {              Departement dep=new Departement();
                 dep=this.serviceDepartement.RechercheDepParNom(this.Departement);

             if (this.getGrade().equals("Directeur Departement")){
              //Departement dep=new Departement();
                 dep.setAgentDirige(this.nouvelleagent);
                 Departement directionG=this.serviceDepartement.RechercheDepParNom("Direction General");
                 this.nouvelleagent.setAgentAffecter(directionG);
                 this.serviceDepartement.AjoutDirecteurDepartement(this.nouvelleagent, dep);
//setDirecteur(this.Departement);
             }else if (this.getGrade().equals("Personnel")){
                this.nouvelleagent.setAgentAffecter(dep);}
                this.nouvelleagent.setMotDePasse(encryption.cryptme(this.nouvelleagent.getMotDePasse()))  ;
                /* this.nouvelleagent.setDirecteur(grade);
             this.nouvelleagent.setDepartement(this.Departement);
             this.nouvelleagent.setMotDePasse(encryption.cryptme(this.nouvelleagent.getMotDePasse()))  ; 
            */    service.ajouter(this.nouvelleagent);
         
                this.nouvelleagent = new Agent();
             FacesContext f=FacesContext.getCurrentInstance();
             f.addMessage(null,new FacesMessage("ajout effectué"));
             
         }else{
              FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erreur!", "Matricule Existant Ou Directeurs Existant."));
              this.nouvelleagent = new Agent();
              
         }
     }
     //</editor-fold>
     
       // <editor-fold desc="ajout mission" defaultstate="collapsed">
     // ajout d'une mission
     public void ajouterMission() throws ParseException{
     
         this.mission.setAgent(service.ifExistsAgent(this.agent.getMatricule()));
         
        mission.setAgent(agent);
        mission.setLes_villes(serviceville.StringTOVille(Arrays.asList(getSelectedCities2())));
        mission.setType(type);
        mission.setDateDeb(date1);
        mission.setDateFin(date2);
        mission.setFdiver(this.fdiver);
        mission.setFhebergement(this.fhebergement);
        mission.setFtransport(this.ftransport);
        this.vh.setDisponibiliter("0");
        mission.setVehicule(this.vh);
        serviceVehicule.Updatevehicule(this.vh);
        this.vehicule = new ArrayList<vehicule>() ;
        this.vehicule = serviceVehicule.listevehiculeDisponible() ;
        
       if (this.serviceMission.compareDate(this.mission.getDateDeb(), this.mission.getDateFin())){
          FacesContext f=FacesContext.getCurrentInstance();
         f.addMessage(null,new FacesMessage("Erreur date!!"+(this.serviceMission.compareDate(this.mission.getDateDeb(), this.mission.getDateFin()))));
     
        }else{
        
        
        serviceMission.ajoutMission(mission);
        
         this.mission=new Mission();
         insertprevision() ;
         this.date1 = null;
         this.date2 = null;
         this.type=null;
         this.selectedCities2=null;
         
         FacesContext f=FacesContext.getCurrentInstance();
         f.addMessage(null,new FacesMessage("Ajout effectuée"));
        }
     this.mission=new Mission();}
        // </editor-fold>
     
       // <editor-fold desc="redirect user detail" defaultstate="collapsed">
    // redirect to page edit page
     public void leadstoupdate() throws IOException {
       
      if(
          this.service.ifExists(this.matricule)){
          this.nouvelleagent = this.service.ifExistsAgent(this.matricule) ;
          
          FacesContext.getCurrentInstance().getExternalContext().redirect("agentdetail.xhtml");
      }else{
          FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erreur!", "Matricule inexistante."));
      }
          
      
       }  
       public List<Mission> ListerlesMissionValiderParAgent(){
     return this.service.ListerlesMissionValiderParAgent(this.agent.getMatricule());}
      
   
     //</editor-fold>
     
       // <editor-fold desc="Modifier Agent" defaultstate="collapsed">
     public void updateagent() throws IOException, NoSuchAlgorithmException
      {
          
          this.Message = "opération effectuée  , Agent modifier" ;
          this.nouvelleagent.setMotDePasse(encryption.cryptme(this.nouvelleagent.getMotDePasse()))  ;
          
         // this.service.update(this.nouvelleagent);
           this.nouvelleagent = new Agent();
          FacesContext.getCurrentInstance().getExternalContext().redirect("users.xhtml");
      }
     //</editor-fold>
     
       // <editor-fold desc="suppression agent" defaultstate="collapsed">
     public void deleteAgent() throws IOException
     {
         this.Message = "opération effectuée  , Agent supprimer" ;
         this.service.delete(this.nouvelleagent);
          this.nouvelleagent = new Agent();
           FacesContext.getCurrentInstance().getExternalContext().redirect("users.xhtml");
     }
     //</editor-fold>
     
       // <editor-fold desc="Lists" defaultstate="collapsed">
     public List<Agent> tableAgent()
     {
        return  this.agents = service.listerAgent() ;
     }
     
     
     public List<Mission> ListerMission()
     {
        
     return this.service.ListerlesMissionParAgent(this.agent.getMatricule());
     }
     
      
     public List<Mission> ListerMissionRejeter()
     {
        
     return this.service.ListerlesMissionRejeterParAgent(this.agent.getMatricule());
     }
     
     public List<Mission>ListerMissionAValider()
     {
         String Poste=this.service.chefoupas(this.agent).getNomDep();
        if (Poste==null)
            return null;
        else if (Poste.equals("Direction General"))
             return this.service.ListerlesMissionsNonValiderParDirecteur();
         else 
     return this.service.LesMissionAValiderDuChef(this.agent);
     }
     
     
     public void LesVillesString(){
     
        this.Villes= this.serviceMission.StringVille(this.mission);}
        // </editor-fold>
     
       // <editor-fold desc="print" defaultstate="collapsed">
    public void FormPrint(Mission m) throws IOException{
        
        this.mission=m;
         this.LesVillesString();
         this.LesFaisreel=this.service.LesFraixduneMissionCloture(mission);
        FacesContext.getCurrentInstance().getExternalContext().redirect("FormPrint.xhtml");
    }
      public void FormPrintRejet(Mission m) throws IOException{
        
        this.mission=m;
         this.LesVillesString();
        FacesContext.getCurrentInstance().getExternalContext().redirect("FormPrintRejet.xhtml");
    }
       public void FormValid(Mission m) throws IOException{
        this.mission=m;
             FacesContext.getCurrentInstance().getExternalContext().redirect("FormValid.xhtml");
    }
      //</editor-fold> 
       
       // <editor-fold desc="validation mission" defaultstate="collapsed">   
    public void ValiderMission() throws IOException{
   this.service.ValiderMission(this.mission,this.agent);
            FacesContext.getCurrentInstance().getExternalContext().redirect("ValidationMission.xhtml");

    
    }
       //</editor-fold>
    
       // <editor-fold desc="Cloture mission" defaultstate="collapsed">   
  
    public void ClotureMission() throws IOException{
    this.Message=this.serviceMission.ClotureMission(mission, agent);
    vehicule vehi = mission.getVehicule() ; 
    vehi.setDisponibiliter("1");
  serviceVehicule.Updatevehicule(vehi);
  vehicule = serviceVehicule.listevehiculeDisponible() ;
    FacesContext.getCurrentInstance().getExternalContext().redirect("LesMissions.xhtml");}

    
           //</editor-fold>

       // <editor-fold desc="données previsionnels" defaultstate="collapsed">   
    public void insertprevision()
    {
        previsions = serviceadmin.getprevision(); // information du budget
       this.fdiver = previsions.getFdiver() ;
       this.fhebergement = previsions.getFhebergement();
       this.ftransport = previsions.getFtransport();
       this.prixEssance = previsions.getPrixEssence() ;
       this.prixGasoil = previsions.getPrixGasoil();
    }
    /*public void notifica()
        {
          if ( this.service.LesMissionAValiderDuChef(this.agent) == null )
          {
           this.notif = 0;
           
          }else{
              this.notif = this.service.LesMissionAValiderDuChef(this.agent).size() ;
          }
        }
   */ //</editor-fold>
        
       // <editor-fold desc="Calcul des montants " defaultstate="collapsed"> 
    public void calculMontant()
       {
           
          final long MILLISECONDS_PER_DAY = 1000 * 60 * 60 * 24; 
 Long delta = (this.date2.getTime() - this.date1.getTime()) / (MILLISECONDS_PER_DAY);
       
         if (delta == 0) { // une mission sans hebergement
          this.fhebergement = 0F;
           
           this.fdiver =  this.previsions.getFdiver() ;
           
           this.ftotal = this.fdiver + this.fhebergement + this.ftransport ;
           }
       

        if (delta >= 1 ) { // 
          this.fhebergement = 100 * (float) delta;
           
           this.fdiver = delta * this.previsions.getFdiver() ;
           
           this.ftotal = this.fdiver + this.fhebergement + this.ftransport ;
           }
       }
    
 //</editor-fold>
           
       // <editor-fold desc="Annulation mission " defaultstate="collapsed"> 
  public void AnnulerMission() throws IOException{
  
  this.Message=this.serviceMission.AnnulerMission(mission, agent);
  vehicule vehi = mission.getVehicule() ; 
  vehi.setDisponibiliter("1");
  serviceVehicule.Updatevehicule(this.vh);
  vehicule = serviceVehicule.listevehiculeDisponible() ;
    FacesContext.getCurrentInstance().getExternalContext().redirect("AnnulerMission.xhtml");}

  
    
    
     //</editor-fold>
      
       
       // <editor-fold desc="Modifier mission " defaultstate="collapsed"> 
public void ModifierMission() throws IOException{
this.mission=this.serviceMission.RetourMission(this.mission.getCodeMission());
if (this.mission==null){
    this.Message="Mission innexistante";
    this.mission=new Mission();
    FacesContext.getCurrentInstance().getExternalContext().redirect("AnnulerMission.xhtml");
    
}

else if (this.mission.getAgent().getMatricule()!=agent.getMatricule()){
   this.Message="Vous n'avez pas les droit sur cette mission";
    FacesContext.getCurrentInstance().getExternalContext().redirect("AnnulerMission.xhtml");}
else 

{
    this.Message=null;
this.vehiculeMatricule=this.mission.getVehicule().getId();

    this.LesVillesString();
        FacesContext.getCurrentInstance().getExternalContext().redirect("ModifierMission.xhtml");
       
 this.selectedCities2=new String[this.mission.Les_villes.size()];
     int i=0;
    
        for (ville v:this.mission.Les_villes)
    {   this.selectedCities2[i]=v.toString();
        i++;
    }
        this.date1=this.mission.getDateDeb();
                this.date2=this.mission.getDateFin();
                this.type=this.mission.getType();

    }}

     public void ModifierLaMission() throws ParseException, IOException{
     
        mission.setLes_villes(serviceville.StringTOVille(Arrays.asList(getSelectedCities2())));
        mission.setType(type);
        mission.setDateDeb(date1);
        mission.setDateFin(date2);
        mission.setFdiver(this.fdiver);
        mission.setFhebergement(this.fhebergement);
        mission.setFtransport(this.ftransport);
      vehicule v;
      v=this.serviceVehicule.recherchevehicule(this.vehiculeMatricule);
        if (this.serviceMission.compareDate(this.mission.getDateDeb(), this.mission.getDateFin())){
          FacesContext f=FacesContext.getCurrentInstance();
         f.addMessage(null,new FacesMessage("Erreur date!!"));
     
        }
        else if (v==null){
           FacesContext f=FacesContext.getCurrentInstance();
         f.addMessage(null,new FacesMessage("Erreur vehicule inexistant!!"));
     
        }
        else{
                mission.setVehicule(this.serviceVehicule.recherchevehicule(this.vehiculeMatricule));

        
        this.Message=serviceMission.ModifierMission(mission);
        
         this.mission=new Mission();
         insertprevision() ;
         this.date1 = new Date();
         this.date2 = new Date() ;
         this.vehiculeMatricule=0;
         
    FacesContext.getCurrentInstance().getExternalContext().redirect("AnnulerMission.xhtml");
 }}
   
  //</editor-fold>



public void rejet() throws IOException{
this.mission.setRejet(this.Rejet);
this.service.AffecterRejet(mission);
vehicule vehi = mission.getVehicule() ; 
  vehi.setDisponibiliter("1");
  serviceVehicule.Updatevehicule(vehi);
  vehicule = serviceVehicule.listevehiculeDisponible() ;
FacesContext.getCurrentInstance().getExternalContext().redirect("ValidationMission.xhtml");

}   

}

       
        


