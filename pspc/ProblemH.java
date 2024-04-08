import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Scanner;

public class ProblemH {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scan = new Scanner(new File("h.in.txt"));

        while(scan.hasNextLong()) {
            String output = "";
            String pattern = "110";
            //int count = 0;
            long number = scan.nextLong();
            /*String binary = Long.toBinaryString(number);

            String newS = binary.replace(pattern,"");
            count = binary.length() - newS.length() / pattern.length();

            System.out.println(count);*/

            int patInt = 0;
            int powerTwo = 1;

            int allOne = 0;

            for (int i = pattern.length() - 1; i >= 0; i--){
                int currentBit =pattern.charAt(i) - '0';
                patInt += (powerTwo* currentBit);
                allOne = allOne + powerTwo;
                powerTwo = powerTwo*2;
            }
            int count = 0;
            while(number != 0 && number >= patInt) {
                if ((number & allOne) == patInt) {
                    count++;
                }
                number = number >> 1;


            }
            if(count == 3){
                output = "SPOOKY";
            }
            else{
                output = "MEH";
            }
            System.out.println(output);



            //System.out.println(output);
            /*String binary = Long.toBinaryString(number);
            //int count = StringUtils
            String binaryNumber = Arrays.toString(binary.split("110"));


//            if (String.valueOf(binary.contains("110")) {
//
//            }

            //binary.split("110");
            System.out.println(StringUtils.count);
            //System.out.println(binaryNumber);*/
        }

    }
}
