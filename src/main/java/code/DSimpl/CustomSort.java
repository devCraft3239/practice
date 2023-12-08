package code.DSimpl;

public interface CustomSort<E>{
    void selectionSort(E[] array);
    void insertionSort(E[] array);
    void bubbleSort(E[] array);
    void mergeSort(E[] array, int l, int h);
    void quickSort(E[] array, int l, int h);
    void heapSort(E[] array);
}
