/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.IOException;
/**
 *
 * @author Jishnu
 */
import java.io.Serializable;
public class SinglePlayer implements Serializable {
      /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	
	  private String  raceText;  
      public int countword;
      public double progress;
      public int wordcompleted;
      private int totalWord;
      public String name = "";
      private RaceText rcText = new RaceText();
    /**
     * Function to be used In Single Player Racing.
     * @return 
     */
    public SinglePlayer(){
    
    	raceText="";
    	countword=0;
    	wordcompleted=0;
    
    }
    
    public SinglePlayer(String incoming_name){
        
    	raceText="";
    	countword=0;
    	wordcompleted=0;
    	name = incoming_name;
    
    }
    public void setText(String s){
    
    	rcText.setRaceText(s);
        
    }
        
    
    public String getText(){
    
    	raceText = rcText.getRaceText();
    
    	totalWord = this.textLength();
    
    	return raceText;
    
    }
    
    public void addCount(){
    
    	countword++;
    
    }
    
    public void reduceCount(){
        
    	countword--;
    
    }
    
    public int getCount(){
    
    	return countword;
    
    }
    
    public char getWord(){ //get word from raceText
    
    	return raceText.charAt(countword);
    
    }
    
    public boolean checkWord(char letter){
    
    	if(letter == this.getWord()){
     
    		return true;   
    
    	}else{
    
    		return false;
    
    	}      
    
    }
    
    public int textLength(){
    	
    	return raceText.length()-1;
    
    }
    
    public boolean isCompleted(){
    
    	if(totalWord==countword){
    
    		return true;
   
    	}else return false;
   
    }
    
    public void reset(){
    	
    	raceText="";  
        countword=0;
        wordcompleted=0;
        totalWord=0;
        
    	
    }
    
}
