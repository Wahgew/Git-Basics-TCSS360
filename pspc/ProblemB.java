import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ProblemB {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scan = new Scanner(new File("b.in.txt"));
        String equalCandy = "";
        int candyTotal = 0;

        while(scan.hasNextInt()) {
            int pieces = scan.nextInt();
            int kids = scan.nextInt();

           for (int i = 0; i < pieces; i++) {
               candyTotal += scan.nextInt();
           }

           //candyTotal % pieces == 0 && candyTotal % kids == 0
            if (pieces % kids == 0 || pieces % kids == 2  && candyTotal % kids == 0) {
                equalCandy = "YES";
            } else {
                equalCandy = "NO";
            }
            System.out.println(equalCandy);
            candyTotal = 0;
        }

    }
}
