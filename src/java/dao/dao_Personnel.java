/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import beans.Admin;
import beans.Personnel;
import beans.SuperAdmin;
import java.util.ArrayList;
import java.util.List;
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
   public Personnel lister(int Matricule)
    {
   org.hibernate.Session s=HibernateUtil.getSessionFactory().openSession();
        s.beginTransaction();
        Personnel p=(Personnel)s.get(Personnel.class,Matricule);
        s.getTransaction().commit();
        s.close();
        return p;
    } 
   public String verifGrade(int Matricule){
   String gr=null;
   
   org.hibernate.Session s=HibernateUtil.getSessionFactory().openSession();
        s.beginTransaction();
          SuperAdmin a=(SuperAdmin)s.get(SuperAdmin.class,Matricule);
           if (a!=null){
               gr="SuperAdmin";}
           else {
               Admin ad=(Admin)s.get(Admin.class,Matricule);
           if (ad!=null)
               gr="Admin";
        
           }
           
        s.getTransaction().commit();
        s.close();

           return gr;
   }
}
