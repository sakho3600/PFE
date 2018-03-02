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
import beans.Personnel ;
import dao.dao_Admin ;
import dao.dao_Personnel ;
import beans.Agent ; 
import org.hibernate.HibernateException;
import org.hibernate.Session;
import beans.Admin ;
import dao.dao_Agent;

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
     privs privileges  ;
     dao_Personnel perso= new dao_Personnel() ;
     dao_Agent ajent = new dao_Agent() ;
     Agent aj = new Agent() ; 
     List<privs> p =new ArrayList<>() ;
 try {
     
     
     
    boolean a = ajent.ifcanbelogged(10, "md") ;
    
    if ( a)
    System.out.print("true") ;
    else {
       System.out.print("no") ; 
    }
     
   
}catch(HibernateException ex){
//log.error("building sessionFactory failed.",ex);
throw new ExceptionInInitializerError(ex);}
}
 
}
