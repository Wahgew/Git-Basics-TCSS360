import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class ProblemA {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scan = new Scanner(new File("a.in.txt"));
        ArrayList<String> glutten = new ArrayList<>();
        ArrayList<String> nut = new ArrayList<>();
        glutten.add("soy sauce");
        glutten.add("bread");
        glutten.add("wheat");
        glutten.add("kamut");
        glutten.add("peanut chocolate");

        nut.add("peanut");
        nut.add("almond");
        nut.add("nut butter");
        nut.add("peanut chocolate");
        nut.add("cashew");
        while(scan.hasNextLine()) {
            String input = scan.nextLine().toLowerCase();
            String output = "";
            if (nut.contains(input) && glutten.contains(input)) {
                output = "BOTH";

            }else if (glutten.contains(input)) {
                output = "GLUTEN";
            } else if (nut.contains(input)) {
                output = "NUT";
            }
            else {
                output = "SAFE";
            }
            System.out.println(output);
        }
    }
}
