package code.array;

import java.lang.reflect.Array;
import java.util.*;

import static code.array.SubArrayWithGivenSum.sumArray;

/**
 Given an array arr[] of non-negative integers and an integer sum, find a subarray that adds to a given sum.
 Input: arr[] = {1, 4, 20, 3, 10, 5}, sum = 33
 Output: Sum found between indexes 2 and 4
 Explanation: Sum of elements between indices 2 and 4 is 20 + 3 + 10 = 33

 Input: arr[] = {1, 4, 0, 0, 3, 10, 5}, sum = 7
 Output: Sum found between indexes 1 and 4
 Explanation: Sum of elements between indices 1 and 4 is 4 + 0 + 0 + 3 = 7

 Input: arr[] = {1, 4}, sum = 0
 Output: No subarray found
 Explanation: There is no subarray with 0 sum
 * */
public class SubArrayWithGivenSum {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(subArrayWithGivenSum(new int[]{1, 4, 20, 3, 10, 5}, 33)));
        System.out.println(Arrays.toString(subArrayWithGivenSum(new int[]{1, 4, 0, 0, 3, 10, 5}, 7)));
        System.out.println(Arrays.toString(subArrayWithGivenSum(new int[]{1, 4}, 0)));
        System.out.println("=====================================");

        System.out.println(Arrays.toString(subArrayWithGivenSumOptimised(new int[]{1, 4, 20, 3, 10, 5}, 33)));
        System.out.println(Arrays.toString(subArrayWithGivenSumOptimised(new int[]{1, 4, 0, 0, 3, 10, 5}, 7)));
        System.out.println(Arrays.toString(subArrayWithGivenSumOptimised(new int[]{1, 4}, 0)));
        System.out.println("=====================================");

        System.out.println(Arrays.toString(subArrayWithGivenSumOptimised2(new int[]{1, 4, 20, 3, 10, 5}, 33)));
        System.out.println(Arrays.toString(subArrayWithGivenSumOptimised2(new int[]{1, 4, 0, 0, 3, 10, 5}, 7)));
        System.out.println(Arrays.toString(subArrayWithGivenSumOptimised2(new int[]{1, 4}, 0)));
    }

    static int[] subArrayWithGivenSum(int[] arr, int sum){
        int n = arr.length;
        for (int i = 0; i < n; i++) { // start
            for (int j = i; j < n; j++) { // end
                if(sumArray(arr, i, j) == sum)
                    return new int[]{i,j};
            }
        }
        return new int[]{-1,-1};
    }

    static int[] subArrayWithGivenSumOptimised(int[] arr, int sum){
        int n= arr.length;
        int currSum = arr[0], start = 0;
        for (int i = 1; i <= n; i++) {
            while (currSum > sum && start < i-1){
                currSum -= arr[start];
                start++;
            }
            if(currSum == sum)
                return new int[]{start, i-1};
            if(i < n)
                currSum += arr[i];
        }
        return new int[]{-1,-1};
    }

    static int[] subArrayWithGivenSumOptimised2(int[] arr, int sum){
        int n= arr.length;
        int currSum = 0;
        Map<Integer, Integer> prefixSum =  new HashMap<>();
        for (int i = 0; i < n; i++) {
            currSum += arr[i];
            int prevReqSum =  currSum - sum;
            if(prefixSum.containsKey(prevReqSum)) // there is an array with sum = 0
                return new int[]{prefixSum.get(prevReqSum)+1, i};
            prefixSum.put(currSum, i);
        }
        return new int[]{-1,-1};
    }
    public static int sumArray(int[] arr, int start, int end){
        int sum = 0;
        for (int i = start; i <= end; i++) {
            sum += arr[i];
        }
        return sum;
    }
}

/**
 * Given an unsorted array of integers, find the number of subarrays having a sum exactly equal to a given number k.
 * nput : arr[] = {10, 2, -2, -20, 10}, k = -10
 * Output : 3
 * Explanation: Subarrays: arr[0…3], arr[1…4], arr[3..4] have a sum exactly equal to -10.
 *
 * Input : arr[] = {9, 4, 20, 3, 10, 5}, k = 33
 * Output : 2
 * Explanation: Subarrays : arr[0…2], arr[2…4] have a sum exactly equal to 33.
 * */
class NumberOfSubArrayWithGivenSum{
    public static void main(String[] args) {
        System.out.println(numberOfSubArrayWithGivenSum(new int[]{1,2,3,4,5}, 5));
        System.out.println(numberOfSubArrayWithGivenSum(new int[]{1, 4, 0, 0, 3, 10, 5}, 7));
        System.out.println(numberOfSubArrayWithGivenSum(new int[]{1, 4}, 0));
        System.out.println("=====================================");

        System.out.println(numberOfSubArrayWithGivenSumOptimised(new int[]{1,2,3,4,5}, 5));
        System.out.println(numberOfSubArrayWithGivenSumOptimised(new int[]{1, 4, 0, 0, 3, 10, 5}, 7));
        System.out.println(numberOfSubArrayWithGivenSumOptimised(new int[]{1, 4}, 0));
        System.out.println("=====================================");
    }

    static int numberOfSubArrayWithGivenSum(int[] arr, int sum){
        int n = arr.length;
        int count = 0;
        for (int i = 0; i < n; i++) { // start
            for (int j = i; j < n; j++) { // end
                if(sumArray(arr, i, j) == sum)
                    count++;
            }
        }
        return count;
    }

    static int numberOfSubArrayWithGivenSumOptimised(int[] arr, int sum){
        int n= arr.length;
        int currSum = arr[0], start = 0, count = 0;
        for (int i = 1; i <= n; i++) {
            while (currSum > sum && start < i-1){
                currSum -= arr[start];
                start++;
            }
            if(currSum == sum)
                count++;
            if(i < n)
                currSum += arr[i];
        }
        return count;
    }

    static int numberOfSubArrayWithGivenSumOptimised2(int[] arr, int sum){
        int n= arr.length;
        int currSum = 0, count = 0;
        Map<Integer, Integer> prefixSum =  new HashMap<>();
        for (int i = 0; i < n; i++) {
            currSum += arr[i];
            int prevReqSum =  currSum - sum;
            if(prefixSum.containsKey(prevReqSum)) // there is an sub array previously with sum as x-k
                count += prefixSum.get(prevReqSum);
            prefixSum.put(currSum, prefixSum.getOrDefault(currSum, 0)+1);
        }
        return count;
    }
}

/**
 * Given an array of integers nums and an integer sum, return largest subarray sum equal to sum.
 * Input: nums = [1, -1, 5, -2, 3], sum = 2
 * Output: 3
 * Explanation: Subarray [5, -2, 3] sum is 2 and equal to 2.
 * input: nums = [1, -1, 5, -2, 3], sum = 4
 * Output: 3
 * Explanation: Subarray [1, -1, 5, -2, 3] sum is 3 and equal to 4.
 * input: nums = [1, -1, 5, -2, 3], sum = 10
 * Output: 0
 * Explanation: There is no subarray sum equal to 10.
 * input: nums = [1, -1, 5, -2, 3], sum = 0
 * Output: 0
 * Explanation: There is no subarray sum equal to 0.
 * input: nums = [1, -1, 5, -2, 3], sum = 6
 * Output: 6
 *
 * */
class LargestSubArrayWithGivenSum{
    public static void main(String[] args) {
        System.out.println(largestSubArrayWithGivenSum(new int[]{1, -1, 5, -2, 3}, 2));
        System.out.println(largestSubArrayWithGivenSum(new int[]{1, -1, 5, -2, 3}, 4));
        System.out.println(largestSubArrayWithGivenSum(new int[]{1, -1, 5, -2, 3}, 10));
        System.out.println("=====================================");

        System.out.println(largestSubArrayWithGivenSumOptimised(new int[]{1, -1, 5, -2, 3}, 2));
        System.out.println(largestSubArrayWithGivenSumOptimised(new int[]{1, -1, 5, -2, 3}, 4));
        System.out.println(largestSubArrayWithGivenSumOptimised(new int[]{1, -1, 5, -2, 3}, 10));
        System.out.println("=====================================");


        System.out.println(longestSubArrayWithGivenSumOptimised2(new int[]{1, -1, 5, -2, 3}, 2));
        System.out.println(longestSubArrayWithGivenSumOptimised2(new int[]{1, -1, 5, -2, 3}, 4));
        System.out.println(longestSubArrayWithGivenSumOptimised2(new int[]{1, -1, 5, -2, 3}, 10));
        System.out.println("=====================================");

    }

    static int largestSubArrayWithGivenSum(int[] arr, int sum){
        int n = arr.length;
        int maxLen = 0;
        for (int i = 0; i < n; i++) { // start
            for (int j = i; j < n; j++) { // end
                if(sumArray(arr, i, j) == sum)
                    maxLen = Math.max(maxLen, j-i+1);
            }
        }
        return maxLen;
    }

    static int largestSubArrayWithGivenSumOptimised(int[] arr, int sum){ // handle positive numbers only
        int n= arr.length;
        int currSum = arr[0], start = 0, maxLen = 0;
        for (int i = 1; i <= n; i++) {
            while (currSum > sum && start < i-1){
                currSum -= arr[start];
                start++;
            }
            if(currSum == sum)
                maxLen = Math.max(maxLen, i-start);
            if(i < n)
                currSum += arr[i];
        }
        return maxLen;
    }

    static int longestSubArrayWithGivenSumOptimised2(int[] arr, int sum){
        int n= arr.length;
        int currSum = 0, maxLen = 0;
        Map<Integer, Integer> prefixSum =  new HashMap<>();
        for (int i = 0; i < n; i++) {
            currSum += arr[i];
            int prevReqSum =  currSum - sum;
            if(prefixSum.containsKey(prevReqSum)) // there is an sub array previously with sum as x-k
                maxLen = Math.max(maxLen, i-prefixSum.get(prevReqSum));
            if (!prefixSum.containsKey(currSum))
                prefixSum.put(currSum, i);
        }
        return maxLen;
    }

}


/**
 Given an array arr[] of length N, find the length of the longest sub-array with a sum equal to 0.
 Input: arr[] = {15, -2, 2, -8, 1, 7, 10, 23}
 Output: 5
 Explanation: The longest sub-array with elements summing up-to 0 is {-2, 2, -8, 1, 7}

 Input: arr[] = {1, 2, 3}
 Output: 0
 Explanation: There is no subarray with 0 sum

 Input:  arr[] = {1, 0, 3}
 Output:  1
 Explanation: The longest sub-array with elements summing up-to 0 is {0}
 * */
class largestSubArrayWithSum0 {

    public static void main(String[] args) {
        System.out.println(largestSubarrayWithSum0(new int[]{15, -2, 2, -8, 1, 7, 10, 23}));
        System.out.println(largestSubarrayWithSum0(new int[]{1, 0, 3}));
        System.out.println(largestSubarrayWithSum0(new int[]{1, 2, 3}));
    }

    static int largestSubarrayWithSum0(int arr[]) {
        int currSum = 0, maxLen = 0;
        Map<Integer, Integer> prefixSum = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            currSum += arr[i];
            if (prefixSum.containsKey(currSum))
                maxLen = Math.max(maxLen, i - prefixSum.get(currSum));
            else {
                prefixSum.put(currSum, i);
            }
        }
        return maxLen;
    }

}


/**
 Given an array arr[] of integers and an integer sum, find a subarray that adds to a given sum.
 Input: arr[] = {10, 2, -2, -20, 10}, sum = -10
 Output: Sum found between indexes 0 to 3
 Explanation: Sum of elements between indices
 0 and 3 is 10 + 2 – 2 – 20 = -10
 */
class SubArrayWithGivenSumHandleNegative {   // prefix sum hashing
    public static void main(String[] args) {
        System.out.println(Arrays.toString(subArrayWithGivenSumHandleNegative(new int[]{10, 2, -2, -20, 10}, -10)));
        System.out.println(Arrays.toString(subArrayWithGivenSumHandleNegative(new int[]{10, 2, -4, -5, 10}, -7)));
    }

    public static int[] subArrayWithGivenSumHandleNegative(int[] arr, int k){
        int x = 0;
        Map<Integer, Integer> prefixSum =  new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            x += arr[i];   // represents current sum from beginning
            if(x -k == 0) // currentSum - 0(K) = 0
                return new int[]{0,i};
            if (prefixSum.containsKey(x - k))  // there exist a sub array with sum = k because second sub array sum =  x-k and total sum till i =x
                return new int[]{prefixSum.get(x-k)+1, i};
            prefixSum.put(x, i);
        }
        return new int[]{-1,-1};
    }
}


/**
 Given an array of both positive and negative numbers, the task is to find out the subarray whose sum is closest to 0.
 There can be multiple such subarrays, we need to output just 1 of them.
 Input : arr[] = {-1, 3, 2, -5, 4}
 Output : 1, 3
 Subarray from index 1 to 3 has sum closest to 0 i.e.
 3 + 2 + -5 = 0

 Input : {2, -5, 4, -6, 3}
 Output : 0, 2
 2 + -5 + 4 = 1 closest to 0

 arr[] = {-1, 3, 2, -5, 4}
 prefixSum
 -1 3
 2 1
 4 2
 3 4
 {1,3}

 {2, -5, 4, -6, 3}
 prefixSum
 2 0
 -3 1
 1 2
 -5 3
 -2 4

 -5 3
 -3 1
 -2 4
  1 2
  2 0

 [1,4]
 [0,2]
 * */
class LargestSubarrayWithSumClosestToZero{
    public static void main(String[] args) {
        System.out.println(Arrays.toString(sumClosestToZeroArray(new int[]{-1, 3, 2, -5, 4})));
        System.out.println(Arrays.toString(sumClosestToZeroArray(new int[]{2, -5, 4, -6, 3})));
    }

    public static int[] sumClosestToZeroArray(int[] arr){
        Map<Integer, Integer> prefixSum =  new HashMap<>();
        int currSum = 0;
        for (int i = 0; i < arr.length; i++) {
            currSum += arr[i];
            if (prefixSum.containsKey(currSum)) // there is an array with sum = 0
                return new int[]{prefixSum.get(currSum)+1, i};
            prefixSum.put(currSum, i);
        }
        // if some zero is not present
        int res = Integer.MAX_VALUE;
        Map<Integer, Integer> sortedMap  = new TreeMap<>(prefixSum);
        List<Integer> sums =  new ArrayList<>(sortedMap.keySet());
        int[] resArr =  new int[2];
        for (int i = 1; i < sums.size(); i++) {
            if(res < sums.get(i)- sums.get(i-1)){
                res  = sums.get(i)- sums.get(i-1);
                resArr = new int[]{sortedMap.get(sums.get(i)), sortedMap.get(sums.get(i-1))};
            }
            res = Math.min(res, sums.get(i)-sums.get(i-1));
        }
        return resArr;
    }
}
