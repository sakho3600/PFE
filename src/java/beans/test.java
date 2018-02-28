/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.util.HashSet;
import java.util.Set;
import modele.modele_Admin;
import modele.modele_Personnel;
import dao.dao_Admin ;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import beans.Admin ;

import dao.privs ; 
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author HTOUM
 */
public class test {
    
 public static void main (String []args){
     Admin ad = new Admin() ;
     user_privs rl = new user_privs() ;
     dao_Admin s = new dao_Admin() ;

 try {
     
     List <privs> roles = new ArrayList<privs>() ;
    
     roles.add(privs.DELuser) ;
     roles.add(privs.GA) ;
     roles.add(privs.GMA) ;
     
     
     
     ad.setAdmin_privs(roles);
     
     ad.setAdmin_ID(1337);
     ad.setMatricule(1337);
     ad.setMotDePasse("aaa");
     ad.setNom("aaaa");
     ad.setPernom("aaa");
     ad.setUsername("aaaa");
    
     
     
     s.ajouter(ad);
  
}catch(HibernateException ex){
//log.error("building sessionFactory failed.",ex);
throw new ExceptionInInitializerError(ex);}
}
 
}
