/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import beans.ville;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import utilitaire.HibernateUtil;

/**
 *
 * @author HTOUM
 */
public class dao_ville {
        private Session s ; // Hibernate Session 

         private void openSession(){
        s=HibernateUtil.getSessionFactory().openSession();
        s.beginTransaction();
    }
    /* fermer une session Hibernate */
    private void closeSession(){
        s.getTransaction().commit();
        s.close();
    }
   
     public List<ville> ListerVille()
    {
        List<ville> list=new ArrayList<>();
        openSession();
        
       String hq="FROM ville" ;
       Query query = s.createQuery(hq);
       list= (List<ville>)query.list() ;
            
        closeSession();
        
     return list ;   
        
    }
     
     public void ajout(ville v){
      Session s =HibernateUtil.getSessionFactory().openSession();
        s.beginTransaction();
              s.save(v);
   s.getTransaction().commit();
        s.close();    }
    
}
