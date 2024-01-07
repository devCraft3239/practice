package code.DSimpl;

public class Entry<K,V>{
    K key;
    V value;
    Entry<K,V> next;
    public Entry(K key, V value){
        this.key = key;
        this.value = value;
    }

    public Entry(K key){
        this.key = key;
        this.value = null;
    }
}
