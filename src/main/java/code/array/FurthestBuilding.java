package code.array;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 You are given an integer array heights representing the heights of buildings, some bricks, and some ladders.

 You start your journey from building 0 and move to the next building by possibly using bricks or ladders.

 While moving from building i to building i+1 (0-indexed),

 If the current building's height is greater than or equal to the next building's height, you do not need a ladder or bricks.
 If the current building's height is less than the next building's height, you can either use one ladder or (h[i+1] - h[i]) bricks.
 Return the furthest building index (0-indexed) you can reach if you use the given ladders and bricks optimally.

 Input: heights = [4,2,7,6,9,14,12], bricks = 5, ladders = 1
 Output: 4
 Explanation: Starting at building 0, you can follow these steps:
 - Go to building 1 without using ladders nor bricks since 4 >= 2.
 - Go to building 2 using 5 bricks. You must use either bricks or ladders because 2 < 7.
 - Go to building 3 without using ladders nor bricks since 7 >= 6.
 - Go to building 4 using your only ladder. You must use either bricks or ladders because 6 < 9.
 It is impossible to go beyond building 4 because you do not have any more bricks or ladders.
 Example 2:

 Input: heights = [4,12,2,7,3,18,20,3,19], bricks = 10, ladders = 2
 Output: 7
 Example 3:

 Input: heights = [14,3,19,3], bricks = 17, ladders = 0
 Output: 3
 */
public class FurthestBuilding {
    public static void main(String[] args) {
        int[] arr = {4,2,7,6,9,11,14,12,8};
        int bricks = 5;
        int ropes = 2;
        // Output: 8
        System.out.println(furthestBuilding(arr, bricks, ropes));
        arr = new int[]{4,2,7,6,9,11,14,12,8};
        ropes = 1;
        System.out.println(furthestBuilding(arr, bricks, ropes));
        // Output: 5
    }
    static int furthestBuilding(int[] arr, int bricks, int ropes){
        int n = arr.length;
        Queue<Integer> pq = new PriorityQueue<>((a, b) -> a - b);
        for (int i = 1; i < n-1; i++) {
            int diff =  arr[i] - arr[i-1];
            if (diff > 0){
                pq.add(diff);
            }
            if (pq.size() > ropes){
                bricks -= pq.poll();
            }
            if (bricks < 0){
                return i-1;
            }
        }
        return n-1;
    }
}
