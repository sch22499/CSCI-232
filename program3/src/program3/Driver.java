/*
 * Sarah Hall
 * CSCI 232
 * program 3
 */
package program3;
import java.io.*;
import java.util.*;

public class Driver
{
    
    public static <K, V> void main(String[] args)
    {
    	Scanner scan = new Scanner(System.in);
    	
    	int num = scan.nextInt();
       HashTable<K,V> table = new HashTable<K,V>();
       System.out.println("Please enter an option");
       System.out.println("Press 1 to print the table");
       System.out.println("Press 2 to add a Node");
       System.out.println("Press 3 to remove a Node");
       System.out.println("Press 4 to search for a Node");
       String input = scan.nextLine();
       switch(num) {
       case 1:{
    	   table.display();
       }
       case 2:{
    	   table.add();
       }
       case 3:{
    	   table.remove(key);
       }
       case 4:{
    	   table.locate(key);
       }
       }
       
    }
    
    
  
}