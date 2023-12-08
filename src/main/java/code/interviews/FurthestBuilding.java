package code.interviews;

import java.util.Collections;
import java.util.PriorityQueue;

public class FurthestBuilding {
    public static void main(String[] args) {
        System.out.println(furthestBuilding(new int[]{4,2,7,6,9,14,12}, 5,1));
        System.out.println(furthestBuilding(new int[]{4,12,2,7,3,18,20,3,19}, 10, 2));
        System.out.println(furthestBuilding(new int[]{14,3,19,3}, 17, 0));
    }
    static int furthestBuilding(int[] h ,int bricks, int ladders){
        PriorityQueue<Integer> bricksUsed = new PriorityQueue<>(Collections.reverseOrder());
        for (int i = 0; i < h.length-1; i++) {
            if(h[i] >=  h[i+1]) continue;
            int diff = h[i+1] - h[i];
            bricks -= diff;
            bricksUsed.add(diff);
            if(bricks < 0){ // replace max-height diff with ladder
                bricks += bricksUsed.poll();
                if (ladders >0)
                    ladders--;
                else
                    return i;
            }
        }
        return h.length-1;
    }
}
