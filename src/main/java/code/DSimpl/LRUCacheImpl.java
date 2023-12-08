package code.DSimpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class CacheEntry<K,V>{
    private K key;
    private V value;
    private CacheEntry<K,V> prev;
    private CacheEntry<K,V> next;

    public CacheEntry(K key, V value){
        this.key = key;
        this.value = value;
        this.prev = null;
        this.next = null;
    }

    public K getKey(){
        return this.key;
    }

    public V getValue(){
        return this.value;
    }

    public CacheEntry<K,V> getPrev(){
        return this.prev;
    }

    public CacheEntry<K,V> getNext(){
        return this.next;
    }

    public void setPrev(CacheEntry<K,V> prev){
        this.prev = prev;
    }

    public void setNext(CacheEntry<K,V> next){
        this.next = next;
    }
}
public class LRUCacheImpl<K,V> implements LRUCache<K,V>{
    private final int capacity;
    private int size;
    private Map<K,CacheEntry<K,V>> map;

    private List<CacheEntry<K,V>> cache;

    public LRUCacheImpl(int capacity){
        this.capacity = capacity;
        this.size = 0;
        this.map = new HashMap<>();
        this.cache = new ArrayList<>();
    }


    @Override
    public V get(K key) {
        CacheEntry entry = map.get(key);
        if (entry != null){
            // remove entry from cache
            removeEntry(entry);
            // add entry to cache at last
            cache.add(entry);
            return (V) entry.getValue();
        }
        return null;
    }

    @Override
    public void put(K key, V value) {
        // check if key exists
        // if exists, update entry from cache
        // if not exists, add entry to cache
        CacheEntry<K,V> entry = map.get(key);
        if (entry != null){
            // remove entry from cache
            // add entry to cache at last
            removeEntry(entry);
            cache.add(entry);
        } else {
            CacheEntry<K, V> newEntry = new CacheEntry<>(key, value);
            // check if cache is full
            if (size == capacity){
                // remove first entry from cache
                CacheEntry<K,V> firstEntry = cache.get(0);
                removeEntry(firstEntry);
                map.remove(firstEntry.getKey());
            } else {
                size++;
            }
            cache.add(newEntry);
            map.put(key, newEntry);
        }
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return size ==0;
    }

    private void removeEntry(CacheEntry<K,V> entry){
        CacheEntry<K,V> prev = entry.getPrev();
        CacheEntry<K,V> next = entry.getNext();
        if (prev != null){
            prev.setNext(next);
        }
        if (next != null){
            next.setPrev(prev);
        }
        if (entry == cache.get(0)){
            cache.set(0, next);
        }
        if (entry == cache.get(cache.size()-1)){
            cache.set(cache.size()-1, prev);
        }
    }
}
