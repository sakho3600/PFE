/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import beans.Admin;
import beans.Personnel;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import utilitaire.HibernateUtil;

/**
 *
 * @author HTOUM
 */
public class dao_Personnel {
    
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
    public boolean ifExists(int matricule)
    {  
       Personnel result = new Personnel();
       
        try{
        openSession();
         result = (Personnel) s.get(Personnel.class,matricule) ;   
        closeSession();
           }catch(Exception e){
	e.printStackTrace();
        }
         return result != null;
                
    }
    
   
     /* Ajouter un Personnel */
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
   
   
   
}

  

