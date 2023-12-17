package code.array;

/**
 * There are N petrol pumps along a circular route, where the amount of petrol at pump i is petrol[i].
 * <p>  You have a car with an unlimited gas tank and it costs cost[i] of gas to travel from pump i to its next pump (i+1).
 * You begin the journey with an empty tank at one of the petrol pumps.
 * <p>  Return the starting petrol pump's index if you can travel around the circuit once in the clockwise direction, otherwise return -1.
 * <p>  Note: If there exists a solution, it is guaranteed to be unique.
 * <p>  Example 1:
 *  Input: petrol = [1,2,3,4,5], cost = [3,4,5,1,2]
 *  Output: 3
 *  Explanation:
 *  If you start at station 3 (index 3) and fill up with 4 unit of gas.
 *  Your tank = 0 + 4 = 4
 *  Travel to station 4. Your tank = 4 - 1 + 5 = 8
 */
public class CircularRoute {
    public static void main(String[] args) {
        int[] petrol = {1, 2, 3, 4, 5};
        int[] cost = {3, 4, 5, 1, 2};
        System.out.println(circularRoute(petrol, cost));
    }

    static int circularRoute(int[] petrol, int[] cost) {
        int start = 0;
        int end = 1;
        int currentPetrol = petrol[start] - cost[start];
        while (start != end || currentPetrol < 0) {
            while (start != end && currentPetrol < 0) {
                currentPetrol -= petrol[start] - cost[start];
                start = (start + 1) % petrol.length;
                if (start == 0) {
                    return -1;
                }
            }
            currentPetrol += petrol[end] - cost[end];
            end = (end + 1) % petrol.length;
        }
        return start;
    }
}