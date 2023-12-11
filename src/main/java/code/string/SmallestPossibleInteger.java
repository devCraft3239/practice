package code.string;

import java.util.Stack;

public class SmallestPossibleInteger {
    public static void main(String[] args) {
        String s = "1432219";
        int k = 3;
        StringBuilder res = new StringBuilder();
        System.out.println(removeKdigits(s, k));
    }

    public static String removeKdigits(String num, int k) {
        int len = num.length();
        // corner case
        if(k==len)
            return "0";
        if(k==0)
            return num;
        Stack<Character> stack =  new Stack<>();
        int index = 0;
        while (index < len){
            Character current =  num.charAt(index);
            while(k > 0 && !stack.isEmpty() && stack.peek() > current) {
                stack.pop();
                k--;
            }
            stack.push(num.charAt(index++));
        }

        while (k-- > 0){
            stack.pop();
        }
        return stack.toString().replace("[", "").replace("]", "").replace(", ", "");

    }

}
