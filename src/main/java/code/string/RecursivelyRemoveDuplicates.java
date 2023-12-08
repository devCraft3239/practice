package code.string;

import java.util.Stack;

/**
 * Given a string, recursively remove adjacent duplicate characters from the string.
 * The output string should not have any adjacent duplicates. See the following examples.
 * Input: azxxzy
 * Output: ay
 *
 * First “azxxzy” is reduced to “azzy”.
 * The string “azzy” contains duplicates,
 * so it is further reduced to “ay”.
 * Input: geeksforgeeg
 * Output: gksfor
 *
 * First “geeksforgeeg” is reduced to “gksforgg”.
 * The string “gksforgg” contains duplicates,
 * so it is further reduced to “gksfor”.
 * Input: caaabbbaacdddd
 * Output: Empty String
 *
 * Input: acaaabbbacdddd
 * Output: acac
 * */
public class RecursivelyRemoveDuplicates {
    private  static  char lastRemoved;
    public static void main(String[] args) {
        System.out.println(recurRemoveAdjDuplicateStringBuilder("azxxxzy"));
        System.out.println(recurRemoveAdjDuplicateOptimised("azxxxzy"));
        System.out.println(recurRemoveAdjDuplicateStringBuilder("caaabbbaac"));
        System.out.println(recurRemoveAdjDuplicateOptimised("caaabbbaac"));
    }

    public static String recursivelyRemoveAdjDuplicate(String str){
        char[] chars = str.toCharArray();
        int j=0, flag = 0, i;
        for (i = 1; i < chars.length;) {
            if (chars[i] != chars[i-1]){
                chars[j++] = chars[i-1];
                i++;
            }
            else {
                flag =1;
                while(i < chars.length && chars[i] == chars[i-1]){
                    i++;
                }
                if(i<chars.length)
                    i++;
            }
        }
        chars[j++]=chars[i-1]; // add the last vhacter
        String res =  String.valueOf(chars).substring(0,j);
        if (flag == 1){
            return recursivelyRemoveAdjDuplicate(res);
        }
        return res;
    }

    public static String recurRemoveAdjDuplicateOptimised(String str){
        // base case
        if (str.length() == 0 || str.length() == 1)
            return str;
        char[] chars = str.toCharArray();

        // Remove leftmost duplicate characters and recur for remaining string
        if (chars[0] == chars[1]){
            lastRemoved = chars[0];
            while (str.length() > 1 && str.charAt(0) == str.charAt(1))
                str = str.substring(1, str.length());
            str = str.substring(1, str.length());
            return recurRemoveAdjDuplicateOptimised(str); // recur for distinct string after first dup removal
        }

        // At this point, the first character is definitely
        // different from its adjacent. Ignore first
        // character and recursively remove characters from
        // remaining string
        String remStr = recurRemoveAdjDuplicateOptimised(str.substring(1,str.length())); // rem str after first duplicate removal

        // apply all three cases

        // case if rem is empty and lastRemoved == str[0]  ex:  "acbbcddc"
        if(remStr.length() == 0 && lastRemoved == str.charAt(0))
            return "";

        // case if remStr[0] == str[0] //
        if(remStr.length() != 0 && remStr.charAt(0) == str.charAt(0)){
            lastRemoved =  remStr.charAt(0);
            return remStr.substring(1, remStr.length());
        }


        // If the two first characters of str and rem_str
        // don't match, append first character of str before
        // the first character of rem_str
        return str.charAt(0)+remStr;
    }

    /*
    * caaaabbbaac
    *           i
    * st->c
    *
    * azxxxzy
    *      i
    * st->azz
    * */
    static String recurRemoveAdjDuplicateStringBuilder(String str){
        char[] chars = str.toCharArray();
        int n = chars.length;
        Stack<Character>  st  = new Stack();
        int i;
        for (i = 0; i < n; i++) {
            if (!st.isEmpty() && st.peek() == chars[i]) {
                while (!st.isEmpty() && i < n && st.peek() == chars[i])
                    i++;
                i--;
                st.pop();
            }else{
                st.push(chars[i]);
            }
        }
        if (!st.isEmpty() && i< n && st.peek() == chars[i])
            st.pop();
        StringBuilder sb =  new StringBuilder();
        while (!st.isEmpty())
            sb.append(st.pop());
        return sb.reverse().toString();
    }
}