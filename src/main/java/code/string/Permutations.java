package code.string;

import code.util.Utils;

import java.util.Arrays;

public class Permutations {

    public static void main(String[] args) {
        permutations("ABC");
    }

    public static void permutations(String str){
        Character[] chars = str.chars().mapToObj(c-> (char)c).toArray(Character[]::new);
        permute(chars, 0, chars.length-1);
    }

    public static void permute(Character[] str, int l, int r){
        if (l == r)
            System.out.println(Arrays.toString(str));
        else
        {
            for (int i = l; i <= r; i++) {
                Utils.swap(str, l, i);  // swap l with each element till r
                permute(str, l+1, r); // fixing one element at a time
                Utils.swap(str, l, i);  // backtracking to get original previous string
            }
        }
    }
}
