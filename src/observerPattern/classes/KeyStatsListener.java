package observerPattern.classes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import observerPattern.interfaces.CacheListener;

/**
 * The KeyStatsListener collects key-level stats for cache operations.
 *
 * @param <K>
 * @param <V>
 */
public class KeyStatsListener<K, V> implements CacheListener<K, V> {

    private final Map<K, Integer> nrHits = new HashMap<>();
    private final Map<K, Integer> nrMisses = new HashMap<>();
    private final Map<K, Integer> nrUpdates = new HashMap<>();

    /**
     * Get the number of hits for a key.
     *
     * @param key the key
     * @return number of hits
     */
    public int getKeyHits(K key) {
        return nrHits.getOrDefault(key, 0);
    }

    /**
     * Get the number of misses for a key.
     *
     * @param key the key
     * @return number of misses
     */
    public int getKeyMisses(K key) {
        return nrMisses.getOrDefault(key, 0);
    }

    /**
     * Get the number of updates for a key.
     *
     * @param key the key
     * @return number of updates
     */
    public int getKeyUpdates(K key) {
        return nrUpdates.getOrDefault(key, 0);
    }

    /**
     * Get the @top most hit keys.
     *
     * @param top number of top keys
     * @return the list of keys
     */
    public List<K> getTopHitKeys(int top) {
        return getTop(nrHits, top);
    }

    /**
     * Get the @top most missed keys.
     *
     * @param top number of top keys
     * @return the list of keys
     */
    public List<K> getTopMissedKeys(int top) {
        return getTop(nrMisses, top);
    }

    /**
     * Get the @top most updated keys.
     *
     * @param top number of top keys
     * @return the list of keys
     */
    public List<K> getTopUpdatedKeys(int top) {
        return getTop(nrUpdates, top);
    }

    @Override
    public void onHit(K key) {
        nrHits.put(key, getKeyHits(key) + 1);
    }

    /**
     *
     */
    @Override
    public void onMiss(K key) {
        nrMisses.put(key, getKeyMisses(key) + 1);
    }

    /**
     *
     */
    @Override
    public void onPut(K key, V value) {
        nrUpdates.put(key, getKeyUpdates(key) + 1);
    }

    /**
     *
     * @param map
     * @param top
     * @return
     */
    private static <T> List<T> getTop(Map<T, Integer> map, int top) {
        List<Map.Entry<T, Integer>> entryList = new ArrayList<>();
        entryList.addAll(map.entrySet());
        Collections.sort(entryList, new Comparator<Map.Entry<T, Integer>>() {
            @Override
            public int compare(Map.Entry<T, Integer> o1, Map.Entry<T, Integer> o2) {
                return o2.getValue() - o1.getValue();
            }
        });
        if (top > entryList.size()) {
            top = entryList.size();
        }
        List<Map.Entry<T, Integer>> subList = entryList.subList(0, top);

        List<T> keyList = new ArrayList<>();
        for (Map.Entry<T, Integer> entry : subList) {
            keyList.add(entry.getKey());
        }
        return keyList;
    }
}
