/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import javax.faces.convert.EnumConverter;
import javax.faces.convert.FacesConverter;
/**
 *
 * @author Mohammed Mehdi Sarray#
 */

@FacesConverter(value="converenum")
public class converenum extends EnumConverter{

    public converenum() {
        super(privs.class);
    }
    
}
