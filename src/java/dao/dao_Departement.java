/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import beans.Agent;
import beans.Departement;
import beans.Personnel;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import utilitaire.HibernateUtil;

/**
 *
 * @author HTOUM
 */
public class dao_Departement {
    
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
  
    public void AjoutParNom(String n){
        Departement d=new Departement(n);
    openSession();
    s.save(d);
    closeSession();
    }
  public void ajout(Departement d){
  
      openSession();
              s.saveOrUpdate(d);
  closeSession();
  }
  public void AjoutDirecteurDepartement(Agent a, Departement d){
 
  d.setAgentDirige(a);
        openSession();
        s.saveOrUpdate(d);
          closeSession();
  }

  public Departement RechercheDepParNom(String NomDep){
Departement d=new Departement();
      openSession();
       Query query = s.createQuery("from Departement where NomDep = :code ");
                    query.setParameter("code", NomDep);
                    d=(Departement)query.list().get(0);
          closeSession();
          return d;
}
  
  public List<String> ListerLesDepartement(){
  List<String>Liste=new ArrayList<>();
  openSession();
  Liste= s.createCriteria(Departement.class).setProjection(Projections.property("NomDep")).list();
  closeSession();
  return Liste;
  }
  public void EffacerDepartement(Departement d)
  {
      openSession();
      s.delete(d);
       closeSession();
  }

    public void updateDep(Departement dep) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
