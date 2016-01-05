/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Formatter;
import java.util.ResourceBundle;
import java.util.Scanner;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
/**
 *
 * @author Jishnu
 */
public class Login_controller implements Initializable {
    
    private Stage stage;
    private String filePath = "src/artifacts/user_details.txt";
    private Calendar cal;
    private DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    public String user="RA One";
    public PrintWriter out;
    public Formatter fileFormatter;
    public String current_user = "src/artifacts/current_user.txt";
    public String appendToHistory = "src/artifacts/history.txt";
    @FXML
    public TextField username;
    public PasswordField password;
    public Button login;
    public Button new_user;
    public Button quit;
    public Label status;
    
    
    Scanner sc;
    int flag;
    
    @FXML
    private void login(ActionEvent event) throws IOException {
        
    	if(username.getText().equals("") || password.getText().equals("")){
    		status.setText("Username or password field is empty");
    	}else{
    		sc = new Scanner(new File(filePath));
    		@SuppressWarnings("unused")
			String uname = null,passwd = null,date = null,time="";
    		int count=0;
    		flag=0;
    		while(sc.hasNext()){
    			uname = sc.next();
    			passwd = sc.next();
    			date = sc.next();
    			time= sc.next();
    			
    			if(username.getText().equals(uname) && password.getText().equals(passwd)){
    				status.setText("User exists at " + count);
    				flag=1;
    				fileFormatter = new Formatter(current_user);
    				fileFormatter.format("%s", uname);
    				fileFormatter.close();
    				
    				out = new PrintWriter(new BufferedWriter(new FileWriter(appendToHistory, true)));
    				cal = Calendar.getInstance();
    				out.println(username.getText() + " " + dateFormat.format(cal.getTime()));
    				out.close();
    				
    				try{
    				Parent playStage_root = FXMLLoader.load(getClass().getResource("/view/select_user_mode.fxml"));
    				Scene playStage_scene = new Scene(playStage_root);
    				Stage playSTAGE = (Stage) ((Node) event.getSource()).getScene().getWindow();
    				playSTAGE.hide();
    				playSTAGE.setScene(playStage_scene);
    				playSTAGE.setTitle("Offline Typeracer");
    				playSTAGE.show();
    				}catch(Exception e){status.setText("error hello : " + e.getMessage());
    				System.out.println("error : " + e.getMessage());
    				}
    				break;
    			}
    			count++;
    		}
    		 
    		sc.close();
    		if(flag==0){
    			status.setText("Username or password incorrect");
    		}
    	}
    	
       
    
    }
    
    @FXML
    private void insert_new_user(ActionEvent event) throws FileNotFoundException {
    
        //status.setText("New user added !");
    	if(username.getText().equals("") && password.getText().equals("")){
    		status.setText("Username or password field is empty");
    	}else{
    		sc = new Scanner(new File(filePath));
    		@SuppressWarnings("unused")
			String uname = null,passwd = null,date = null,time=null;
    		flag=0;
    		while(sc.hasNext()){
    			uname = sc.next();
    			passwd = sc.next();
    			date = sc.next();
    			time= sc.next();
    			
    			if(username.getText().equals(uname)){
    				flag=1;
    				break;
    			}
    		}
    		 
    		sc.close();
    		if(flag==1){
    			status.setText("User already exists !!!");
    		}else{
    
    			try{
        	 
    				// using bufferedreader to minimize the cost of computation
        	 
    				out = new PrintWriter(new BufferedWriter(new FileWriter(filePath, true)));
    				cal = Calendar.getInstance();
    				out.println(username.getText() + " " + password.getText() + " " + dateFormat.format(cal.getTime()));
    				
    				out.close();
    				
    				String directoryName = "src/artifacts/Users/" + username.getText();
    				String subdir1 = "src/artifacts/Users/" + username.getText() + "/single";
    				String subdir2 = "src/artifacts/Users/" + username.getText() + "/multi";
    				@SuppressWarnings("unused")
					boolean theDir = new File(directoryName).mkdir();
    				boolean theDi = new File(subdir1).mkdir();
    				boolean theD = new File(subdir2).mkdir();
    				File file1 = new File(subdir1+"/playerStats.txt");
    				File file2 = new File(subdir2+"/playerStats.txt");
    				file1.createNewFile();
    				file2.createNewFile();
       				status.setText("User added. Now login...");
    				}catch(Exception e){
    					status.setText("error : " + e.getMessage());
    					System.out.println("error : " + e.getMessage());
    				}
    		}
    				}
    		}
    
    @FXML
    private void quit(ActionEvent event) {
        
        stage.close();
        
    }
    
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    public void init(Stage stage) {
        
        this.stage = stage;
        
    }
  
}
