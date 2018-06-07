package modele;
 
import beans.Agent;
import beans.Departement;
import beans.Mission;
import dao.dao_Agent;
import dao.dao_Departement;
import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
 
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.HorizontalBarChartModel;
import org.primefaces.model.chart.ChartSeries;
 
@ManagedBean
public class ChartView implements Serializable {
 
    private BarChartModel barModel;
 
    @PostConstruct
    public void init() {
        createBarModels();
    }
 
    public BarChartModel getBarModel() {
        return barModel;
    }
     
    private BarChartModel initBarModel() {
        BarChartModel model = new BarChartModel();
 
        
         dao_Departement d=new dao_Departement();
    dao_Agent da=new dao_Agent();

   List<Departement>ld=d.ListerLesDepartements();
   for(Departement dd:ld){
       List<Agent>a= d.TousLesAgentDepartement(dd);
       List<Mission>lm=da.ListerLesMissionClotureDesAgentsParTrimestre(a);
        String chr=da.LesFraixdesMissionCloture(lm);
       if(chr.length()>0){
         
String[] parts = chr.split(",");
      System.out.println( parts[0]); // 004
      System.out.println( parts[1]); // 004
      System.out.println( parts[2]); // 004
            
      
      
      
      ChartSeries Departement = new ChartSeries();
        Departement.setLabel(dd.getNomDep());
        Departement.set("FD", Double.parseDouble(parts[0]));
        Departement.set("FH",Double.parseDouble(parts[1]));
        Departement.set("FT", Double.parseDouble(parts[2]));
 
    
 
        model.addSeries(Departement);
   }         }
        return model;
    }
     
    private void createBarModels() {
        createBarModel();
    }
     
    private void createBarModel() {
        barModel = initBarModel();
         
        barModel.setTitle("Comparaison des Departements");
        barModel.setLegendPosition("ne");
         
        Axis xAxis = barModel.getAxis(AxisType.X);
        xAxis.setLabel("frais");
         
        Axis yAxis = barModel.getAxis(AxisType.Y);
        yAxis.setLabel("Type");
        yAxis.setMin(0);
        yAxis.setMax(1000);
    }
     
  
 
}