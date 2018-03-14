package modele;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.OutputStream;
import java.io.Serializable;
import java.net.URL;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.xhtmlrenderer.pdf.ITextRenderer;

/**
 *
 * @author HTOUM
 */
@ManagedBean
@SessionScoped
public class PrintController implements Serializable{

    /**
     * Creates a new instance of PrintController
     */
    public PrintController() {
    }
    
    public void createPdf(){
    FacesContext facesContext= FacesContext.getCurrentInstance();
    ExternalContext externalContext =facesContext.getExternalContext();
    HttpSession session= (HttpSession) externalContext.getSession(true);
    
    try{
        ITextRenderer renderer=new ITextRenderer();
        renderer.setDocument(new URL("http://localhost:8081/Pfe/FormPrint.xhtml").toString());
        renderer.layout();
        HttpServletResponse response=(HttpServletResponse) externalContext.getResponse();
        response.reset();
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition","inline; filename=\"print-file.pdf\"");
        OutputStream outputStream=response.getOutputStream();
        renderer.createPDF(outputStream);
    }catch(Exception ex){
     ex.printStackTrace();}
    facesContext.responseComplete();
    }
}
