/*
 * Student Name- Tanveer Kalia
 * Student Number- 200357701
 */
package test3_200357701;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Slider;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author tanve
 */
public class CanController implements Initializable {

    @FXML private TableView<Can> canTable;
    @FXML private TableColumn<Can, String> brandColumn;
    @FXML private TableColumn<Can, Integer> volumeColumn;
    @FXML private Slider volumeSlider;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
       volumeSlider.setMin(0);
       volumeSlider.setMax(2000);
       volumeSlider.setValue(500);
    }    
    
}
