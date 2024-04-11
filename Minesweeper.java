/**
 * @author Sopheanith Ny
 * TCSS 360
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * This Minesweeper class is implement a basic game of the minesweeper. It allows users
 *  to enter the dimension rows and columns of a minefield and then reveals the
 *  numbers indicating the number of surrounding mines for each cell that isn't a mine itself.
 *
 */
public class Minesweeper {
    /**
     * This is a main method where it's printing the minefield and start the game.
     *
     * @param args
     * @throws FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(System.in);
        //Scanner scanner = new Scanner(new File("minesweeper_input.txt"));
        int fieldNum = 1;
        while (true) {
            //System.out.print("Enter the rows and columns: ");
            int row = scanner.nextInt();
            int column = scanner.nextInt();
            char[][] mineField = new char[row][column];

            if (row == 0 && column == 0) {
                break;
            }
            for (int i = 0; i < row; i++) {
                String line = scanner.next();
                for (int j = 0; j < column; j++) {
                    mineField[i][j] = line.charAt(j);
                }
            }
            System.out.println("Field #" + fieldNum + ":");
            minesField(mineField);
            System.out.println();
            fieldNum++;
        }
        scanner.close();
    }

    /**
     * The mineField is a helper method to print the minesfield with number
     * @param field
     */
    private static void minesField(char[][] field) {
        int row = field.length;
        int column = field[0].length;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                if (field[i][j] == '*') {
                    System.out.print("*");
                } else {
                    int count = countMines(field, i, j);
                    System.out.print(count);
                }
            }
            System.out.println();
        }
    }

    /**
     * the countMines method is a helper method that counting the number
     * of the mines that surrounding in the fields.
     *
     * @param field
     * @param row
     * @param col
     * @return the number of surrounding around the fields.
     */
    private static int countMines(char[][] field, int row, int col) {
        int count = 0;
        int rows = field.length;
        int cols = field[0].length;

        for (int i = Math.max(0, row - 1); i <= Math.min(rows - 1, row + 1); i++) {
            for (int j = Math.max(0, col - 1); j <= Math.min(cols - 1, col + 1); j++) {
                if (i != row || j != col) {
                    if (field[i][j] == '*') {
                        count++;
                    }
                }
            }
        }
        return count;
    }
}
