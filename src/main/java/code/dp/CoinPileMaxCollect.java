package code.dp;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 Given a list piles, where piles[i] is a list of integers denoting the composition of the ith pile from top to bottom,
 and a positive integer k, return the maximum total value of coins you can have in your wallet if you choose exactly k coins optimally.
 Input: piles = [[1,100,3],[7,8,9]], k = 2
 Output: 101
 Explanation:
 The above diagram shows the different ways we can choose k coins.
 The maximum total we can obtain is 101.

 Input: piles = [[100],[100],[100],[100],[100],[100],[1,1,1,1,1,1,700]], k = 7
 Output: 706
 Explanation:
 The maximum total can be obtained if we choose all coins from the last pile

 * */
public class CoinPileMaxCollect {

    static int maxCollection(List<List<Integer>> piles,int k){
        return maxCollectionCompute(piles,0, k);
    }

    static int maxCollectionCompute(List<List<Integer>> piles, int pileIndex, int k){
        if (piles.size() == 0 || k == 0)
            return 0;
        if (piles.size() == 1)
            return piles.get(0).stream().limit(k).mapToInt(Integer::intValue).sum();
        if (k == 1)
            return piles.stream().mapToInt(pile -> pile.get(0)) // map each pile to first coin
                    .max().getAsInt(); // get max from all first coin
        int maxCollect = maxCollectionCompute(piles.subList(1, piles.size()), k, pileIndex+1); // don't pick any from the current pile
        int pickAmount = 0;
        for (int i = 0; i < Math.min(k, piles.get(pileIndex).size()); i++) { // pick i+1 coins from current pile
            pickAmount += piles.get(pileIndex).get(i);
            maxCollect = Math.max(maxCollect, pickAmount+maxCollectionCompute(piles.subList(1, piles.size()),  pileIndex+1, k-(i+1))); // pick i+1 coins from current pile
        }
        return maxCollect;
    }

    static int maxCollect(List<List<Integer>> piles,int k){
        int n =  piles.size();
        int[][] dp =  new int[n][k+1];
        for (int[] row: dp) {
            Arrays.fill(row, -1);
        }
        return computeMax(0, piles, k, dp);
    }

    static int computeMax(int pileIndex, List<List<Integer>> piles, int k, int[][] dp){
        if(pileIndex == piles.size() || k==0)
            return 0;

        if(dp[pileIndex][k] != -1)
            return dp[pileIndex][k];

        int bestCollect = computeMax(pileIndex+1, piles, k, dp); //don't pick any from the current pile
        int pickAmount = 0;
        for (int i = 0; i < Math.min(k, piles.get(pileIndex).size()); i++) { // pick i+1 coins from current pile
            pickAmount += piles.get(pileIndex).get(i);
            bestCollect = Math.max(bestCollect, pickAmount+computeMax(pileIndex+1, piles, k-(i+1), dp));
        }
        return dp[pileIndex][k] = bestCollect;
    }
}
