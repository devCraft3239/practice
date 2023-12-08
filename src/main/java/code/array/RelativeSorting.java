package code.array;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 Given two arrays A1[] and A2[], sort A1 in such a way that the relative order among the elements will be same as those are in A2.
 For the elements not present in A2, append them at last in sorted order.
 Input: A1[] = {2, 1, 2, 5, 7, 1, 9, 3, 6, 8, 8}
 A2[] = {2, 1, 8, 3}
 Output: A1[] = {2, 2, 1, 1, 8, 8, 3, 5, 6, 7, 9}

 Input: A1[] = {4, 5, 1, 1, 3, 2}
 A2[] = {3, 1}
 Output: A1[] = {3, 1, 1, 2, 4, 5}

 * */
public class RelativeSorting {
    static void relativeSort(int arr1[], int arr2[]) {
        int m = arr1.length;
        int n = arr2.length;

        HashMap<Integer, Integer> freqMap = new HashMap<>();
        for (int i = 0; i < m; i++) {
            freqMap.put(arr1[i], freqMap.getOrDefault(arr1[i], 0) + 1);
        }

        AtomicInteger idx = new AtomicInteger();
        // put elem acc to relative arr
        for (int i = 0; i < n; i++) {
            if (freqMap.containsKey(arr2[i])) {
                for (int j = 0; j < freqMap.get(arr2[i]); j++) {
                    arr1[idx.get()] = arr2[i];
                    idx.getAndIncrement();
                }
                freqMap.remove(arr2[i]);
            }
        }

        // put the rem elem in asc order
        freqMap.entrySet().stream().sorted(Map.Entry.comparingByKey())
                .forEach(entry -> {
                    for (int i = 0; i < entry.getValue(); i++) {
                        arr1[idx.get()] = entry.getKey();
                        idx.getAndIncrement();
                    }
                });
    }
}
