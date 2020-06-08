 package MCI;
 
import java.awt.Font;
import java.awt.Image;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
 import java.util.Map.Entry;
 import java.util.Set;
   
  import javax.imageio.ImageIO;
 import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
  import org.jfree.chart.JFreeChart;
 import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
 import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;
import java.io.* ;

  public class editor {
      public void createPort(String title,Map<String,Double> datas,Font font){
           try {          
        	   	 DefaultPieDataset pds = new DefaultPieDataset();

                 Set<Entry<String, Double>> set =  datas.entrySet();
                
                 Iterator iterator=(Iterator) set.iterator();
                 Entry entry=null;
                 String key ="";
                 double i = 0;
             
                 while(iterator.hasNext()){
                	 entry=(Entry) iterator.next(); 
                     pds.setValue(entry.getKey().toString(),Double.parseDouble(entry.getValue().toString()));
                     if(i<Double.parseDouble(entry.getValue().toString())) {
                    	 i = Double.parseDouble(entry.getValue().toString());
                    	 key = entry.getKey().toString();
                     }
                 }

                 JFreeChart chart = ChartFactory.createPieChart(title, pds, true, false, true);

                 chart.getTitle().setFont(font);
     
  
                 PiePlot plot = (PiePlot) chart.getPlot();

                 plot.setExplodePercent(key, 0.1);
      
                 plot.setLabelFont(font);
      
                 chart.getLegend().setItemFont(font);
                 plot.setStartAngle(new Float(3.14f / 2f));
                 plot.setForegroundAlpha(0.7f);
                 plot.setBackgroundAlpha(0.0f);

                 plot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0}({1})/{2}"));
        
                 ChartUtilities.saveChartAsJPEG(new File("C:\\Users\\dageb\\OneDrive\\图片\\aa.png"), chart, 900, 500);
            } catch (Exception e) {
                 e.printStackTrace();
            }
     }

    public String read_output(List<String> file_data_output) {
    	  if(file_data_output.size()==0) {
    		  return "no answer for your question\n";
    	  }else {
    		  ArrayList<Owner_result> pie = sort_output(file_data_output);
              Font font = new Font("Times New Roman", Font.BOLD, 15);
              Map<String, Double> map=new HashMap<String, Double>();
              for(Owner_result own:pie) {
//            	  int ss = own.get_url().indexOf(".")+4;

            	  map.put(own.get_url(), (double)own.get_Time());
              }
              createPort("pie chart of answer", map, font);
    	  }
    	  return "the pie chart is showing\n";         
     }
    public ArrayList<Owner_result> sort_output(List<String> file_data_output) {
    	ArrayList<Owner_result> pie = new ArrayList();
    	ArrayList<String> pie_url = new ArrayList();
    	ArrayList<Integer> pie_time = new ArrayList();

    	while(file_data_output.size()!=0) {
    		String token = file_data_output.get(0);
    		if(pie_url.contains(token)) {
    			int place = pie_url.indexOf(token);
    			pie_time.set(place, pie_time.get(place)+1);
    			System.out.println();
    		}else {
    			pie_url.add(token);
    			pie_time.add(1);
    		}
    		file_data_output.remove(0);
    		
    	}
    	
    	int i = 0;
    	for(int j = 0; j<pie_url.size();j++) {
    		if(pie_time.get(j)>i) {
    			i++;
    		}
    		pie.add(new Owner_result(pie_url.get(j),pie_time.get(j)));
    	}
    	Owner_result change = new Owner_result();
    	return pie;
    }
     
 }
