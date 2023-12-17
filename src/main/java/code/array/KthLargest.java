package code.array;

import code.util.Utils;

import java.util.Arrays;

/**
 Given an array and a number K where K is smaller than the size of the array.
 Find the K’th smallest element in the given array. Given that all array elements are distinct.
 Examples:
 Input: arr[] = {7, 10, 4, 3, 20, 15}, K = 3
 Output: 7
 Input: arr[] = {7, 10, 4, 3, 20, 15}, K = 4
 Output: 10
 * */
public class KthLargest {
    public static void main(String[] args) {
        System.out.println(kthSmallest(new int[]{7, 10, 4, 3, 20, 15}, 3));
    }
    static int kthSmallest(int arr[], int k){
        return kthSmallestUtil(arr, 0, arr.length-1, k);
    }
    static int kthSmallestUtil(int[] arr, int low, int high, int k){
        if(k > 0 && k <= high-low+1){
            int pi =  QuickSort.partition(arr, low, high);
            if (pi == k-1)
                return arr[pi];
            else if(pi > k-1) // if pi is greater than k-1, then kth smallest is in left subarray
                return kthSmallestUtil(arr, low, pi-1, k);
            else
                return kthSmallestUtil(arr, pi+1, high, k);
        }
        return Integer.MAX_VALUE;
    }
}

class QuickSort{
    public static void quickSort(int arr[]){
        quickSortUtil(arr, 0, arr.length-1);
    }
    public static void quickSortUtil(int[] arr, int low, int high){
        if(low < high){
            int pi = partition(arr, low, high); // pi is now at its correct position
            quickSortUtil(arr, low, pi-1); // sort left
            quickSortUtil(arr, pi+1, high);
        }
    }
    public static int partition(int[] arr, int low, int high){
        int pivot = arr[high];
        int i=low-1;
        int j=low;
        for (;j < high; j++) {
            if (arr[j] < pivot) {
                Utils.swap(arr, ++i, j);
            }
        }
        i++;
        Utils.swap(arr, i, high);
        return i;
    }
}

class QuickSortAlgo{
    public static void main(String[] args) {
       int[] arr =  new int[]{2,3,1,4,7,5};
       quickSort(arr);
       System.out.println(Arrays.toString(arr));
    }
    static void quickSort(int[] arr){
        quickSortUtil(arr, 0, arr.length-1);
    }
    static void quickSortUtil(int[] arr, int l, int r){
        if(l>=r)
            return;
        int piIndex =  partition(arr, l,r);
        quickSortUtil(arr, l, piIndex-1);
        quickSortUtil(arr, piIndex+1, r);
    }
    static int partition(int[] arr, int l, int r){
        int pi = r, i=l, j=l;
        while(j < r){
            if (arr[j] < arr[pi])
                Utils.swap(arr, i++,j++);
            else
                j++;
        }
        Utils.swap(arr, pi, i);
        return i;
    }
}
