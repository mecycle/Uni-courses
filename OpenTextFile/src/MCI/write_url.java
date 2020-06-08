package MCI;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class write_url {
	public String output(ArrayList<String> filter_user,String path) {
		String text;
		try {
		
			BufferedWriter writer = new BufferedWriter(new FileWriter(path));
			
			int length1 = 0;
			int length2 = 0;
			writer.write("The result is : \n");
			for(String owner: filter_user) {
				writer.write(owner+"\n");
			}
			
			writer.close();
			text = "Successful print---url";
			System.out.println(text);
		    
	    } catch (IOException e) {
	    	text = "An error occurred.";
	    	System.out.println(text);
	        e.printStackTrace();
	    }
		return text;
	}
}
