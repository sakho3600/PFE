/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilitaire;
import java.io.IOException;
import javax.faces.application.ResourceHandler;
import  javax.servlet.Filter ;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.ServletContext;


/**
 *
 * @author Mohammed Mehdi Sarray#
 */
@WebFilter( urlPatterns = "/faces/administrator/*" )
public class ApplicationFilter implements Filter{
     private ServletContext context;
     private static final String AJAX_REDIRECT_XML = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
        + "<partial-response><redirect url=\"%s\"></redirect></partial-response>";

    @Override
    public void init(FilterConfig fc) throws ServletException {
        System.out.println("Cache Filter started: ");
        
   
    
    
    }

    @Override
    public void doFilter(ServletRequest sr, ServletResponse sr1, FilterChain fc) throws IOException, ServletException {
        //delanchement du request et le response pour avoir la session 
  
        HttpServletRequest request = (HttpServletRequest) sr;
        HttpServletResponse response = (HttpServletResponse) sr1;
        HttpSession session = request.getSession(false);
        String loginURL = request.getContextPath() + "/faces/administrator/login.xhtml";
         boolean secured = false;
         
        if (session.getAttribute("userkey") !=null){
            
         String compare = (String) session.getAttribute("userkey") ; 
        
         
         if (compare.contains("administrateur"))
         {
            secured = true;
         }
        }
         boolean loggedIn = (session != null) && (secured) ;
         boolean loginRequest = request.getRequestURI().equals(loginURL);
         boolean resourceRequest = request.getRequestURI().startsWith(request.getContextPath() + ResourceHandler.RESOURCE_IDENTIFIER + "/");
         boolean ajaxRequest = "partial/ajax".equals(request.getHeader("Faces-Request"));
         
          /*
         if (loggedIn == false)
         {
             response.sendRedirect(loginURL);
         }
         */
         
         if (loggedIn || loginRequest || resourceRequest) {
            if (!resourceRequest) { // Prevent browser from caching restricted resources. 
                response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
                response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
                response.setDateHeader("Expires", 0); // Proxies.
            }
                
           fc.doFilter(request, response); // So, just continue request.
        }
        else if (ajaxRequest) {
            response.setContentType("text/xml");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().printf(AJAX_REDIRECT_XML, loginURL); // So, return special XML response instructing JSF ajax to send a redirect.
        }
        else {
            
             response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
                response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
                response.setDateHeader("Expires", 0); // Proxies.
            response.sendRedirect(loginURL); // So, just perform standard synchronous redirect.
        }
    }
         
         
        
       
    

    @Override
    public void destroy() {
       
    }
    
}

    
    
    
    

