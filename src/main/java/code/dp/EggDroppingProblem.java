package code.dp;

import java.util.function.IntBinaryOperator;
import java.util.stream.IntStream;

/**
 You are given N identical eggs, and you have access to a K-floored building from 1 to K.

 There exists a floor f where 0 <= f <= K such that any egg dropped at a floor higher than f will break, and any egg dropped at or below floor f will not break.
 There are few rules given below.
 An egg that survives a fall can be used again.
 A broken egg must be discarded.
 The effect of a fall is the same for all eggs.
 If the egg doesn't break at a certain floor, it will not break at any floor below.
 If the eggs breaks at a certain floor, it will break at any floor above.
 Return the minimum number of moves that you need to determine with certainty what the value of f is.
 Input:
 N = 1, K = 2
 Output: 2
 Explanation:
 1. Drop the egg from floor 1. If it
 breaks, we know that f = 0.
 2. Otherwise, drop the egg from floor 2.
 If it breaks, we know that f = 1.
 3. If it does not break, then we know f = 2.
 4. Hence, we need at minimum 2 moves to
 determine with certainty what the value of f is.
 * */
public class EggDroppingProblem {
    public static void main(String[] args) {
        System.out.println(minTrailCnt(2,1));
        System.out.println(minTrailCnt(2,6));
        System.out.println(minTrailCnt(2,10));

        System.out.println(minTrailRecur(2,1));
        System.out.println(minTrailRecur(2,6));
        System.out.println(minTrailRecur(2,10));
    }

    static int minTrailRecur(int n, int f){
        if (n==1)
            return f;
        if (f==0 || f == 1)
            return f;

        int minCnt =  Integer.MAX_VALUE, cnt =0;
        for (int k = 2; k <= f; k++) {
            cnt = 1 + // adding 1 for current attempt
                    Math.max(
                    minTrailRecur(n-1, k-1), // if egg breaks
                    minTrailRecur(n, f-k) // if egg doesn't break check for upper f-k floor
            );
            minCnt = Math.min(minCnt, cnt);
        }
        return minCnt;
    }

    static int minTrailCnt(int n, int f){
        int[][] t =  new int[n+1][f+1];

        // floor = 1
        for (int i = 1; i <=n; i++) {
            t[i][0] = 0;
            t[i][1] = 1;
        }

        // case eggs=1
        for (int j = 1; j <=f; j++) {
            t[1][j] = j;
        }

        for (int i = 2; i <=n; i++) { // i represent egg
            for (int j = 2; j<= f; j++) { // j represent floor
                if(i > j){ // number of eggs greater than number of floor
                    t[i][j] = t[i-1][j]; // j represent same floors
                }
                else {
                    t[i][j] =  Integer.MAX_VALUE;
                    for (int k = 1; k <=j; k++) { // check from every floor k if egg breaks or not
                        t[i][j] = Math.min( t[i][j],
                                    1+ // adding one for current attempt
                                            Math.max(
                                        t[i-1][k-1], // if break
                                        t[i][j-k]) // if it doesn't break check for upper j-k floor
                                        );
                    }
//                    int finalI = i;
//                    int finalJ = j;
//                    t[i][j] = IntStream.range(1,j).map(x ->
//                            Math.max(
//                                    t[finalI-1][finalJ -1], // if break
//                                    1+t[finalI][finalJ - x])) // if it doesn't break
//                            .min().getAsInt();
                }
            }
        }
        return t[n][f];
    }
}
