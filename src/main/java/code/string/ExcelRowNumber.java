package code.string;

import java.util.Scanner;
/**
 MS Excel columns have a pattern like A, B, C, …, Z, AA, AB, AC, …., AZ, BA, BB, … ZZ, AAA, AAB ….. etc. In other words, column 1 is named “A”, column 2 as “B”, and column 27 as “AA”.
 Given a column number, find its corresponding Excel column name. The following are more examples.

 Input          Output
 26             Z
 51             AY
 52             AZ
 80             CB
 676            YZ
 702            ZZ
 705            AAC
 * */

public class ExcelRowNumber {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String rowCell = sc.nextLine();
        System.out.println(findNumberForCell(rowCell));
    }

    public static long findNumberForCell(String row) {
        long res = 0;
        int n= row.length()-1;
        for (int i = n; i>=0 ; i--) {
            res += (row.charAt(i) - 'A'+1) * Math.pow(26, n-i);
        }
        return res;
    }
}

class AtoI {
    public static void main(String[] args) {
        String str =  "123";
        System.out.println(strToInt(str));
    }

    public static int strToInt(String str){
        int res = 0, n= str.length()-1;
        for (int i = n; i >=0 ; i--) {
            res += (str.charAt(i) - '0') * Math.pow(10,n-i);
        }
        return res;
    }
}
