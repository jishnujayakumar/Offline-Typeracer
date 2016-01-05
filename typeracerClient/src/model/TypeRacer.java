/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

//import java.io.IOException;

import java.io.IOException;
//import java.io.InputStreamReader;
//import java.io.Reader;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;



/**
 *
 * @author VivekPro
 */
public class TypeRacer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        // TODO code application logic here
    String mode="Multi";
    if(mode.equals("Single")) { 
     //   System.out.println("Hello");
    SinglePlayer single = new SinglePlayer();
   //  System.out.println(single.name);
    String racetext=single.getText();//get racetext at every race
    //System.out.println(racetext);//
    //System.out.println(single.getWord());
    // System.out.println("Hello1");
//Scanner w;
//char c;
      //  w = new Scanner(System.in);
        
    //while(true){    
    //String c= w.next();//take a single word from console
    //Reader reader = new InputStreamReader(System.in);
    Scanner scanner = new Scanner(System.in);
    int i;
    for(i=0;i<7;i++){
    if(!single.isCompleted()){
    char c = scanner.next().charAt(0);
    if(single.checkWord(c)){
    single.addCount();
    }else
        System.out.print("wrong" + c);
    }else{
    System.out.println("race Completed");
    break;
    } 
        
    }
          
   }else{
   //write code for multiplayer
   
    /* Multiplayer multi= new Multiplayer();
     //Multiplayer multi2= new Multiplayer();
    // multi.name="Hemant";
     boolean startServer=true;
    if(startServer){
    Server input=new Server();// creating an input object which implemants runnable to athread constructor.
        Thread thread=new Thread(input);
        thread.start();
    }
   
    System.out.println("Join Server");
    multi.joinServer();
    System.out.println("Server Started");
    multi.sendMyInfo();
   // System.out.println("sendTheInfo"); 
    //multi2.joinServer();
    //multi2.sendMyInfo();
    int d=0;
    while(startServer){
    //   System.out.println("llop");
       multi.getPlayerList();
       d++;
       for(int i=0;i<10;i++){
      // System.out.print("and");
        
    if(multi.single[i]!=null){
        
    System.out.println("a"+i+"a"+multi.single[i].name+"and"+multi.single[i].wordcompleted );
    
    }  
    if(d==2){
    startServer=false;
    }
    
    }
    
    }  
    //algorithm  to send message.
   //frist get
    System.out.println("And out");
    
    //
    String racetext=multi.getText(); 
       Scanner scanner = new Scanner(System.in);
    int i;
    multi.joinGame(multi.single);
    //multi2.joinGame();
    //for(i=0;i<7;i++){
  //  if(!multi.isCompleted()){
    //char c = scanner.next().charAt(0);
    //if(multi.checkWord(c)){
    multi.addCount();
    
    multi.sendMsg(multi.name,multi.countword );
    System.out.println("SSSend");
    multi.addCount();
    
    multi.sendMsg(multi.name,multi.countword );
    
    System.out.println("SSSend");
    multi.addCount();
    
    multi.sendMsg(multi.name,multi.countword );
//    System.out.println("s"+multi.single[1].name);
    System.out.println("Count of @" +multi.single[0].countword);
    System.out.println("SSSend");
    multi.addCount();
    
    multi.sendMsg(multi.name,multi.countword );
//    System.out.println("s"+multi.single[1].name);
    System.out.println("Count of @" +multi.single[0].countword);
//}else
      //  System.out.print("wrong" + c);
    //}else{
   // System.out.println("race Completed");
   // break;
    //} 

   

//}
    
    
    //end
}
    
}
}

class Server implements Runnable
{
  @Override
  public void run() //read the message.
    {
        try //read the message.
        {
            createServer server=new createServer();
            
            try {
                server.create();
            } catch (Exception ex) {
                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            }
            
           while(true){
            server.connectPlayers();
           }
           } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }*/
  }   
}
}





