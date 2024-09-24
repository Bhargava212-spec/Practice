package custom;

import java.util.Objects;

@SuppressWarnings("unchecked")
public class CustomHashMap<K, V> {

    private int size;

    class CustomEntry<K, V> {

        private K key;
        private V value;
        private CustomEntry<K, V> next;

        public CustomEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }

    }

    private final int DEFAULT_SIZE = 16;

    private CustomEntry<K, V>[] table;


    public CustomHashMap() {
        table = new CustomEntry[DEFAULT_SIZE];
    }

    public void put(K key, V value) {
        var hash = key.hashCode() % table.length;
        CustomEntry<K, V> entry = table[hash];
        if (Objects.isNull(entry)) {
            table[hash] = new CustomEntry<>(key, value);
        } else {
            while (Objects.nonNull(entry.next)) {
                if (entry.getKey().equals(key)) {
                    entry.setValue(value);
                    return;
                }
                entry = entry.next;
            }
            entry.next = new CustomEntry<>(key, value);
            size++;
        }
    }

    public V get(K key) {
        var hash = key.hashCode() % table.length;
        CustomEntry<K, V> entry = table[hash];
        if (Objects.isNull(entry)) {
            return null;
        }
        while (Objects.nonNull(entry)) {
            if (entry.getKey().equals(key)) {
                return entry.getValue();
            }
            entry = entry.next;
        }
        return null;
    }

    public V remove(K key) {
        var hash = key.hashCode() % table.length;
        CustomEntry<K, V> entry = table[hash];
        CustomEntry<K, V> prev = null;
        if (entry == null) {
            return null;
        }
        while (Objects.nonNull(entry)) {
            if (entry.getKey().equals(key)) {
                if (prev == null) {
                    table[hash] = entry.next;
                } else {
                    prev.next = entry.next;
                }
                size--;
                return entry.getValue();
            }
            prev = entry;
            entry = entry.next;
        }
        return null;
    }

    public boolean containsKey(K key) {
        var hash = key.hashCode() % table.length;
        CustomEntry<K, V> entry = table[hash];
        if (entry == null) {
            return false;
        }
        while (entry != null) {
            if (entry.getKey().equals(key)) {
                return true;
            }
            entry = entry.next;
        }
        return false;
    }

    public int size() {
        return size;
    }

    public V getOrDefault(K key, V defaultValue) {
        V value = get(key);
        if (value == null) {
            return defaultValue;
        }
        return value;
    }

    private void resize() {
        CustomEntry<K, V>[] oldTable = table;
        table = new CustomEntry[oldTable.length * 2];
        size = 0;
        for (CustomEntry<K, V> entry : oldTable) {
            while (entry != null) {
                put(entry.getKey(), entry.getValue());
                entry = entry.next;
            }
        }
    }

}
