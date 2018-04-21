/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment2_200357701;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author tanve
 */
public class SignInViewController implements Initializable {

    /**
     * Initializes the controller class.
     */
     @FXML private TextField customerIdTextField;
    @FXML private PasswordField pwField;
    @FXML private Label errMsgLabel;
    
    public void loginButtonPushed(ActionEvent event) throws IOException, NoSuchAlgorithmException
    {
        //query the database with the volunteerID provided, get the salt
        //and encrypted password stored in the database
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        
        int customerNum = Integer.parseInt(customerIdTextField.getText());
        
        try{
            //1.  connect to the DB
            conn = DriverManager.getConnection("jdbc:mysql://aws.computerstudi.es:3306/" + "gc200357701", "gc200357701", "20VCRJu0Fn");
            
            //2.  create a query string with ? used instead of the values given by the user
            String sql = "SELECT * FROM index1 WHERE customerId = ?";
            
            //3.  prepare the statement
            ps = conn.prepareStatement(sql);
            
            //4.  bind the volunteerID to the ?
            ps.setInt(1, customerNum);
            
            //5. execute the query
            resultSet = ps.executeQuery();
            
            //6.  extract the password and salt from the resultSet
            String dbPassword=null;
            byte[] salt = null;
            Index index = null;
            
            while (resultSet.next())
            {
                dbPassword = resultSet.getString("password");
                
                Blob blob = resultSet.getBlob("salt");
                
                //convert into a byte array
                int blobLength = (int) blob.length();
                salt = blob.getBytes(1, blobLength);
                
                index = new Index(resultSet.getString("customerName"),
                                                       resultSet.getString("password"));
                index.setCustomerId(resultSet.getInt("customerId"));
                  
            }
            
            //convert the password given by the user into an encryted password
            //using the salt from the database
            String userPW = PasswordGenerator.getSHA512Password(pwField.getText(), salt);
            
            SceneChanger sc = new SceneChanger();
            
            
            //if the passwords match - change to the VolunteerTableView
            if (userPW.equals(dbPassword))
                sc.changeScenes(event, "Car.fxml", "All Cars");
            else
                //if the do not match, update the error message
                errMsgLabel.setText("The customerID and password do not match");
        }
        catch (SQLException e)
        {
            System.err.println(e.getMessage());
        }
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
