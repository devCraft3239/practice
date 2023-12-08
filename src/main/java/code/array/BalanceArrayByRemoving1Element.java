package code.array;

/**
 Given an array arr[] of size N,
 the task is to find index of array elements whose removal makes
 the sum of odd and even indexed elements equal.
 If no such element exists, then print -1.
 * */
public class BalanceArrayByRemoving1Element {
    public static void main(String[] args) {
        System.out.println(balance(new int[]{4,3,5,2,1}));
        System.out.println(balance(new int[]{4,2,5,3,1}));
        System.out.println(balance(new int[]{2,4,6,3,4}));
//        System.out.println(balance(new int[]{2,4,6,3,4,5}));// didn't work with even length

        System.out.println(balanceIndex(new int[]{4,3,5,2,1}));
        System.out.println(balanceIndex(new int[]{4,2,5,3,1}));
        System.out.println(balanceIndex(new int[]{2,4,6,3,4}));
//        System.out.println(balanceIndex(new int[]{2,4,6,3,4,5}));// didn't work with even length
    }
    public static int balance(int[] arr){
        int n = arr.length;
        int[] lOdd = new int[n], lEven = new int[n];
        int[] rOdd = new int[n], rEven = new int[n];

        int evenSum = 0, oddSum=0;
        for (int i = 0; i < n; i++) {
            if (i%2==0){
                evenSum += arr[i];
            }else {
                oddSum += arr[i];
            }
            lEven[i] = evenSum;
            lOdd[i] = oddSum;
        }

        evenSum = 0;
        oddSum=0;
        for (int i = n-1; i >= 0; i--) {
            if (i%2==0){
                evenSum += arr[i];
            }else {
                oddSum += arr[i];
            }
            rEven[i] = evenSum;
            rOdd[i] = oddSum;
        }

        for (int i = 0; i < n; i++) {
            if (lEven[i]+rOdd[i] == lOdd[i]+rEven[i])
                return i;
        }
        return -1;
    }

    static int balanceIndex(int[] arr){
        int n = arr.length;
        if(n%2 == 0 || n == 0)
            return -1;
        int[] lEven = new int[n], rEven = new int[n], lOdd = new int[n], rOdd = new int[n];
        lEven[0] = arr[0]; rEven[n-1] = arr[n-1];
        for (int i = 1; i < n; i++) {
            lEven[i] = lEven[i-1];
            lOdd[i] = lOdd[i-1];
            if(i%2 == 0)
                lEven[i] += arr[i];
            else
                lOdd[i] += arr[i];
        }

        for (int i = n-2; i >=0; i--) {
            rEven[i] = rEven[i+1];
            rOdd[i] = rOdd[i+1];
            if(i%2 == 0)
                rEven[i] += arr[i];
            else
                rOdd[i] += arr[i];
        }

        for (int i = 0; i < n; i++) {
            if (lEven[i]+rOdd[i] ==  lOdd[i]+rEven[i])
                return i;
        }
        return -1;
    }
}
