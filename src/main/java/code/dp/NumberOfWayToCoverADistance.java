package code.dp;

/**
 A frog jumps either 1, 2, or 3 steps to go to the top.
 In how many ways can it reach the top. As the answer will be large find the answer modulo 1000000007.
 or Given a distance ‘dist’, count total number of ways to cover the distance with 1, 2 and 3 steps.
 Input: n = 3
 Output: 4
 Explanation:
 Below are the four ways
 1 step + 1 step + 1 step
 1 step + 2 step
 2 step + 1 step
 3 step

 Input: n = 4
 Output: 7
 Explanation:
 Below are the four ways
 1 step + 1 step + 1 step + 1 step
 1 step + 2 step + 1 step
 2 step + 1 step + 1 step
 1 step + 1 step + 2 step
 2 step + 2 step
 3 step + 1 step
 1 step + 3 step
 * */
public class NumberOfWayToCoverADistance {
    public static void main(String[] args) {
        System.out.println(minWaysCnt(3));
        System.out.println(minWaysCnt(4));
        System.out.println(minWaysCnt(5));
        System.out.println(minWaysCnt(6));
        System.out.println(minWaysCnt(7));

    }
    static int minWaysCnt(int n){ // similar to fibonacci
        // ways[i] represent number of way to reach to ith index
        int[] ways = new int[n+1];
        ways[0] = 1;
        ways[1] = 1;
        ways[2] = 2;
        for (int i = 3; i <=n; i++) {
            ways[i] = ways[i-1]+ways[i-2]+ways[i-3]; // formula
        }
        return ways[n];
    }
}


/**
 Given an array of n non-negative numbers,
 the task is to find the minimum sum of elements (picked from the array)
 such that at least one element is picked out of every 3 consecutive elements in the array.
 Input : arr[] = {1, 2, 3, 6, 7, 1}
 Output : 4
 We pick 3 and 1  (3 + 1 = 4)
 Note that there are following subarrays
 of three consecutive elements
 {1, 2, 3}, {2, 3, 6}, {3, 6, 7} and {6, 7, 1}
 We have picked one element from every subarray.
 * */
class MinSumOneEvery3Consecutive {
    static int minSum(int arr[]){
        int n = arr.length;
        //sum[i] is going to store minimum possible sum when arr[i] i part of the solution.
        int sum[] =new int[n];

        // When there are less than or equal to
        // 3 elements
        sum[0] = arr[0];
        sum[1] = arr[1];
        sum[2] = arr[2];

        // Iterate through all other elements
        for (int i = 3; i < n; i++)
            sum[i] = arr[i] + minimum(sum[i - 3],
                    sum[i - 2], sum[i - 1]);

        return minimum(sum[n - 1], sum[n - 2], sum[n - 3]);
    }

    static int minimum(int a, int b, int c)
    {
        return Math. min(Math.min(a, b), c);
    }
}


/**
 Given a number N. Find the minimum number of operations required to reach N starting from 0.
 You have 2 operations available:
 Double the number
 Add one to the number
 Input:
 N = 8
 Output: 4
 Explanation: 0 + 1 = 1, 1 + 1 = 2,
 2 * 2 = 4, 4 * 2 = 8
 * */
class MinOperation0toN {
    public static void main(String[] args) {
        System.out.println(minOperation(7));
        System.out.println(minOperation(8));
        System.out.println(minOperation(9));
        System.out.println(minOperation(10));
    }
    static int minOperation(int N){
        int minOp[] =  new int[N+1];
        minOp[0] = 0;
        minOp[1] = 1;
        minOp[2] = 2;

        for (int i = 3; i <= N; i++) {
            minOp[i] =  i%2 !=0
                    ? minOp[i-1]+1
                    : minOp[i/2]+1;
        }
        return minOp[N];
    }
}
