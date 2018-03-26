/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab1_200357701;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;


   
public class FXMLController implements Initializable {

   @FXML private TableView<cars> carsTable;
    @FXML private TableColumn<cars, String> makeColumn;
    @FXML private TableColumn<cars, String> modelColumn;
    @FXML private TableColumn<cars, Integer> yearColumn;
    @FXML private TableColumn<cars, Integer> mileageColumn;
    
    @FXML private Slider maximumresolutionSlider;
    @FXML private Label maximumresolutionLabel;
    @FXML private Slider minimumresolutionSlider;
    @FXML private Label minimumresolutionLabel;
    @FXML private ComboBox<String> makeComboBox;
    
    
    
    
    public void CompleteButtonPushed(ActionEvent event) throws IOException
    {
        SceneChanger sc = new SceneChanger();
        sc.changeScenes(event, "Complete.fxml", "All Cars");
    }
   
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        makeColumn.setCellValueFactory(new PropertyValueFactory<cars, String>("make"));
        modelColumn.setCellValueFactory(new PropertyValueFactory<cars, String>("model"));
        yearColumn.setCellValueFactory(new PropertyValueFactory<cars, Integer>("year"));
        mileageColumn.setCellValueFactory(new PropertyValueFactory<cars, Integer>("mileage"));
        
       
        
        maximumresolutionSlider.setMin(2000);
        maximumresolutionSlider.setMax(2016);
        maximumresolutionSlider.setValue(2016);
        maximumresolutionLabel.setText(Integer.toString((int)maximumresolutionSlider.getValue()));
        

        minimumresolutionSlider.setMin(2000);
        minimumresolutionSlider.setMax(2016);
        minimumresolutionSlider.setValue(2000);
        minimumresolutionLabel.setText(Integer.toString((int)minimumresolutionSlider.getValue()));

        
        try{
            loadCars();
            updateComboBoxFromDB();
        }
        catch(SQLException e){
            System.err.println(e.getMessage());
        }
        
    } 
    
    /**
     * This method will load the contacts from the database and load them into the TableView object
     */
    public void loadCars() throws SQLException
    {
        ObservableList<cars> Cars = FXCollections.observableArrayList();
        
        Connection conn = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try{
             //1. Connect to the database
            conn = DriverManager.getConnection("jdbc:mysql://aws.computerstudi.es:3306/" + "gc200357701", "gc200357701", "20VCRJu0Fn");
            
            //2. create a statement object
            statement = conn.createStatement();
            
            //3. createthe SQL query
            resultSet = statement.executeQuery("SELECT * FROM cars");
            
            //4. create contact objects from each record
            while(resultSet.next())
            {
                cars Car = new cars(resultSet.getString("make"),
                                                  resultSet.getString("model"),
                                                  resultSet.getInt("year"),
                                                  resultSet.getInt("mileage"));
                Cars.add(Car);
               
            }
            carsTable.getItems().addAll(Cars);
           
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
    
     public void maximumresolutionSliderMoved() throws SQLException{
    String label = String.format("%.0f ", maximumresolutionSlider.getValue());
        maximumresolutionLabel.setText(label);
        updateCarSlider();
        
        
    }
    
    public void minimumresolutionSliderMoved() throws SQLException{
    String label = String.format("%.0f", minimumresolutionSlider.getValue());
        minimumresolutionLabel.setText(label);
        updateCarSlider();
        
    }
    
    
    public void updateCarSlider() throws SQLException
    {
        this.carsTable.getItems().clear();
        ObservableList<cars> Cars = FXCollections.observableArrayList();
        
        Connection conn = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try{
             //1. Connect to the database
            conn = DriverManager.getConnection("jdbc:mysql://aws.computerstudi.es:3306/" + "gc200357701", "gc200357701", "20VCRJu0Fn");
            
            //2. create a statement object
            statement = conn.createStatement();
            
            //3. createthe SQL query
            resultSet = statement.executeQuery("SELECT * FROM cars WHERE year BETWEEN "+minimumresolutionSlider.getValue()+ " AND " +maximumresolutionSlider.getValue());
            
            //4. create contact objects from each record
            while(resultSet.next())
            {
                 cars Car = new cars(resultSet.getString("make"),
                                                  resultSet.getString("model"),
                                                  resultSet.getInt("year"),
                                                  resultSet.getInt("mileage"));
                Cars.add(Car);
               
               
            }
            carsTable.getItems().addAll(Cars);
           
           
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
    public void updateComboBoxFromDB() throws SQLException 
    {
       
        
        Connection conn=null;
        Statement statement = null;
        ResultSet resultSet = null;
        
        try
        {
        
         //1. Connect to the database
            conn = DriverManager.getConnection("jdbc:mysql://aws.computerstudi.es:3306/" + "gc200357701", "gc200357701", "20VCRJu0Fn");
        
         //2.  Prepare the query
           statement = (Statement) conn.createStatement();
          
         //3 create and execute sql query
           resultSet = statement.executeQuery("select make from cars");
           
         //populate the combobox
         while(resultSet.next()){
             
          makeComboBox.getItems().add(resultSet.getString("make"));
         }
          
        }
         catch (SQLException e)
        {
            System.err.println(e);
        }
        finally
        {
            if (conn != null)
                conn.close();
           if (statement != null)
               statement.close();
            if(resultSet  != null){
               resultSet.close();
        }
                  
        
    }
        
    }
    
}
    
    

