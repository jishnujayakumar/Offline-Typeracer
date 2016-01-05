package controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Formatter;
import java.util.Scanner;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.SinglePlayer;

public class singlePlayer_controller {

	
	protected Stage stage;
    public String activeUser; 
    public String uname;
    public SinglePlayer single = new SinglePlayer(); 
    public char currentCharacter;
    public String text;
    public String status = "progress";
    public int usr_input_length;
    public int maxSpeed = 0;
    public int uSpeed;
    public Double progress=0.0;
    public String racetext;
    public Formatter fileFormatter;
    public String userSpeed ="";
    public String matchesPlayed = "";
    public String matchesWon = "";
    public String resultTime = "src/artifacts/result_time.txt";
    public String efficiencyFile = "src/artifacts/efficiency.txt";
    public String speedFile = "src/artifacts/speed.txt";
    public String playerStatsFile = ""; 
    public PrintWriter out;
	//public String mwon="1";
	
    Scanner sc;

    @FXML  
    public javafx.scene.control.TextField username;
    public TextArea textarea;
    public Button chooseFile;
    public Button replay;
    public Button quit;
    public TextField user_input;
    public TextField bestSpeed;
    public TextField averageSpeed;
    public TextField level;
    public TextField matchPlayed;
    //public TextField matchWon; --for multi player
    public TextField progressBox;
    
    long startTime;
    long elapsedTime = 0L;
    int countError = 0;
    int efficiency=0;
    int speed = 0;
    public singlePlayer_controller(){
	
    	this.activeUser = "src/artifacts/current_user.txt";
    	this.uname = "Unknown";
    	
    }
    
    @FXML
    public void initialize(){
    
    	try {
			sc= new Scanner(new File(activeUser));
			if(sc.hasNext()){
	    		this.uname = sc.next(); 
	    	}
	    	
	    	username.setText(uname);
	    	
	    	
	    	playerStatsFile = "src/artifacts/Users/"+uname+"/single/playerStats.txt";
	    	
	    	sc= new Scanner(new File(playerStatsFile));
	    	
	    	maxSpeed = 0;
	    	
	    	int count = 0;
	    	int mwon =0 ;
	    	Double average = 0.0;
	    	
			if(sc.hasNext()){
	    		

	    		while(sc.hasNext()){
	    			
	    			userSpeed = sc.next();
	    			//matchesWon = sc.next();
	    			
	    			uSpeed = Integer.parseInt(userSpeed); 
	
	    			if(uSpeed > maxSpeed){
	    				maxSpeed = uSpeed;
	    			}
	    			
	    			/*if(matchesWon.equals("1")){
	    				mwon++;
	    			}*/
	    			
	    			
	    			
	    			Double some = Double.parseDouble(userSpeed);
	    			
	    			average += some;
	    			
	    			count++;
	    			
	    		}
	    	
	    		bestSpeed.setText("" + maxSpeed);
	    		averageSpeed.setText("" + Math.round((average / count)) );
	    		
	    		if(maxSpeed < 100){
	    			level.setText("beginner");
	    		}
	    		
	    		if(maxSpeed > 100 && maxSpeed < 250){
	    			level.setText("moderate");
	    		}
	    			    		
	    		if(maxSpeed > 250){
	    			level.setText("advanced");
	    		}
	    		
	    		matchPlayed.setText(""+count);
	    		
	    		//matchWon.setText(""+mwon);
	    		
	    	}else{
	    		bestSpeed.setText("No record");
	    		averageSpeed.setText("No record");
	    		level.setText("No record");
	    		matchPlayed.setText("No record");
	    		//matchWon.setText("No record");
	    	}
	    	
	    	
	    	
		} catch (FileNotFoundException e) {
			System.out.println("error : " + e.getMessage());
		}
    	
    	
    }    

    int i=0;
    int minus_one = -1;    
    
    @FXML
    private void checkWithTextarea(KeyEvent event) throws FileNotFoundException {
    	
    	single.setText(textarea.getText().toString());
    	
        racetext=single.getText().toString();//get racetext at every race
      
        //progress = (single.getCount()/(single.textLength() + 1));
        
        progressBox.setText("" + (single.getCount()*100)/single.textLength() +"%");
        
        i=0;
        
        minus_one = -1;
        
        usr_input_length = user_input.getText().length();
        
        if(usr_input_length > 0){
        
        if(!single.isCompleted()){
      	
        i = usr_input_length - 1;		
        
        //System.out.println("entered while 2 : " + i);
        
        text = user_input.getText();
        
        currentCharacter = text.charAt(i);
        
        //System.out.println("text : " + text + " charAt " + i + ": " + currentCharacter + " getWord : " + single.getWord() + " getCount : " + single.getCount() +"raceTextLength : " + (racetext.length()-1));
        
        if(single.checkWord(currentCharacter)){
        
        	System.out.println("Correctly entered.");
        
           	user_input.setStyle("-fx-border-color: #ccc; -fx-background-color: white; -fx-text-fill: black;");
        	
        	single.addCount();
        
        	status = "progress";
       
        }else{
        
        	user_input.setStyle("-fx-background-color: red; -fx-text-fill: white;");
        	
        	countError++;
        
        	status = "wrong";
        	//System.out.println("wrong");
        
        }}
        else {
        	
        	//Time storage
        	
            elapsedTime = ((new Date()).getTime() - startTime)/1000;
            fileFormatter = new Formatter(resultTime);
			fileFormatter.format("%s", elapsedTime);
			fileFormatter.close();
        	status = "race completed";
        
        	//Efficiency storage
        	
        	efficiency = ((single.textLength()+1 - countError)*100/(single.textLength()+1));
        	fileFormatter = new Formatter(efficiencyFile);
			fileFormatter.format("%s", efficiency);
			fileFormatter.close();
			
			//speed Storage
	
	       	speed = (int) (single.textLength() * 60 / elapsedTime); 
	       	fileFormatter = new Formatter(speedFile);
			fileFormatter.format("%s", speed);	
			fileFormatter.close();
			
			fileFormatter = new Formatter();
			fileFormatter.format("%s", speed);	
			fileFormatter.close();
			
			//Appending to respective users stats file

			try {
				out = new PrintWriter(new BufferedWriter(new FileWriter(playerStatsFile, true)));
				//out.println(speed + " " + mwon);
				out.println(speed);
				out.close();
		
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
						
        }
        
        }
        
        
     //   if(status=="wrong"){
        	
        	
        	
        //}else if(status=="progress"){
        	
     
        	
       // }else 
        if(status == "race completed"){
        	
        	//Show result
        	//System.out.println("race Completed");
        	
			try{
			Parent playStage_root = FXMLLoader.load(getClass().getResource("/view/matchResult.fxml"));
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
            
        }
            
    //}
    @SuppressWarnings("resource")
	@FXML
    private void chooseFile(ActionEvent event) throws FileNotFoundException, IOException {
        
    	single.reset();
    	
    	countError = 0;
    	
    	user_input.setStyle("-fx-border-color: #ccc; -fx-background-color: white; -fx-text-fill: black;");
    	
    	user_input.setText("");
    	
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
        	
            Scanner readFile = new Scanner(selectedFile);      
            String fileText = "";
            // int count=0;
            while(readFile.hasNext()){
            fileText += readFile.nextLine();
                // System.out.println("Count : " + count);
            textarea.setText(fileText);
            }
 
        }
        
        startTime = System.currentTimeMillis();
          
    }
    
    void init(Stage stage) {
        this.stage = stage;
    }
 
}