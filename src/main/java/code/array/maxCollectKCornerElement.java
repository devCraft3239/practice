package code.array;

/**
 Given an array arr[] and an integer K, the task is to find to maximize the sum of K elements in the Array by taking only corner elements.
 Input: arr[] = {8, 4, 4, 8, 12, 3, 2, 9}, K = 3
 Output: 21
 Explanation:
 The optimal strategy is to pick the elements form the array is, two indexes from the beginning and one index from the end.
 All other possible choice will yield lesser sum. Hence, arr[0] + arr[1] + arr[7] = 21.
 * */
public class maxCollectKCornerElement {
    public static void main(String[] args) {
        System.out.println(collectMax(new int[]{2, 1, 14, 6, 4, 3}, 3));
        System.out.println(collectMax(new int[]{8,4,4,8,12,3,2,9}, 3));

        System.out.println(maxCollect(new int[]{2, 1, 14, 6, 4, 3}, 3));
        System.out.println(maxCollect(new int[]{8,4,4,8,12,3,2,9}, 3));
    }
    static int collectMax(int[] arr, int k){
        int currSum = 0, maxSum = 0;
        // pick first k element to window
        for (int i = 0; i < k; i++) {
            currSum +=  arr[i];
        }
        maxSum =  currSum;
        // now remove right most element from window and pick from right corner one by one
        int n = arr.length-1; // right most
        for (int i = k-1; i >=0 ; i--) {
            int j =  k-i-1;
            currSum = currSum
                    -arr[i] // exclude left corner element (right most) from window
                    +arr[n-j]; // include right element
            maxSum =  Math.max(currSum, maxSum);
        }
        return maxSum;
    }
    static int maxCollect(int[] arr, int k){
        int n = arr.length;
        int sum = 0;
        for (int i = 0; i < k; i++) {
            sum += arr[i];
        }
        int maxSum = sum;
        for (int i = 0; i < k; i++) {
            sum = sum +arr[n-1-i]-arr[k-1];
            maxSum = Math.max(sum, maxSum);
        }
        return maxSum;
    }
}

/**
 There are two players P1 and P2 and two piles of coins consisting of M and N coins respectively.
 At each turn, a player can choose only one of the piles out of these and discard the other one.
 This discarded pile cannot be used further in the game.
 The pile player chooses is further divided into two piles of non-zero parts.
 The player who cannot divide the pile i.e. the number of coins in the pile is < 2, loses the game.
 The task is to determine which player wins if P1 starts the game and both the players play optimally.
 * */
class PredictWinnerPileGame{
    // Driver code
    public static void main(String[] args)
    {
        int M = 1, N = 2;
        findWinner(M, N);
        System.out.println(winner(M,N));
    }
    // Function to print the winner of the game
    static void findWinner(int M, int N)
    {
        if (M % 2 == 0 || N % 2 == 0)
            System.out.println("Player 1");
        else
            System.out.println("Player 2");
    }

    static String winner(int M, int N){
        if(M%2 == 0 || N%2 == 0)
            return "P1";
        else
            return "P2";
    }
}

/**
 You are given an array A of size N. The array contains integers and is of even length.
 The elements of the array represent N coin of values V1, V2, ....Vn.
 You play against an opponent in an alternating way.
 In each turn, a player selects either the first or last coin from the row, removes it from the row permanently, and receives the value of the coin.
 You need to determine the maximum possible amount of money you can win if you go first.
 Note: Both the players are playing optimally.

 N = 4
 A[] = {5,3,7,10}
        i      j
 Output: 15
 Explanation: The user collects maximum
 value as 15(10 + 5)
 N = 4
 A[] = {8,15,3,7}
        i      j
 Output: 22
 Explanation: The user collects maximum
 value as 22(7 + 15)
 * */

class MaxSumCollectByPickingCornerCoin{
    public static void main(String[] args) {
        System.out.println(maxSumCollectByPickingCornerCoin(new int[]{8,15,3,7}));
        System.out.println(maxSumCollectByPickingCornerCoin(new int[]{5,3,7,10}));

        System.out.println(maxCollectCornerCoins(new int[]{8,15,3,7}));
        System.out.println(maxCollectCornerCoins(new int[]{5,3,7,10}));
    }

    public static int maxSumCollectByPickingCornerCoin(int[] arr){
        int n = arr.length;
        int[][]  table =  new int[n][n];
        int gap, i, j, x, y, z;

        for (int k = 0; k < n; k++) { // let's say number of coin available [coin to consider]
            for (i = 0, j= k; j < n; i++, j++) {
                // if you pick ith index
                // opp can pick only i+1, j index
                x = (i+2) <= j ? table[i + 2][j]: 0; // if opp pick i+1 index
                y = (i+1) <= j-1 ? table[i + 1][j - 1]: 0; // if opp pick jth index

                // if you pick jth index
                // opp can pick only i, j-1 index
                y = (i+1) <= j-1 ? table[i + 1][j - 1]: 0; // if opp pick ith index
                z = i <= (j-2) ? table[i][j - 2]: 0; // if opp pick j-1 index

                // if both the player play optimally, you'll leave the opponent with minimum value
                table[i][j] = Math.max(
                        arr[i] + Math.min(x, y), // you pick ith index
                        arr[j] + Math.min(y, z) // you pick jth index
                );
            }
        }
        return table[0][n-1];
    }

    static int maxCollectCornerCoins(int[] arr){
        return maxCollectCornerCoinsUtil(arr, 0, arr.length-1);
    }

    static int maxCollectCornerCoinsUtil(int[] arr, int i, int j){
        if(i>j)  // no coin left
            return 0;
        if(i==j) // only one coin left
            return arr[i];
        if(j==i+1) // only two coin left
            return Math.max(arr[i], arr[j]);
        return Math.max(
                arr[i] // p1 chosen i
                +Math.min(
                        maxCollectCornerCoinsUtil(arr, i+2, j), // if opponent choose i+1
                        maxCollectCornerCoinsUtil(arr, i+1, j-1) // if opponent choosen j  // repeating
                ),
                arr[j] // p1 chosen j
                +Math.min(
                        maxCollectCornerCoinsUtil(arr, i+1, j-1) // if opp choosen i   // repeating
                        ,maxCollectCornerCoinsUtil(arr, i, j-2) // if opp chosen j-1
                )
        );
    }

    static int[][] dp;
    static int maxCollectCornerCoinsDp(int[] arr){
        int n = arr.length;
        dp = new int[n+1][n+1];
        return maxCollectCornerCoinsDpUtil(arr, 0, n-1);
    }

    static int maxCollectCornerCoinsDpUtil(int[] arr, int i, int j){
        if(i>j) // no coin left
            return 0;
        if(i==j) //
            return arr[i];
        if(dp[i][j]  != -1) // already computed
            return dp[i][j];
        dp[i][j] =  Math.max(
                arr[i] // p1 choosen i
                        +Math.min(
                        maxCollectCornerCoinsUtil(arr, i+2, j), // if opponent choose i+1
                        maxCollectCornerCoinsUtil(arr, i+1, j-1) // if opponent choosen j  // repeating
                ),
                arr[j]
                        +Math.min(
                        maxCollectCornerCoinsUtil(arr, i+1, j-1) // if opp choosen i   // repeating
                        ,maxCollectCornerCoinsUtil(arr, i, j-2) // if opp chosen j-1
                )
        );
        return dp[i][j];
    }
}

/**
 *
 */

class OptimalStrategyToWinCoinGame {
    public static void main(String[] args) {
        System.out.println(optimalStrategyToWinGame(new int[]{5,3,7,10}, 0,4));
        System.out.println(optimalStrategyToWinGame(new int[]{8,15,3,7}, 0, 4));

    }
    static String optimalStrategyToWinGame(int[] arr, int st, int end){
        int n = arr.length;
        int evenSum =0, oddSum =0;
        for (int i = 0; i < n; i++) {
            if (i%2==0)
                evenSum += arr[i];
            else
                oddSum += arr[i];
        }
        return evenSum > oddSum ? "even_idx" : "odd_idx";
    }
}
