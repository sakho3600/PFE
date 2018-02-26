/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import beans.Admin;
import beans.Personnel;
import utilitaire.HibernateUtil;

/**
 *
 * @author HTOUM
 */
public class dao_Personnel {
       public void ajouter(Personnel p)
     {
     org.hibernate.Session s=HibernateUtil.getSessionFactory().openSession();
     s.beginTransaction();
     s.save(p);
     s.getTransaction().commit();
     s.close();
     }
  
}
