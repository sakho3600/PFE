/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import beans.Admin;
import utilitaire.HibernateUtil;

/**
 *
 * @author HTOUM
 */
public class dao_Admin {
       public void ajouter(Admin a)
     {
     org.hibernate.Session s=HibernateUtil.getSessionFactory().openSession();
     s.beginTransaction();
     s.save(a);
     s.getTransaction().commit();
     s.close();
     }
  
}
