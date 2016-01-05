package controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
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
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.*;

public class playStage_controller {


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
    public double progress=0.0;
    public String racetext;
    public Formatter fileFormatter;
    String filetext="";
    public String userSpeed ="";
    public int index=0;
    public String name="";
    public String matchesPlayed = "";
    public String matchesWon = "";
    public String resultTime = "src/artifacts/result_time.txt";
    public String ipPortFile = "src/artifacts/ip_port.txt";
    public String efficiencyFile = "src/artifacts/efficiency.txt";
    public String speedFile = "src/artifacts/speed.txt";
    public String playerStatsFile = "";
    public PrintWriter out;
	public String mwon="1";
	public int port=0;
	public int rank=0;
	public String ip="localhost";
	static Socket socket;
	static ObjectInputStream readStream; //to take input stream
	static ObjectOutputStream sendStream;// to take output stream
    static ClientName cname;
    public Multiplayer multi;
    boolean getFile=true;

    Scanner sc;

    @FXML
    public javafx.scene.control.TextField username;
    public TextArea textarea;
    public Button getConnected;
    public TextField user_input;
    public TextField bestSpeed;
    public TextField averageSpeed;
    public TextField level;
    public TextField matchPlayed;
    public TextField matchWon; //--for multi player
    public TextField progressBox;
    public Label l1;
    public Label l2;
    public Label l3;
    public Label l4;
    public Label l5;
    public ProgressBar pb1;
    public ProgressBar pb2;
    public ProgressBar pb3;
    public ProgressBar pb4;
    public ProgressBar pb5;

    long startTime;
    long elapsedTime = 0L;
    int countError = 0;
    int efficiency=0;
    int speed = 0;
    public playStage_controller(){

    	this.activeUser = "src/artifacts/current_user.txt";
    	this.uname = "Unknown";

    }

    @FXML
    public void initialize() throws IOException{

    	try {
			sc= new Scanner(new File(activeUser));
			if(sc.hasNext()){
	    		this.uname = sc.next();
	    	}

	    	username.setText(uname);


	    	playerStatsFile = "src/artifacts/Users/"+uname+"/multi/playerStats.txt";

	    	sc= new Scanner(new File(playerStatsFile));

	    	maxSpeed = 0;

	    	int count = 0;
	    	int mwon =0 ;
	    	Double average = 0.0;

			if(sc.hasNext()){


	    		while(sc.hasNext()){

	    			userSpeed = sc.next();
	    			matchesWon = sc.next();

	    			uSpeed = Integer.parseInt(userSpeed);

	    			if(uSpeed > maxSpeed){
	    				maxSpeed = uSpeed;
	    			}

	    			if(matchesWon.equals("1")){
	    				mwon++;
	    			}



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

	    		matchWon.setText(""+mwon);

	    	}else{
	    		bestSpeed.setText("No record");
	    		averageSpeed.setText("No record");
	    		level.setText("No record");
	    		matchPlayed.setText("No record");
	    		matchWon.setText("No record");
	    	}


			sc= new Scanner(new File(ipPortFile));
			if(sc.hasNext()){
	    		this.ip = sc.next();
	    		this.port = Integer.parseInt(sc.next());
	    	}

			sc.close();

		} catch (FileNotFoundException e) {
			System.out.println("error : " + e.getMessage());
		}


    }



    @FXML
    private void getConnectedWithServer(ActionEvent event) throws Exception, IOException{

    	getConnected.setDisable(true);
    	/*textarea.setText(textarea.getText() + "Connecting to the server" + "\n");
		socket=new Socket(this.ip,this.port);
		textarea.setText(textarea.getText() + "Connection Successful" + "\n");
        readStream=new DataInputStream(socket.getInputStream());//take input from socket
		sendStream=new ObjectOutputStream(socket.getOutputStream());
		cname = new ClientName(uname);
		textarea.setText(textarea.getText() + "Entering name:" + cname.getName() + "\n");
        sendStream.writeObject(cname);//send the name
        textarea.setText(textarea.getText() + "Sending data to server" + "\n");
        textarea.setText(textarea.getText() + "Recieving data from server" + "\n");
        String c = readStream.readUTF();
        textarea.setText(textarea.getText() + "Server : " + c + "\n");*/



    	multi = new Multiplayer(uname);

    	multi.joinServer(ip, port);

    	//textarea.setText(textarea.getText() + "Connection Successful" + "\n");

    	multi.sendMsg(multi.name, multi.progress);
    	multi.getPlayerList();
    	int counterrrr=0;
    	for(int j=0;j<5;j++){
    		if(multi.single[j]!=null){
    			//counterrrr++;
    			switch (++counterrrr) {
				case 1:
					l1.setText(multi.single[j].name);
					l1.setStyle("-fx-text-fill: green;");
					pb1.setProgress(0);
					break;
				case 2:
					l2.setText(multi.single[j].name);
					l2.setStyle("-fx-text-fill: red;");
					pb2.setProgress(0);
					break;
				case 3:
					l3.setText(multi.single[j].name);
					l3.setStyle("-fx-text-fill: magenta;");
					pb3.setProgress(0);
					break;
				case 4:
					l4.setText(multi.single[j].name);
					l4.setStyle("-fx-text-fill: blue;");
					pb4.setProgress(0);
					break;
				case 5:
					l5.setText(multi.single[j].name);
					l5.setStyle("-fx-text-fill: orange;");
					pb5.setProgress(0);
					break;
				default:
					break;
				}
    			textarea.setPromptText("Now wait till server sends the reference text...");;


    	//////////////////////////////////////////////////////////////////
    		while(getFile){
    		////System.out.println("123456");
    		filetext = multi.ois.readUTF();
    		///System.out.println("654321");
    		textarea.setText(filetext);
    		///System.out.println("3333");
    		getFile=false;
    		///System.out.println("9999");
    		}
    		}
    	}
    	textarea.setText(filetext);
    	startTime = System.currentTimeMillis();
		multi.joinGame(multi.single);

        }





    int i=0;
    int minus_one = -1;

    @FXML
    private void checkWithTextarea(KeyEvent event) throws IOException {


        if(textarea.getText().length() > 0)
       {

    	single.setText(textarea.getText().toString());

        racetext=single.getText().toString();//get racetext at every race

        progressBox.setText("" + (single.getCount()*100)/single.textLength() +"%");

        progress = (single.getCount()/(single.textLength() * 1.0));

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

 //       	System.out.println("Correctly entered.");

           	user_input.setStyle("-fx-border-color: #ccc; -fx-background-color: white; -fx-text-fill: black;");

        	single.addCount();

        	status = "progress";



        	multi.progress=progress;

        	multi.sendMsg(multi.name, multi.progress);


        	int counterrrrr=0;
        	for(int j=0;j<5;j++){
        		if(multi.single[j]!=null){
        			//counterrrr++;
        			switch (++counterrrrr) {
    				case 1:
    					if(multi.single[j].progress==1.0){
    						rank++;
    					l1.setText(multi.single[j].name + " : Winner " );
    					l1.setStyle("-fx-text-fill: green;");
    					pb1.setProgress(multi.single[j].progress);
    					break;
    					}else{
    						if(multi.single[j].progress==1.2){

    		  					l1.setText(multi.single[j].name + " : Winner" );
    		  					index = j;
    		  					name=multi.single[j].name;
    		  					l1.setStyle("-fx-text-fill: green;");
    	    					pb1.setProgress(multi.single[j].progress);
    							break;
    						}else{
    						l1.setText(multi.single[j].name);
        					l1.setStyle("-fx-text-fill: green;");
        					pb1.setProgress(multi.single[j].progress);
        					break;}
    					}
    				case 2:

    					if(multi.single[j].progress==1.0){
    						rank++;
    					l2.setText(multi.single[j].name + " : Rank " + rank );
    					l2.setStyle("-fx-text-fill: red;");
    					pb2.setProgress(multi.single[j].progress);
    					break;
    					}else{
    						if(multi.single[j].progress==1.2){
    							index = j;
    		  					name=multi.single[j].name;
    		  					
		  					l1.setText(multi.single[j].name + " : Winner" );
		  					l1.setStyle("-fx-text-fill: green;");
	    					pb1.setProgress(1.0);
							break;
						}else{

    						l2.setText(multi.single[j].name);
        					l2.setStyle("-fx-text-fill: red;");
        					pb2.setProgress(multi.single[j].progress);
        					break;
    					}
    					}
    				case 3:

    					if(multi.single[j].progress==1.0){
    						rank++;
    						
    					l3.setText(multi.single[j].name + " : Rank " + rank );
    					l3.setStyle("-fx-text-fill: magenta;");
    					pb3.setProgress(multi.single[j].progress);
    					break;
    					}else{
    						if(multi.single[j].progress==1.2){
    							index = j;
    		  					name=multi.single[j].name;
    		  					
    		  					l1.setText(multi.single[j].name + " : Winner" );
    		  					l1.setStyle("-fx-text-fill: green;");
    	    					pb1.setProgress(1.0);
    							break;
    						}else{

    						l3.setText(multi.single[j].name);
        					l3.setStyle("-fx-text-fill: magenta;");
        					pb3.setProgress(multi.single[j].progress);
        					break;
    				}
        			}
    				case 4:
    					if(multi.single[j].progress==1.0){
    						rank++;
    					l4.setText(multi.single[j].name + " : Rank " + rank );
    					l4.setStyle("-fx-text-fill: blue;");
    					pb4.setProgress(multi.single[j].progress);
    					break;
    					}else{
    						if(multi.single[j].progress==1.2){
    							index = j;
    		  					name=multi.single[j].name;
    		  					
    		  					l1.setText(multi.single[j].name + " : Winner" );
    		  					l1.setStyle("-fx-text-fill: green;");
    	    					pb1.setProgress(1.0);
    							break;
    						}else{

    						l4.setText(multi.single[j].name);
        					l4.setStyle("-fx-text-fill: blue;");
        					pb4.setProgress(multi.single[j].progress);
        					break;
    						}
    					}
    				case 5:

    					if(multi.single[j].progress==1.0){
    						rank++;
    					l5.setText(multi.single[j].name + " : Rank " + rank );
    					l5.setStyle("-fx-text-fill: orange;");
    					pb5.setProgress(multi.single[j].progress);
    					break;
    					}else{
    						if(multi.single[j].progress==1.2){
    							index = j;
    		  					name=multi.single[j].name;
    		  					
    		  					l1.setText(multi.single[j].name + " : Winner" );
    		  					l1.setStyle("-fx-text-fill: green;");
    	    					pb1.setProgress(1.0);
    							break;
    						}else{

    						l5.setText(multi.single[j].name);
        					l5.setStyle("-fx-text-fill: orange;");
        					pb5.setProgress(multi.single[j].progress);
        					break;
    						}
    						}
    				default:
    					break;
    				}
        		}
        		}



        }else{

        	user_input.setStyle("-fx-background-color: red; -fx-text-fill: white;");

        	countError++;

        	status = "wrong";
        	//System.out.println("wrong");

        }}
        else {

        	//Time storage

        	//multi.endGame(multi.single);

            elapsedTime = ((new Date()).getTime() - startTime)/1000;
            fileFormatter = new Formatter(resultTime);
			fileFormatter.format("%s", elapsedTime);
			fileFormatter.close();
        	status = "race completed";

        	System.out.println("Elapsed Time : " + elapsedTime);

        	//Efficiency storage

        	efficiency = ((single.textLength()+1 - countError)*100/(single.textLength()+1));
        	fileFormatter = new Formatter(efficiencyFile);
			fileFormatter.format("%s", efficiency);
			fileFormatter.close();

			System.out.println("Efficiency : " + efficiency);

			//speed Storage

	       	speed = (int) (single.textLength() * 60 / elapsedTime);
	       	fileFormatter = new Formatter(speedFile);
			fileFormatter.format("%s", speed);
			fileFormatter.close();

			System.out.println("Speed : " + speed);

			//Appending to respective users stats file

			multi.progress=progress;

        	multi.sendMsg(multi.name, multi.progress);
        	multi.sendMsg(multi.name, 1.2);



			try {
				if(name.equals(multi.name)){
				out = new PrintWriter(new BufferedWriter(new FileWriter(playerStatsFile, true)));
				out.println(speed + " " + mwon);
				//out.println(speed);
				out.close();
				}
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
        }

        }


       if(status=="wrong"){



        }else if(status=="progress"){



       }else
        if(status == "race completed"){

        	//Show result
        	//System.out.println("race Completed");

			try{
			Parent playStage_root = FXMLLoader.load(getClass().getResource("/view/matchResult_multi.fxml"));
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

    	//single.reset();

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