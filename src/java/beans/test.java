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
import dao.dao_Departement;

import dao.dao_Mission;
import dao.dao_assurer;
import dao.dao_ville;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import utilitaire.SessionKeyGen ;

import java.util.ArrayList;
import java.util.Calendar;
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
       
    
 
 public static void main (String []args) throws NoSuchAlgorithmException, ParseException{
 
     dao_assurer a= new dao_assurer();   
    Assurer aa=new Assurer();
aa=(Assurer) a.verif(202,"a");
     
List<BulletinMensuel> l=new ArrayList<>();
l=a.ListerNouveauBulletin(aa);
for (BulletinMensuel ll:l)
    System.out.println(ll.getId());
     /* //dao_Departement d=new dao_Departement();
    //dao_Agent da=new dao_Agent();
    
    cryptpasswords d = new cryptpasswords();
    dao_assurer a= new dao_assurer();
    
    Assurer assur= new Assurer() ;
  
    assur.setMatricule(1199);
    assur.setEmail("mylmail@gmail.com");
    assur.setMotDePasse(d.cryptme("azlle"));
    assur.setNom("lll");
    assur.setPernom("prelllnom");
    
    a.Add(assur);
    
   /*
   for (int i=0;i<2;i++){
   try {
       if (i==0){
   a.Add(bmv);}
   if (i==1){
   a.Add(bm);}
   }
   catch(org.hibernate.TransientPropertyValueException exception)
   {
       System.out.print("gotcha!");
   }
   */
   }
   /*
   Assurer asd = new Assurer() ; 
   asd.setNom("ESKETIT");
   asd.setPernom("G");
   asd.setMotDePasse("a");
   asd.setEmail("v@vh.com");
   asd.setMatricule(777);
   a.Add(asd);*/
     /*  dao_Agent d=new dao_Agent();
     List<Mission>lim=new ArrayList<>();
     Date d1=new Date(2018,04,11);
     Date d2=new Date(2018,04,25);
     lim=d.ListerLesMissionClotureParDate(d1,d2);
      
     System.out.println(d.LesFraixdesMissionClotureParAgent(lim));

     /*dao_Agent d=new dao_Agent();
     dao_Departement daod=new dao_Departement();
     List <Agent> lm=new ArrayList<>();
     Departement dp=daod.RechercheDepParNom("Informatique");
     lm=daod.TousLesAgentDepartement(dp);
     List<Mission>lim=new ArrayList<>();
     lim=d.ListerLesMissionClotureDesAgents(lm);
     
     for (Mission m:lim)
     System.out.println(m.getCodeMission());

     /*
     
     dao_Agent d=new dao_Agent();
     List <Mission> lm=new ArrayList<>();
     lm=d.ListerlesMissionClotureParAgent(2);
     System.out.print(d.LesFraixdesMissionClotureParAgent(lm));

     /* dao_Admin d=new dao_Admin();
  prevision  s=d.getprevision();
  System.out.print(s.getFdiver());
     
     /*dao_Departement d=new dao_Departement();
  List<String>l=d.ListerLesDepartement();
  for (String s:l)
        System.out.println(s);
     /* dao_Agent aj = new dao_Agent() ;
     Agent a=aj.ifExistsAgent(2);
     if (aj.chefoupas(a).getNomDep()==null)
     System.out.print("aa");
     else
     System.out.print("bb");    
     
     /*String[] selectedCities2;
   List<String> cities;
    dao_ville d=new dao_ville();
   
       cities = new ArrayList<String>();
      List<ville> l=new ArrayList<>();
      
      l=d.ListerVille();
      
      for (ville str:l)
      {  System.out.println(str.toString());}
      
     /*
     dao_Admin service=new dao_Admin();
    cryptpasswords d = new cryptpasswords() ;
     privileges prve =new privileges("ALL");
     Admin a = new Admin();
     a.setMatricule(1040);
     a.setMotDePasse(d.cryptme("tt"));
     a.setUsername("tt");
     
     List<String> selectedPrivs = new ArrayList(); 
     
    selectedPrivs.add("ALL") ; 
    selectedPrivs.add("GM");
     selectedPrivs.add("GM");
     
     
     privileges priv = new privileges();
          List<privileges> prv = new ArrayList();
          List <privileges> lista= service.listpriv() ;
          
         for(int i = 0 ; i<lista.size(); i++)
         {
           if( selectedPrivs.contains(lista.get(i).getLibelle()) )
           {
             
               priv.setCode_privilege(lista.get(i).getCode_privilege());
               priv.setLibelle(lista.get(i).getLibelle());
               prv.add(priv) ;
               priv = new privileges();
             
           }
            
         }
         
         for(privileges p : prv)
         {
             System.out.println(p.getLibelle()) ;
         }
    
     
            
 
     
     
     
     /*
     dao_Mission m=new dao_Mission();
   System.out.println(m.compareDate(,null));
     
     /*  
Calendar cal = Calendar.getInstance();
   DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
   
   cal.add(Calendar.DATE, -1);
   System.out.println("Yesterday's date was "+dateFormat.format(cal.getTime())); 
   
     
     
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


     dao_Mission dd = new dao_Mission();
     Mission m = dd.RetourMission(2) ;
     
     System.out.print(m.getDateDeb());
     System.out.print(m.getIntitule_Mission());
     */

}

