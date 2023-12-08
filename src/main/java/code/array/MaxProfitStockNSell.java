package code.array;

import com.sun.tools.javac.util.Pair;

import java.util.*;
import java.util.stream.Collectors;

/**
 You are given an array prices where prices[i] is the price of a given stock on the ith day.
 You want to maximize your profit by choosing a single day to buy one stock and choosing a different day in the future to sell that stock.
 Return the maximum profit you can achieve from this transaction. If you cannot achieve any profit, return 0.

 prices[] = {100, 180, 260, 310, 40, 535, 695}
 (i) maxProfit if allowed make single txn
 (ii) maxProfit if allowed to make any number of txn
 (iii) maxProfit if allowed to make at most 2 number of txn
 (iv) maxProfit if allowed to make at most K number of txn
 */
public class MaxProfitStockNSell {
    public static void main(String[] args) {
//        System.out.println(maxProfitSingleBuySell(new int[]{100, 180, 260, 310, 40, 30, 535, 695}));
//        System.out.println(maxProfitSingleBuySell(new int[]{100, 360, 260, 180, 40, 30, 695, 535}));
//
//        System.out.println(maxProfitSingleTransaction(new int[]{100, 180, 260, 310, 40, 30, 535, 695}));
//        System.out.println(maxProfitSingleTransaction(new int[]{100, 360, 260, 180, 40, 30, 695, 535}));

//        System.out.println(maxProfit(new int[]{100, 180, 260, 310, 40, 30, 535, 695}));
//        System.out.println(maxProfit(new int[]{100, 360, 260, 180, 40, 30, 695, 535}));
//
        System.out.println(maxProfitAnyTransaction(new int[]{100, 180, 260, 310, 40, 30, 535, 695}));
        System.out.println(maxProfitAnyTransaction(new int[]{100, 360, 260, 180, 40, 30, 695, 535}));


//        System.out.println(maxProfitTwiceBuySell(new int[]{100, 180, 260, 310, 40, 30, 535, 695}));
//        System.out.println(maxProfitTwiceBuySell(new int[]{100, 360, 260, 180, 40, 30, 695, 535}));
//
//        System.out.println(maxProfitExactlyTwoTransaction(new int[]{100, 180, 260, 310, 40, 30, 535, 695}));
//        System.out.println(maxProfitExactlyTwoTransaction(new int[]{100, 360, 260, 180, 40, 30, 695, 535}));
    }

    public static int maxProfit(int[] arr){
        int localMin = 0, localMax = 0, maxSum =0;
        for (int i = 1; i < arr.length; i++) {
            if(arr[localMax] > arr[i]){
                maxSum += arr[localMax]-arr[localMin] ;
                localMin =  i;
            }
            localMax =  i;
        }
        if (localMin !=arr.length-1){  // if we can make profit by selling at last day
            maxSum += arr[localMax]-arr[localMin] ;
        }
        return maxSum;
    }

    public static List<Integer> maxProfitSingleBuySellIndex(int[] arr){
        int start = 0, maxProfit = Integer.MIN_VALUE;
        List<Integer> res= null;
        for (int i = 1; i < arr.length; i++) {
            if(arr[i] < arr[i-1] || i==(arr.length-1)){
                if(arr.length-1 == i && maxProfit < arr[i]- arr[start] ){
                    res =  Arrays.asList(start, i);
                }
                else if(maxProfit < arr[i-1]- arr[start]){
                    res =  Arrays.asList(start, i-1);
                    start =i;
                }
            }
        }
//        {100, 180, 260, 310, 40, 535, 695}
        System.out.println(arr[res.get(1)]- arr[res.get(0)]);
        return res;
    }

    public static int maxProfitSingleBuySell(int[] arr){// arr[j] > arr[i] s.t j>i
        int n = arr.length, maxProfit = Integer.MIN_VALUE;
//        for (int i = 0; i < n; i++) {
//            for (int j = i+1; j <n ; j++) {
//                if(arr[j] > arr[i])
//                    maxProfit =  Math.max(maxProfit, arr[j]-arr[i]);
//            }
//        }

        int minSoFar = arr[0];
        for (int i = 1; i < n; i++) {
            maxProfit = Math.max(maxProfit, (arr[i]- minSoFar));
            if(arr[i] < minSoFar)
                minSoFar =  arr[i];
        }
        return maxProfit;
    }

    public static int maxProfitTwiceBuySell(int[] arr){
        /**
         *    arr=  [100 180 260 310 40 535 695]
         *   lprof= [0  80  160  210 210 495 655]
         *   rProf= [655 655 655 655 655 160  0]
         *   prof = [655,735,815,865,865,655,655]
         */
        int n =  arr.length, buy=arr[0], sell = arr[n-1], maxProf=0;;
        int[] lpr = new int[n], rpr = new int[n], prof = new int[n];
        lpr[0] = 0; rpr[n-1] = 0;
        for (int i = 1; i < n; i++) {
            lpr[i] = Math.max(lpr[i-1], arr[i]-buy);
            buy =  Math.min(buy, arr[i]);
        }

        for (int i = n-2; i >=0; i--) {
            rpr[i] = Math.max(rpr[i+1], sell-arr[i]);
            sell =  Math.max(sell, arr[i]);
        }
        for (int i = 0; i < n; i++) {
            maxProf = Math.max(maxProf, lpr[i]+rpr[i]);
        }
        return maxProf;
    }

    public static int maxProfitWithKTransaction(int[] arr, int k){
        int n= arr.length;;
        int[][] profit =  new int[k+1][n];
        // day=0, can't earn any money
        for (int  i= 0; i < k; i++) {
            profit[i][0] = 0;
        }

        // k=0 i.e. can't make txns means no profit
        for (int i = 0; i < n; i++) {
            profit[0][i] =0;
        }

        for (int i = 1; i < k; i++) { // number of txns allowed
            int maxProfitSoFar = Integer.MIN_VALUE;
            for (int j = 1; j < n; j++) {
                maxProfitSoFar = Math.max(maxProfitSoFar,  // not buying on j-1 index
                        profit[i - 1][j - 1] - arr[j - 1]); // j-1 par buy krke so need to (-)
                profit[i][j] = Math.max(
                        profit[i][j - 1], // not selling at jth day.
                        arr[j] + maxProfitSoFar /// selling at jth index
                );
            }
            // preMaxProfit =  -100
            /**           (i)   0   1   2   3   4   5   6
             *             K    100 180 260 310 40 535 695   stock (j)
             *             0    0   0   0   0   0   0   0
             *             1    0   80  160 210 210 495 655
             *             2    0   80  160 210 210 705 825
             *             3    0   80  160 210 210 705 825
             */
        }
        return profit[k][n-1];
    }


    static int maxProfitSingleTransaction(int[] arr){
        int n = arr.length, maxProfit = 0, buy = arr[0];
        for (int i = 1; i < n; i++) {
            if (arr[i] < arr[i-1]){
                maxProfit =  Math.max(maxProfit, arr[i-1]-buy);
                buy = arr[i];
            }
        }
        return Math.max(maxProfit, arr[n-1]-buy);
    }

    static int maxProfitAnyTransaction(int[] arr){
        int n = arr.length, buy = arr[0], sell = 0, maxProfit = 0;
        for (int i = 1; i < n; i++) {
            if (arr[i] < arr[i-1]){
                sell = arr[i-1];
                maxProfit += sell-buy;
                buy = arr[i];
            }
        }
        return maxProfit+Math.max(0, arr[n-1]-buy);
    }

    static int maxProfitExactlyTwoTransaction(int[] arr){
        int n = arr.length, buy = arr[0], sell = 0;
        List<Integer> profits =  new ArrayList<>();
        for (int i = 1; i < n; i++) {
            if (arr[i] < arr[i-1]){
                sell = arr[i-1];
                profits.add(sell-buy);
                buy = arr[i];
            }
        }
        if (arr[n-1]-buy > 0)
            profits.add(arr[n-1]- buy);
        profits = profits.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
        return profits.get(0)+profits.get(1);
    }

}



/**
 *
 * Max difference j-i such that arr[j] > arr[i]
 * */
class MaximumIndexJMinusI {
    public static void main(String[] args) {
        int[] arr = new int[]{34, 8, 10, 3, 2, 80, 5, 30, 33, 1};
//        System.out.println(Arrays.toString(maxIndex(arr)));
        System.out.println(Arrays.toString(maxIndexOptimised(arr)));
        System.out.println();
    }
    public static int[] maxIndex(int[] arr){
        int[] pair = null;
        int i, j, n = arr.length, maxDiff =Integer.MIN_VALUE;
        for (i = 0; i < n; i++) {
            for (j = n-1; j > i && arr[j] < arr[i]; j--) {
            }
            if(maxDiff < j - i + 1){
                maxDiff  = j -i + 1;
                pair =  new int[]{i,j};
            }
        }
        return pair;
    }

    public static int[] maxIndexOptimised(int[] arr) {
        int n = arr.length;
        int[] pair = null;
        int[] lMin = new int[n];
        lMin[0] = arr[0];
        int[] rMax = new int[n];
        rMax[n - 1] = arr[n - 1];
        for (int i = 1; i < n; i++) {
            lMin[i] = Math.min(arr[i], lMin[i - 1]);
        }

        for (int j = n - 2; j >= 0; j--) {
            rMax[j] = Math.max(arr[j], rMax[j + 1]);
        }

        int i = 0, j = 0;
        int maxDiff = 0;
        while (i < n && j < n) {
            if (lMin[i] <= rMax[j]) {
                if (maxDiff < j - i) {
                    maxDiff = j - i;
                    pair = new int[]{i, j};
                }
                j++;
            } else {
                i++;
            }

        }
        return pair;
    }
}
