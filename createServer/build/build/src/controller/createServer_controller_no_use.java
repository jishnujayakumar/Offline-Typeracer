package controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Formatter;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class createServer_controller_no_use {

	public Formatter fileFormatter;
    public String port_number = "src/artifacts/port_number.txt";
	public Stage stage;
	
	@FXML
	public TextField server_port_number;
	public Button create;
	
	@FXML
	public void serverCreation(ActionEvent event) {
	 
		try {
			fileFormatter = new Formatter(port_number);
		} catch (FileNotFoundException e) {
			System.out.println("error : " + e.getMessage());
		}
		fileFormatter.format("%s", server_port_number.getText());
		fileFormatter.close();
		
		//System.out.println("Hello : " + server_port_number.getText());
	
		try{
			Parent playStage_root = FXMLLoader.load(getClass().getResource("/view/playStage_server_view.fxml"));
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
	
	public void init(Stage stage){

		this.stage = stage;
		
	}	
}
