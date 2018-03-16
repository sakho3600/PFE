/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import beans.Admin;
import beans.Agent;
import beans.Mission;
import beans.Personnel;
import beans.Personnel;
import java.util.ArrayList;
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
        s.getTransaction().commit();
        s.close();
    }
    
    
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
               openSession();
               Agent a=(Agent) s.get(Agent.class,Matricule);
               Set<Mission> l= new HashSet<>();
               l.add(m);
               a.setMissions(l);
               m.setAgent(a);
               s.save(m);
               closeSession();               
    }
    
    public List<Mission> ListerlesMissionParAgent(int Matricule){
                   openSession();
                   List<Mission> l= new ArrayList<>();
                    Query query = s.createQuery("from Mission where Matricule = :code ");
                    query.setParameter("code", Matricule);
                     if(query.list().isEmpty()){
                     l=null;}
                     else{
                       l = query.list();
                   }
                   
     /*              
                   
       String hq="FROM Mission M WHERE M.Matricule = :m" ;
       Query query = s.createQuery(hq);
       query.setParameter("m", Matricule);
        if (!query.list().isEmpty()){
      l=(List<Mission>) query.list().get(0);
        
    }else {
        l=null;}*/
    closeSession();
    return l;}
    
    /* Ajouter un agent */
   public void ajouter(Object Employ)
   {
        try { 
    openSession() ;
             s.save(Employ);
    closeSession() ;
    }catch(Exception e){
	e.printStackTrace();
    }
    }
    
   
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
   

    public List<Agent> ListerAgentParChef(int Matricule){
                   openSession();
                   List<Agent> l= new ArrayList<>();
                    Query query = s.createQuery("from Agent where MatriculeChef = :code ");
                    query.setParameter("code", Matricule);
                     if(query.list().isEmpty()){
                     l=null;}
                     else{
                       l = query.list();
                   }
                  
                     closeSession();
    return l;}
    
    
    public List<Mission> ListerlesMissionNonValiderParAgent(int Matricule){
                   openSession();
                   List<Mission> l= new ArrayList<>();
                    Query query = s.createQuery("from Mission where Matricule = :code and Etat= 0");
                    query.setParameter("code", Matricule);
                     if(query.list().isEmpty()){
                     l=null;}
                     else{
                       l = query.list();
                   }
                 
    closeSession();
    return l;}
    
    
    
    public List<Mission> ListerMissionNonValiderDesAgents(List<Agent> LAgent){
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