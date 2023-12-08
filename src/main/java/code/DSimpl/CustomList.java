package code.DSimpl;

import java.io.Serializable;
import java.util.RandomAccess;

public  interface CustomList<E> extends RandomAccess, Cloneable, Serializable {
    boolean add(E element);
    E remove(int index);
    E get(int index);
    void set(int index, E element);
    int size();
    boolean isEmpty();
}
