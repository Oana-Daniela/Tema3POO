package cachingSystem.classes;

import dataStructures.classes.Pair;
import observerPattern.interfaces.CacheListener;

/**
 * This cache is very similar to the FIFOCache, but guarantees O(1) complexity for the get, put and
 * remove operations.
 */
public class LRUCache<K, V> extends ObservableCache<K, V> {

    private final DoublyLinkedList<K, V> listCache = new DoublyLinkedList<>();

    /**
     * elimin cheia si notific listenerii
     * daca se gaseste cheia , eliminam elementul
     * il readaugam la sfarsitul listei,
     * incrementand si listenerii
     */
    @Override
    public V get(K key) {
        V value = listCache.removeItem(key);
        CacheListener<K, V> listener = getCacheListener();
        if (listener != null) {
            if (value != null) {
                listCache.put(key, value);
                listener.onHit(key);
            } else {
                listener.onMiss(key);
            }
        }
        return value;
    }

    /**
     * Adaug un nou fisier
     */
    @Override
    public void put(K key, V value) {
        listCache.put(key, value);
        CacheListener<K, V> listener = getCacheListener();
        if (listener != null) {
            listener.onPut(key, value);
        }
        clearStaleEntries();
    }

    @Override
    public int size() {
        return listCache.size();
    }

    @Override
    public boolean isEmpty() {
        return listCache.isEmpty();
    }

    @Override
    public V remove(K key) {
        return listCache.removeItem(key);
    }

    @Override
    public void clearAll() {
        listCache.clear();
    }

    @Override
    public Pair<K, V> getEldestEntry() {
        return listCache.getEldestEntry();
    }
}
