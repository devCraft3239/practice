package code.dp;

import java.util.Arrays;
import java.util.HashSet;

/**
 Given an array of integers, find the length of the longest sub-sequence such that elements in the subsequence are consecutive integers, the consecutive numbers can be in any order.
 Input: arr[] = {1, 9, 3, 10, 4, 20, 2}
 Output: 4
 Explanation: The subsequence 1, 3, 4, 2 is the longest subsequence of consecutive elements

 Input: arr[] = {36, 41, 56, 35, 44, 33, 34, 92, 43, 32, 42}
 Output: 5
 Explanation: The subsequence 36, 35, 33, 34, 32 is the longest subsequence of consecutive elements.
 * */
public class LongestConsecutiveSubsequence { // Hashing

    public static int longestConsecutiveSubsequence(int[] arr){
        arr = Arrays.stream(arr).sorted().distinct().toArray();
        int cnt = 1, lcs = 1;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] == arr[i-1]+1)
                cnt++;
            else
                cnt = 1;
            lcs = Math.max(lcs, cnt);
        }
        return lcs;
    }

    public static int longestConsecutiveSubsequenceHashing(int[] arr){
        HashSet lookupSet =  new HashSet(Arrays.asList(arr));
        int lcs = 1;
        for (int i = 0; i < arr.length; i++) {
            // check if this can be starting of seq
            if (!lookupSet.contains(arr[i]-1)){ // check if this can be start of the consecutive subsequence
                int j = arr[i];
                while (lookupSet.contains(j))
                    j++;
                lcs = Math.max(lcs, j- arr[i]);
            }
        }
        return lcs;
    }
}
