package dao;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import beans.Admin;
import java.util.ArrayList;
import java.util.List;
import utilitaire.HibernateUtil;

/**
 *
 * @author HTOUM
 */
public class dao_Admin {
    private List<privs> privileges ;
    
   
    
    
    
    
    
    
       public void ajouter(Admin a)
     {
     org.hibernate.Session s=HibernateUtil.getSessionFactory().openSession();
     s.beginTransaction();
     s.save(a);
     s.getTransaction().commit();
     s.close();
     }
       
       public boolean hasRole(privs role) {
        return privileges.contains(role);
    }
}
