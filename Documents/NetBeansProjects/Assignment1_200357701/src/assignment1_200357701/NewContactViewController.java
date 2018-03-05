
package assignment1_200357701;


import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;

/**
 * FXML Controller class
 *
 * @author tanve
 */

  public class NewContactViewController implements Initializable {  

   @FXML private TextField firstNameTextField;
   @FXML private TextField lastNameTextField;
   @FXML private TextField phoneTextField;
   @FXML private TextField addressTextField;
   @FXML private DatePicker birthday;
   @FXML private Label errorMsg;
   @FXML private ImageView imageView;
   
   private File imageFile;
   private boolean changeImageFile;
    
    
    
   public void  createContactButtonPushed(ActionEvent event)throws IOException{
       try{
           Contacts contact;
           if(changeImageFile)
           {
            contact = new Contacts(firstNameTextField.getText(), 
                                        this.lastNameTextField.getText(), 
                                        this.phoneTextField.getText(),
                                        this.addressTextField.getText(),
                                        this.birthday.getValue(), imageFile);
           }
           else
           {
               contact = new Contacts(firstNameTextField.getText(), 
                                        this.lastNameTextField.getText(), 
                                        this.phoneTextField.getText(),
                                        this.addressTextField.getText(),
                                        this.birthday.getValue());
           }
                                           
            errorMsg.setText("");
            contact.insertIntoDB();
            SceneChanger sc = new SceneChanger();
            sc.changeScenes(event, "NewContactTableView.fxml", "All contacts");
            
            
        }
        catch (IllegalArgumentException e)
        {
            errorMsg.setText(e.getMessage());
            
        } catch (SQLException ex)
        {
            Logger.getLogger(NewContactViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
      
      
    }
   
   public void cancelButtonPushed(ActionEvent event) throws IOException
    {
        SceneChanger sc = new SceneChanger();
        sc.changeScenes(event, "NewContactTableView.fxml", "All Contacts");
    }
   
    public void chooseImageButtonPushed(ActionEvent event)
    {
        //get the Stage to open a new window (or Stage in JavaFX)
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        //Instantiate a FileChooser object
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Image");
        
        //filter for .jpg and .png
         FileChooser.ExtensionFilter imgFilter = new FileChooser.ExtensionFilter("Image Files","*.jpg","*.png");
        fileChooser.getExtensionFilters().add(imgFilter);
        
        //Set to the user's picture directory or user directory if not available
        String userDirectoryString = System.getProperty("user.home")+"\\Pictures";
        File userDirectory = new File(userDirectoryString);
        
        //if you cannot navigate to the pictures directory, go to the user home
        if (!userDirectory.canRead())
            userDirectory = new File(System.getProperty("user.home"));
        
        fileChooser.setInitialDirectory(userDirectory);
        
        //open the file dialog window
        File tmpImageFile = fileChooser.showOpenDialog(stage);
        
        if (tmpImageFile != null)
        {
            imageFile = tmpImageFile;
        
            //update the ImageView with the new image
            if (imageFile.isFile())
            {
                try
                {
                    BufferedImage bufferedImage = ImageIO.read(imageFile);
                    Image img = SwingFXUtils.toFXImage(bufferedImage, null);
                    imageView.setImage(img);
                    changeImageFile = true;
                }
                catch (IOException e)
                {
                    System.err.println(e.getMessage());
                }
            }
        }
        
    }
   
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        birthday.setValue(LocalDate.now().minusYears(18));
        changeImageFile = false;
        errorMsg.setText(""); 
   
   try{
            imageFile = new File("./src/Images/DefaultImage.jpg");
            BufferedImage bufferedImage = ImageIO.read(imageFile);
            Image image = SwingFXUtils.toFXImage(bufferedImage, null);
            imageView.setImage(image);
            
        }
        catch (IOException e)
        {
            System.err.println(e.getMessage());
        }
}    
  }




