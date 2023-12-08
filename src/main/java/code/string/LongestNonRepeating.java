package code.string;

import java.util.HashMap;
import java.util.Map;
/**
 Example 1:
 Input: “ABCDEFGABEF”
 Output: 7
 Explanation: The longest substring without repeating characters are “ABCDEFG”, “BCDEFGA”, and “CDEFGAB” with lengths of 7

 Example 2:
 Input: “GEEKSFORGEEKS”
 Output: 7
 Explanation: The longest substrings without repeating characters are “EKSFORG” and “KSFORGE”, with lengths of 7
 * */
public class LongestNonRepeating {
    public static void main(String[] args) {
//        System.out.println(longestNonRepeatingSubString("ABCDEFGABEF"));
        System.out.println(longestNonRepeatingSubString("GEEKSFORGEEKS"));
    }
    static int longestNonRepeatingSubstring(String s){
        if(s.isEmpty())
            return 0;
        Map<Character, Integer> map  = new HashMap<>();
        int st=0, len=1, maxLen=1, n = s.length();
        for (int i = 0; i < n; i++) {
            char key =  s.charAt(i);
            if(map.containsKey(key)){
                st =  Math.max(map.get(key)+1,st);
            }
            maxLen =  Math.max(maxLen, i -st+1);
            map.put(key, i);
        }
        return Math.max(maxLen, n-st);
    }

    static int longestNonRepeatingSubString(String s){
        int n = s.length();
        Map<Character, Integer> lof = new HashMap<>();
        lof.put(s.charAt(0), 0);
        int st=0, maxLen =1;
        for (int i = 1; i < n; i++) {
            Character key = s.charAt(i);
            if(lof.containsKey(key) && st <= lof.get(key)){
                maxLen = Math.max(maxLen, i-st);
                st = lof.get(key)+1;
            }
            lof.put(key, i);
        }
        return maxLen;
    }
}
