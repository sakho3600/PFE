package dao;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import beans.vehicule;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import org.hibernate.Query;
import org.hibernate.Session;
import utilitaire.HibernateUtil;

/**
 *
 * @author HTOUM
 */
@ManagedBean(name = "carService")
@ApplicationScoped

public class dao_Vehicule {
    
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
    
    public void AjoutVehicule(vehicule v){
     openSession();
    s.save(v);
    closeSession();
    }
    public void EffacerVehicule (int id){
     openSession();
    s.delete(id);
    closeSession();}
    
    public void MajVehicule(vehicule v){
     openSession();
    s.saveOrUpdate(v);
    closeSession();
    }
    
    
       public List<vehicule> listevehicule()
    {
         List<vehicule> vehicule= new ArrayList<>();
       try { 
    openSession() ;
             String hql = "FROM vehicule";
             Query query = s.createQuery(hql);
             vehicule = query.list();
    closeSession() ;
    }catch(Exception e){
	e.printStackTrace();
       
        }
     return vehicule ;
    }
}
