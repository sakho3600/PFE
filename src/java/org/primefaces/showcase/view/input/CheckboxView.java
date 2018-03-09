package org.primefaces.showcase.view.input;
 
import beans.ville;
import dao.dao_ville;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.model.SelectItem;
import javax.faces.model.SelectItemGroup;
 
@ManagedBean
public class CheckboxView {
         
  
    private String[] selectedCities2;
    private List<String> cities;
    dao_ville d=new dao_ville();
    @PostConstruct
    public void init() {
        cities = new ArrayList<String>();
      List<ville> l=new ArrayList<>();
      l=d.ListerVille();
      for (ville str:l)
          cities.add(str.toString());
       
    }
 
 
    public String[] getSelectedCities2() {
        return selectedCities2;
    }
 
    public void setSelectedCities2(String[] selectedCities2) {
        this.selectedCities2 = selectedCities2;
    }
 
    public List<String> getCities() {
        return cities;
    }
     
 
}