/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment2_200357701;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;




/**
 *
 * @author tanve
 */
public class SceneLaunch extends Application{
    public static void main(String[] args) {
        launch(args);
    }
    
   
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("SignUpView.fxml"));
        
        Scene scene = new Scene(root);
       
        stage.setTitle("Create Contact");
        stage.setScene(scene);
        stage.show();
    }
    
}
