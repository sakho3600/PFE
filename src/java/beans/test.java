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
import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 *
 * @author HTOUM
 */
public class test {
    
 public static void main (String []args){

 try {
Admin p= new Admin("c",2,"q","q","q");
modele_Admin s= new modele_Admin();
s.ajouter(p);
}catch(HibernateException ex){
//log.error("building sessionFactory failed.",ex);
throw new ExceptionInInitializerError(ex);}
}
 
}
