package code.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given a positive integer N and an array arr[] of size K consisting of binary string where each string is of size N,
 * the task is to find all the binary strings of size N that are not present in the array arr[].
 * Input: N = 3, arr[] = {“101”, “111”, “001”, “010”, “011”, “100”, “110”}
 * Output: 000
 * Explanation: Only 000 is absent in the given array.
 *
 * Input: N = 2, arr[] = {“00”, “01”}
 * Output: 10 11
 * */
public class BinaryNotPresentInList {

    public static void main(String[] args) {
        List<String> givenList = Arrays.asList("101", "110", "001", "100");
        BinaryGeneratorLengthN.allBinary.removeAll(givenList);
        System.out.println(BinaryGeneratorLengthN.allBinary);
    }
}

class BinaryGeneratorLengthN{
    public static final List<String> allBinary = new ArrayList<>();
    public static void main(String[] args) {
        int n =  3;
        generateBinaryOfLengthN(n);
        System.out.println(allBinary);
    }


    private static void generateAllBinaryOfLengthN(int n){
        int[] arr =  new int[n];
        generateAllBinaryOfLengthNUtil(arr, n-1);
    }

    private static void generateAllBinaryOfLengthNUtil(int[] arr, int i){
        if(i<0)
        {allBinary.add(Arrays.toString(arr));return;}
        // put arr[i] = 0 and generate all possible permutations
        arr[i] = 0;
        generateAllBinaryOfLengthNUtil(arr, i-1);

        // put arr[i] = 1 and generate all possible permutations
        arr[i] = 1;
        generateAllBinaryOfLengthNUtil(arr, i-1);
    }

    private static void generateBinaryOfLengthN(int n){
        int[] arr = new int[n];
        generateBinaryOfLengthNUtil(arr, n-1);
    }

    private static void generateBinaryOfLengthNUtil(int[] arr, int i){
        if (i < 0)
        {allBinary.add(Arrays.toString(arr));
            return;}
        arr[i] = 0;

        generateBinaryOfLengthNUtil(arr, i-1);
        arr[i] = 1;
        generateBinaryOfLengthNUtil(arr, i-1);
    }
}