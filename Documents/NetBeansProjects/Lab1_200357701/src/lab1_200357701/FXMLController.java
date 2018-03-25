/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab1_200357701;

import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;


   
public class FXMLController implements Initializable {

   @FXML private TableView<?> contactTable;
    @FXML private TableColumn<?, ?> makeColumn;
    @FXML private TableColumn<?, ?> modelColumn;
    @FXML private TableColumn<?, ?> yearColumn;
    @FXML private TableColumn<?, ?> mileageColumn;
    
    
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    } 
    
    /**
     * This method will load the contacts from the database and load them into the TableView object
     */
    public void loadCars() throws SQLException
    {
        //ObservableList<carsd> cars = FXCollections.observableArrayList();
        
        Connection conn = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try{
             //1. Connect to the database
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/carsd_db?user=root");
            
            //2. create a statement object
            statement = conn.createStatement();
            
            //3. createthe SQL query
            resultSet = statement.executeQuery("SELECT * FROM contacts");
            
            //4. create contact objects from each record
            while(resultSet.next())
            {
                resultSet.getString("makeColumn");
                resultSet.getString("modelColumn");
                resultSet.getString("yearColumn");
                resultSet.getString("mileageColumn");
               
            }
           
        }
        
        catch(Exception e){
            System.err.println(e.getMessage());

        }
        finally{
            if(conn != null)
                conn.close();
            if(statement != null)
                statement.close();
            if(resultSet != null)
                resultSet.close();
        }
    }
    
    
    
}
