package MCI;

import java.util.*;
public class RepoOwner {
	private String name = "";
	private String postId = "";
	private String GHUrl = "";
	private int time;

	public RepoOwner() {
		
	}
	public RepoOwner(String[] user) {
		this.name = user[0];
		this.postId = user[1];
		this.GHUrl = user[2];
		this.time = 0;
	}
	
	public String get_Name() {
		return this.name;
	}
	public String get_PostId() {
		return this.postId;
	}
	public String get_GHUrl() {
		return this.GHUrl;
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
