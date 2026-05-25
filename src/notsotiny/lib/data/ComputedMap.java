package notsotiny.lib.data;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

/**
 * A map which provides a computed value instead of null
 * Wraps a given map
 * @param <K>
 * @param <V>
 */
public class ComputedMap<K, V> implements Map<K, V> {
    
    private Map<K, V> map;
    
    private Function<K, V> func;
    
    /**
     * Create a DefaultMap wrapping map with default value function func
     * @param map
     * @param defVal
     */
    public ComputedMap(Map<K, V> map, Function<K, V> func) {
        this.map = map;
        this.func = func;
    }

    @Override
    public int size() {
        return this.map.size();
    }

    @Override
    public boolean isEmpty() {
        return this.map.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return this.map.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return this.map.containsValue(value);
    }

    @SuppressWarnings("unchecked") // its in a try catch so shut up
    @Override
    public V get(Object key) {
        if(this.map.containsKey(key)) {
            return this.map.get(key);
        } else {
            try {
                return this.func.apply((K) key);
            } catch(ClassCastException c) {
                return this.map.get(key);
            }
        }
    }

    @Override
    public V put(K key, V value) {
        return this.map.put(key, value);
    }

    @Override
    public V remove(Object key) {
        return this.map.remove(key);
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        this.map.putAll(m);
    }

    @Override
    public void clear() {
        this.map.clear();
    }

    @Override
    public Set<K> keySet() {
        return this.map.keySet();
    }

    @Override
    public Collection<V> values() {
        return this.map.values();
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        return this.map.entrySet();
    }
    
}
