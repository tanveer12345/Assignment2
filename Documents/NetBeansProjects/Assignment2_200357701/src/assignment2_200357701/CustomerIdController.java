/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment2_200357701;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.function.Function;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author tanve
 */
public class CustomerIdController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML private Label customerId;
    
    public void signInButtonPushed(ActionEvent event) throws IOException{
        SceneChanger sc = new SceneChanger();
        
        sc.changeScenes(event, "SignInView.fxml", "Sign In");
    }
    
    public void showCustomerId() throws SQLException{
        Connection conn = null;
        Statement statement = null;
        ResultSet resultSet = null;
        
        try{
            conn = DriverManager.getConnection("jdbc:mysql://aws.computerstudi.es:3306/" + "gc200357701", "gc200357701", "20VCRJu0Fn");
                    
            statement = (Statement) conn.createStatement();
            
            resultSet = statement.executeQuery("select customerId from index1;");
            
            while(resultSet.next()){
                Function<Integer, String> converter = (intToConvert) -> Integer.toString(intToConvert);
                customerId.setText(converter.apply(resultSet.getInt("customerId")));
                 
            }
        }
        catch(Exception e){
            System.err.println(e.getMessage());
            
            
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            showCustomerId();
        } catch (SQLException ex) {
            Logger.getLogger(CustomerIdController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
}
