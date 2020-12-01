package HelperClasses;

import java.nio.file.FileSystem;
import java.util.Scanner;

public class MenuHelper {

    private MenuHelper(){}

    public static <T>T displayMenu(T[] options){
        return displayMenu("", options);
    }

    public static <T>T displayMenu(String message, T... options){
        // Format a string
        StringBuilder sb = new StringBuilder();

        // Append the introduction message and line.
        sb.append(message).append(System.lineSeparator());

        // Use +1 to allow conversion of 0 to 1 based index.
        for (int i = 0; i < options.length; i++) {
            // Generates a menu based off of the toString method.
            sb.append(i + 1)
                    .append(").")
                    .append(" ")
                    .append(options[i].toString())
                    .append(System.lineSeparator());
        }

        // Get the index of the menu option.
        int index = displayMenu(sb.toString(), 1, options.length);

        // Convert back to 0 based index.
        return options[index - 1];
    }

    public static int displayMenu(String message, int min, int max){
        // Display initial message
        System.out.println(message);

        // Create the scanner
        Scanner scan = new Scanner(System.in);

        // Store the index
        int index;

        // Loop until it breaks out of loop
        while (true){
            // Tell the user the range of values.
            System.out.println("Please enter a number between " + min + " and " + max);

            // Type safe loop
            while (!scan.hasNextInt()){
                // Re display the message.
                System.out.println("Please enter a number between " + min + " and " + max);

                // Flush the buffer
                scan.nextLine();
            }

            // Store value
            int value = scan.nextInt();

            // Validate the index value
            if (value >= min && value <= max){
                index = value;
                break;
            }
        }
        return index;
    }

    public static String getInput(String prompt){
        // Display the prompt.
        System.out.println(prompt);

        // Create the scanner
        Scanner scan = new Scanner(System.in);

        return scan.nextLine();
    }
}
