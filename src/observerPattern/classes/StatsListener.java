package observerPattern.classes;

import observerPattern.interfaces.CacheListener;

/**
 * The StatsListener collects hit / miss / update stats for a cache.
 *
 * @param <K>
 * @param <V>
 */
public class StatsListener<K, V> implements CacheListener<K, V> {

    private int nrHits;
    private int nrMisses;
    private int nrUpdates;

    /**
     * Get the number of hits for the cache.
     *
     * @return number of hits
     */
    public int getHits() {
        return nrHits;
    }

    /**
     * Get the number of misses for the cache.
     *
     * @return number of misses
     */
    public int getMisses() {
        return nrMisses;
    }

    /**
     * Get the number of updates (put operations) for the cache.
     *
     * @return number of updates
     */
    public int getUpdates() {
        return nrUpdates;
    }

    @Override
    public void onHit(K key) {
        nrHits++;
    }

    @Override
    public void onMiss(K key) {
        nrMisses++;
    }

    @Override
    public void onPut(K key, V value) {
        nrUpdates++;
    }
}
