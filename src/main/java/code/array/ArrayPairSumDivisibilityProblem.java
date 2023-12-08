package code.array;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 Given an array of integers and a number k, write a function that returns true
 if given array can be divided into pairs such that sum of every pair is divisible by k.
 Input : arr = [9, 5, 7, 3], k = 6
 Output: True
 Explanation: {(9, 3), (5, 7)} is a
 possible solution. 9 + 3 = 12 is divisible
 by 6 and 7 + 5 = 12 is also divisible by 6.

 Input : arr = [2, 4, 1, 3], k = 4
 Output: False
 Explanation: There is no possible solution.

 * */
public class ArrayPairSumDivisibilityProblem {
    public static void main(String[] args) {
        System.out.println(isDivisible(new int[]{2, 4, 1, 3}, 4));
        System.out.println(isDivisible(new int[]{9, 5, 7, 3}, 6));
        System.out.println(isDivisible(new int[]{3,6,7,9},5));
        System.out.println(isDivisible(new int[]{3,6,7,9,6,1}, 5));
        System.out.println("\n\n");
        System.out.println(isDivisible2(new int[]{2, 4, 1, 3}, 4));
        System.out.println(isDivisible2(new int[]{9, 5, 7, 3}, 6));
        System.out.println(isDivisible2(new int[]{3,6,7,9},5));
        System.out.println(isDivisible2(new int[]{3,6,7,9,6,1}, 5));

    }
    static  boolean dividablePairExist(int arr[], int k){ // O(n)
        int n = arr.length;
        if (n%2 != 0)
            return false;

        Map<Integer, Integer> freqMap =  new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            int rem =  arr[i]%k;
            freqMap.put(rem, freqMap.getOrDefault(rem,0)+1);
        }

        for (int i = 0; i < arr.length; i++) {
            int rem =  arr[i]%k;
            if((rem == 0 || rem*2 ==k) && freqMap.get(rem)%2 !=0)
                return false;
            if(freqMap.get(rem) != freqMap.get(k-rem))
                return false;
        }
        return true;
    }

    static boolean dividablePairExist2(int[] arr, int k){ // O(nlogn)
        int n =  arr.length;
        if(n%2 !=0)
            return false;
        for (int i = 0; i < n; i++) {
            arr[i] = arr[i]%k;
        }

        Arrays.sort(arr);
        int i=0, j = arr.length-1;
        while (i<j){
            if ((arr[i++]+arr[j--])%k != 0)
                return false;
        }
        return true;
    }

    static boolean isDivisible(int[] arr, int k){
        int n =  arr.length;
        if(n%2 != 0)
            return false;
        for(int i=0;i<arr.length;i++){
            arr[i] = arr[i]%k;
        }
        Arrays.sort(arr);
        int i= 0, j = arr.length -1;
        while(i<j){
            if((arr[i++]+arr[j--])%k !=0)
                return false;
        }
        return true;
    }
    static boolean isDivisible2(int[] arr, int k){
        Map<Integer, Integer> lookUp = new HashMap<>();
        int n = arr.length;
        for (int i = 0; i < n; i++)
            lookUp.put(arr[i]%k, lookUp.getOrDefault(arr[i]%k, 0)+1);
        AtomicBoolean flag = new AtomicBoolean(true);
        lookUp.entrySet().forEach(entry -> {
            int key = entry.getKey();
            if ((key ==0 || key*2 == k) && lookUp.get(key) !=2)
                flag.set(false);
            if (lookUp.get(key) != lookUp.get(k-key))
                flag.set(false);
        });
        return flag.get();
    }
}
