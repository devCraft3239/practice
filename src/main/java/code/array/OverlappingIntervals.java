package code.array;

import java.util.Arrays;

/**
 Given an array of intervals where intervals[i] = [starti, endi], merge all overlapping intervals, and return an array of the non-overlapping intervals that cover all the intervals in the input.

 Example 1:

 Input: intervals = [[1,3],[2,6],[8,10],[15,18]]
 Output: [[1,6],[8,10],[15,18]]
 Explanation: Since intervals [1,3] and [2,6] overlap, merge them into [1,6].
 Example 2:

 Input: intervals = [[1,4],[4,5]]
 Output: [[1,5]]
 Explanation: Intervals [1,4] and [4,5] are considered overlapping.

 input: Intervals =  [[1,5] [6,7] [9,10], [10, 12], [11, 13], [15, 20]]
 output: [[1, 7], [9, 13], [15, 20]]
 */
public class OverlappingIntervals {
    public static void main(String[] args) {
        int[][] intervals = {{1,5}, {6,7}, {9,10}, {10, 12}, {11, 13}, {15, 20}};
        int[][] mergedIntervals = merge(intervals);
        for (int[] mergedInterval : mergedIntervals) {
            System.out.println(mergedInterval[0] + " " + mergedInterval[1]);
        }
    }

    static class Interval{
        int start;
        int end;
        Interval(int start, int end){
            this.start = start;
            this.end = end;
        }
    }

    static int[][] merge(int[][] intervals){
        int[][] mergedIntervals = new int[intervals.length][2];
        int index = 0;
        mergedIntervals[index++] = intervals[0];
        for (int i = 1; i < intervals.length; i++) {
            int[] interval = intervals[i];
            int[] prevInterval = mergedIntervals[index - 1];
            if (prevInterval[1] >= interval[0]){
                prevInterval[1] = Math.max(prevInterval[1], interval[1]);
            }else{
                mergedIntervals[index++] = interval;
            }
        }
        int[][] result = new int[index][2];
        System.arraycopy(mergedIntervals, 0, result, 0, index);
        return result;
    }

    static int[][] merge2(int[][] intervals){
          Interval[] intervalObjects = new Interval[intervals.length];
          for (int i = 0; i < intervals.length; i++) {
                intervalObjects[i] = new Interval(intervals[i][0], intervals[i][1]);
          }
          Arrays.sort(intervalObjects, (a, b) -> a.start - b.start);
          int index = 0;
        Interval[] mergedIntervals = new Interval[intervals.length];
        mergedIntervals[index++] = intervalObjects[0];
        for (int i = 1; i < intervalObjects.length; i++) {
            Interval interval = intervalObjects[i];
            Interval prevInterval = mergedIntervals[index - 1];
            if (prevInterval.end >= interval.start){
                prevInterval.end = Math.max(prevInterval.end, interval.end);
            }else{
                mergedIntervals[index++] = interval;
            }
        }
        return Arrays.stream(mergedIntervals).map(interval -> new int[]{interval.start, interval.end}).toArray(int[][]::new);
    }
}
