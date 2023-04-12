/**
 *
 *
 * @since 1.0
 * @author Derek Wicoff
 * @version 1.0
 */
package Model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;
/** main class.*/
public class FieldData { //poparray turned interface
    /** character array used for field.*/
    private static char[] field;
    /** counter used for displaying field number.*/
    private static int fieldCount = 1;
    /** scanner used for reading the file.*/
    private static Scanner scan;
    /** file, method contains setter for command line arguments.*/
    private static File file = new File("src/Model/minesweeper_input.txt");
    /** runs populate which uses setField().*/
    public static void getField() {
        try {
            scan = new Scanner(file);
            populate(scan); // turned into interface implemented
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
    /** toString method for the field char array.
     * @return string for display.*/
    public final String toString() {
        return Arrays.toString(field);
    }
    private static void populate(final Scanner sc) {
        scan = sc;
        int width = 0;
        int height = 0;
        StringBuilder sb = new StringBuilder();
        height = scan.nextInt();
        width = scan.nextInt();
        if (height + width == 0) { // manual break
            return;
        }
        System.out.println("Field #" + fieldCount++);
        char[] mineField //+height for '\n' chars
                = new char[height * width + (1 * height)];
        while (!scan.hasNextInt()) {
            String temp = scan.next();
            // adds the line from the input
            // and a new line true width is width +1
            sb.append(temp + "\n");
            for (int i = 0; i < sb.length(); i++) {
            //character data with offset correction  -2-width
            mineField[i] = sb.charAt(i);
            }
        }
        int count = 0;
        for (int i = 0; i < mineField.length; i++) { //
            count = 0;

            if (i - 1 > -1
                    && mineField[i - 1] == '*') { // check left
                count++;
            }
            if (i + 1 < mineField.length
                    && mineField[i + 1] == '*') {
                // check right
                count++;
            }
            if (i - (width + 1) > -1
                    && mineField[i - (width + 1)] == '*') {
                // check upper
                count++;
            }
            if (i - (width + 2) > -1
                    && mineField[i - (width + 2)] == '*') {
                // check upper left
                count++;
            }
            if (i - width > -1
                    && mineField[i - width] == '*') { // check upper right
                count++;
            }
            // check lower left
            if (i + width < mineField.length
                    && mineField[i + width] == '*') {
                count++;
            }
            // check lower
            if (i + width + 1 < mineField.length
                    && mineField[i + width + 1] == '*') {
                count++;
            }
            // check lower right
            if (i + width + 2 < mineField.length
                    && mineField[i + width + 2] == '*') {
                count++;
            }
            if (mineField[i] == '\n') {
                System.out.println();
            }
            if (mineField[i] != '*' && mineField[i] != '\n') {
                System.out.print(count);
            }
            if (mineField[i] == '*') {
                System.out.print('*');
            }
        }
        if (scan.hasNextInt()) {
            System.out.println();
            populate(scan);
        }
    }
    /** used in main to set the file to the command line argument.
     * @param fileIn string input for changing files
     *                for use with command line args*/
    public void setFile(final String fileIn) {
        file = new File(fileIn);
    }
}
