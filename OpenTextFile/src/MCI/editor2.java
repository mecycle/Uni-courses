package MCI;
//this is for bar chart

import java.awt.Font;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.imageio.ImageIO;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import java.util.List;
import java.util.Map.Entry;
public class editor2 {
	public void createPort(String title, Map<String, Map<String, Double>> result, String type, String unit,
			Font font) {
		try {

			DefaultCategoryDataset ds = new DefaultCategoryDataset();
			Set<Entry<String, Map<String, Double>>> set1 = result.entrySet(); 
			Iterator iterator1 = (Iterator) set1.iterator(); 
			Iterator iterator2 = null;
			HashMap<String, Double> map = null;
			Set<Entry<String, Double>> set2 = null;
			Entry entry1 = null;
			Entry entry2 = null;

			entry1 = (Entry) iterator1.next(); 
			map = (HashMap<String, Double>) entry1.getValue();
			set2 = map.entrySet(); 
			iterator2 = set2.iterator(); 
			while (iterator2.hasNext()) {
				entry2 = (Entry) iterator2.next();
				ds.setValue(Double.parseDouble(entry2.getValue().toString()), 
						entry2.getKey().toString(), 
						entry2.getKey().toString());
			}


			JFreeChart chart = ChartFactory.createBarChart(title, type, unit, ds, PlotOrientation.VERTICAL, true,
					true, true);

			chart.getTitle().setFont(font);

			font = new Font("Times New Roman", Font.BOLD, 15);
			chart.getLegend().setItemFont(font);

			CategoryPlot plot = (CategoryPlot) chart.getPlot();
			plot.getDomainAxis().setLabelFont(font);

			plot.getDomainAxis().setTickLabelFont(font);
			plot.getRangeAxis().setLabelFont(font);

			plot.setForegroundAlpha(1.0f);

			ChartUtilities.saveChartAsJPEG(new File("C:\\Users\\dageb\\Pictures\\Camera Roll\\bb.png"), chart, 600, 400);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String read_output(List<String> file_data_output) {
		if (file_data_output.size() == 0) {
			return "no answer for your question\n";
		} else {
			ArrayList<Owner_result> pie = sort_output(file_data_output);
			Font font = new Font("Times New Roman", Font.BOLD, 15);
			Map<String, Double> map = new HashMap<String, Double>();
			Map<String, Map<String, Double>> result = new HashMap<>();
			for (Owner_result own : pie) {
//				int ss = own.get_url().indexOf(".") + 4;

				map.put(own.get_url(), (double) own.get_Time());
				
				result.put(own.get_url(), map);
			}
			createPort("bar chart of the answers", result, "URLs", "amount",font);
		}
		return "the bar chart is showing\n";
	}


	public ArrayList<Owner_result> sort_output(List<String> file_data_output) {
		ArrayList<Owner_result> pie = new ArrayList();
		ArrayList<String> pie_url = new ArrayList();
		ArrayList<Integer> pie_time = new ArrayList();

		while (file_data_output.size() != 0) {
			String token = file_data_output.get(0);
			if (pie_url.contains(token)) {
				int place = pie_url.indexOf(token);
				pie_time.set(place, pie_time.get(place) + 1);
				System.out.println();
			} else {
				pie_url.add(token);
				pie_time.add(1);
			}
			file_data_output.remove(0);

		}

		int i = 0;
		for (int j = 0; j < pie_url.size(); j++) {
			if (pie_time.get(j) > i) {
				i++;
			}
			pie.add(new Owner_result(pie_url.get(j), pie_time.get(j)));
		}
		Owner_result change = new Owner_result();
		return pie;
	}
}