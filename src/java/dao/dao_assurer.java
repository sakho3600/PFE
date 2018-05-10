/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import beans.Assurer;
import beans.BulletinMensuel;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import utilitaire.HibernateUtil;
import utilitaire.cryptpasswords;

/**
 *
 * @author Mohammed Mehdi Sarray#
 */
public class dao_assurer {
    
    
    
     private Session s ; // Hibernate Session 
     private cryptpasswords crp = new cryptpasswords() ;
  
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
    
    
    public void Add(Object o)
    { 
        openSession();
        s.save(o);
        closeSession();
    }
    public void update(Object o)
    {
        openSession();
        s.update(o);
        closeSession();
    }
     public List<Assurer> ajouter(List<BulletinMensuel> Bulletin)  {
       List<Assurer> assurerInexistant = new ArrayList<>() ;
       Assurer introuvable = new Assurer() ;
         openSession();
  
      for (int i=0;i<Bulletin.size();i++) {
         try {
          //save the object
          
          s.save(Bulletin.get(i));
          
          if( i % 20 == 0 ) // Same as the JDBC batch size
          { 
           //flush a batch of inserts and release memory:
           s.flush();
           s.clear();
          }
          }
        catch(org.hibernate.TransientPropertyValueException exception)
         {
       System.out.print("gotcha!\n");
       introuvable = Bulletin.get(i).getAssurer() ;
       assurerInexistant.add(introuvable) ;
       introuvable = new Assurer() ;
         s.clear();
         }
      } 
      closeSession();
      
      return assurerInexistant ;
         }
     
      public Assurer ifExistsAssurer(int matricule)
    {  
       Assurer result = new Assurer();
       
        try{
        openSession();
         result = (Assurer) s.get(Assurer.class,matricule) ;   
        closeSession();
           }catch(Exception e){
	e.printStackTrace();
        }
         return result ;
                
    }
     
     public boolean ifcanbelogged(int matricule,String mdp)
    {
         
        Assurer assurer = ifExistsAssurer(matricule) ;
        
       if ( assurer == null)
       {
        return false ;   
       }
       else  if(assurer.getMotDePasse().equals(mdp)) {
           return true;
           
       }else {
           return false ;
       }

    }
    public Object verif(int mat, String pass ) throws NoSuchAlgorithmException
     {
               Object o = new Object();
           try{           
       openSession();
                    Query query = s.createQuery("from Assurer where Matricule = :code and MotdePasse=:mdp ");
                    query.setParameter("code", mat);
                    query.setParameter("mdp", crp.cryptme(pass));
                    if (!query.list().isEmpty())
                    o  = query.list().get(0);
                     
                   
                   
    closeSession(); 
               }catch(Exception e){
	e.printStackTrace();
       
        }
               return o ;
     }
     
     public void ajouterAssurer(List<Assurer> assur)  {
      
         openSession();
  
      for (int i=0;i<assur.size();i++) {
        
          //save the object
          
          s.saveOrUpdate(assur.get(i));
          
          if( i % 20 == 0 ) // Same as the JDBC batch size
          { 
           //flush a batch of inserts and release memory:
           s.flush();
           s.clear();
          }
          }
      
       
      closeSession();
      
         }
     
       public List<BulletinMensuel> ListerHistorique(Assurer assurer) {
  
        List<BulletinMensuel>l=new ArrayList<>();
        openSession();     
             Query query = s.createQuery("from BulletinMensuel where Matricule= :matricule");
                   query.setParameter("matricule",assurer.getMatricule());
                     if(query.list().isEmpty()){
                     l=null;}
                     else{
                       l = query.list();
                   }
                     closeSession();
                
return l;    }
      
              public List<BulletinMensuel> ListerNouveauBulletin(Assurer assurer) {
  
        List<BulletinMensuel>l=new ArrayList<>();
        openSession();     
             Query query = s.createQuery("from BulletinMensuel where Matricule= :matricule and DATEDIFF( sysdate(),DateDeCreation )<=7");
                   query.setParameter("matricule",assurer.getMatricule());
                     if(query.list().isEmpty()){
                     l=null;}
                     else{
                       l = query.list();
                   }
                     closeSession();
                
return l;    }
              
              
         public List<Assurer> getMails(List<Assurer> list)
         {
             
             Assurer result = new Assurer();
             List<Assurer> lists = new ArrayList<Assurer>() ;
               
        try{
        openSession();
        for(int i = 0 ; i < list.size() ; i++) {
         result = (Assurer) s.get(Assurer.class,list.get(i).getMatricule()) ;
         if (result != null){
         lists.add(result) ;
         result = new Assurer() ;}
        }
         closeSession();
         
           }catch(Exception e){
	e.printStackTrace();
        }
        
         return lists ;
             
         }
    
}