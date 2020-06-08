package MCI;

import java.io.* ;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class run {
	public ArrayList<String> sum_output(List<String> file_data_output) {
    	ArrayList<String> pie_url = new ArrayList();
    	while(file_data_output.size()!=0) {
    		String token = file_data_output.get(0);
    		if(pie_url.contains(token)) {

    		}else {
    			pie_url.add(token);

    		}
    		file_data_output.remove(0);
    		
    	}

    	return pie_url;
    }
	
	public String extract(ArrayList<String> body) {
		List<String> containedUrls = new ArrayList<String>();
	    String urlRegex = "((?:https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|])";
	    Pattern pattern = Pattern.compile(urlRegex, Pattern.CASE_INSENSITIVE);
	    for(int i= 0; i<body.size(); i++) {
	    	Matcher urlMatcher = pattern.matcher(body.get(i));
	    	while (urlMatcher.find())
		    {
		        containedUrls.add(body.get(i).substring(urlMatcher.start(0),
		                urlMatcher.end(0)));
		    }
	    }
		return "successful extract";
	}
	
	public ArrayList<String> sort_output(ArrayList<String> body) {

		List<String> containedUrls = new ArrayList<String>();
	    String urlRegex = "((?:https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|])";
	    Pattern pattern = Pattern.compile(urlRegex, Pattern.CASE_INSENSITIVE);
	    for(int i= 0; i<body.size(); i++) {
	    	Matcher urlMatcher = pattern.matcher(body.get(i));
	    	while (urlMatcher.find())
		    {
		        containedUrls.add(body.get(i).substring(urlMatcher.start(0),
		                urlMatcher.end(0)));
		    }
	    }
		
		return sum_output(containedUrls);
	}
	public ArrayList<String> sort_output_nosum(ArrayList<String> body) {

		ArrayList<String> containedUrls = new ArrayList<String>();
	    String urlRegex = "((?:https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|])";
	    Pattern pattern = Pattern.compile(urlRegex, Pattern.CASE_INSENSITIVE);
	    for(int i= 0; i<body.size(); i++) {
	    	Matcher urlMatcher = pattern.matcher(body.get(i));
	    	while (urlMatcher.find())
		    {
		        containedUrls.add(body.get(i).substring(urlMatcher.start(0),
		                urlMatcher.end(0)));
		    }
	    }
		
		return containedUrls;
	}

}
