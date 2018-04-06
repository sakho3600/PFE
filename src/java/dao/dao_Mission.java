/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import beans.Agent;
import beans.Mission;
import beans.ville;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.hibernate.Query;
import org.hibernate.Session;
import utilitaire.HibernateUtil;

/**
 *
 * @author HTOUM
 */
public class dao_Mission {
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
   
 public int CalculdJours(Mission m){
 
      
 final long MILLISECONDS_PER_DAY = 1000 * 60 * 60 * 24; 
 long delta = (m.getDateFin().getTime() - m.getDateDeb().getTime()) / (MILLISECONDS_PER_DAY);
 return (int)delta;
 }
    
  public void ajoutMission(Mission m){
  
      
 Session s =HibernateUtil.getSessionFactory().openSession();
        s.beginTransaction();
        m.setEtat(0);
        m.setValidDirecturGeneral(0);
        m.setNbrJours(this.CalculdJours(m));
              s.save(m);
   s.getTransaction().commit();
        s.close();    }


public Set<ville> ListerVille(int CodeMission){
    Set<ville> l=new HashSet<>();
    Mission m =new Mission();
    openSession();
    m= (Mission) s.get(Mission.class,CodeMission) ;   
    l= m.getLes_villes();
    closeSession();
    return l;
}

public String StringVille(Mission mission){
   Set<ville> l=new HashSet<>();
   l=mission.getLes_villes();
  String LesVilles=new String();
    for (ville ville:l)
        LesVilles+=ville.toString()+ ",";
    
return LesVilles.substring(0,LesVilles.length()-1);
}


public Mission RetourMission(int CodeMission){
Mission m =new Mission();
    openSession();
    m= (Mission) s.get(Mission.class,CodeMission) ;
   closeSession();
    return m;
}

public String ClotureMission(Mission mission,Agent agent){
String Message=new String();
Mission m=this.RetourMission(mission.getCodeMission());
if (m==null){
    Message="Mission inexistante";
}

else if(m.getEtat()==0){
Message="Mission Non validée par le chef hiarchique";
}
else if (m.getDateFin().after(new Date())){
    Message="Erreur date fin de mission!!";

}

else if (m.getAgent().getMatricule()==agent.getMatricule() && (m.getStatus().equals("En cours"))){
openSession();
    m.setStatus("Fin de Mission");
s.update(m);
closeSession();
Message="Mission Terminée";}
else if (m.getAgent().getMatricule()!=agent.getMatricule())
    Message="Vous n'avez pas les droit sur cette mission";
else if(m.getStatus().equals("Colturé"))
    Message ="Mission deja cloturée";
else if (m.getStatus().equals("Fin de Mission"))
    Message="Mission deja terminée";
else if (m.getStatus().equals("Annuler"))
    Message="Mission Deja Annuler";
return Message;
}

public void terminerMission(Mission m){

openSession();
    m.setStatus("Colturé");
s.update(m);
closeSession();

}

    public List<Mission> listMissionnoncloturer() {
        
        List<Mission>l=new ArrayList<>();
        openSession();     
             Query query = s.createQuery("from Mission where Status = :code ");
                    query.setParameter("code", "Fin de Mission");
                     if(query.list().isEmpty()){
                     l=null;}
                     else{
                       l = query.list();
                   }
                     closeSession();
                
return l;
    }
    
    public void updateMission(Object Mission)
      {

       try { 
    openSession() ;
    
             s.update(Mission);
    closeSession() ;
    
    }catch(Exception e){
	e.printStackTrace();
    }

}
    
    public void deleteMission(Object Mission)
      {

       try { 
    openSession() ;
    
             s.delete(Mission);
    closeSession() ;
    
    }catch(Exception e){
	e.printStackTrace();
    }

}

    public String AnnulerMission(Mission mission, Agent agent) {
        
        
String Message=new String();
Mission m=this.RetourMission(mission.getCodeMission());

if (m==null){
    Message="Mission inexistante";
}
else if(m.getEtat()==1){
Message="Mission Deja Valider vous devez Contacter l'administration";
}
else if (m.getAgent().getMatricule()==agent.getMatricule() && (m.getStatus().equals("En cours"))){
openSession();
    m.setStatus("Annuler");
s.update(m);
closeSession();
Message="Mission Annuler";}
else if (m.getAgent().getMatricule()!=agent.getMatricule())
    Message="Vous n'avez pas les droit sur cette mission";
else if(m.getStatus().equals("Colturé"))
    Message ="Mission cloturée";
else if (m.getStatus().equals("Fin de Mission"))
    Message="Mission terminée";
else if (m.getStatus().equals("Annuler"))
    Message="Mission Deja Annuler";

return Message;
    }
    
    
    public boolean compareDate(Date d,Date d1) throws ParseException{
 
      
   DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
   Date today=new Date();
   Date date=dateFormat.parse(dateFormat.format(today));
   Date datedeb=dateFormat.parse(dateFormat.format(d));
   Date dateFin=dateFormat.parse(dateFormat.format(d1));
   
    return(datedeb.before(date)|| datedeb.after(dateFin) );


    }
    
}
