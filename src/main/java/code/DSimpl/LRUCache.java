package code.DSimpl;

public interface LRUCache<K,V> {
    V get(K key);
    void put(K key, V value);
    int size();
    boolean isEmpty();
}
