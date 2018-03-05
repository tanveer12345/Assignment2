/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment1_200357701;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Month;

/**
 *
 * @author tanve
 */
public class Assignment1_200357701 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, SQLException {
        Contacts contact= new Contacts("tanveer2","Kalia2", "705-970-7430","317 grove street",LocalDate.of(1998, Month.NOVEMBER,13),
                                new File("./src/Images/DefaultImage.jpg"));
        System.out.printf("%s%n",contact);
        contact.insertIntoDB();
    
    }
    
}
