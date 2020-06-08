package MCI;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.*;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import java.util.*;

public class insert_body {
	public ArrayList<String> connect_sql(String input,int match) {
		ArrayList<String> answer = new ArrayList<>();
		try {
		
	      Class.forName("com.mysql.jdbc.Driver");    
	      System.out.println("Success loading Mysql Driver!");
	    }
	    catch (Exception e) {
	      System.out.print("Error loading Mysql Driver!");
	      e.printStackTrace();
	    }
	    try {
	    	
	      Connection connect = (Connection) DriverManager.getConnection(
	          "jdbc:mysql://localhost:3306/test?user=root&password=qweasd665&useUnicode=true&characterEncoding=UTF-8&autoReconnect=true&failOverReadOnly=false&maxReconnects=10");
 
	      System.out.println("Success connect Mysql server!");
	      Statement stmt = (Statement) connect.createStatement();
	      ResultSet rs = null;
	      rs = stmt.executeQuery("update test1 WHERE Body LIKE '%"+input+"%' AND PostTypeId = 1");

	      ArrayList<String> body = new ArrayList<>();  
	      ArrayList<String> user = new ArrayList<>(); 
	     
	      while (rs.next()) {
	          System.out.println(rs.getString("Id"));
	          
	          user.add(rs.getString("Id"));
	          if(match == 1) {
	        	  body.add(rs.getString("Body"));
	          }
	          
	      }
	      MatchUrl m = new MatchUrl();
	      if(match ==1) {
	    	  user = m.match(user,body,input);
	      }
	      
	      for(String s: user) {	    	 
	    	  rs = stmt.executeQuery("select * from test1 WHERE parentId ="+ s +" AND PostTypeId = 2");
	    	  while (rs.next()) {
		          answer.add(rs.getString("Body"));
		      }
	      }
		  
	      connect.close();
		  rs.close();
		  System.out.println("SQL finish");
	    }
	    catch (Exception e) {
	    	System.out.print("get data error!");
		    e.printStackTrace();
	    }
	    if(answer.size()==0) {
	    	answer.add("0");
	    }
	    return answer;
	}
}

