package code.string;

import java.util.Arrays;

public class KmpPatternSearch {
    public static void main(String[] args) {
        System.out.println(kmpSearch("ABABDABACDABABCABAB", "ABABCABAB"));
    }

    public static int kmpSearch(String str, String pattern){
        int m =  str.length(), n = pattern.length();
        int[] lps =  prepareSuffixPrefix(pattern);
        System.out.println(Arrays.toString(lps));
        char[] text = str.toCharArray();
        char[] pat =  pattern.toCharArray();
        int i=0,j=0;
        while(i<m && j<n){
            if(text[i] == pat[j]){
                i++;
                j++;
            }else{
                if (j!=0)
                    j=lps[j-1];
                else{
                    i++;
                }
            }
        }
        if (j==n)
            return i-j;
        return -1;
    }

//    "ABABCABAB"
    public static int[] prepareSuffixPrefix(String pattern){
        int n =  pattern.length();
        char[] pat = pattern.toCharArray();
        int[] lps = new int[n];
        lps[0] = 0; // for len=1 no sufPref
        int i=1, j=0;

        while(i<n){
           if(pat[i] == pat[j])
               lps[i++] = ++j;
           else{
               lps[i] =  j==0 ? 0 : lps[j-1];
           }
        }
        return lps;
    }
}
