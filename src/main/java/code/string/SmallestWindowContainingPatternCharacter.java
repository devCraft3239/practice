package code.string;

import java.util.Locale;

/**
 Given two strings, string and pattern, the task is to find the smallest substring in string containing all characters of pattern.

 Input: string = “this is a test string”, pattern = “tist”
 Output: “t stri”
 Explanation: “t stri” contains all the characters of pattern.

 Input: string = “geeksforgeeks”, pattern = “ork”
 Output: “ksfor”

 * */
public class SmallestWindowContainingPatternCharacter {

    public static void main(String[] args) {
        System.out.println(smallestWindowContainingPatternChars("timeToPractice", "toc"));
        System.out.println(smallestWindowContainingPatternChars("timeToPractico", "toc"));
    }
    static final int NO_OF_CHAR =  256;
    static String smallestWindowContainingPatternChars(String str, String pat){
        str =  str.toLowerCase(Locale.ROOT);
        pat =  pat.toLowerCase(Locale.ROOT);
        int freqPat[] =  new int[NO_OF_CHAR], freqStr[] = new int[NO_OF_CHAR];
        for (int i = 0; i < pat.length(); i++)
            freqPat[pat.charAt(i)]++;

        int cnt=0, wStart=0, minLength = Integer.MAX_VALUE;
        for (int i = 0; i < str.length(); i++) {
            char c =  str.charAt(i);
            freqStr[c]++;

            // increment cnt if char also found in patter and freqStr[c] <= freqPat[c]
            if(freqStr[c] <= freqPat[c]){
                cnt++;
            }

            // try to minimise the window by removing char by char i.e. slide window
            while(cnt == pat.length()){
                char startChar = str.charAt(wStart);
                freqStr[startChar]--;

                // if occ of c in freqStr is more than we can ignore else need to decrement cnt;
                if (freqStr[startChar] < freqPat[startChar]){
                    cnt--;
                    minLength = Math.min(minLength, i-wStart+1);
                }

                wStart++;
            }
        }
        return str.substring(wStart-1, wStart+minLength-1);
    }
}
