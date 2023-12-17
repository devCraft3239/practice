package code.array;


import code.util.Utils;

import java.util.Collections;
import java.util.PriorityQueue;

/**
 Given are N ropes of different lengths, the task is to connect these ropes into one rope with minimum cost,
 such that the cost to connect two ropes is equal to the sum of their lengths.
 Input: arr[] = {4,3,2,6} , N = 4
 Output: 29
 Explanation:

 First, connect ropes of lengths 2 and 3. Now we have three ropes of lengths 4, 6, and 5.
 Now connect ropes of lengths 4 and 5. Now we have two ropes of lengths 6 and 9.
 Finally connect the two ropes and all ropes have connected.
 * */

public class MinCostToConnectNRopes {

    public static int minCost(int[] arr){ // O(nlogn)
        int minCost = 0;
        MinHeap minHeap =  new MinHeap(arr);
        while (minHeap.size >1){
            int min =  minHeap.extract();
            int secMin = minHeap.extract();
            int cost =  min+secMin;
            minCost += cost;
            minHeap.insert(cost);
        }
        return minCost;
    }

    public static int minCostPriorityQueue(int[] arr){
        PriorityQueue<Integer> pq =  new PriorityQueue<>(); // default implementation as min-heap
        PriorityQueue<Integer> pqMax =  new PriorityQueue<>(Collections.reverseOrder()); // implementation as max-code.heap
        for (int i = 0; i < arr.length; i++) {
            pq.add(arr[i]);
        }
        int min = 0, secMin = 0, currCost = 0, minCost=0;
        while (pq.size() >1){
            min = pq.poll();
            secMin = pq.poll();
            currCost = min+secMin;
            minCost += currCost;
            pq.add(currCost);
        }
        return minCost;
    }
}

class MinHeap{
    int[] harr;
    int CAPACITY;
    int size;
    MinHeap(int[] arr){
        this.harr =  arr;
        this.CAPACITY = arr.length;
        this.size =  arr.length;
        int i =  (size-1)/2; // last non-leaf node
        while (i>=0)
            heapify(i--);
    }

    void heapify(int i){
        int smallest = i;
        int left = 2*i+1, right = 2*i+2;
        if(harr[smallest] < harr[left])
            smallest =  left;
        if (harr[smallest] < harr[right])
            smallest =  right;
        if(i !=  smallest){
            Utils.swap(harr, smallest, i);
            heapify(smallest);
        }
    }

    int getSize(){
        return size;
    }

    int parent(int i){
        return (i-1)/2;
    }

    int left(int i){
        return 2*i+1;
    }

    int right(int i){
        return 2*i+2;
    }

    void insert(int k){
        if (size == CAPACITY)
            throw new StackOverflowError("Out of memory");
        size++;
        int i = size-1;
        harr[i] = k;
        while (i != 0 && harr[parent(i)] > harr[i]) {
            Utils.swap(harr, i, parent(i));
            i = parent(i);
        }
    }

    public int extract(){
        int val = harr[0];
        Utils.swap(harr, 0, size-1);
        size--;
        heapify(0);
        return val;
    }

}

class MinHeap2{
    int[] harr;
    int size;
    int CAPACITY = 16;

    MinHeap2(){
        harr =  new int[CAPACITY];
    }
    MinHeap2(int[] arr){
        harr =  arr;
        size = arr.length;
        CAPACITY = size;
        buildMinHeap(harr);
    }

    void buildMinHeap(int[] arr){
        int n = arr.length;
        for (int i = n/2-1; i >=0 ; i--) {
            heapify(arr, i, n);
        }
    }

    void heapify(int[] arr, int i, int n){
        int largest = i, l = 2*i+1, r = 2*i+2;
        if(l < n && arr[largest] > arr[l])
            largest = l;
        if (r < n && arr[largest] > arr[r])
            largest = r;
        if (i != largest){
            Utils.swap(arr, i, largest);
            heapify(arr, largest, i);
        }
    }

    int getMin(){
        int minVal = Integer.MAX_VALUE;
        if(size > 0){
            minVal = harr[0];
            Utils.swap(harr, 0, size-1);
            heapify(harr, 0, size--);
        }
        return minVal;
    }

    int parent(int i){
        return i/2-1;
    }

    void insert(int key){
        if (size ==  CAPACITY)
            throw new StackOverflowError("Out of memory, heap capacity is full ");
        int i =  size++;
        harr[i] = key;
        while (i != 0 && harr[parent(i)] > harr[i]) {
            Utils.swap(harr, i, parent(i));
            i = parent(i);
        }
    }

    int size(){
        return size;
    }
}



