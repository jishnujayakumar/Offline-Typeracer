/**
 * This piece of code is of private use only within the Team-3 of CN course.
 * Any use of this code should brought under author's notice.
 * @author VivekPro
 */

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;


public class createServer {
	
static ServerSocket serversocket;
static Socket ssocket;
static DataOutputStream out;
static DataInputStream in;
//create 10 user object for 10 threads to hear 10 diffrent socket.
public Users[] user=new Users[10]; // create array of 10 user object

public int count=0;
int i=0;
//maintain array of 10 user to know who are connected.
public SinglePlayer[] player= new SinglePlayer[10];
public boolean allowedToConnect=true;
public InputStream is ;
public ObjectInputStream ois;
public OutputStream os;
public  ObjectOutputStream oos ;
public int stimer;

public void create()throws Exception
 {
	System.out.println("Starting the server");
	serversocket=new ServerSocket(7777);
	System.out.println("server Started");
        
 }
    public void connectPlayers(int players,ServerSocket ss,Socket s) throws IOException{    
        int iii=0;
    	while(iii<players){
  		
    		s=ss.accept(); 	
    		//System.out.println("Socket accepted from : " + s.getInetAddress());
    		os = s.getOutputStream();
            oos = new ObjectOutputStream(os);
            oos.flush();
            is = s.getInputStream();
            ois = new ObjectInputStream(is);
              
//send an beacon object to all connected users bout new user
       
        if(user[i]==null)
		{
            try {
                user[i]=new Users(oos,ois,user);
             msgObject to= (msgObject) ois.readObject();
                
                if(to!=null){
                player[i]=new SinglePlayer();
                player[i].name=to.name;
                player[i].progress=to.countword;
                
                }
           
                Thread thread=new Thread(user[i]);
                thread.start();
                i++;
                
                
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(createServer.class.getName()).log(Level.SEVERE, null, ex);
            }
		}
        iii++;
   }	
    	sendPlayerList(user);
 }
  public int getTotalPlayers(){
  return count;
  } 
  
  public void startCounter(){
  //write code for time.
  
  }
  
  public void endServer(){
  //write code to end server.
  
  }
    public void sendPlayerList(Users[] user) throws IOException{
   //  System.out.println("Send Server list");
        for(int i=0;i<10;i++)
		{
                   
			if(user[i]!=null){
		//	 System.out.println("a"+i);
                          user[i].oos.reset();
                           user[i].oos.writeObject(player);	 //send the message.
                            user[i].oos.flush();    
                        }   
                }
        
          //  oos.writeObject(player);  
            
            
    }
    
    
    public void sendFile(Users[] user,String file) throws IOException{
    	   //  System.out.println("Send Server list");
    	        for(int i=0;i<10;i++)
    			{
    	                   
    				if(user[i]!=null){
    			//	 System.out.println("a"+i);
    	                          user[i].oos.reset();
    	                                user[i].oos.writeUTF(file);	 //send the message.
    	                            user[i].oos.flush();    
    	                        }   
    	                }
    	        
    	          //  oos.writeObject(player);  
    	            
    }
    
    
  /*   public void getPlayerInfo(){
    
    testobject to = (testobject)ois.readObject();
    if (to!=null){System.out.println(to.id);}
    
    }   */

}

class Users implements Runnable
{   
	//DataOutputStream out;//new input output stream for each user.
	//DataInputStream in;
         ObjectInputStream ois;
         ObjectOutputStream oos;
	Users[]	user=new Users[10];// array of users
//	String name; //
	public Users(ObjectOutputStream oos,ObjectInputStream ois,Users[] user)
	{
		this.oos=oos;
		this.ois=ois;
		this.user=user;		
	}
	
	

	public void run()// getClientobject
	{
		
      while(true)
      {
    	 
    	try {           
    		
             //System.out.println("msgObject create server.java");
    			msgObject message;
             //if(ois.readObject()!=null){	
                 message=(msgObject) ois.readObject(); //} 
                      
                 
               //  System.out.println("rec be ser");// read the message
		if(message!=null){
                for(int i=0;i<5;i++)
		{       	
			if(user[i]!=null){
				//System.out.println("FOR LOOP : " + i);
            user[i].oos.reset();
            //System.out.println(message.name+"and " + message.countword);
			user[i].oos.writeObject( message);	 //send the message.
                   //         System.out.println("write by ser ti" + i);
                }
                }
                
                }
		} catch (IOException e) {
		
			this.oos=null;
			this.ois=null;
		} catch (ClassNotFoundException ex) {  
              Logger.getLogger(Users.class.getName()).log(Level.SEVERE, null, ex);
          }  
      }
	
        }
}






