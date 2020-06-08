package MCI;
import java.io.* ;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class read_File{
	public ArrayList<String> readFile(String path)
	{
		ArrayList<String> records = new ArrayList<String>();
		try
		{
			File myFile = new File(path);
		    BufferedReader reader = new BufferedReader(new FileReader(myFile));
		    String line;
		    int no_firstLine = 0;
		    
		    while ((line = reader.readLine()) != null)
		    {
		    	
		    	if(no_firstLine != 0) {
		    		records.add(line);
				    
		    	}
		    	no_firstLine++;
		    }
		    reader.close();
		    return records;
		}
		catch (Exception e)
		{
		    System.err.format("Exception occurred trying to read '%s'.");
		    e.printStackTrace();
		    return null;
		}
	}
}

