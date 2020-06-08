package MCI;

import java.util.*;
//this is for pie chart
public class Owner_result {
	private String url = "";
	private int time;

	public Owner_result() {
		
	}
	public Owner_result(String user,int time) {
		this.url = user;
		this.time = time;
	}
	

	public String get_url() {
		return this.url;
	}
	public void set_time(int time) {
		this.time += time;
	}
	public int get_Time() {
		return this.time;
	}
	
	public int compareTo(RepoOwner comparestu) {
        int compareTime=((RepoOwner)comparestu).get_Time();
        return compareTime - this.time;

    }
	
	
}
