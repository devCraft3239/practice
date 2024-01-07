package code.DSimpl;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.locks.ReentrantLock;


import static java.util.Objects.hash;

public class CustomConcurrentHashMapImpl<K,V> implements CustomConcurrentHashMap<K,V>{

    private static final int DEFAULT_CAPACITY = 16;
    private int size = 0;
    private Map<K,V>[] segments;

    private ReentrantLock[] locks;

    public CustomConcurrentHashMapImpl(){
        segments = new Map[DEFAULT_CAPACITY];
        locks = new ReentrantLock[DEFAULT_CAPACITY];
        for(int i=0; i<DEFAULT_CAPACITY; i++){
            segments[i] = new HashMap<>();
            locks[i] = new ReentrantLock();
        }
    }

    private int getSegmentIndex(K key){
        return hash(key) % segments.length;
    }

    @Override
    public V putIfAbsent(K key, V value) {
        int segmentIndex = getSegmentIndex(key);
        locks[segmentIndex].lock();
        try{
            Map<K,V> segment = segments[segmentIndex];
            V oldValue = segment.get(key);
            if(oldValue == null){
                segment.put(key, value);
                size++;
            }
            return oldValue;
        } finally {
            locks[segmentIndex].unlock();
        }
    }

    @Override
    public boolean remove(Object key, Object value) {
        int segmentIndex = getSegmentIndex((K) key);
        locks[segmentIndex].lock();
        try{
            Map<K,V> segment = segments[segmentIndex];
            V oldValue = segment.get(key);
            if(oldValue != null && oldValue.equals(value)){
                segment.remove(key);
                size--;
                return true;
            }
            return false;
        } finally {
            locks[segmentIndex].unlock();
        }
    }

    @Override
    public V getOrDefault(Object key, V defaultValue) {
        int segmentIndex = getSegmentIndex((K) key);
        Map<K,V> segment = segments[segmentIndex];
        V oldValue = segment.get(key);
        if(oldValue == null){
            segment.put((K) key, defaultValue);
            size++;
            return defaultValue;
        }
        return oldValue;
    }
}
