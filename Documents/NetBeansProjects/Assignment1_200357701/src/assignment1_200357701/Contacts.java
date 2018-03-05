
package assignment1_200357701;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;


/**
 *
 * @author tanve
 */
public class Contacts {

private String firstName, lastName, address, phone;
private LocalDate birthday;
private File imageFile;
private int contactID;

    public Contacts(String firstName, String lastName, String phone,String address, LocalDate birthday) {
        setFirstName(firstName);
        setLastName(lastName);
        setPhone(phone);
        setAddress(address);
        setBirthday(birthday);
        setImageFile(new File("./src/Images/DefaultImage.jpg"));
    }
 public Contacts(String firstName, String lastName, String address, String phone, LocalDate birthday, File imageFile) throws IOException {
        this(firstName, lastName,address, phone, birthday);
        setImageFile(imageFile);
        copyImageFile();
    }

    public int getContactID() {
        return contactID;
    }

    public void setContactID(int contactID) {
        if(contactID >= 0)
            this.contactID = contactID;
        
        else
            throw new IllegalArgumentException("Contact id must be >= 0");
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        if(phone.matches("[2-9]\\d{2}[-.]?\\d{3}[-.]\\d{4}"))
            return phone;
    else
            throw new IllegalArgumentException("The Phone Number should be in this format- NXX-XXX-XXXX");
    
    }

    public LocalDate getBirthday() {
       int age = Period.between(birthday,LocalDate.now()).getYears();
        if(age > 10 && age < 100)
                return birthday;
        else
            throw new IllegalArgumentException("The age should be between 10 to 100 years old");
    }

    public File getImageFile() {
        return imageFile;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public void setImageFile(File imageFile) {
        this.imageFile = imageFile;
    }
    
    public void copyImageFile() throws IOException
    {
        //create a new Path to copy the image into a local directory
        Path sourcePath = imageFile.toPath();
        
        Path targetPath = Paths.get("./src/Images/"+imageFile.getName());
        
        //copy the file to the new directory
        Files.copy(sourcePath, targetPath, StandardCopyOption.REPLACE_EXISTING);
        
        //update the imageFile to point to the new File
        imageFile = new File(targetPath.toString());
    }
    
    
    
    
   
    
    
@Override
public String toString(){
    return String.format("%s %s is %d years old", firstName, lastName, Period.between(birthday,LocalDate.now()).getYears());
}
 public void insertIntoDB() throws SQLException
    {
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        
        try
        {
            //1. Connect to the database
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/contacts_db?user=root");
            
            //2. Create a String that holds the query with ? as user inputs
            String sql = "INSERT INTO contacts (firstName, lastName, phone,address, birthday, imageFile)"
                    + "VALUES (?,?,?,?,?,?)"; 
                    
            //3. prepare the query
            preparedStatement = conn.prepareStatement(sql);
            
            //4. Convert the birthday into a SQL date
            Date db = Date.valueOf(birthday);
                   
            //5. Bind the values to the parameters
            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);
            preparedStatement.setString(3, phone);
            preparedStatement.setString(4, address);
            preparedStatement.setDate(5, db);
            preparedStatement.setString(6, imageFile.getName());
            
            preparedStatement.executeUpdate();
        }
        catch (Exception e)
        {
            System.err.println(e.getMessage());
        }
        finally
        {
            if (preparedStatement != null)
                preparedStatement.close();
            
            if (conn != null)
                conn.close();
        }
    }

}
