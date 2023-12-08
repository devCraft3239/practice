package code.greedy;

import java.util.*;

/**
 You are given n activities with their start and finish times.
 Select the maximum number of activities that can be performed by a single person,
 assuming that a person can only work on a single activity at a time.
 * */
public class ActivitySelectionProblem {

    public static void main(String[] args) {
        System.out.println(maxActivityPerformed(new int[]{ 1, 3, 0, 5, 8, 5 }, new int[]{ 2, 4, 6, 7, 9, 9 }));
    }
    public static class Activity{
        int start;
        int end;

        public Activity(int start, int end){
            this.start = start;
            this.end = end;
        }

        @Override
        public String toString() {
            return "Activity{" +
                    "start=" + start +
                    ", end=" + end +
                    '}';
        }
    }

    public static List<Activity> maxActivityPerformed(int[] start, int[] finis){
        List<Activity> activities = new ArrayList<>();
        for (int i = 0; i < start.length; i++) {
            activities.add(new Activity(start[i], finis[i]));
        }
        return maxActivityPerformed(activities);
    }
    public static List<Activity> maxActivityPerformed(List<Activity> activities){
        // sort by end time
        List<Activity> res = new ArrayList<>();
        Collections.sort(activities, Comparator.comparingInt(o -> o.end));
        Activity statActivity = activities.get(0);
        res.add(statActivity);
        int endTime = statActivity.end;
        for (int i = 1; i < activities.size(); i++) {
            if (activities.get(i).start > endTime){
                res.add(activities.get(i));
                endTime = activities.get(i).end;
            }
        }
        return res;
    }
}
