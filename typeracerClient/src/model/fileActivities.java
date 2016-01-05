/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Formatter;
import java.util.Scanner;

/**
 *
 * @author Jishnu
 */
public class fileActivities {
    
    protected String filePath;
    public Formatter x;
    private Scanner sc ;
    private Calendar cal = Calendar.getInstance();
    private DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    public fileActivities(String anyFilePath){      
        
        this.filePath = anyFilePath;
        
    }
    
    public void createFile () throws FileNotFoundException{
    
    x = new Formatter(filePath);
    
    }
    
    
    public void openFileForWriting () throws FileNotFoundException{
    
    x = new Formatter(filePath);
    
    }
    
    
    
    public void openFileForReading () throws FileNotFoundException{
    
    sc = new Scanner(new File(filePath));
    
    }
 
    
    public void setFilePath(String filepath){
    
        filePath = filepath;
        
    }
    
    public String getFilePath(){
    
        return filePath;
        
    }
    
    public String readFileByWord() throws FileNotFoundException{
    
    sc = new Scanner(new File(filePath));
    
    String word="";
    while(sc.hasNext()){
    
        word += sc.next() + "\t";
    
    }
        return word;
    
    }
    
    public String readFileLineByLine() throws FileNotFoundException{
    
    sc = new Scanner(new File(filePath));
    
    String word = "";
    
    while(sc.hasNext()){
    
        word += sc.nextLine();
    
    }
        return word;
    
    }
    
    
    public void writeToFile(String usrname, String password){
    
        x.format("%s %s %s",usrname,password,dateFormat.format(cal.getTime()));
    
    }
    
    
    
    public void closeFile(){
    
        x.close();
    
    }
   
    
}
