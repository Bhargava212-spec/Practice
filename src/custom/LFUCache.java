package custom;

import java.util.*;

public class LFUCache {
    int cap;
    LinkedHashMap<Integer, Integer> countMap;
    HashMap<Integer, Integer> cacheMap;

    public LFUCache(int capacity) {
        cap = capacity;
        countMap = new LinkedHashMap<>();
        cacheMap = new HashMap<>();
    }

    public int get(int key) {
        if (cacheMap.containsKey(key)) {
            var val = countMap.get(key);
            countMap.remove(key);
            countMap.put(key, val + 1);
            return cacheMap.get(key);
        }
        return -1;

    }

    public void put(int key, int value) {
        if (cap == cacheMap.size()) {
            if (cacheMap.containsKey(key)) {
                cacheMap.put(key, value);
                countMap.put(key, countMap.get(key) + 1);
                return;
            }
            var min = Collections.min(countMap.values());
            var list = countMap.entrySet().stream().filter(var -> Objects.equals(var.getValue(), min)).map(Map.Entry::getKey).toList();
            var leastRecent = list.getFirst();
            cacheMap.remove(leastRecent);
            countMap.remove(leastRecent);
        }
        cacheMap.put(key, value);
        countMap.put(key, countMap.getOrDefault(key, 0) + 1);
    }

}
