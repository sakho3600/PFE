package dao;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import beans.Admin;
import beans.Personnel;
import beans.prevision;
import beans.privileges;
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
    public Admin ifExistsAdminobject(int matricule)
    {  
       Admin result = new Admin();
       
        try{
        openSession();
         result = (Admin) s.get(Admin.class,matricule) ;   
        closeSession();
           }catch(Exception e){
	e.printStackTrace();
        }
         return result ;
                
    }
    
    /* Lister les privileges d'un Admin */
    public List<privileges> listpriv(Admin admin)
    {
       List<privileges> privileges = new ArrayList();
        
    try { 
       openSession() ;
        Admin result=(Admin)s.get(Admin.class,admin.getMatricule()) ;
        closeSession() ;
         privileges.addAll(result.getLes_privileges()); // convert from set to LIST 

    }catch(Exception e){
	e.printStackTrace();
    }
        return privileges;
    }
    
    public List<privileges> listpriv()
    {
       List<privileges> privileges = new ArrayList();
        
    try { 
       openSession() ;
      String hql = "FROM privileges";
             Query query = s.createQuery(hql);
               privileges = query.list();
        closeSession() ;
        

    }catch(Exception e){
	e.printStackTrace();
    }
        return privileges;
    }
    
    /* Lister les privileges d'un Admin */
   
     /* Ajouter un Personnel */
   public void ajouter(Object Employ)
   {
        try { 
    openSession() ;
             s.saveOrUpdate(Employ);
    closeSession() ;
    }catch(Exception e){
	e.printStackTrace();
    }
    }
   
   public void ajoutadmin(Admin a){
   openSession();
   s.save(a);
   closeSession();}
   public void updateadmin(Object admin)
      {

       try { 
    openSession() ;
    
             s.update(admin);
    closeSession() ;
    
    }catch(Exception e){
	e.printStackTrace();
    }
    
   
      }
    
    public List<Admin> listerAdmin()
   {
        List<Admin> listeAdmin= new ArrayList<>();
       try { 
    openSession() ;
             String hql = "FROM Admin";
             Query query = s.createQuery(hql);
             listeAdmin = query.list();
    closeSession() ;
    }catch(Exception e){
	e.printStackTrace();
       
        }
     return listeAdmin ;

    }
      public void addprevision(prevision p)
      {
          try { 
    openSession() ;
             s.save(p);
    closeSession() ;
    }catch(Exception e){
	e.printStackTrace();
    }
      }
   
     public prevision getprevision()
       {
          
           prevision prv =new prevision();
           try { 
    openSession() ;
    String hq="FROM prevision ORDER BY numprevs desc" ;
       Query query = s.createQuery(hq);
        prv=(prevision) query.list().get(0);
    closeSession() ;
     
    }catch(Exception e){
	e.printStackTrace();
    }
         return prv ;
       }
      
 
      
      public void updateprevision(Object prevision)
      {

       try { 
    openSession() ;
    
             s.save(prevision);
    closeSession() ;
    
    }catch(Exception e){
	e.printStackTrace();
    }
    
   
      }
      
      
      
       public void delete(Object prev)
   {
       try { 
    openSession() ;
             s.delete(prev);
    closeSession() ;
    }catch(Exception e){
	e.printStackTrace();
    }
    }
       public boolean ifexistspresion()
       {
           boolean result = false ;
           try { 
    openSession() ;
          prevision prv = (prevision) s.get(prevision.class, 1) ;
    closeSession() ;
     if( prv!=null)
     {
         result = true ;
     }
    }catch(Exception e){
	e.printStackTrace();
    }
           return result ;
       }
      
}
