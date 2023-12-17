package code.array;

import java.util.Arrays;

/**
 Given an array arr[] of size N,
 the task is to find index of array elements whose removal makes
 the sum of odd and even indexed elements equal.
 If no such element exists, then print -1.
 eg:
    Input: arr[] = {4, 3, 5, 2, 1}
    Output: 2
    Explanation:
    Removing arr[2] from the array modifies the array to {4, 3, 2, 1}.

    Input: arr[] = {4, 2, 5, 3, 1}
    Output: -1
    Explanation:
    No element can be removed from the array to make the sum of odd and even indexed elements equal.

    Input: arr[] = {2, 4, 6, 3, 4}
    Output: 3
    Explanation:
    Removing arr[3] from the array modifies the array to {2, 4, 6, 4}.
 * */
public class BalanceArrayByRemoving1Element {
    public static void main(String[] args) {

        System.out.println(balanceIndex(new int[]{4,3,5,2,1}));
        System.out.println(balanceIndex(new int[]{4,2,5,3,1}));
        System.out.println(balanceIndex(new int[]{2,4,6,3,4}));
    }

    static int balanceIndex(int[] arr){
        int n = arr.length;
        if (n%2 == 0)
            return -1;
        int[] leftEven = new int[n]; // leftEven[i] = sum of even indexed elements from 0 to i
        int[] leftOdd = new int[n]; // leftOdd[i] = sum of odd indexed elements from 0 to i
        int[] rightEven = new int[n]; // rightEven[i] = sum of even indexed elements from i to n-1
        int[] rightOdd = new int[n]; // rightOdd[i] = sum of odd indexed elements from i to n-1
        leftEven[0] = arr[0];
        leftOdd[0] = 0;
        rightEven[n-1] = arr[n-1];
        rightOdd[n-1] = 0;
        for (int i = 1; i < n; i++) {
            leftEven[i] = i%2 == 0 ? leftEven[i-1]+arr[i] : leftEven[i-1];
            leftOdd[i] = i%2 != 0 ? leftOdd[i-1]+arr[i] : leftOdd[i-1];
        }

        for (int j = n-2; j >=0; j--) {
            rightEven[j] = j%2 == 0 ? rightEven[j+1]+arr[j] : rightEven[j+1];
            rightOdd[j] = j%2 != 0 ? rightOdd[j+1]+arr[j] : rightOdd[j+1];
        }
        System.out.println(Arrays.toString(arr));
        System.out.println(Arrays.toString(leftEven));
        System.out.println(Arrays.toString(leftOdd));
        System.out.println(Arrays.toString(rightEven));
        System.out.println(Arrays.toString(rightOdd));

        for (int i = 0; i < n; i++) {
            if (leftOdd[i] + rightEven[i] == leftEven[i] + rightOdd[i]) {
                return i;
            }
        }
        return -1;
    }
}
