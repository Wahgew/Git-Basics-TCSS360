import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Scanner;

public class ProblemE {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scan = new Scanner(new File("e.in.txt"));
        ArrayList<Integer> broomsHeight = new ArrayList<>();
        ArrayList<Integer> witchHeight = new ArrayList<>();
        ArrayList<Integer> broomAndWitch = new ArrayList<>();

        int count = 0;

        while (scan.hasNextInt()) {
            String output = "";
            int witch = scan.nextInt();

            for (int i = 0; i < witch; i++) {
                witchHeight.add(scan.nextInt());
            }

            for (int i = 0; i < witch; i++) {
                broomsHeight.add(scan.nextInt());
            }
            broomAndWitch.addAll(witchHeight);
            //broomAndWitch.addAll(broomsHeight);
            //Collections.sort(broomAndWitch);
            Collections.sort(broomsHeight);
            Collections.sort(witchHeight);

            for (int i = 0; i < broomsHeight.size(); i++) {
                int total = broomsHeight.get(i) - witchHeight.get(i);
                count += Math.abs(total);
            }
            //System.out.println();
            System.out.println(count);
            //broomAndWitch.clear();
            broomsHeight.clear();
            witchHeight.clear();
            count = 0;
        }
    }
}
