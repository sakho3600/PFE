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
import dao.dao_ville;
import utilitaire.SessionKeyGen ;
import dao.privs ; 
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author HTOUM
 */
public class test {
    
 public static void main (String []args){
 ville v = new ville(80,"dd");
 dao_ville d= new dao_ville ();
d.ajout(v);
         List<ville> liste= new ArrayList<>();
        liste=d.ListerVille();
        for (ville str:liste)
            System.out.print(str.toString());
 
}}
