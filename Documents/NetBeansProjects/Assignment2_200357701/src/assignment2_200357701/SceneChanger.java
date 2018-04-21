/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment2_200357701;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author tanve
 */
public class SceneChanger {
    private static Index loggedInUser;

    
    
    /**
     * This method will accept the title of the new scene, the .fxml file name for
     * the view and the ActionEvent that triggered the change
     */
    public void changeScenes(ActionEvent event, String viewName, String title) throws IOException
    {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(viewName));
        Parent parent = loader.load();
        
        Scene scene = new Scene(parent);
        
        //get the stage from the event that was passed in
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        stage.setTitle(title);
        stage.setScene(scene);
        stage.show();
    }

    
    public static Index getLoggedInUser() {
        return loggedInUser;
    }

    public static void setLoggedInUser(Index loggedInUser) {
        SceneChanger.loggedInUser = loggedInUser;
    }
    
}
