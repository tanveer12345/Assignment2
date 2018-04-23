/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test3_200357701;

import java.util.Arrays;
import java.util.TreeSet;

/**
 *
 * @author tanve
 */
public class Test3_200357701 {

 /*
 * Student Name- Tanveer Kalia
 * Student Number- 200357701
 */
    public static void main(String[] args) {
   /**
         * Question 1 - Using existing Java collections, store the following Strings in a 
         *              data model that will automatically store the results alphabetically 
         *              and ensure there is no duplication.
         *              "alpha","Beta","beta","Charlie","Delta","Echo"
         *              Marks - 2 marks
         */
        
        System.out.println("Storing the List alphabetically with no duplicates");
        TreeSet<String> treeSet = new TreeSet<>();
        treeSet.add("alpha");
        treeSet.add("Beta");
        treeSet.add("beta");
        treeSet.add("Charlie");
        treeSet.add("Delta");
        treeSet.add("Echo");
        
        for (String name: treeSet)
        {
            System.out.printf("Name: %s%n", name);
        }
    
    }
    
}
