package code.DSimpl;

import java.util.Arrays;

public class CustomArrayList<E>  implements CustomList<E>{
    private E[] dynamicArray;
    private static final int DEFAULT_CAPACITY =  10;
    private int size = 0;

    public CustomArrayList(){
        dynamicArray = (E[]) new Object[DEFAULT_CAPACITY];
    }

    public CustomArrayList(int capacity){
        if(capacity < 0){
            throw new IllegalArgumentException("Illegal capacity: "+capacity);
        } else {
            dynamicArray = (E[]) new Object[capacity];
        }
    }

    @Override
    public boolean add(E element) {
        ensureCapacity(size+1);
        dynamicArray[size++] = element;
        return true;
    }

    @Override
    public E remove(int index) {
        checkIndexAndThrowException(index);
        E removedVal =  dynamicArray[index];
        int numShift =  dynamicArray.length - index - 1;
        if(numShift > 0)
            System.arraycopy(dynamicArray, index+1, dynamicArray, index, numShift);
        size--;
        return removedVal;
    }

    @Override
    public E get(int index) {
        checkIndexAndThrowException(index);
        return dynamicArray[index];
    }

    @Override
    public void set(int index, E element) {
        checkIndexAndThrowException(index);
        dynamicArray[index] = element;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size ==0;
    }

    private void ensureCapacity(int minCapacity){
        if(dynamicArray.length < minCapacity)
            growArray(minCapacity);
    }

    private void growArray(int minCapacity) {
        int newCapacity = Math.max(dynamicArray.length + dynamicArray.length >> 1, minCapacity);
        dynamicArray =  Arrays.copyOf(dynamicArray, newCapacity);
    }

    private void checkIndexAndThrowException(int index){
        if (index >= size || index < 0)
            throw new IndexOutOfBoundsException("Index: "+index+", Size: "+size);
    }
}
