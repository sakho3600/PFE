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
     
     
    ville v = new ville(8,"dd");
    ville v1=new ville (9,"ss");
  dao_ville d= new dao_ville ();
  d.ajout(v);
  d.ajout(v1);
 Hebergement h=new Hebergement (123l,"Ibis",2.5f,v);
 Hebergement i=new Hebergement (124l,"laico",3.5f,v);
 Hebergement j=new Hebergement (125l,"hambra",5.6f,v);
 Set<Hebergement> heb= new HashSet<>();
 Set<ville>vil=new HashSet<>();
 heb.add(h);
 heb.add(i);
 heb.add(j);
 vil.add(v);
 vil.add(v1);
    dao_Hebergement dh=new dao_Hebergement();
    dh.ajout(h);
    dh.ajout(i);
    dh.ajout(j);
   
  /*
         List<ville> liste= new ArrayList<>();
        liste=d.ListerVille();
        for (ville str:liste)
            System.out.print(str.toString());
 */
      
     

    
   Agent a = new Agent();
           dao_Agent da=new dao_Agent();
a=da.ifExistsAgent(3);
   Mission m =new Mission(14,a,"aaaa","Obj",null,null,3,1235.5f,vil,heb);
   
   da.CreationMission(3, m);
   
}}
