package dao;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import modele.privs;
import beans.Admin;
import beans.Personnel;
import beans.prevision;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query ;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import utilitaire.HibernateUtil;

/**
 *
 * @author HTOUM
 */
public class dao_Admin {
    
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
   
    
     public Admin ifAdmin(String username)
    {
        Admin ds= new Admin();
       openSession();
        
       String hq="FROM Admin A WHERE A.username = :user" ;
       Query query = s.createQuery(hq);
       query.setParameter("user", username);
        if (!query.list().isEmpty()){
       ds= (Admin)query.list().get(0) ;
            }
        else{
            ds=null ;
        }
        closeSession();
        
     return ds ;   
        
    }
     
     
     public boolean ifAdminvalid(String username)
    {
       boolean result ; 
        
       openSession();
        
       String hq="FROM Admin A WHERE A.username = :user" ;
       Query query = s.createQuery(hq);
       query.setParameter("user", username);
        if (!query.list().isEmpty()){
       result = true ;
            }
        else{
            result = false ;
        }
        closeSession();
        
    return result ;
        
    }
    
    public boolean ifcanbelogged(String username,String mdp)
    {
         
        Admin admin=ifAdmin(username) ;
        
       if ( admin == null)
       {
        return false ;   
       }
       else  if(admin.getMotDePasse().equals(mdp)) {
           return true;
           
       }else {
           return false ;
       }

    }
    
    
    
    /**
     * 
     * Verifier si un Employer Existe dans la table Admin 
     *
     */
    public boolean ifExistsAdmin(int matricule)
    {  
       Admin result = new Admin();
       
        try{
        openSession();
         result = (Admin) s.get(Admin.class,matricule) ;   
        closeSession();
           }catch(Exception e){
	e.printStackTrace();
        }
         return result != null;
                
    }
    
    
   
    
    /* Lister les privileges d'un Admin */
    public List<privs> listpriv(Admin admin)
    {
       List<privs> privileges = new ArrayList();
        
    try { 
       openSession() ;
        Admin result=(Admin)s.get(Admin.class,admin.getMatricule()) ;
        closeSession() ;
         privileges = result.getAdmin_privs() ;

    }catch(Exception e){
	e.printStackTrace();
    }
        return privileges;
    }
    
     /* Ajouter un Personnel */
   public void ajouter(Object Employ)
   {
        try { 
    openSession() ;
             s.save(Employ);
    closeSession() ;
    }catch(Exception e){
	e.printStackTrace();
    }
    }
    
   
      public void addprevision(Object prevision)
      {
          try { 
    openSession() ;
             s.save(prevision);
    closeSession() ;
    }catch(Exception e){
	e.printStackTrace();
    }
      }
   
      public boolean verifprevision(String type)
    {
       boolean result ; 
        
       openSession();
        
       String hq="FROM prevision A WHERE A.type = :type" ;
       Query query = s.createQuery(hq);
       query.setParameter("type", type);
        if (query.list()!=null){
         result = true ;
            }
        else{
            result = false ;
        }
        closeSession();
        
    return result ;
        
    }
      
      
      public List<String> toutlesprevisions() // getting only type column from prevision class
      {
       List<String> myprevision = null ;
       openSession();
        Criteria criteria = s.createCriteria(prevision.class) ;
          criteria.setProjection(Projections.property("type")) ;
        myprevision = criteria.list() ;
       
       
        closeSession();
      return myprevision ;
      }
      
      
      public void updateprevision(Object prevision)
      {
         
          
       try { 
    openSession() ;
    
             s.update(prevision);
    closeSession() ;
    
    }catch(Exception e){
	e.printStackTrace();
    }
    
   
      }
      
      
       public void delete(prevision prev)
   {
       try { 
    openSession() ;
             s.delete(prev);
    closeSession() ;
    }catch(Exception e){
	e.printStackTrace();
    }
    }
      
}
