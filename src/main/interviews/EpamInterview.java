/*
strings strs , group the anagrams together. You can return the answer in any order.

An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase, typically using all the original letters exactly once.
Input: strs = ["Eat","Tea","Tan","ate","nat","bat"]
Output: [["bat"],["nat","Tan"],["ate","Eat","Tea"]]

["Eat", "Tea", "Tan", "ate", "nat", "bat"] -> N
[100010..1], [1001111],               []
* */


import java.util.*;

public class EpamInterview {

    public static void main(String[] agrs){
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
    public static List<List<String>> groupAnagram(List<String> list){
        int n =  list.size();
        boolean[][] occ = new boolean[n][26];
        for(int i=0;i<n;i++){
            String s = list.get(i).toLowerCase(Locale.ROOT);
            for(char c: s.toCharArray()){
                occ[i][c - 'a'] =  true;
            }
        }

        Map<String, List<String>> map =  new HashMap<>();
        List<String> l = null;
        for (int i = 0; i < n; i++) {
            String key = Arrays.toString(occ[i]);
            if(map.containsKey(key)){
                l =  map.get(key);
                l.add(list.get(i));
                map.put(key, l);
            }else{
                l = new ArrayList<>();
                l.add(list.get(i));
                map.put(key, l);
            }
        }

        List<List<String>> result =  new ArrayList<>();
        map.entrySet().forEach(entry -> {
            result.add(entry.getValue());
        });
        return result;
    }
}

/*
Given an integer array of unique elements, return all possible subsets (the power set).

The solution set must not contain duplicate subsets. Return the solution in any order.



Input: nums = [1,2,3]
Output: [[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
Input: nums = [0]
Output: [[],[0]]

[1,2,3]
[]
[1],[2],[3]
[1,2] [1,3] [2,3]
[1,2,3]

[1,2,3]
[]
[1,0], [2,1], [3,2]
[1,2] [1,3] [2,3]
[1,2,3]
(2^N)

                       [1,2,3]
                         []
                    [1]    [2]       [3]
                [1,2] [1,3]  [2,3]      [3]
             [1,2,3]
* */
class Epam2{

    public static void  main(String[] args){
        generateSubSet(new int[]{1,2,3});
    }

    public static void generateSubSet(int[] arr){
        generateUtil(arr, arr.length, 0, new HashSet());
    }
    public static void generateUtil(int[] arr, int n, int i, Set list){
        if(i >= n)
            return;
        Set<Integer> l =  new HashSet<>();
        for(int j=i;j<n;j++){
            l.add(arr[j]);
            System.out.println(l);
            generateUtil(arr, n,i+1, l);
        }
    }
}