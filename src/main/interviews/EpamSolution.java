
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.regex.Pattern;


public class EpamSolution {
    public static final int MAX = 0xFFFFF;
    public static final int MIN = 0;
    private static final Pattern NUMERIC = Pattern.compile("\\d+");
    private final Stack<Integer> stack = new Stack<>();

    public int solution(String S) {
        try {
            Arrays.stream(S.split(" ")).forEach(this::applyInstruction);
            return pop();
        } catch (IllegalArgumentException e) {
            return -1;
        }
    }

    private void applyInstruction(String ins) {
        if (NUMERIC.matcher(ins).matches()) {
            push(Integer.valueOf(ins));
        } else {
            switch (ins){
                case "POP":
                    pop();
                    break;
                case "DUP":
                    dup();
                    break;
                case "+":
                    add();
                    break;
                case "-":
                    sub();
                    break;
                default:
                    throw new IllegalArgumentException();
            }
        }
    }

    private void push(int i) {
        withinRange(i);
        stack.push(i);
    }

    private int pop() {
        return stack.pop();
    }

    private void dup() {
        hasElements(1);
        push(stack.peek());
    }

    private void add() {
        hasElements(2);
        push(stack.pop() + stack.pop());
    }

    private void sub() {
        hasElements(2);
        push(stack.pop() - stack.pop());
    }

    private int hasElements(int i) {
        if(stack.size() < i){
            throw new IllegalArgumentException("Too few elements available");
        }
        return i;
    }

    private int withinRange(int i){
        if(i < MIN || i > MAX){
            throw new IllegalArgumentException("Input under/overflow");
        }
        return i;
    }
}