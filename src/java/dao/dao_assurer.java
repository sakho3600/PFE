/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import beans.Assurer;
import beans.BulletinMensuel;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import utilitaire.HibernateUtil;

/**
 *
 * @author Mohammed Mehdi Sarray#
 */
public class dao_assurer {
    
    
    
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
    
    
    public void Add(Object o)
    { 
        openSession();
        s.save(o);
        closeSession();
    }
    public void update(Object o)
    {
        openSession();
        s.update(o);
        closeSession();
    }
     public List<Assurer> ajouter(List<BulletinMensuel> Bulletin)  {
       List<Assurer> assurerInexistant = new ArrayList<>() ;
       Assurer introuvable = new Assurer() ;
         openSession();
  
      for (int i=0;i<Bulletin.size();i++) {
         try {
          //save the object
          
          s.save(Bulletin.get(i));
          
          if( i % 20 == 0 ) // Same as the JDBC batch size
          { 
           //flush a batch of inserts and release memory:
           s.flush();
           s.clear();
          }
          }
        catch(org.hibernate.TransientPropertyValueException exception)
         {
       System.out.print("gotcha!\n");
       introuvable = Bulletin.get(i).getAssurer() ;
       assurerInexistant.add(introuvable) ;
       introuvable = new Assurer() ;
         s.clear();
         }
      } 
      closeSession();
      
      return assurerInexistant ;
         }
     
      public Assurer ifExistsAssurer(int matricule)
    {  
       Assurer result = new Assurer();
       
        try{
        openSession();
         result = (Assurer) s.get(Assurer.class,matricule) ;   
        closeSession();
           }catch(Exception e){
	e.printStackTrace();
        }
         return result ;
                
    }
     
     public boolean ifcanbelogged(int matricule,String mdp)
    {
         
        Assurer assurer = ifExistsAssurer(matricule) ;
        
       if ( assurer == null)
       {
        return false ;   
       }
       else  if(assurer.getMotDePasse().equals(mdp)) {
           return true;
           
       }else {
           return false ;
       }

    }
    
}
