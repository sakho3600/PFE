/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import beans.Admin;
import beans.Agent;
import beans.Cloture;
import beans.Departement;
import beans.Mission;
import beans.Personnel;
import beans.Personnel;
import beans.vehicule;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.hibernate.Query ;
import org.hibernate.Session;
import utilitaire.HibernateUtil;
/**
 *
 * @author Mohammed Mehdi Sarray#
 */
public class dao_Agent {
    
      private Session s ; // Hibernate Session 
  
    /* ouvrire une session Hibernate */
    private void openSession(){
        s=HibernateUtil.getSessionFactory().openSession();
        s.beginTransaction();
    }
    /* fermer une session Hibernate */
    private void closeSession(){
       if (!s.getTransaction().wasCommitted())
       { s.getTransaction().commit();
        s.close();}
    }
    
       private String grade; 
    /**
     * 
     * Verifier si un Employer Existe dans la base 
     * avant de l'ajouter
     *  
     * @param employ
     * @return 
     */
    public Agent ifExistsAgent(int matricule)
    {  
       Agent result = new Agent();
       
        try{
        openSession();
         result = (Agent) s.get(Agent.class,matricule) ;   
        closeSession();
           }catch(Exception e){
	e.printStackTrace();
        }
         return result ;
                
    }
    
    public boolean ifExistsDirection(String Direction,String Departement){
       boolean resultat=false ;
    
     try{openSession();
       
       if (!Direction.equals("Personnel")){
     Query query = s.createQuery("from Personnel where Directeur= :code ");
    if (Direction.equals("Directeur Generale"))
                    query.setParameter("code", Direction);
    else if (Direction.equals("Directeur Departement"))
                    query.setParameter("code", Departement);
           
        if(!query.list().isEmpty())
            resultat =true;
                           
    

       }

closeSession();  
               }catch(Exception e){
	e.printStackTrace();
       
        }   
     return resultat;   
    }
    
    public boolean ifExists(int matricule)
    {  
       Agent result = new Agent();
       boolean resultat = false  ;
       
        try{
        openSession();
         result = (Agent) s.get(Agent.class,matricule) ;
         if(result !=  null)
         {
             resultat = true;
         }
        closeSession();
           }catch(Exception e){
	e.printStackTrace();
        }
         return resultat ;
                
    }
    
    
    public boolean ifcanbelogged(int matricule,String mdp)
    {
         
        Agent agent = ifExistsAgent(matricule) ;
        
       if ( agent == null)
       {
        return false ;   
       }
       else  if(agent.getMotDePasse().equals(mdp)) {
           return true;
           
       }else {
           return false ;
       }

    }
    
    public void CreationMission(int Matricule,Mission m){
        try{      
        openSession();
               Agent a=(Agent) s.get(Agent.class,Matricule);
               Set<Mission> l= new HashSet<>();
               l.add(m);
               a.setMissions(l);
               m.setAgent(a);
               s.save(m);

closeSession();  
               }catch(Exception e){
	e.printStackTrace();
       
        }       }
    
    public List<Mission> ListerlesMissionParAgent(int Matricule){
                   List<Mission> l= new ArrayList<>();

        try{          
        openSession();
                    Query query = s.createQuery("from Mission where Matricule = :code ");
                    query.setParameter("code", Matricule);
                     if(query.list().isEmpty()){
                     l=null;}
                     else{
                       l = query.list();
                   }

closeSession();  
               }catch(Exception e){
	e.printStackTrace();
       
        }       return l;}
    

    public List<Mission> ListerlesMissionRejeterParAgent(int Matricule){
                          List<Mission> l= new ArrayList<>();

        try{           
        openSession();
                    Query query = s.createQuery("from Mission where Matricule = :code and Status=:stat ");
                    query.setParameter("code", Matricule);
                    query.setParameter("stat", "Rejeter");
                    if(query.list().isEmpty()){
                     l=null;}
                     else{
                       l = query.list();
                   }
closeSession();  
               }catch(Exception e){
	e.printStackTrace();
       
        }     return l;}
    
    public List<Mission> ListerlesMissionClotureParAgent(int Matricule){
                   List<Mission> l= new ArrayList<>();
try{
        openSession();
                    Query query = s.createQuery("from Mission where Matricule = :code and Status='Colturé'");
                    query.setParameter("code", Matricule);
                     if(!query.list().isEmpty()){
                       l = query.list();
                   }

closeSession();  
               }catch(Exception e){
	e.printStackTrace();
       
        }      return l;}
    
    
     
     
     
    /* Ajouter un agent */
   public void ajouter(Agent Employ)
   {
     //  this.AffectMatChef(Employ);
        try { 
    openSession() ;
             s.save(Employ);
    closeSession() ;
    }catch(Exception e){
	e.printStackTrace();
    }
    }
    
     public void AjouterAgentDepartement(Agent a,Departement d){
   
  try{
      openSession();
        a.setAgentAffecter(d);

        s.saveOrUpdate(a);

closeSession();  
               }catch(Exception e){
	e.printStackTrace();
       
        }   }
/*   public void AffectMatChef(Agent Employ){
     
         Personnel p=new Personnel();
       if (Employ.getDirecteur().equals("Directeur Generale"))
            Employ.setPersonnel(Employ);
       
       
       else if (Employ.getDirecteur().equals("Personnel")){      
        String Depart=Employ.getDepartement();
        try { 
    openSession() ; 
      Query query = s.createQuery("from Personnel where Directeur= :code ");
                    query.setParameter("code", Depart);
                    p=(Personnel)query.list().get(0);
               Employ.setPersonnel(p);
    closeSession() ;
    }catch(Exception e){
	e.printStackTrace();
    }}
       else {
            try { 
    openSession() ; 
     
         Query query = s.createQuery("from Personnel where Directeur= :code ");
                    query.setParameter("code", "Directeur Generale");
                    p=(Personnel)query.list().get(0);
                    Employ.setPersonnel(p);
       closeSession() ;
    }catch(Exception e){
	e.printStackTrace();
    }
               }}
   
   public void update(Agent Employ)
   {
       try { 
    openSession() ;
             s.update(Employ);
    closeSession() ;
    }catch(Exception e){
	e.printStackTrace();
    }
    
   }
   */
     
   public void delete(Agent Employ)
   {
       try { 
    openSession() ;
             s.delete(Employ);
    closeSession() ;
    }catch(Exception e){
	e.printStackTrace();
    }
    }
   
   
   public List<Agent> listerAgent()
   {
        List<Agent> listeAgent= new ArrayList<>();
       try { 
    openSession() ;
             String hql = "FROM Agent";
             Query query = s.createQuery(hql);
             listeAgent = query.list();
    closeSession() ;
    }catch(Exception e){
	e.printStackTrace();
       
        }
     return listeAgent ;

    }
//Close   
public Departement chefoupas(Agent Matricule){
    
    Departement rep=new Departement();  
    try { 
    openSession();             
                    Query query = s.createQuery("from Departement where agentDirige= :code ");
                    query.setParameter("code", Matricule);
                     if(!query.list().isEmpty())
                      rep= (Departement)query.list().get(0);
                    
         
              closeSession();  
               }catch(Exception e){
	e.printStackTrace();
       
        }
        return rep;
}

    public List<Agent> ListerAgentParChef(Agent Matricule){
                         

        Departement d=new Departement();
d=this.chefoupas(Matricule);  
            List<Agent> l= new ArrayList<>();

try{
   openSession();
                    if (d!=null){
                    Query query = s.createQuery("from Agent where AgentAffecter= :code ");
                    query.setParameter("code", d);
                     if(!query.list().isEmpty()){
                       l = query.list();
                                       }}
                     closeSession();  
               }catch(Exception e){
	e.printStackTrace();
       
        }
    return l;}
    
    
    public List<Mission> ListerlesMissionsNonValiderParDirecteur(){
                   List<Mission> l= new ArrayList<>();

        try{
        openSession();
                    Query query = s.createQuery("from Mission where ValidDirecturGeneral= 0 and Status= :stat ");
                    query.setParameter("stat", "En cours");
                    if(query.list().isEmpty()){
                     l=null;}
                     else{
                       l = query.list();
                   }
                 
    closeSession();  
               }catch(Exception e){
	e.printStackTrace();
       
        }
    return l;}
    
    
      public List<Mission> ListerlesMissionValiderParAgent(int Matricule){
                   List<Mission> l=new ArrayList<>();
                   try{
                       openSession();
                    Query query = s.createQuery("from Mission where Matricule = :code and Etat= 1 and ValidationRH=1 and Status= :stat");
                    query.setParameter("code", Matricule);
                    query.setParameter("stat", "En cours");
                    if(query.list().isEmpty()){
                     l=new ArrayList<>();}
                     else{
                       l = query.list();
                   }
 closeSession();  
               }catch(Exception e){
	e.printStackTrace();
       
        }
    return l;}
    
    
    public List<Mission> ListerlesMissionNonValiderParAgent(int Matricule){
                   List<Mission> l=new ArrayList<>();
                   try{
                       openSession();
                    Query query = s.createQuery("from Mission where Matricule = :code and Etat= 0 and Status= :stat");
                    query.setParameter("code", Matricule);
                    query.setParameter("stat", "En cours");
                    if(query.list().isEmpty()){
                     l=new ArrayList<>();}
                     else{
                       l = query.list();
                   }
                 
 closeSession();  
               }catch(Exception e){
	e.printStackTrace();
       
        }
               return l;}
    
   
    
    
    public List<Mission> ListerMissionNonValiderDesAgents(List<Agent> LAgent){
       if (LAgent==null){
           return null;
       }else{
        
        List<Mission>ListMiss=new ArrayList<>();       
        
        for (Agent ag:LAgent)
                {
                 List<Mission>List= ListerlesMissionNonValiderParAgent(ag.getMatricule());
                    if (List !=null)
                    {
                    ListMiss.addAll(List);
                    }
              }
        if (ListMiss!=null)
            return ListMiss;
        else 
            return null;
       }
    }
public List<Mission> LesMissionAValiderDuChef(Agent agent){
   
return  this.ListerMissionNonValiderDesAgents(this.ListerAgentParChef(agent));
}

public void ValiderMission(Mission m,Agent a){
try{
    openSession();
    if (this.chefoupas(a).getNomDep().equals("Direction General")){
        if (this.chefoupas(m.getAgent()).getNomDep()==null){
        m.setValidDirecturGeneral(1);
        }else {
            m.setValidDirecturGeneral(1);
            m.setEtat(1);
        }
    
    }else{
            m.setEtat(1);
    }
 
        openSession();

    s.saveOrUpdate(m);
    System.out.println("**************************");
    System.out.println(m.getEtat());
    System.out.println(m.getValidDirecturGeneral());
    System.out.println("**************************");

    closeSession();  
               }catch(Exception e){
	e.printStackTrace();
       
        }
}



    // <editor-fold desc="Stat" defaultstate="collapsed">

    public String LesFraixdesMissionClotureParAgent(List<Mission> LM){
         String Chain=new String();
        Chain="";
        if (!LM.isEmpty())        {
         float fd=0;
    float fh=0;
    float ft=0;
            try{openSession();

       
            Cloture result ;   

   
    for (Mission m:LM){
    result = (Cloture) s.get(Cloture.class,m.getCodeMission());
    fd=fd+result.getFdiver();
    fh=fh+result.getFhebergent();
    ft=ft+result.getFtransport();
    }     closeSession();  
               }catch(Exception e){
	e.printStackTrace();
       
        }

    Chain="Les Frais divers "+fd+" Les Frais D'hebergement "+fh+" Les Frais de Transport "+ft;}
    return Chain;
        
    }

    public String LesFraixduneMissionCloture(Mission mission){
        String Chain=new String();
        Chain="Mission non Cloturé";
            Cloture result=new Cloture() ;   

    
    float fd=0;
    float fh=0;
    float ft=0;
    try{openSession();

    result = (Cloture) s.get(Cloture.class,mission.getCodeMission());
    
 closeSession();  
               }catch(Exception e){
	e.printStackTrace();
               }
    
    if (result !=null){
        fd=fd+result.getFdiver();
    fh=fh+result.getFhebergent();
    ft=ft+result.getFtransport();
        Chain="Les Frais divers "+fd+" Les Frais D'hebergement "+fh+" Les Frais de Transport "+ft;}

    return Chain;
    
    }
    


    
    public String LesFraixdesMissionCloture(List<Mission> LM){
        if (!LM.isEmpty())        {
        openSession();

        String Chain=new String();
        Chain="";
            Cloture result ;   

    float fd=0;
    float fh=0;
    float ft=0;
    for (Mission m:LM){
    result = (Cloture) s.get(Cloture.class,m.getCodeMission());
    fd=fd+result.getFdiver();
    fh=fh+result.getFhebergent();
    ft=ft+result.getFtransport();
    }     closeSession();

    Chain=fd+","+fh+","+ft;
    return Chain;}
        else
            return "";
    }



 public List<Mission> ListerLesMissionClotureDesAgents(List<Agent>LAgent){
     if (LAgent==null){
           return null;
       }else{
        
        List<Mission>ListMiss=new ArrayList<>();       
        
        for (Agent ag:LAgent){
            List<Mission>l=ListerlesMissionClotureParAgent(ag.getMatricule());
            if(l!=null)
            ListMiss.addAll(l);
                }
   if (ListMiss!=null)
            return ListMiss;
        else 
            return null;
     }
      
    }
 
  
    public List<Mission> ListerlesMissionClotureParAgentParDate(int Matricule,Date d1,Date d2) throws ParseException{
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
   Date datedeb=dateFormat.parse(dateFormat.format(d1));
   Date dateFin=dateFormat.parse(dateFormat.format(d2));          
                      List<Mission> l= new ArrayList<>();

   try{     
   openSession();
                    Query query = s.createQuery("from Mission where Matricule = :code and DateDeb >= :dd and DateFin <= :df and Status=:Stat");
                    query.setParameter("code", Matricule);
                    query.setParameter("dd", datedeb);
                    query.setParameter("df", dateFin);
                    query.setParameter("Stat","Colturé");
                     if(!query.list().isEmpty()){
                    
                       l = query.list();
                   }
 closeSession();  
               }catch(Exception e){
	e.printStackTrace();
       
        }    return l;}

 public List<Mission> ListerLesMissionClotureDesAgentsParDate(List<Agent>LAgent,Date d1,Date d2) throws ParseException{
     if (LAgent==null){
           return null;
       }else{
        
        List<Mission>ListMiss=new ArrayList<>();       
        
        for (Agent ag:LAgent){
            List<Mission>l=ListerlesMissionClotureParAgentParDate(ag.getMatricule(),d1,d2);
            if(l!=null)
            ListMiss.addAll(l);
                }
   if (ListMiss!=null)
            return ListMiss;
        else 
            return null;
     }
      
    }
 public List<Mission> ListertoutesMissionsCloture() throws ParseException{
              List<Mission> l= new ArrayList<>();

   try{    
   openSession();
                    Query query = s.createQuery("from Mission where Status=:Stat");
                    query.setParameter("Stat","Colturé");
                    if(!query.list().isEmpty()){
                   
                       l = query.list();
                   }
 closeSession();  
               }catch(Exception e){
	e.printStackTrace();
       
        }    return l;
 }
 public List<Mission> ListerLesMissionClotureParDate(Date d1,Date d2) throws ParseException{
 DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
   Date datedeb=dateFormat.parse(dateFormat.format(d1));
   Date dateFin=dateFormat.parse(dateFormat.format(d2));
                     List<Mission> l= new ArrayList<>();

   try{    
   openSession();
                    Query query = s.createQuery("from Mission where DateDeb >= :dd and DateFin <= :df and Status=:Stat");
                    query.setParameter("dd", datedeb);
                    query.setParameter("df", dateFin);
                    query.setParameter("Stat","Colturé");
                    if(!query.list().isEmpty()){
                   
                       l = query.list();
                   }
 closeSession();  
               }catch(Exception e){
	e.printStackTrace();
       
        }    return l;
 }
// </editor-fold> 

     public void AffecterRejet(Mission m){
         try{ 
         openSession();
       m.setStatus("Rejeter");
       s.update(m);
           
closeSession();  
               }catch(Exception e){
	e.printStackTrace();
       
        } 
     }

    public List<Mission> ListerLesMissionClotureDesAgentsParTrimestre(List<Agent>LAgent) {
     if (LAgent==null){
           return null;
       }else{
        
        List<Mission>ListMiss=new ArrayList<>();       
        
        for (Agent ag:LAgent){
            List<Mission>l=ListerlesMissionClotureParAgentParTrimestre(ag.getMatricule());
            if(l!=null)
            ListMiss.addAll(l);
                }
   if (ListMiss!=null)
            return ListMiss;
        else 
            return null;
     }
      
    }    


    public List<Mission> ListerlesMissionClotureParAgentParTrimestre(int Matricule) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        
                      List<Mission> l= new ArrayList<>();

   try{     
   openSession();
                    Query query = s.createQuery("from Mission where Matricule = :code and  DATEDIFF( sysdate(),DateDeh )<=2 and Status=:Stat");
                    query.setParameter("code", Matricule);
               
                    query.setParameter("Stat","Colturé");
                     if(!query.list().isEmpty()){
                    
                       l = query.list();
                   }
 closeSession();  
               }catch(Exception e){
	e.printStackTrace();
       
        }    return l;}

}
     
     



