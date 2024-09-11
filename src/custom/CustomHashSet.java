package custom;

import java.util.HashMap;
import java.util.Map;

public class CustomHashSet<T> {

    public Map<T, Object> map;

    static final Object PRESENT = new Object();

    public CustomHashSet() {
        map = new HashMap<>();
    }

    public boolean add(T value) {
        return map.put(value, PRESENT) == null;
    }

    public boolean remove(T value) {
        return map.remove(value) == PRESENT;
    }

    public boolean contains(T value) {
        return map.containsKey(value);
    }

    public boolean isEmpty() {
        return map.isEmpty();
    }

    public int size() {
        return map.size();
    }

}
