package controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class matchResultController {
	
	//private Stage stage;
	
	public String resultTime = "src/artifacts/result_time.txt";
    public String efficiencyFile = "src/artifacts/efficiency.txt";
	public String speedFile = "src/artifacts/speed.txt";
	Scanner sc;
	
	@FXML
	public TextField time;
	public TextField efficiency;
	public TextField speed;
	public Button replay;
	public Button quit;
	private Stage stage;
	
	@FXML
	 public void initialize() {
        

    	try {
			sc= new Scanner(new File(speedFile));
	
			if(sc.hasNext()){
	    	
				speed.setText(sc.next() + " LPM"); 
	    	
			}
	    
			sc= new Scanner(new File(efficiencyFile));
			
			if(sc.hasNext()){
	    	
				efficiency.setText(sc.next() + "%"); 
	    	
			}
	    
			sc= new Scanner(new File(resultTime));
			
			if(sc.hasNext()){
	    	
				time.setText(sc.next() + " seconds"); 
	    	}
			
			sc.close();
	    
		} catch (FileNotFoundException e) {
			System.out.println("error : " + e.getMessage());
		}
		
    }    

	
	@FXML
    private void replay(ActionEvent event){
	
		try{
			Parent playStage_root = FXMLLoader.load(getClass().getResource("/view/singlePlayer.fxml"));
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
