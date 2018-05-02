/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import beans.Assurer;
import beans.BulletinMensuel;
import dao.dao_assurer;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
 
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


 
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import utilitaire.SessionKeyGen;
import utilitaire.cryptpasswords;
  
/**
 *
 * @author Mohammed Mehdi Sarray#
 */
@ManagedBean
@RequestScoped
public class modele_assurer {
  
    private String destination="C:\\x\\";
    private dao_assurer service = new dao_assurer();
    private Assurer userConnection = new Assurer(); 
    private String SessionKey ;
    private String results =new String();
    private SessionKeyGen sessionId= new SessionKeyGen() ; // generateur d'id de session (UUID)
    private cryptpasswords encryption = new cryptpasswords() ; // SHA256 ENCRYPTION
    private List<Assurer> lesAssurerintrouvable = new ArrayList<>();
  
   public void handleFileUpload(FileUploadEvent event) {
   
        try {
            copyFile(event.getFile().getFileName(), event.getFile().getInputstream());
            excel(event.getFile().getFileName()) ;
             
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erreur!", "Les Matricules des assurers suivant sont inexistant  .\n"+this.results));
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }
  
    public void copyFile(String fileName, InputStream in) {
           try {
              
              
                // write the inputStream to a FileOutputStream
                OutputStream out = new FileOutputStream(new File(destination + fileName));
              
                int read = 0;
                byte[] bytes = new byte[1024];
              
                while ((read = in.read(bytes)) != -1) {
                    out.write(bytes, 0, read);
                }
              
                in.close();
                out.flush();
                out.close();
              
                System.out.println("New file created!");
                } catch (IOException e) {
                System.out.println(e.getMessage());
                }
    }
    
    public void excel(String fileName) throws FileNotFoundException, IOException
    {  
        SimpleDateFormat DtFormat = new SimpleDateFormat("dd/MM/yyyy");
        DataFormatter fmt = new DataFormatter();
        Assurer assurer=new Assurer() ;
    
        List<BulletinMensuel> Lbm = new ArrayList<>() ;
        
        BulletinMensuel BM = new BulletinMensuel() ;
      
        FileInputStream input_document = new FileInputStream(new File("C:\\x\\"+fileName));
        /* Load workbook */
                XSSFWorkbook my_xls_workbook = new XSSFWorkbook(input_document);
               XSSFSheet my_worksheet = my_xls_workbook.getSheetAt(0);
           //    Iterator<Row> rowIterator = my_worksheet.iterator();
               Row row ;
               for(int i = 1 ; i<=my_worksheet.getLastRowNum() ; i++)
               {
                   row = (Row) my_worksheet.getRow(i) ;
                   if (row.getCell(0)!=null)
                   {
                     BM.setNum_bulletin(Integer.parseInt(fmt.formatCellValue(row.getCell(0))));
                   }
             
                   
                   if(row.getCell(1)==null)
                   {
                       BM.setMatricule(0);
                   }else BM.setMatricule(Integer.parseInt(fmt.formatCellValue(row.getCell(1))));
                         assurer.setMatricule(Integer.parseInt(fmt.formatCellValue(row.getCell(1))));
                   
                   if(row.getCell(2)!=null)
                   {
                    BM.setMalade(fmt.formatCellValue(row.getCell(2)));   
                   } 
                  
                   if (row.getCell(3)!=null)
                   {
                    BM.setMontant_prescrit(Float.parseFloat(fmt.formatCellValue(row.getCell(3))));  
                   }
                   
                   if (row.getCell(4)!=null)
                   {
                       BM.setDate_transmit(new Date(fmt.formatCellValue(row.getCell(4))));
                       
                   } 
                   if (row.getCell(5)!=null)
                   {
                       BM.setDate_Remboursement(new Date(fmt.formatCellValue(row.getCell(5))));
                   }
                   if (row.getCell(6)!=null)
                   {
                       BM.setMontant_remboursement(Float.parseFloat(fmt.formatCellValue(row.getCell(6))));
                   } 
                   if (row.getCell(7)!=null)
                   {
                       BM.setObservation(fmt.formatCellValue(row.getCell(7)));
                   } 
                   BM.setAssurer(assurer);
                   Lbm.add(BM) ;
                   BM = new BulletinMensuel() ;
                   assurer= new Assurer();
               }
              lesAssurerintrouvable = service.ajouter(Lbm);
              
             if (!lesAssurerintrouvable.isEmpty())
             {
              for(Assurer as : lesAssurerintrouvable)
              {
                  results = results+as.getMatricule()+"\n\t" ;
              }
              System.out.print("liste non ajouter:\n"+results);
           
             }
    }
    
// <editor-fold desc="getters and setters" defaultstate="collapsed">
    public dao_assurer getService() {
        return service;
    }

    public void setService(dao_assurer service) {
        this.service = service;
    }

    public Assurer getUserConnection() {
        return userConnection;
    }

    public void setUserConnection(Assurer userConnection) {
        this.userConnection = userConnection;
    }
 
    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getSessionKey() {
        return SessionKey;
    }

    public void setSessionKey(String SessionKey) {
        this.SessionKey = SessionKey;
    }

    public SessionKeyGen getSessionId() {
        return sessionId;
    }

    public void setSessionId(SessionKeyGen sessionId) {
        this.sessionId = sessionId;
    }

    public cryptpasswords getEncryption() {
        return encryption;
    }

    public void setEncryption(cryptpasswords encryption) {
        this.encryption = encryption;
    }
    
    
    
   // </editor-fold> 
    
// <editor-fold desc="Login Agent" defaultstate="collapsed">
    public void logmein() throws IOException, NoSuchAlgorithmException
  {
      if (service.ifcanbelogged(userConnection.getMatricule(),encryption.cryptme(userConnection.getMotDePasse()))) // verification de l'authentification
      {
      userConnection=service.ifExistsAssurer(userConnection.getMatricule());
      
          this.SessionKey = this.sessionId.getRandomUUIDString() ; // affectation de valeur uuid 
          FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("userkey", SessionKey+"assurance"); // Ajout de id de session
          FacesContext.getCurrentInstance().getExternalContext().redirect("assurance/welcome.xhtml"); // redirection vers la page d'acceuil apré une verification de l'utilisateur
         
          
      }else{ // retour a la page INDEX
	FacesContext context=FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Matricule ou Mot de passe incorrect")); // Message d'erreur
         }
     
      
  
  }
    //</editor-fold>  
    
// <editor-fold desc="logout" defaultstate="collapsed">
     public void logout() throws IOException // deconnexion
  {
      FacesContext context = FacesContext.getCurrentInstance(); 
       context.getExternalContext().getSessionMap().remove("userkey") ;
       FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
       
  }
     //</editor-fold>
    
    
}
