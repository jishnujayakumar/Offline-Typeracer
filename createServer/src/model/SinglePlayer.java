/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.DataInputStream;

/**
 *
 * @author VivekPro
 */
import java.io.Serializable;
public class SinglePlayer implements Serializable {
	private static final long serialVersionUID = 1L;
      private String  raceText;  
      public int countword;
      public double progress=0.0;
      public int wordcompleted;
      private int totalWord;
      DataInputStream in;
      public int timer;
      public String name="";//for login name.
    /**
     * Function to be used In Single Player Racing.
     * @return 
     */
    public SinglePlayer(){
    //raceText=getText();
    countword=0;
    wordcompleted=0;
    }  

    SinglePlayer(DataInputStream in) {
    this.in=in;
    }
    /*public String getText(){
    RaceText rcText = new RaceText();
    raceText=rcText.getRaceText();
    totalWord=textLength();
    return raceText;
    }*/
    public void addCount(){
     countword++;
    }
    public int getCount(){
    return countword;
    }
    public char getWord(){ //get word from raceText
    return raceText.charAt(countword);
    }
    public boolean checkWord(char letter){
    char thisword;
          thisword = letter;
    if(thisword==getWord()){
    //proceeds the typing & make the textword green.
    //addCount(); //count is added here to confirm that user always type correctly.   
     return true;   
    }else{
    return false;
    }      
    
    }
    public int textLength(){
    return raceText.length();
    }
    public boolean isCompleted(){
    if(totalWord==countword){
    return true;
    }else return false;
    }
    
    

}
