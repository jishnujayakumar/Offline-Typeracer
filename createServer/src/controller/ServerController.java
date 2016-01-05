package controller;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Scanner;
import java.net.ServerSocket;
import java.net.Socket;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.createServer;
import model.createServer_model;

public class ServerController{

	Scanner sc;
	public String port="22353";
	int portNUM=0;
	long startTime;
	long elapsedTime;
	protected Stage stage;
	public ServerSocket serverSocket;
	public Socket socket;
	public ArrayList<Socket> playerSocketList = new ArrayList<Socket>();
	public ArrayList<String> playerNames = new ArrayList<String>();
	public ObjectInputStream objINStream;
	public ObjectOutputStream objOUTStream;
	public boolean allowedToconnect = true;
    public int players = 0;
    public int playersConnected = 0;
    public int numOfPlayers = 0;
    public int timerSpan = 15;
    static DataOutputStream out;
    static DataInputStream in;
    static ServerSocket serversocket;
    static DataOutputStream send;
    static DataOutputStream send1;
    static ObjectInputStream read;
    static int total_players = 0;
    String fileText="";
    public createServer cs;
    
    
	@FXML
    public TextField ip;
	@FXML
	public TextField portNumber;
	@FXML
	public TextField serverStatus;
	@FXML
	public Button selectFile;
	@FXML
	public Button restartServer;
	@FXML
	public Button startServer;
	@FXML
	public TextField numPlayers;
	@FXML
	public TextArea playerList;
	
	@FXML
	public void initialize() {
		
		restartServer.setDisable(true);
	
		selectFile.setDisable(true);
		
				try {
					
					sc= new Scanner(new File("src/artifacts/port_number.txt"));
					if(sc.hasNext()){
			    		serverStatus.setText("OFF");
			    		serverStatus.setStyle("-fx-text-fill: red;");
			    		port=sc.next();
			    		portNumber.setText(port);
			    		portNUM= Integer.parseInt(port);
			    		//portNumber.setText(sc.next());
			    		ip.setText(InetAddress.getLocalHost().getHostAddress());
			    	}else{
			    		serverStatus.setText("???");
			    		serverStatus.setStyle("-fx-text-fill: cyan;");
			    		portNumber.setText("Unknown");
			    		ip.setText("Unknown");
			    		restartServer.setDisable(false);
			    		startServer.setDisable(true);
			    		selectFile.setDisable(true);
			    		}
					
					sc.close();
					
			    	}catch(Exception e){//System.out.println("error : " + e.getMessage());
			    	
			    		e.printStackTrace();
			    	
			    	}
				
			    	}
				
	
	
	@FXML
	
	public void chooseFile(ActionEvent event) throws IOException{
		
		 FileChooser fileChooser = new FileChooser();
        
         fileChooser.setTitle("Open Text File");
        
         //fileChooser.setInitialDirectory(new File("user.home")); --This will not work for windows but will work for Linux and MAC
        
         fileChooser.getExtensionFilters().addAll(
        
         new FileChooser.ExtensionFilter("Text Files", "*.txt"),
         
         //new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"),
         
         //new FileChooser.ExtensionFilter("Audio Files", "*.wav", "*.mp3", "*.aac"),
         
         new FileChooser.ExtensionFilter("All Files", "*.*"));
        
         File selectedFile = fileChooser.showOpenDialog(stage);
        
        if (selectedFile != null) {
        	
            @SuppressWarnings("resource")
		
            Scanner readFile = new Scanner(selectedFile);      
            
            
			
           // String fileText = "";
            
            // int count=0;
            
            while(readFile.hasNext()){
            
            	fileText += readFile.nextLine();
                
            	// System.out.println("Count : " + count);
            
            	//textarea.setText(fileText);
            }
            
            
            
            //Send the code to everybody
            
            //cs = new createServer();
            cs.sendFile(cs.user, fileText);

        }
        
	}
        
      


@FXML
	
	public void restartServer(ActionEvent event){
	
	try{
		Parent playStage_root = FXMLLoader.load(getClass().getResource("/view/createServer.fxml"));
		Scene playStage_scene = new Scene(playStage_root);
		Stage playSTAGE = (Stage) ((Node) event.getSource()).getScene().getWindow();
		playSTAGE.hide();
		playSTAGE.setScene(playStage_scene);
		playSTAGE.setTitle("Offline Typeracer");
		playSTAGE.show();
		}catch(Exception e){System.out.println("error hello : " + e.getMessage());
		System.out.println("error : " + e.getMessage());
		}
	}


@FXML

public void startServer(ActionEvent event) throws IOException, ClassNotFoundException{
	serverStatus.setText("ON");
	serverStatus.setStyle("-fx-text-fill: green;");
	startServer.setDisable(true);
	restartServer.setDisable(false);
	selectFile.setDisable(false);
	numPlayers.setDisable(true);
	//System.out.println("Starting the server");

	//System.out.println("Setting up the server");
	serversocket=new ServerSocket(portNUM);
	//System.out.println("server Started");
	
	total_players = Integer.parseInt(numPlayers.getText());
		int counterr = 0;
		cs = new createServer();
        cs.connectPlayers(total_players, serversocket,socket);
        for (int i1 = 0; i1 < 5; i1++) {
        	if(cs.player[i1]!=null){
        		counterr++;
			playerList.setText(playerList.getText() + cs.player[i1].name + " connected" + "\n");
        	}
        }
        
        if(counterr > 0){
        	playerList.setStyle("-fx-text-fill: green;");
        }
        
      //  System.out.println("Players2:" + players);
	}

public void init(Stage stage){

	this.stage = stage;

}


}

