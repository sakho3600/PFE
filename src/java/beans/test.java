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
import dao.dao_Cloture;
import dao.dao_Hebergement;
import dao.dao_Mission;
import dao.dao_ville;
import java.security.NoSuchAlgorithmException;
import utilitaire.SessionKeyGen ;
import modele.privs ; 
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import modele.modele_agent;
import org.hibernate.Query;
import utilitaire.HibernateUtil;
import utilitaire.cryptpasswords;
/**
 *
 * @author HTOUM
 */
public class test {
       
    
 
 public static void main (String []args) throws NoSuchAlgorithmException{
     
         Session s=HibernateUtil.getSessionFactory().openSession();
        s.beginTransaction();

     List<Mission> l= new ArrayList<>();
                   Query query = s.createQuery("from Mission");
                  //  query.setParameter("code", 11);
                    l = query.list();
                   s.getTransaction().commit();
        s.close();
 for (Mission p:l)
         System.out.println(p.getCodeMission());
     
     
     /* dao_Agent a = new dao_Agent();
         List<Mission> l= new ArrayList<>();
l=a.ListerlesMissionsNonValider();
     for (Mission s:l)
         System.out.println(s.getCodeMission());

     /*modele_agent m = new modele_agent();
     
     
     
     /*
     Mission m=new Mission(38);
     Cloture c=new Cloture(m,1.0F,1.0F,1.0F);
     dao_Cloture d=new dao_Cloture();
     d.ajoutCloture(c);
     
 /*
     Date d = new Date();
     Date d2=new Date();
     d.setDate(14);
     d.setMonth(3);
     d.setYear(2017);
     
     d2.setDate(18);
     d2.setMonth(3);
     d2.setYear(2018);
 final long MILLISECONDS_PER_DAY = 1000 * 60 * 60 * 24; 
 long delta = d2.getTime() - d.getTime();
     System.out.println(delta / (MILLISECONDS_PER_DAY));*/
     /*
     dao_Mission d=new dao_Mission();
     Set<ville>l =new HashSet<>();
        Mission m =new Mission();
       //System.out.println(d.StringVille(26));
     
     /*
    dao_Agent d= new dao_Agent();
    List<Mission> l= new ArrayList<>();
    List<Agent> la=new ArrayList<>();
    la=d.ListerAgentParChef(14);
    l=d.ListerMissionNonValiderDesAgents(la);
     for (Mission s:l)
         System.out.println(s.getCodeMission());

  /*for (Agent a:la)
      System.out.println(a.getMatricule());
    /* 
 /* 
     
     Personnel p = new Personnel(1,"a","a","a","Informatique");
     dao_Personnel da=new dao_Personnel();
     da.ajouter(p);
 Agent a =new Agent("Informatique",13,"ab","ab","ab");
 d.ajouter(a);
 
    public boolean ifExistsDirection(String Direction,String Departement){
    
 
 
 
 List<Agent>l = new ArrayList<>();
 Set<Mission> m=new HashSet<>();
 l=d.ListerAgentParChef(1);

List<Mission>lm =new ArrayList<>(); 
lm= d.ListerMissionNonValiderDesAgents(l);
     for (Mission st:lm)
     {       System.out.print(st.toString());
     }
     
     
     
     /*    dao_ville d=new dao_ville();
     ville v=new ville(8,"dd");
     ville v1=new ville (9,"ss");
     List<String> l=new ArrayList<>();
     l.add(v.toString());
     l.add(v1.toString());
     
     Set<ville> lv=new HashSet<>();
     lv=d.StringTOVille(l);
     for (ville st:lv)
         System.out.print(st.toString());
     cryptpasswords encryption = new cryptpasswords();
     
     dao_Admin servicead = new dao_Admin() ;
 Admin adm = new Admin();
 dao_Agent aj = new dao_Agent() ;
 dao_Mission ms = new dao_Mission() ;

 
 List<privs> prv= new ArrayList<privs>() ;

prv.add(privs.ALL); 

adm.setAdmin_privs(prv);
adm.setMatricule(02020);
adm.setMotDePasse(encryption.cryptme("tt"));
adm.setUsername("tt");
adm.setNom("tt");
adm.setPernom("tt");


   servicead.ajouter(adm);


     */
     
     dao_Admin servad=new dao_Admin();
     
     
     prevision p = new prevision();
     
     List<String> pr=new ArrayList();
     
    pr = servad.toutlesprevisions();
 
    for(String pv:pr){
        System.out.println(pv) ;
  
    }
}}
