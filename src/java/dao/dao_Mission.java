/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import beans.Mission;
import org.hibernate.Session;
import utilitaire.HibernateUtil;

/**
 *
 * @author HTOUM
 */
public class dao_Mission {
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
   
    
  public void ajoutMission(Mission m){
      Session s =HibernateUtil.getSessionFactory().openSession();
        s.beginTransaction();
        m.setEtat(0);
              s.save(m);
   s.getTransaction().commit();
        s.close();    }
}