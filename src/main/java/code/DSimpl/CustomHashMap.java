package code.DSimpl;

class Entry<K,V>{
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

public class CustomHashMap<K, V> implements CustomMap<K, V> {
    private Entry<K,V>[] buckets;
    private static final int DEFAULT_CAPACITY = 16;
    private int size = 0;

    public CustomHashMap(){
        buckets = new Entry[DEFAULT_CAPACITY];
    }

    public CustomHashMap(int capacity){
        if(capacity < 0){
            throw new IllegalArgumentException("Illegal capacity: "+capacity);
        } else {
            buckets = new Entry[capacity];
        }
    }

    private int getBucketIndex(K key){
        return key.hashCode() % buckets.length;
    }


    @Override
    public V put(K key, V value) {
        int bucketIndex = getBucketIndex(key);
        Entry<K,V> entry = buckets[bucketIndex];
        if(entry == null){
            buckets[bucketIndex] = new Entry<>(key, value);
            size++;
            return value;
        } else {
            while(entry.next != null){
                if(entry.key.equals(key)){
                    V oldValue = entry.value;
                    entry.value = value;
                    return oldValue;
                }
                entry = entry.next;
            }
            if(entry.key.equals(key)){
                V oldValue = entry.value;
                entry.value = value;
                return oldValue;
            }
            entry.next = new Entry<>(key, value);
            size++;
            return value;
        }
    }

    @Override
    public V get(K key) {
        int bucketIndex = getBucketIndex(key);
        Entry<K,V> entry = buckets[bucketIndex];
        while(entry != null){
            if(entry.key.equals(key)){
                return entry.value;
            }
            entry = entry.next;
        }
        return null;
    }

    @Override
    public V remove(K key) {
        int bucketIndex = getBucketIndex(key);
        Entry<K,V> entry = buckets[bucketIndex];
        Entry<K,V> prev = null;
        while(entry != null){
            if(entry.key.equals(key)){
                if(prev == null){
                    buckets[bucketIndex] = entry.next;
                } else {
                    prev.next = entry.next;
                }
                size--;
                return entry.value;
            }
            prev = entry;
            entry = entry.next;
        }
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }
}
