/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import beans.Agent;
import beans.Mission;
import beans.ville;
import dao.dao_Agent;
import dao.dao_Mission;
import dao.dao_ville;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
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
 
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
 
import org.primefaces.PrimeFaces;
import org.primefaces.event.SelectEvent;
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
      private String Message;
     
    
    /** variables **/
     private int matricule;
     private String password;
     private String SessionKey ;
     private String Departement  ;
     private String grade;
     /** Tables **/ 
    private List<Agent> agents ;
   
    /** Objects **/
    dao_Agent service = new dao_Agent();
    Agent agent = new Agent(); // connected 
    Agent nouvelleagent = new Agent(); // the new one 
    Mission mission= new Mission();
    dao_Mission serviceMission= new dao_Mission();
    dao_ville serviceville= new dao_ville();
    SessionKeyGen sessionId= new SessionKeyGen() ; // generateur d'id de session (UUID)
    cryptpasswords encryption = new cryptpasswords() ; // SHA ENCRYPTION
    String Villes=new String();
    
    
    public modele_agent() {
    }
    
    @PostConstruct
    public void modele_agent() {
        
       
   
        //this.Departement.add("Resource Humaine") ; 
        
         cities = new ArrayList<String>();
      List<ville> l=new ArrayList<>();
      l=d.ListerVille();
      for (ville str:l)
          cities.add(str.toString());
       
    }
   
    
  

    // <editor-fold desc="getters and setters" defaultstate="collapsed">
  
      public String getVilles() {
        return Villes;
    }
    public void setVilles(String Villes) {
        this.Villes = Villes;
    }

    public String[] getSelectedCities2() {
        return selectedCities2;
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
        return Departement;
    }

    public void setDepartement(String Departement) {
        this.Departement = Departement;
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
    
    //</editor-fold>
    
    
     public void onDateSelect(SelectEvent event) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Date Selected", format.format(event.getObject())));
    }
     
    public void click() {
        PrimeFaces.current().ajax().update("form:display");
        PrimeFaces.current().executeScript("PF('dlg').show()");
    }
 
   
    
    
       // <editor-fold desc="Login Agent" defaultstate="collapsed">
    public void logmein() throws IOException, NoSuchAlgorithmException
  {
      if (service.ifcanbelogged(agent.getMatricule(),encryption.cryptme(agent.getMotDePasse()))) // verification de l'authentification
      {
      agent=service.ifExistsAgent(agent.getMatricule());
          this.SessionKey = this.sessionId.getRandomUUIDString() ; // affectation de valeur uuid 
          FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("userkey", SessionKey); // Ajout de id de session
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
       context.getExternalContext().getSessionMap().remove("userkey") ;
       FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
       
  }
     //</editor-fold>
     
       // <editor-fold desc="new Agent" defaultstate="collapsed">
     /**creation d'un nouvelle agen
     * @throws java.security.NoSuchAlgorithmExceptiont**/
     public void ajouter() throws NoSuchAlgorithmException {
         
         if(!service.ifExists(this.nouvelleagent.getMatricule()) && (!service.ifExistsDirection(grade, Departement)))
         {   
             if (this.getGrade().equals("Directeur Departement")){
             this.nouvelleagent.setDirecteur(this.Departement);
             }else
                 this.nouvelleagent.setDirecteur(grade);
             this.nouvelleagent.setDepartement(this.Departement);
             this.nouvelleagent.setMotDePasse(encryption.cryptme(this.nouvelleagent.getMotDePasse()))  ; 
             service.ajouter(this.nouvelleagent);
             this.nouvelleagent = new Agent();
             FacesContext f=FacesContext.getCurrentInstance();
             f.addMessage(null,new FacesMessage("Ajout effectuer"));
             
         }else{
              FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erreur!", "Matricule Existant Ou Directeurs Existant."));
              this.nouvelleagent = new Agent();
              
         }
     }
     //</editor-fold>
     
       // <editor-fold desc="ajout mission" defaultstate="collapsed">
     // ajout d'une mission
     public void ajouterMission(){
     
         this.mission.setAgent(service.ifExistsAgent(this.agent.getMatricule()));
         
        mission.setAgent(agent);
        mission.setLes_villes(serviceville.StringTOVille(Arrays.asList(getSelectedCities2())));
        mission.setType(type);
        mission.setDateDeb(date1);
        mission.setDateFin(date2);
        serviceMission.ajoutMission(mission);
        
         this.mission=new Mission();
         
         FacesContext f=FacesContext.getCurrentInstance();
         f.addMessage(null,new FacesMessage("Ajout effectuer"));
        }
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
     //</editor-fold>
     
       // <editor-fold desc="Modifier Agent" defaultstate="collapsed">
     public void updateagent() throws IOException, NoSuchAlgorithmException
      {
          
          this.Message = "Operation effectuer , Agent modifier" ;
          this.nouvelleagent.setMotDePasse(encryption.cryptme(this.nouvelleagent.getMotDePasse()))  ;
          
          this.service.update(this.nouvelleagent);
           this.nouvelleagent = new Agent();
          FacesContext.getCurrentInstance().getExternalContext().redirect("users.xhtml");
      }
     //</editor-fold>
     
       // <editor-fold desc="suppression agent" defaultstate="collapsed">
     public void deleteAgent() throws IOException
     {
         this.Message = "Operation effectuer , Agent supprimer" ;
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
     
     public List<Mission>ListerMissionAValider()
     {
     return this.service.LesMissionAValiderDuChef(this.agent);
     }
     
     public void LesVillesString(){
     
        this.Villes= this.serviceMission.StringVille(this.mission);}
        // </editor-fold>
     
        // <editor-fold desc="print" defaultstate="collapsed">
    public void FormPrint(Mission m) throws IOException{
        
        this.mission=m;
         this.LesVillesString();
        FacesContext.getCurrentInstance().getExternalContext().redirect("FormPrint.xhtml");
    }
       public void FormValid(Mission m) throws IOException{
        this.mission=m;
             FacesContext.getCurrentInstance().getExternalContext().redirect("FormValid.xhtml");
    }
      //</editor-fold> 
       
       // <editor-fold desc="validation mission" defaultstate="collapsed">   
    public void ValiderMission() throws IOException{
   this.service.ValiderMission(this.mission);
            FacesContext.getCurrentInstance().getExternalContext().redirect("ValidationMission.xhtml");

    
    }
       //</editor-fold>
       
}
