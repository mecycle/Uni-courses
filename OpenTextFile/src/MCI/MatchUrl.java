package MCI;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MatchUrl {
	public ArrayList<String> match(ArrayList<String> user,ArrayList<String> body,String input){
		
		ArrayList <String> us = new ArrayList<>();
		ArrayList <String> use = new ArrayList<>();
		List<String> containedUrls = new ArrayList<String>();
	    String urlRegex = "((?:https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|])";
	    Pattern pattern = Pattern.compile(urlRegex, Pattern.CASE_INSENSITIVE);
	    for(int i= 0; i<body.size(); i++) {
	    	Matcher urlMatcher = pattern.matcher(body.get(i));
	    	while (urlMatcher.find())
		    {
		        containedUrls.add(body.get(i).substring(urlMatcher.start(0),
		                urlMatcher.end(0)));
		        us.add(user.get(i));
		    }
	    }
//	    System.out.println("user"+user.size());
//	    System.out.println("url"+ containedUrls.size());
	    
	    
	    
	    
	    
	    for(int i = 0; i<containedUrls.size(); i++) {
	    	if(containedUrls.get(i).equals(input)) {
	    		if(!use.contains(us.get(i))) {
	    			use.add(us.get(i));
	    		}
	    	}
	    }

	    

//	    for(String tok:containedUrls) {
//	    	System.out.println(tok);
//	    }
		
		
		
//	    System.out.println("use"+use.size());
		return use;
	}
}
