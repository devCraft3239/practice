package code.DSimpl;

public class CustomLinkedListImpl<T> implements CustomLinkedList<T>{
private static class Node<T> {
        T data;
        Node<T> next;

        Node(T data) {
            this.data = data;
            this.next = null;
        }
    }

    private Node<T> head;
    private int size;
    @Override
    public void add(T data) {
        Node<T> newNode = new Node<>(data);
        if (head == null) {
            head = newNode;
            size++;
            return;
        }
        Node<T> temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = newNode;
        size++;
    }

    @Override
    public void add(int index, T data) {
        Node<T> newNode = new Node<>(data);
        if (index == 0) {
            newNode.next = head;
            head = newNode;
            size++;
            return;
        }
        Node<T> temp = head;
        for (int i = 0; i < index - 1; i++) {
            if (temp.next == null) {
                throw new IndexOutOfBoundsException();
            }
            temp = temp.next;
        }
        newNode.next = temp.next;
        temp.next = newNode;
        size++;
    }

    @Override
    public void remove(int index) {
        if (index == 0) {
            head = head.next;
            size--;
            return;
        }
        Node<T> temp = head;
        for (int i = 0; i < index - 1; i++) {
            if (temp.next == null) {
                throw new IndexOutOfBoundsException();
            }
            temp = temp.next;
        }
        temp.next = temp.next.next;
        size--;
    }

    @Override
    public void remove(T data) {
        if (head.data == data) {
            head = head.next;
            size--;
            return;
        }
        Node<T> temp = head;
        while (temp.next != null) {
            if (temp.next.data == data) {
                temp.next = temp.next.next;
                size--;
                return;
            }
            temp = temp.next;
        }
    }

    @Override
    public T get(int index) {
        if (index == 0) {
            return head.data;
        }
        Node<T> temp = head;
        for (int i = 0; i < index; i++) {
            if (temp.next == null) {
                throw new IndexOutOfBoundsException();
            }
            temp = temp.next;
        }
        return temp.data;
    }

    @Override
    public void set(int index, T data) {
        if (index == 0) {
            head.data = data;
            return;
        }
        Node<T> temp = head;
        for (int i = 0; i < index; i++) {
            if (temp.next == null) {
                throw new IndexOutOfBoundsException();
            }
            temp = temp.next;
        }
        temp.data = data;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(T data) {
        Node<T> temp = head;
        while (temp != null) {
            if (temp.data == data) {
                return true;
            }
            temp = temp.next;
        }
        return false;
    }

    @Override
    public void clear() {
        head = null;
        size = 0;
    }

    @Override
    public int indexOf(T data) {
        Node<T> temp = head;
        int index = 0;
        while (temp != null) {
            if (temp.data == data) {
                return index;
            }
            temp = temp.next;
            index++;
        }
        return -1;
    }

    @Override
    public T[] toArray() {
        T[] arr = (T[]) new Object[size];
        Node<T> temp = head;
        int index = 0;
        while (temp != null) {
            arr[index++] = temp.data;
            temp = temp.next;
        }
        return arr;
    }

    @Override
    public T reverse() {
        Node<T> prev = null, next = null, current = head;
        while (current != null) {
            next = current.next;
            current.next = prev;

            prev = current;
            current = next;
        }
        return prev.data;
    }

    @Override
    public void print() {
        Node<T> temp = head;
        while (temp != null) {
            System.out.print(temp.data + " -> ");
            temp = temp.next;
        }
        System.out.println("null");
    }
}

class CustomDoublyLinkedListImpl<T> implements CustomLinkedList<T>{

private static class Node<T> {
        T data;
        Node<T> next;
        Node<T> prev;

        Node(T data) {
            this.data = data;
            this.next = null;
            this.prev = null;
        }
    }

    private Node<T> head;
    private Node<T> tail;
    private int size;

    @Override
    public void add(T data) {
        Node<T> newNode = new Node<>(data);
        if (head == null) {
            head = newNode;
            tail = newNode;
            size++;
            return;
        }
        tail.next = newNode;
    }

    @Override
    public void add(int index, T data) {
        Node<T> newNode = new Node<>(data);
        if (index == 0) {
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
            size++;
            return;
        }
        Node<T> temp = head;
        for (int i = 0; i < index - 1; i++) {
            if (temp.next == null) {
                throw new IndexOutOfBoundsException();
            }
            temp = temp.next;
        }
        newNode.next = temp.next;
        newNode.prev = temp;
        temp.next = newNode;
        size++;
    }

    @Override
    public void remove(int index) {
        if (index == 0) {
            head = head.next;
            head.prev = null;
            size--;
            return;
        }
        Node<T> temp = head;
        for (int i = 0; i < index - 1; i++) {
            if (temp.next == null) {
                throw new IndexOutOfBoundsException();
            }
            temp = temp.next;
        }
        temp.next = temp.next.next;
        temp.next.prev = temp;
        size--;
    }

    @Override
    public void remove(T data) {
        if (head.data == data) {
            head = head.next;
            head.prev = null;
            size--;
            return;
        }
        Node<T> temp = head;
        while (temp.next != null) {
            if (temp.next.data == data) {
                temp.next = temp.next.next;
                temp.next.prev = temp;
                size--;
                return;
            }
            temp = temp.next;
        }
    }

    @Override
    public T get(int index) {
        if (index == 0) {
            return head.data;
        }
        Node<T> temp = head;
        for (int i = 0; i < index; i++) {
            if (temp.next == null) {
                throw new IndexOutOfBoundsException();
            }
            temp = temp.next;
        }
        return temp.data;
    }

    @Override
    public void set(int index, T data) {
        if (index == 0) {
            head.data = data;
            return;
        }
        Node<T> temp = head;
        for (int i = 0; i < index; i++) {
            if (temp.next == null) {
                throw new IndexOutOfBoundsException();
            }
            temp = temp.next;
        }
        temp.data = data;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(T data) {
        Node<T> temp = head;
        while (temp != null) {
            if (temp.data == data) {
                return true;
            }
            temp = temp.next;
        }
        return false;
    }

    @Override
    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    @Override
    public int indexOf(T data) {
        Node<T> temp = head;
        int index = 0;
        while (temp != null) {
            if (temp.data == data) {
                return index;
            }
            temp = temp.next;
            index++;
        }
        return 0;
    }

    @Override
    public T[] toArray() {
        T[] arr = (T[]) new Object[size];
        Node<T> temp = head;
        int index = 0;
        while (temp != null) {
            arr[index++] = temp.data;
            temp = temp.next;
        }
        return arr;
    }

    @Override
    public T reverse() {
        Node<T> prev = null, next = null, current = head;
        while (current != null) {
            next = current.next;
            current.next = prev;

            prev = current;
            current = next;
        }
        return prev.data;
    }

    @Override
    public void print() {
        Node<T> temp = head;
        while (temp != null) {
            System.out.print(temp.data + " -> ");
            temp = temp.next;
        }
        System.out.println("null");
    }
}