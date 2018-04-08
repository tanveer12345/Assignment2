/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab3;

import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

/**
 *
 * @author tanve
 */
public class Lab3 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws NoSuchAlgorithmException {
        // TODO code application logic here
                Scanner input = new Scanner(System.in);
        
        System.out.println("Please enter the password");
        String password = input.nextLine();
        
        byte[] salt = User.getSalt();
        String hashedPassword = User.getPW(password, salt);
        User user = new User(hashedPassword);
        
        System.out.println("Please enter the password to login into your account");
        String password2 = input.nextLine();
        String hashedPassword2 = User.getPW(password2, salt);
        
        if(!(hashedPassword2.equals(user.getPassword()))){
            do{
                System.out.println("Please enter the right password");
                String password3 = input.nextLine();
                String hashedPassword3 = User.getPW(password3, salt);
                hashedPassword2 = hashedPassword3;
            }
            while(!(hashedPassword2.equals(user.getPassword())));
            System.out.println("Success");
        }
        
        else{
            System.out.println("Success");
        }
        
    }
    
    
}
