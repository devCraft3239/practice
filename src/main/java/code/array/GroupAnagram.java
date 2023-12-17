package code.array;

import java.util.*;

/**
 * Given an array of strings strs, group the anagrams together. You can return the answer in any order.
 * Example 1:
 * Input: strs = ["eat","tea","tan","ate","nat","bat"]
 * output: [["bat"],["nat","tan"],["ate","eat","tea"]]
 *
 * Example 2:
 * Input: strs = [""]
 * Output: [[""]]
 */
public class GroupAnagram {
    public static void main(String[] args) {
        List<String> input = new ArrayList(){{
            add("Eat");
            add("Tea");
            add("Tan");
            add("ate");
            add("nat");
            add("bat");
        }};
        List<List<String>> result =  groupAnagram(input);
        System.out.println(result);
    }
    static List<List<String>> groupAnagram(List<String> list){
        int n =  list.size();
        boolean[][] occ = new boolean[n][26];
        for(int i=0;i<n;i++){
            String s = list.get(i).toLowerCase();
            for(char c: s.toCharArray()){
                occ[i][c - 'a'] =  true;
            }
        }
        Map<String, List<String>> map =  new HashMap<>();
        for (int i = 0; i < n; i++) {
            String key = Arrays.toString(occ[i]);
            List<String> l =  map.getOrDefault(key, new ArrayList<>());
            l.add(list.get(i));
            map.put(key, l);
        }
        List<List<String>> result =  new ArrayList<>();
        map.entrySet().forEach(entry -> {
            result.add(entry.getValue());
        });
        return result;
    }
}
