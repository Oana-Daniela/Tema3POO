package observerPattern.classes;

import java.util.ArrayList;
import java.util.List;

import observerPattern.interfaces.CacheListener;

/**
 * The BroadcastListener broadcasts cache events to other listeners that have been added to it.
 */
public class BroadcastListener<K, V> implements CacheListener<K, V> {

    private final List<CacheListener<K, V>> listeners = new ArrayList<>();

    /**
     * Add a listener to the broadcast list.
     *
     * @param listener the listener
     */
    public void addListener(CacheListener<K, V> listener) {
        if (listener != null) {
            this.listeners.add(listener);
        }
    }

    /**
     * Remove a listener from the broadcast list.
     *
     * @param listener the listener
     */
    public void removeListener(CacheListener<K, V> listener) {
        if (listener != null) {
            this.listeners.remove(listener);
        }
    }

    @Override
    public void onHit(K key) {
        for (CacheListener<K, V> listener : this.listeners) {
            listener.onHit(key);
        }
    }

    @Override
    public void onMiss(K key) {
        for (CacheListener<K, V> listener : this.listeners) {
            listener.onMiss(key);
        }
    }

    @Override
    public void onPut(K key, V value) {
        for (CacheListener<K, V> listener : this.listeners) {
            listener.onPut(key, value);
        }
    }
}
