import code.util.Utils;

import java.io.IOException;
import java.util.*;

import java.util.Arrays;
import java.util.Comparator;

import java.util.ArrayList;
import java.util.Collections;

public class MCQ {
}

class T{
    void m1(){
        System.out.println("T m1");
    }
}

class T2 extends T{
    void m1(){
        System.out.println("T2 m1");
    }
    void m2(){
        System.out.println("T2 m2");
    }
}

class Main{
    public static void main(String[] args) {
        T t =  new T2();
    }
}

class Test3 {
    public static void main(String[] args) {
        String s1 = "abc";
        String s2 = "abc";
        System.out.println("s1 == s2 is:" + s1 == s2);
    }
}

class Test22 {
    public static void main(String[] args) {
//        try {
//            throw new IOException("Hello");
//        } catch (IOException | Exception e) {
//            System.out.println(e.getMessage());
//        }
    }
}



class SortSet {
    public static void main(String... a)
    {
        Set<Integer> treeSet=new TreeSet(new Comparator<Integer>()
        {
            @Override
            public int compare(Integer o1, Integer o2) {
                return 0;
            }

//            @Override
//            public int compareTo(Integer o1,Integer o2)
//            {
//                return o2.compareTo(o1);
//            }
        });
        treeSet.add(3);
        treeSet.add(1);
        treeSet.add(2);
        treeSet.stream().forEach(x-> System.out.println(x));

    }

}


class ListDemo {

    public static void main (String args[])
    {
        List<Integer> list=new ArrayList<Integer>();
        list.add(2);
        list.add(3);
//        m(list);
//        m2(list);

    }
    public static void m(List<Number> list) {
        System.out.println(list);
    }

    public static void m2(List<? extends Number> list) {
        System.out.println(list);
    }

}

class LinkedHashSetTest {
    public static void main (String args[])
    {
        System.out.println("1".equals(1));
        Set s=new LinkedHashSet();
        s.add("1");
        s.add(1);
        s.add(3);
        s.add(2);
        System.out.println(s);
    }
}

class ComparatorTest {
    public static void main(String args[])
    {
        String[] ar= {"c","d","b","a","e"};
        ComparatorTest.InnerClass in=new ComparatorTest.InnerClass();
        Arrays.parallelSort(ar, in);
//        Arrays.sort(ar);
        System.out.println(Arrays.toString(ar));
        System.out.println(Arrays.binarySearch(ar, "b")); //why
    }
    static class InnerClass implements Comparator<String>
    {
        public int compare(String s1, String s2)
        {
            return s2.compareTo(s1);
        }
    }
}

class Test1 {

    public static void main(String[] args) {
        List<Integer> list =Collections.singletonList(1);  // immutable
//        list.set(0, 2);

        list  = Arrays.asList(1,2,3,4,5); // fixed-size list.
//        list.add(6);
//        list.remove(5);
//        list.set(0,6)

        list = new ArrayList<>();
        list.add(1);
        System.out.println(list);
    }

    public static int[] subArrayWithGivenSum(int[] arr, int givenSum){
        int st = 0, end = 0, sum =0;
        while(end < arr.length){
            sum += arr[end++];
            while (sum > givenSum && st < arr.length)
                sum -= arr[st++];
            if(sum ==  givenSum)
                return new int[]{st+1, end};
        }
        return new int[]{-1,-1};
    }

    // {1,2,3,-2,5}
    // {-1,-2,-3,0,-4}
    public static int maxSumContiguousSubArray(int[] arr){
        int st = 0, end =0, currSum =0, maxSum = Integer.MIN_VALUE;
        while (end < arr.length){
            currSum += arr[end++];
            maxSum =  Math.max(currSum, maxSum);
            if(currSum < 0)
                currSum = 0;
        }
        return maxSum;
    }

    // A = { 7 10 4 20 15 }
    // k = 4 -> 15
    public static int randomisedQuickSelect(int[] arr, int k, int low, int high){
        if (low < high){
            int pi = randomisedPartition(arr, low, high);
            if (pi == k-1)
                return arr[pi];
            else if (pi < k-1)
                return randomisedQuickSelect(arr, k, pi+1, high);
            else
                return randomisedQuickSelect(arr, k, low, pi-1);
        }
        return -1;
    }

    public static int randomisedPartition(int[] arr, int low, int high){
        int n = high-low+1;
        int randomIndex = (int) (Math.random()*(n-1));
        Utils.swap(arr,randomIndex, high);

        int pi = arr[high], i = low-1, j=low;
        while (j<=high){
            if (arr[j]<pi){
                Utils.swap(arr,++i,j);
            }
            j++;
        }
        Utils.swap(arr,++i, high);
        return i;
    }

    public static void quickSort(int[] arr, int low, int high){
        if (low < high){
            int  pi =  randomisedPartition(arr, low, high);
            quickSort(arr, low, pi-1);
            quickSort(arr, pi+1, high);
        }
    }
}


class Example {
    public static void main(String args[]) {
        // Create a new ArrayList
        ArrayList<Integer> l = new ArrayList();

        // Add some items to the ArrayList
        l.add(1);
        l.add(2);
        l.add(3);
        l.add(4);
        l.add(5);

        Iterator<Integer> i = l.iterator();

        // Loop through ArrayList contents
        while(i.hasNext()) {
            int item =  i.next();
//             If item is even remove the element
            if (item % 2 == 0) {
            i.remove();
            }
        }
        System.out.println(l);
    }
}

class ConcurrentSkipListMapTest {
    public static void main (String[] args)
    {
//        List<Integer> list = Arrays.asList(1,2,3,4,5);
//        list.remove(3); //
//        System.out.println(list);
//        Iterator<Integer> it =  list.iterator();
//        while (it.hasNext()){
//            int item = it.next();
//            if (item % 2 == 0) {
//                it.remove();
//            }
//        }
//        System.out.println(list);

//        Map<String,String> identityHashMap = new IdentityHashMap<String,String>();
//        identityHashMap.put(new String("a"),"audi");
//        identityHashMap.put(new String("a"),"ferrari");
//        System.out.println(identityHashMap);
//
//        Map<String,String> hashMap = new HashMap<String,String>();
//        hashMap.put(new String("a"),"audi");
//        hashMap.put(new String("a"),"ferrari");
//        System.out.println(hashMap);
//
//        Set set = new TreeSet();
//        set.add(1);
//        set.add("2");
//        set.add(3);
//
//        Iterator iterator = set.iterator();
//        while (iterator. hasNext())
//        {
//            System. out. print(iterator. next()+"");
//        }
//
//
//        Set hashSet = new HashSet();
//        hashSet.add("1");
//        hashSet.add(1);
//        hashSet.add(null);
//        hashSet.add("null");
//        System.out.println(hashSet);
    }

}

