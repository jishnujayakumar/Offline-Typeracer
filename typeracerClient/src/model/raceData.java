package model;

public class raceData {
	
	private String data = "";
	
	public raceData(String data){
		
		this.data = data;
		
	}
	
	public String sendText(){
		
		return data;
		
	}
	
public void setData(String data){
		
		this.data = data;
		
	}

}
