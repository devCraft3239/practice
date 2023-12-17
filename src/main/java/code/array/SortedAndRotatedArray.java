package code.array;

/**
 Given a sorted and rotated array arr[] of size N and a key, the task is to find the key in the array.
 Note: Find the element in O(logN) time and assume that all the elements are distinct.
 
 Input  : arr[] = {5, 6, 7, 8, 9, 10, 1, 2, 3}, key = 3
 Output : Found at index 8

 Input  : arr[] = {5, 6, 7, 8, 9, 10, 1, 2, 3}, key = 30
 Output : Not found

 Input : arr[] = {30, 40, 50, 10, 20}, key = 10
 Output : Found at index 3
 Note: The idea is to find the pivot point, divide the array into two sub-arrays and perform a binary search.
 * */
public class SortedAndRotatedArray {
    public static void main(String[] args) {
        int arr[] = new int[]{30, 40, 50, 10, 20};
        System.out.println(pivotedBinarySearch(arr, 0, arr.length-1, 10));
    }
    static int findPivot(int[] arr, int low, int high){
        if(low <= high){
            int mid =  (low+high)/2;
            if(arr[mid] == arr[low]) // if mid is equal to low, then array is not rotated
                return mid;
            if(arr[mid] < arr[low]) // if mid is less than low, then pivot is in left half
                return findPivot(arr, low, mid-1);
            else
                return findPivot(arr, mid+1, high);
        }
        return -1; // indicate array is not rotated
    }

    static int pivotedBinarySearch(int[] arr, int low, int high, int k){
        int pi =  findPivot(arr, 0, arr.length-1);
        if(pi == -1) // array is not rotated
            return binarySearch(arr, low, high, k);
        if(k == arr[pi])
            return pi;
        else if(k > arr[0])
            return pivotedBinarySearch(arr, low, pi-1, k);
        else
            return pivotedBinarySearch(arr, pi+1, high, k);
    }

    static int binarySearch(int[] arr, int low, int high, int key){
        if (low > high)
            return -1;
        int mid = (low+high)/2;
        if (key == arr[mid])
            return mid;
        if (key < arr[mid])
            return binarySearch(arr, low, mid-1, key);
        else
            return binarySearch(arr, mid+1,high, key);
    }
}
