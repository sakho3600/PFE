/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import beans.Mission;
import beans.ville;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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


public Set<ville> ListerVille(int CodeMission){
    Set<ville> l=new HashSet<>();
    Mission m =new Mission();
    openSession();
    m= (Mission) s.get(Mission.class,CodeMission) ;   
    l= m.getLes_villes();
    closeSession();
    return l;
}

public String StringVille(Mission mission){
   Set<ville> l=new HashSet<>();
   l=mission.getLes_villes();
  String LesVilles=new String();
    for (ville ville:l)
        LesVilles+=ville.toString()+ ",";
    
return LesVilles.substring(0,LesVilles.length()-1);
}


public Mission RetourMission(int CodeMission){
Mission m =new Mission();
    openSession();
    m= (Mission) s.get(Mission.class,CodeMission) ;
   closeSession();
    return m;
}
}