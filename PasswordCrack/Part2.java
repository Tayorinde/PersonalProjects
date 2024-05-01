package Actual;

import java.util.*;
import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Part2 {
	static volatile boolean passwordFound = false;

	public static void main(String[] args) throws InterruptedException {
		try {
			Files.copy(Path.of("protected3.zip"), Path.of("protected5-1.zip"));
			Files.copy(Path.of("protected3.zip"), Path.of("protected5-2.zip"));
			Files.copy(Path.of("protected3.zip"), Path.of("protected5-3.zip"));
			Files.copy(Path.of("protected3.zip"), Path.of("protected5-4.zip"));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
//		Files.delete(Path.of(filename));
		// Specify the path to the password-protected zip file
		String zipFilePath = "protected3.zip";
		
	
		// Initialize the alphabet to lowercase letters only
		String alphabet = "abcdefghijklmnopqrstuvwxyz";

		// Set the password length to 3 characters
		int passwordLength = 3;

		// Set the maximum number of attempts to 26^3 (the number of possible
		// 3-character lowercase letter combinations)
		int maxAttempts = (int) Math.pow(alphabet.length(), passwordLength);

		// Divide the list of passwords into two smaller lists
		int division = maxAttempts / 4;
//		System.out.println("this is the : " + division);
		int midpoint1 = division + 0;
		int midpoint2 = midpoint1 + division; 
		int midpoint3 = midpoint2 + division; 
		
		List<String> passwordList1 = generatePasswordList(alphabet, passwordLength, 0, midpoint1);
		List<String> passwordList2 = generatePasswordList(alphabet, passwordLength, midpoint1, midpoint2);
		List<String> passwordList3 = generatePasswordList(alphabet, passwordLength, midpoint2, midpoint3);
		List<String> passwordList4 = generatePasswordList(alphabet, passwordLength, midpoint3, maxAttempts);

		
//		maxAttempts
		// Set the shared variable that will indicate if the correct password has been
		// found

		// Set the start time
		long startTime = System.currentTimeMillis();

		// Create the first thread to try the passwords in passwordList1
		Thread thread1 = new Thread(() -> {
			for (String password : passwordList1) {
				if (passwordFound) {
					break;
				}
				try {
					// Try to open the zip file with the current password
					ZipFile zipFile = new ZipFile("protected5-1.zip");
					zipFile.setPassword(password);
					zipFile.extractAll("content1");

					// If the zip file was successfully opened and extracted, the correct password
					// has been found
					passwordFound = true;

					// Set the end time
					long endTime = System.currentTimeMillis();

					// Calculate the total time taken
					long totalTime = endTime - startTime;

					// Print the total time taken
					System.out.println("Password found: " + password);
					System.out.println("Total time taken: " + totalTime + " milliseconds");

					// Exit the program
					System.exit(0);

				} catch (ZipException e) {
					// If the zip file could not be opened with the current password, continue with
					// the next password
					continue;
				}
			}
		});

		// Create the second thread to try the passwords in passwordList2
		Thread thread2 = new Thread(() -> {
			for (String password : passwordList2) {

				if (passwordFound) {
					break;
				}
				try {
					// Try to open the zip file with the current password
					ZipFile zipFile = new ZipFile("protected5-2.zip");
					zipFile.setPassword(password);
					zipFile.extractAll("content2");

					// If the zip file was successfully opened and extracted, the correct password
					// has been found
					passwordFound = true;

					// Set the end time
					long endTime = System.currentTimeMillis();

					// Calculate the total time taken
					long totalTime = endTime - startTime;

					// Print the total time taken
					System.out.println("Password found: " + password);
					System.out.println("Total time taken: " + totalTime + " milliseconds");

					// Exit the program
					System.exit(0);

				} catch (ZipException e) {
					// If the zip file could not be opened with the current password, continue with
					// the next password
					continue;
				}
			}

		});
		Thread thread3 = new Thread(() -> {
			for (String password : passwordList3) {

				if (passwordFound) {
					break;
				}
				try {
					// Try to open the zip file with the current password
					ZipFile zipFile = new ZipFile("protected5-3.zip");
					zipFile.setPassword(password);
					zipFile.extractAll("content3");
// hi
					// If the zip file was successfully opened and extracted, the correct password
					// has been found
					passwordFound = true;

					// Set the end time
					long endTime = System.currentTimeMillis();

					// Calculate the total time taken
					long totalTime = endTime - startTime;

					// Print the total time taken
					System.out.println("Password found: " + password);
					System.out.println("Total time taken: " + totalTime + " milliseconds");

					// Exit the program
					System.exit(0);

				} catch (ZipException e) {
					// If the zip file could not be opened with the current password, continue with
					// the next password
					continue;
				}
			}

		});
		Thread thread4 = new Thread(() -> {
			for (String password : passwordList4) {

				if (passwordFound) {
					break;
				}
				try {
					// Try to open the zip file with the current password
					ZipFile zipFile = new ZipFile("protected5-4.zip");
					zipFile.setPassword(password);
					zipFile.extractAll("content4");

					// If the zip file was successfully opened and extracted, the correct password
					// has been found
					passwordFound = true;

					// Set the end time
					long endTime = System.currentTimeMillis();

					// Calculate the total time taken
					long totalTime = endTime - startTime;

					// Print the total time taken
					System.out.println("Password found: " + password);
					System.out.println("Total time taken: " + totalTime + " milliseconds");

					// Exit the program
					System.exit(0);

				} catch (ZipException e) {
					// If the zip file could not be opened with the current password, continue with
					// the next password
					continue;
				}
			}

		});

		// Start the threads
		thread1.start();
		thread2.start();
		thread3.start();
		thread4.start();
		// Wait for the threads to finish
		thread1.join();
		thread2.join();
		thread3.join();
		thread4.join();

		// If the password was not found, print a message indicating that it could not
		// be found
		if (!passwordFound) {
			System.out.println("Password not found.");
		}
		try {
			Files.delete(Path.of("protected5-1.zip"));
			Files.delete(Path.of("protected5-2.zip"));
			Files.delete(Path.of("protected5-3.zip"));
			Files.delete(Path.of("protected5-4.zip"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

//Helper method to generate a list of all possible passwords given an alphabet and a length
	public static List<String> generatePasswordList(String alphabet, int length, int start, int end) {
		List<String> passwordList = new ArrayList<>();
		for (int i = start; i < end; i++) {
			StringBuilder password = new StringBuilder();
			int value = i;
			for (int j = 0; j < length; j++) {
				password.insert(0, alphabet.charAt(value % alphabet.length()));
				value /= alphabet.length();
			}
			passwordList.add(password.toString());
		}
		return passwordList;
	}
}