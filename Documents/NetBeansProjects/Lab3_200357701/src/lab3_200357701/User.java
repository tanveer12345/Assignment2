/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab3_200357701;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 *
 * @author tanve
 */
public class User {
    private int userId;
    private String password;

    public User(String password) {
        setPassword(password);
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
     public static byte[] getSalt() throws NoSuchAlgorithmException
    {
        SecureRandom rng = SecureRandom.getInstanceStrong();
        byte[] salt = new byte[16];
        rng.nextBytes(salt);
        return salt;
    }
    
    /**
     * This will hash a password with a given salt and return it as a String
     */
    public static String getPW(String pw, byte[] salt)
    {
        String hashedPW = null;
        
        try
        {
            //configure the hashing algorithm
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            md.update(salt);
            
            byte[] hashed = md.digest(pw.getBytes());
            
            StringBuilder sb = new StringBuilder();
            StringBuilder sb2 = new StringBuilder();
            
        
            
            for (int i=0; i<hashed.length; i++)
            {
                sb.append(Integer.toString((hashed[i] & 0xff)+0x100,16).substring(1));
            }
            
            for (int i=0; i<hashed.length; i++)
            {
   
                sb2.append(String.format("%02x", hashed[i]));
            }
            hashedPW = sb.toString();
           

        }
        catch (NoSuchAlgorithmException e)
        {
           System.err.println(e);
        }
        
        return hashedPW;
    }
    
}
