package code.interviews;

import java.util.HashMap;
import java.util.Map;

public class RomanToInteger {
    static Map<String, Integer> romanToInt =  new HashMap<>();
    static Map<Integer, String> intToRoman =  new HashMap<>();
    static {
        romanToInt.put("I", 1);
        romanToInt.put("V", 5);
        romanToInt.put("X", 10);
        romanToInt.put("L", 50);
        romanToInt.put("C", 100);
        romanToInt.put("D", 500);
        romanToInt.put("M", 1000);
        romanToInt.put("IV", 4);
        romanToInt.put("IX", 9);
        romanToInt.put("XL", 40);
        romanToInt.put("XC", 90);
        romanToInt.put("CD", 400);
        romanToInt.put("CM", 900);
    }

    public static void main(String[] args) {
//        System.out.println(romanToInt("III"));
//        System.out.println(romanToInt("LVIII"));
//        System.out.println(romanToInt("MCMXCIV"));

        System.out.println(intToRomanOptimised(3));
        System.out.println(intToRomanOptimised(58));
        System.out.println(intToRomanOptimised(1994));
    }
    static long romanToInt(String roman){
        char[] chars =  roman.toCharArray();
        String key = null;
        long res = 0;
        for (int i = 0; i < chars.length; i++) {
            char c =  chars[i];

            if(i < chars.length-1 && c == 'I' && (chars[i+1] == 'V' || chars[i+1] == 'X')
                    || i < chars.length-1 && c == 'X' && (chars[i+1] == 'L' || chars[i+1] == 'C')
                    || i < chars.length-1 && c == 'C' && (chars[i+1] == 'D' || chars[i+1] == 'M'))
            {key = c + String.valueOf(chars[i + 1]);i++;}
            else
                key =  String.valueOf(c);
            res += romanToInt.get(key);
        }
        return res;
    }

    static String intToRomanOptimised(int n){
        String[] thousands = {"", "M", "MM", "MMM"};
        String[] hundreds = {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};
        String[] tens = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
        String[] units = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};
        return thousands[n/1000]+hundreds[(n%1000)/100]+tens[(n%100)/10]+units[(n%10)/1];
    }

    static String intToRoman(int n){ // 1994
        String res = "";
        while(n >= 1000){
            res += intToRoman.get(1000);
            n -= 1000;
        }
        while(n >= 900){
            res += intToRoman.get(900);
            n -= 900;
        }
        while(n >=  500){
            res += intToRoman.get(500);
            n -= 500;
        }
        while(n >=  400){
            res += intToRoman.get(400);
            n -= 400;
        }
        while(n >=  100){
            res += intToRoman.get(100);
            n -= 100;
        }
        while(n >=  90){
            res += intToRoman.get(90);
            n -= 90;
        }
        while(n >=  50){
            res += intToRoman.get(50);
            n -= 50;
        }
        while(n >= 40){
            res += intToRoman.get(40);
            n -= 40;
        }
        while(n >= 10){
            res += intToRoman.get(10);
            n -= 10;
        }
        while(n >= 5){
            res += intToRoman.get(5);
            n -= 5;
        }
        while(n >= 4){
            res += intToRoman.get(4);
            n -= 4;
        }
        while(n >= 1){
            res += intToRoman.get(1);
            n -= 1;
        }
        return res;
    }
}
