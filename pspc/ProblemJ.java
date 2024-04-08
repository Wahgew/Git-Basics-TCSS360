import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ProblemJ {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scan = new Scanner(new File("j.in.txt"));
        int count = 0;

        while(scan.hasNext()) {
            String line = scan.nextLine();
            String pat = "o";

            String newS = line.replace(pat,"");
            count = line.length() - newS.length() / line.length();

            }
        
            if (line.contains("oo")) {
               line = line.replace("oo", "oOOoo");
            }
            System.out.println(line);
        }
    }

    //nexthas
}
