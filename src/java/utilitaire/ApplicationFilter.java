/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilitaire;
import java.io.IOException;
import  javax.servlet.Filter ;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 *
 * @author Mohammed Mehdi Sarray#
 */
@javax.servlet.annotation.WebFilter(filterName = "restrict" , urlPatterns = {"faces/administrator"})
public class ApplicationFilter implements Filter{
    
    
    @Override
    public void init(FilterConfig fc) throws ServletException {
        System.out.println("Cache Filter started: ");
        
   
    
    
    }

    @Override
    public void doFilter(ServletRequest sr, ServletResponse sr1, FilterChain fc) throws IOException, ServletException {
        //delanchement du request et le response pour avoir la session 
  
        
        
        
       
    }

    @Override
    public void destroy() {
       
    }
    
    
    
    
}
