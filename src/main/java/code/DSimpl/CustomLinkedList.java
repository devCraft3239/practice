package code.DSimpl;

public interface CustomLinkedList<T> {
    void add(T data);

    void add(int index, T data);

    void remove(int index);

    void remove(T data);

    T get(int index);

    void set(int index, T data);

    int size();

    boolean isEmpty();

    boolean contains(T data);

    void clear();

    int indexOf(T data);

    T[] toArray();

    T reverse();

    void print();
}
