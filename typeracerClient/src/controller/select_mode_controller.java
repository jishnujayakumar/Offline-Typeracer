package controller;

import java.awt.Button;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class select_mode_controller {
	
	@FXML
	private Button singleUser;
	@FXML
	private Button multiUser;
	
	@FXML
	public void goToSingleUserStage(ActionEvent event){
		try{
			Parent play_root = FXMLLoader.load(getClass().getResource("/view/singlePlayer.fxml"));
			Scene play_scene = new Scene(play_root);
			Stage play = (Stage) ((Node) event.getSource()).getScene().getWindow();
			play.hide();
			play.setScene(play_scene);
			play.setTitle("Jishnu Offline Typeracer");
			play.show();
			}catch(Exception e){System.out.println("error  : " + e.getMessage());}
		
	}
	
	@FXML
	public void goToMultiUserStage(ActionEvent event){
	
		try{
			Parent playStage_root = FXMLLoader.load(getClass().getResource("/view/connectToServer.fxml"));
			Scene playStage_scene = new Scene(playStage_root);
			Stage playSTAGE = (Stage) ((Node) event.getSource()).getScene().getWindow();
			playSTAGE.hide();
			playSTAGE.setScene(playStage_scene);
			playSTAGE.setTitle("Offline Typeracer");
			playSTAGE.show();
			}catch(Exception e){System.out.println("error  : " + e.getMessage());}
	
	}
	
}
