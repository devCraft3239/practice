package code.DSimpl;

import java.util.AbstractMap;
import java.util.concurrent.ConcurrentMap;

public interface CustomConcurrentHashMap<K,V> {
    V putIfAbsent(K key, V value);
    boolean remove(Object key, Object value);
    V getOrDefault(Object key, V defaultValue);
}
