package Actual;

import java.util.*;
import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;

public class ZipCracker {
  
  public static void main(String[] args) {
    
    // Specify the path to the password-protected zip file
//    String zipFilePath = "protected3.zip";
	  String zipFilePath = "protected3.zip";
    
    // Initialize the alphabet to lowercase letters only
    String alphabet = "abcdefghijklmnopqrstuvwxyz";
    
    // Set the password length to 3 characters
    int passwordLength = 3;
    
    // Set the maximum number of attempts to 26^3 (the number of possible 3-character lowercase letter combinations)
    int maxAttempts = (int) Math.pow(alphabet.length(), passwordLength);
    
    // Set the start time
    long startTime = System.currentTimeMillis();
    
    // Loop through all possible 3-character lowercase letter combinations until the correct password is found
    for (int i = 0; i < maxAttempts; i++) {
      
      // Generate the next password to try
      String password = "";
      int value = i;
      for (int j = 0; j < passwordLength; j++) {
        int index = value % alphabet.length();
        password += alphabet.charAt(index);
        value /= alphabet.length();
      }
      
      try {
        // Try to open the zip file with the current password
        ZipFile zipFile = new ZipFile(zipFilePath);
        zipFile.setPassword(password);
        zipFile.extractAll(".");
        
        // If the zip file was successfully opened and extracted, the correct password has been found
        System.out.println("Password found: " + password);
        
        // Set the end time
        long endTime = System.currentTimeMillis();
        
        // Calculate the total time taken
        long totalTime = endTime - startTime;
        
        // Print the total time taken
        System.out.println("Total time taken: " + totalTime + " milliseconds");
        
        // Exit the program
        System.exit(0);
        
      } catch (ZipException e) {
        // If the zip file could not be opened with the current password, continue with the next password
        continue;
      }
    }
    
    // If no password was found, print an error message
    System.err.println("Password not found!");
    
  }
  
}
