/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import beans.Hebergement;
import beans.ville;
import org.hibernate.Session;
import utilitaire.HibernateUtil;

/**
 *
 * @author HTOUM
 */
public class dao_Hebergement {
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
   
    
     public void ajout(Hebergement v){
      Session s =HibernateUtil.getSessionFactory().openSession();
        s.beginTransaction();
              s.save(v);
   s.getTransaction().commit();
        s.close();    }
}
