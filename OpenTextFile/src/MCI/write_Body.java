package MCI;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class write_Body {
	public String output(ArrayList<String> filter_user,String path) {
		String text;
		try {
		
			BufferedWriter writer = new BufferedWriter(new FileWriter(path));

			writer.write("The result is : \n");
			for(String owner: filter_user) {
				writer.write(owner+"\n");
			}
			
			writer.close();
			text = "Successful print---body";
			System.out.println(text);
		    
	    } catch (IOException e) {
	    	text = "An error occurred.";
	    	System.out.println(text);
	        e.printStackTrace();
	    }
		return text;
	}
}
