import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class PracticePspc {
    public static void main(String[] args) throws IOException {
        Scanner scan = new Scanner(new File("practice.in.txt"));
        FileWriter fileWriter = new FileWriter("/Users/andrewhwang/IdeaProjects/pspc/p.out.txt");

        String output = "";
        while(scan.hasNextLine()) {
           int number = scan.nextInt();
           if (number == 0) {
               break;
           }

           if (number % 2 == 0) {
            output = "The number " + number + " is EVEN.";
           }
           else {
               output = "The number " + number + " is ODD.";
           }
            fileWriter.write(output);
            System.out.println(output);
        }

        //fileWriter.write(output);

    }
}
