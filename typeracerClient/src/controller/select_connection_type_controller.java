package controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class select_connection_type_controller {
	

	@FXML
    private void createServer(ActionEvent event) throws IOException {
		try{
			Parent playStage_root = FXMLLoader.load(getClass().getResource("/view/createServer.fxml"));
			Scene playStage_scene = new Scene(playStage_root);
			Stage playSTAGE = (Stage) ((Node) event.getSource()).getScene().getWindow();
			playSTAGE.hide();
			playSTAGE.setScene(playStage_scene);
			playSTAGE.setTitle("Offline Typeracer");
			playSTAGE.show();
			}catch(Exception e){
			System.out.println("error : " + e.getMessage());
			}
		
	}
	
	@FXML
    private void connectToServer(ActionEvent event) throws IOException {
		try{
			Parent playStage_root = FXMLLoader.load(getClass().getResource("/view/connectToServer.fxml"));
			Scene playStage_scene = new Scene(playStage_root);
			Stage playSTAGE = (Stage) ((Node) event.getSource()).getScene().getWindow();
			playSTAGE.hide();
			playSTAGE.setScene(playStage_scene);
			playSTAGE.setTitle("Jishnu Offline Typeracer");
			playSTAGE.show();
			}catch(Exception e){
			System.out.println("error : " + e.getMessage());
			}
		
	}

}
