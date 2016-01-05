/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Multiplayer extends SinglePlayer {
    
	

 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

static Socket socket;//if it is only a client.
 
 public SinglePlayer[] single = new SinglePlayer[10];
 
 
 @SuppressWarnings("unused")
private int count=0;
 
  int count2=0;
  ObjectOutputStream oos;
  public ObjectInputStream ois;
  InputStream is;
  OutputStream os;
  String port="10.100.89.220";
 
 public Multiplayer(String in_name) throws IOException{
 super(in_name);
 }

    public void joinServer(String ip,int port) throws IOException{
    System.out.println("Connecting to the server");
		socket=new Socket(ip,port);
		 
                System.out.println("Connection Successful");
                //sendMyInfo();
                //System.out.println("1"); 
                is = socket.getInputStream();
                //System.out.println("2");
                
                os = socket.getOutputStream();
              //  System.out.println("3");
                
                oos = new ObjectOutputStream(os);
               // System.out.println("4");
                oos.flush();
                ois = new ObjectInputStream(is);
                       
    }
   
    public void sendMyInfo() throws IOException{
       msgObject to = new msgObject(name,countword);
             oos.writeObject(to); 
    
    }
    Thread thread;
    public void joinGame(SinglePlayer[] single) throws IOException{
        Input input=new Input(ois,oos,single);// creating an input object which implemants runnable to athread constructor.
        thread=new Thread(input);//create a thread to print message or read the message.
        thread.start();
        //add code to close the thread.
    }
    
    public void endGame(SinglePlayer[] single) throws IOException{
        thread.destroy();
        //add code to close the thread.
    }
   
    
    public void sendMsg(String name1, double progress1) throws IOException{
   //now sent the message.
       
            
             oos.reset();
             msgObject to;
            // System.out.println("Send the msg1");
             to= new msgObject(name1,progress1);
             //System.out.println("Send the msg2");
             oos.writeObject(to);  
             //System.out.println("Send the msg3");
             oos.flush();
        //String sendMessage= sc.nextLine();
        	//out.writeUTF(sendMessage);
       
    
    }
   /* public void rcvMsg(msgObject to ){
        //System.out.println("function recive msg");
    for(int i=0;i<10;i++){
        System.out.println(single[i].name);
     if(single[i]!=null){   
       // System.out.println("counter of player"+i+"is "+ to.countword );
    if(single[i].name.equals(to.name) && count2==0){
        single[i].countword=to.countword;
        count2=1;
      //  System.out.println("The packet" + to.name + to.countword );
        }
    }
    }
    
    }*/
    public void getPlayerList() {
        try{   
    	single=(SinglePlayer[]) ois.readObject();
    }catch(Exception e) {System.out.println("error getPlayerList: " + e.getMessage());}
        }
  

    
}

class Input implements Runnable
{
	ObjectInputStream ois;
       ObjectOutputStream oos;
       SinglePlayer[] single;
       int count2=0;
	public Input(ObjectInputStream ois,ObjectOutputStream oos , SinglePlayer[] single) throws IOException
	{
	   super();
           this.oos=oos;
	this.ois=ois;
        this.single=single;
        }
    public void run() //read the message.
    {
        
        
    	while(true)
    	{
   
    	try
    	{
    	
                    oos.reset();
                    oos.flush();
     msgObject message= (msgObject) ois.readObject();	
     System.out.println("Recieving Progress");	
     for(int i=0;i<5;i++){
     if(single[i]!=null){   

    if(single[i].name.equals(message.name)){
        single[i].progress=message.countword;
    	System.out.println("Name : " + single[i].name + "Progress : " + ((single[i].progress)*1.0));
    }
    }
    }
                
        }
    	catch(IOException e)
    	{
    		//System.out.println("Error readObject 1 : " + e.getMessage());
    	} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
    		System.out.println("Error readObject 2 : " + e.getMessage());
		}
    	}       
    }
}

