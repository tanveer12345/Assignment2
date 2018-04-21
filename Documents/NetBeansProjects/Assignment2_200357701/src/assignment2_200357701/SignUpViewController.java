/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment2_200357701;

import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import java.util.function.Predicate;

/**
 * FXML Controller class
 *
 * @author tanve
 */
public class SignUpViewController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML private TextField customerNameTextField;
    @FXML private Label errMsgLabel;
    @FXML private Label headerLabel;
    private Index index;
    
    //used for the passwords
    @FXML private PasswordField pwField;
    @FXML private PasswordField confirmPwField;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }  
   public boolean PasswordCk(){
        Predicate<String> PasswordCk = (checkPassword) -> checkPassword.length() > 5;
        return PasswordCk.test(pwField.getText());
   }
    
    public boolean validPassword()
    {
        if (pwField.getText().equals(confirmPwField.getText()))
            return true;
        else
            return false;
        
}
    
    public void saveCustomerButtonPushed(ActionEvent event) throws NoSuchAlgorithmException, SQLException
    {
        if (validPassword() && PasswordCk() == true)
        {
            try
            {
                index = new Index(customerNameTextField.getText(), pwField.getText());
                 
                    errMsgLabel.setText("");//do not show errors if creating Volunteer was successful
                    index.insertIntoDB();    
                

                SceneChanger sc = new SceneChanger();
                sc.changeScenes(event, "CustomerId.fxml", "Id");
            }
            catch (Exception e)
            {
                errMsgLabel.setText(e.getMessage());
            }
        }
        
        else{
            errMsgLabel.setText("Password and confirm password must be similar and length must be greater than 5");
        }
    }
    
    
}
