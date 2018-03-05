
package assignment1_200357701;




import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import java.time.LocalDate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Davinder Kaur
 */
public class NewContactTableViewController implements Initializable {

    @FXML private TableView<Contacts> contactTable;
    @FXML private TableColumn<Contacts, Integer> contactIDColumn;
    @FXML private TableColumn<Contacts, String> firstNameColumn;
    @FXML private TableColumn<Contacts, String> lastNameColumn;
    @FXML private TableColumn<Contacts, String> phoneColumn;
    @FXML private TableColumn<Contacts, String> addressColumn;
    @FXML private TableColumn<Contacts, LocalDate> birthdayColumn;
    
    
    
     /**
     * This method will switch to the new contact scene when button is pushed
     */
    public void newContactButtonPushed(ActionEvent event) throws IOException
    {
        SceneChanger sc = new SceneChanger();
        sc.changeScenes(event, "NewContactView.fxml", "Create New Contact");
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // configure the table columns
        contactIDColumn.setCellValueFactory(new PropertyValueFactory<Contacts, Integer>("contactID"));
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<Contacts, String>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<Contacts, String>("lastName"));
        phoneColumn.setCellValueFactory(new PropertyValueFactory<Contacts, String>("phone"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<Contacts, String>("address"));
        birthdayColumn.setCellValueFactory(new PropertyValueFactory<Contacts, LocalDate>("birthday"));
        
        
        
        try{
            loadContacts();
        }
        catch(SQLException e){
            System.err.println(e.getMessage());
        }
    }  
    
    /**
     * This method will load the contacts from the database and load them into the TableView object
     */
    public void loadContacts() throws SQLException
    {
        ObservableList<Contacts> contacts = FXCollections.observableArrayList();
        
        Connection conn = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try{
             //1. Connect to the database
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/contacts_db?user=root");
            
            //2. create a statement object
            statement = conn.createStatement();
            
            //3. createthe SQL query
            resultSet = statement.executeQuery("SELECT * FROM contacts");
            
            //4. create contact objects from each record
            while(resultSet.next())
            {
                Contacts contact = new Contacts(resultSet.getString("firstName"),
                                                  resultSet.getString("lastName"),
                                                  resultSet.getString("phone"),
                                                  resultSet.getString("address"),
                                                  resultSet.getDate("birthday").toLocalDate());
                contact.setContactID(resultSet.getInt("ContactID"));
                contact.setImageFile(new File(resultSet.getString("imageFile")));
                contacts.add(contact);
            }
            contactTable.getItems().addAll(contacts);
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

