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
import dao.dao_Hebergement;
import dao.dao_ville;
import utilitaire.SessionKeyGen ;
import modele.privs ; 
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import utilitaire.HibernateUtil;
/**
 *
 * @author HTOUM
 */
public class test {
       
    
 
 public static void main (String []args){
     
     dao_Admin servicead = new dao_Admin() ;
 Admin adm = new Admin();

 
 List<privs> prv= new ArrayList<privs>() ;

prv.add(privs.ALL); 

adm.setAdmin_privs(prv);
adm.setMatricule(0);
adm.setMotDePasse("leet");
adm.setUsername("leet");
adm.setNom("leet");
adm.setPernom("leet");


   servicead.ajouter(adm);
}}
