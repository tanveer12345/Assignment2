/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment2_200357701;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author tanve
 */
public class Index {
    private String customerName;
    private int customerId;
    private byte[] salt;
    private String password;

    public Index(String cutomerName, String password) throws NoSuchAlgorithmException {
        setCustomerName(customerName);
        salt = PasswordGenerator.getSalt();
        this.password = PasswordGenerator.getSHA512Password(password, salt);
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public int getCustomerId() {
        return customerId;
    }

   

    public byte[] getSalt() {
        return salt;
    }

    public String getPassword() {
        return password;
    }
    
    public void insertIntoDB() throws SQLException
    {
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        
        try
        {
            //1. Connect to the database
            conn = DriverManager.getConnection("jdbc:mysql://aws.computerstudi.es:3306/" + "gc200357701", "gc200357701", "20VCRJu0Fn");
            
            //2. Create a String that holds the query with ? as user inputs
            String sql = "INSERT INTO index1 (customerName, password, salt)"
                    + "VALUES (?,?,?)"; 
                    
            //3. prepare the query
            preparedStatement = conn.prepareStatement(sql);
                   
            //5. Bind the values to the parameters
            preparedStatement.setString(1, customerName);
            preparedStatement.setString(2, password);
            preparedStatement.setBlob(3, new javax.sql.rowset.serial.SerialBlob(salt));
            
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
