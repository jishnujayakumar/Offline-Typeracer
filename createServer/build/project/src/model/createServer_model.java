/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import controller.createServer_controller_no_use;

/**
 *
 * @author Jishnu
 */
public class createServer_model extends Application {
	
	
    @Override
    public void start(Stage stage) throws Exception {
        
        FXMLLoader LOADER = new FXMLLoader(getClass().getResource("/view/createServer.fxml"));
        
        //Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml")); -- original LOC when framed
        
        Parent root =  (Parent) LOADER.load();
        
        createServer_controller_no_use controller = (createServer_controller_no_use) LOADER.getController();
        
        controller.init(stage);
             
        Scene scene = new Scene(root);
   
        //stage.initStyle(StageStyle.UNDECORATED); -- Not to use all 3 buttons ->min,max,close
        //stage.initStyle(StageStyle.UTILITY); -- Only use close not min and max
        stage.resizableProperty().setValue(Boolean.FALSE); // Use min and close not max
        stage.setTitle("Jishnu Offline Typeracer");
        stage.setScene(scene);
        stage.getIcons().add(new Image("file:pics/server.png"));
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
