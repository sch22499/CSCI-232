/* 
 * Sarah Hall
 * CSCI 232
 * Part three of homework assignment 3
 */
package homework3;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


public class Part3 {

    static boolean same = true;

    public static void main(String[] args) throws IOException {
        Scanner fileOne = getFile();
        Scanner fileTwo = getFile();
        int oneLength = 0;
        int twoLength = 0;

        String firstLine = fileOne.nextLine();
        String secondLine = fileTwo.nextLine();
        while (same) {
            same = firstLine != null && secondLine != null && firstLine.equals(secondLine);
            if (fileOne.hasNextLine()) {
                firstLine = fileOne.nextLine();
                oneLength++;
            }
            if (fileTwo.hasNextLine()) {
                secondLine = fileTwo.nextLine();
                twoLength++;
            }

            if (!same || oneLength != twoLength) {
                BufferedWriter out = new BufferedWriter(new FileWriter("diff.txt"));
                out.write("The files are different.");
                out.close();
            }
        }
    }

    public static Scanner getFile() {

        try {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter file (or location + file): ");
            String input = scanner.next();
            System.out.println();
            File file = new File(input);
            Scanner fileInput = new Scanner(file);
            return fileInput;

        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
            return null;

        }
    }
}
