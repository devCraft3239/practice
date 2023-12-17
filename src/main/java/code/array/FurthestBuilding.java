package code.array;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

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
