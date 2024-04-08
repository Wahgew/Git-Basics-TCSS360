import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ProblemD {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scan = new Scanner(new File("d.in.txt"));

        while(scan.hasNextInt()) {
            String output = "";
            int n = scan.nextInt();
            int nEquation1 = n / 3;
            int nEquation2 = 2 * n / 3;

            int m = scan.nextInt();
            int mEquation1 = m / 3;
            int mEquation2 = 2 * m / 3;

            int x = scan.nextInt();
            int y = scan.nextInt();

            if ((nEquation1 < x && x < nEquation2) && (mEquation1 < y && y < mEquation2)) {
                output = "GOOD";
            } else {
                output = "BAD";
            }
            System.out.println(output);
        }


    }
}
