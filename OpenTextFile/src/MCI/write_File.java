package MCI;

import java.io.*;  
import java.util.List;
import java.io.FileWriter;   
import java.nio.file.Paths;


public class write_File {
	public String output(List<RepoOwner> filter_user,int time) {
		String text;
		try {
		
			BufferedWriter writer = new BufferedWriter(new FileWriter("c:/MCI_OUTPUT/output_file.txt"));
			
			int length1 = 0;
			int length2 = 0;
			writer.write("The result is : \n");
			for(RepoOwner owner: filter_user) {
				writer.write("| "+owner.get_Name()+" | "+owner.get_GHUrl()+"\n");
			}
			
			writer.close();
			text = "Successful print";
			System.out.println("Successful print");
		    
	    } catch (IOException e) {
	    	text = "An error occurred.";
	    	System.out.println("An error occurred.");
	        e.printStackTrace();
	    }
		return text;
	}
}
