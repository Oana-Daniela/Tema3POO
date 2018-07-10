package cachingSystem.classes;

import dataStructures.classes.Pair;
import observerPattern.interfaces.CacheListener;

/**
 * Class that adapts the FIFOCache class to the ObservableCache abstract class.
 */
public class ObservableFIFOCache<K, V> extends ObservableCache<K, V> {

    private final FIFOCache<K, V> fifoCache = new FIFOCache<>();

    /**
     *
     */
    @Override
    public V get(K key) {
        V value = fifoCache.get(key);
        CacheListener<K, V> listener = getCacheListener();
        if (listener != null) {
            if (value != null) {
                listener.onHit(key);
            } else {
                listener.onMiss(key);
            }
        }
        return value;
    }

    /**Odata pus un fisier trebuie sters altul,
     * daca nu mai este loc in Cache
     *
     */
    @Override
    public void put(K key, V value) {
        fifoCache.put(key, value);
        CacheListener<K, V> listener = getCacheListener();
        if (listener != null) {
            listener.onPut(key, value);
        }
        clearStaleEntries();
    }

    @Override
    public int size() {
        return fifoCache.size();
    }

    @Override
    public boolean isEmpty() {
        return fifoCache.isEmpty();
    }

    @Override
    public V remove(K key) {
        return fifoCache.remove(key);
    }

    @Override
    public void clearAll() {
        fifoCache.clearAll();
    }

    @Override
    public Pair<K, V> getEldestEntry() {
        return fifoCache.getEldestEntry();
    }
}
