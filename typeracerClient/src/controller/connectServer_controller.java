package controller;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.util.Formatter;
import java.util.Scanner;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class connectServer_controller {
	
	Scanner sc;
	private int portNUM = 0;
	private String port = "";
	private String ip_port = "src/artifacts/ip_port.txt";
	private Formatter fileFormatter;
	@FXML
	public TextField ip_address;
	public TextField port_number;
	public Label status;
	public Button connect;

	@FXML
	public void serverConnection(ActionEvent event) throws Exception{

		
		if(ip_address.getText().equals("") || port_number.getText().equals("")){
			
			
			status.setText("Please provide IP and Port number both");
			
		}else{
		
		fileFormatter = new Formatter(ip_port);
		
		fileFormatter.format("%s %s", ip_address.getText(), port_number.getText());
		
		fileFormatter.close();
		
		try{
			Parent playStage_root = FXMLLoader.load(getClass().getResource("/view/playStage_client_view.fxml"));
			Scene playStage_scene = new Scene(playStage_root);
			Stage playSTAGE = (Stage) ((Node) event.getSource()).getScene().getWindow();
			playSTAGE.hide();
			playSTAGE.setScene(playStage_scene);
			playSTAGE.setTitle("J Offline Typeracer");
			playSTAGE.show();
			}catch(Exception e){System.out.println("error hello : " + e.getMessage());
			System.out.println("error : " + e.getMessage());
			}
	
		}
    		}
	
	
	
	
}