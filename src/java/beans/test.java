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
import dao.dao_Mission;
import dao.dao_ville;
import java.security.NoSuchAlgorithmException;
import utilitaire.SessionKeyGen ;
import modele.privs ; 
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import utilitaire.HibernateUtil;
import utilitaire.cryptpasswords;
/**
 *
 * @author HTOUM
 */
public class test {
       
    
 
 public static void main (String []args) throws NoSuchAlgorithmException{
     
     
     cryptpasswords encryption = new cryptpasswords();
     
     dao_Admin servicead = new dao_Admin() ;
 Admin adm = new Admin();
 dao_Agent aj = new dao_Agent() ;
 dao_Mission ms = new dao_Mission() ;

 /*
 List<privs> prv= new ArrayList<privs>() ;

prv.add(privs.GA); 

adm.setAdmin_privs(prv);
adm.setMatricule(02020);
adm.setMotDePasse(encryption.cryptme("tt"));
adm.setUsername("tt");
adm.setNom("tt");
adm.setPernom("tt");


   servicead.ajouter(adm);
*/
 Agent a = new Agent();
 a.setDepartement("rh");
 a.setMatricule(023);
 a.setMotDePasse("sae");
 a.setNom("sf");
a.setPernom("sfa");

 
 
 Mission m =new Mission() ;
 m.setAgent(a);
 m.setCodeMission(010);
 m.setEtat(0);
 m.setType("reparation");
 m.setDateDeb(new Date());
 m.setDateFin(new Date());
 
 m.setKilometrage(254.0F);
 m.setObjectif("vac");
 m.setNbrJours(54);
 m.setIntitule_Mission("testdate");
 

 Set<Mission> sm = new HashSet<Mission>()  ; 
 sm.add(m) ;
 
 a.setMissions(sm);
 
 ms.ajoutMission(m);
 
 
}}
