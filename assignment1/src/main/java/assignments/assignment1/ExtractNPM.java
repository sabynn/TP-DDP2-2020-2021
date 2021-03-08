package assignments.assignment1;

import java.util.Scanner;

public class ExtractNPM {
    /*
    You can add other method do help you solve
    this problem
    
    Some method you probably need like
    - Method to get tahun masuk or else
    - Method to help you do the validation
    - and so on
    */

    public static boolean validate(long npm) {
        // TODO: validate NPM, return it with boolean
        return false;
    }

    public static String extract(long npm) {
        // TODO: Extract information from NPM, return string with given format
        return "";
    }

    public static void main(String args[]) {
        Scanner input = new Scanner(System.in);
        boolean exitFlag = false;
        while (!exitFlag) {
            long npm = input.nextLong();
            if (npm < 0) {
                exitFlag = true;
                break;
            }

            // TODO: Check validate and extract NPM
            
        }
        input.close();
    }
}