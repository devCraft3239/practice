package code.util;

import java.util.*;
import java.util.stream.IntStream;

public class Utils {
    public static final <T> void swap(T[] arr, int i, int j){
        T t =  arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

    static String swap(String str, int i, int j){
        char[] charArray =  str.toCharArray();
        char temp = charArray[i] ;
        charArray[i] = charArray[j];
        charArray[j] = temp;
        return String.valueOf(charArray);
    }

    public static void swap(int[] arr, int i, int j){
        int temp =  arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static final <T> void swap(List<T> list, int i, int j){
        Collections.swap(list, i, j);
    }

    public static Integer[] boxArr(int[] arr){
        return IntStream.of(arr).boxed().toArray(Integer[]::new);
    }

    public static int[] unboxArr(Integer[] arr){
        return Arrays.stream(arr).mapToInt(Integer::intValue).toArray();
    }
    public static int[] parseArr(String[] arr){
        return Arrays.stream(arr).mapToInt(Integer::parseInt).toArray();
    }

}

class Pair{
    public static <T, U> Map.Entry<T, U> of(T first, U second){
        return new AbstractMap.SimpleImmutableEntry<>(first, second);
    }
}

class Pair2<T>{
    T first;
    T second;
    Pair2(T first, T second){
        this.first = first;
        this.second =  second;
    }

    public static <T> Pair2<T> of(T first, T second){
        return new Pair2(first, second);
    }

    @Override
    public String toString() {
        return "Pair2{" +
                "first=" + first +
                ", second=" + second +
                '}';
    }
}
