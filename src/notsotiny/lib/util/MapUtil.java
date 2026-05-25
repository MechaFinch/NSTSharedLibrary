package notsotiny.lib.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import notsotiny.lib.data.TreeNode;

public class MapUtil {
    
    /**
     * Converts a map from child to parent to a forest of trees
     * @param <K>
     * @param map
     * @return A map from element to containing tree node
     */
    public static <K> Map<K, TreeNode<K>> mapToForest(Map<K, K> map) {
        // Make a tree node for each child and parent
        Map<K, TreeNode<K>> nodeMap = new HashMap<>();
        
        for(K key : map.keySet()) {
            nodeMap.putIfAbsent(key, new TreeNode<>(key));
        }
        
        for(K value : map.values()) {
            nodeMap.putIfAbsent(value, new TreeNode<>(value));
        }
        
        // Populate tree edges
        for(Entry<K, K> entry : map.entrySet()) {
            K childValue = entry.getKey();
            K parentValue = entry.getValue();
            
            nodeMap.get(parentValue).addChild(nodeMap.get(childValue));
        }
        
        return nodeMap;
    }
    
    /**
     * Converts a list to a map from element to index
     * @param <K>
     * @param list
     * @return
     */
    public static <K> Map<K, Integer> listToMap(List<K> list) {
        Map<K, Integer> map = new HashMap<>();
        
        for(int i = 0; i < list.size(); i++) {
            map.put(list.get(i), i);
        }
        
        return map;
    }
    
    /**
     * Get the given map, or create it and add it to the map
     * @param <K1>
     * @param <K2>
     * @param <V>
     * @param map
     * @param key
     * @return
     */
    public static <K1, K2, V> Map<K2, V> getOrCreateMap(Map<K1, Map<K2, V>> map, K1 key) {
        if(map.containsKey(key)) {
            return map.get(key);
        } else {
            Map<K2, V> submap = new HashMap<K2, V>();
            map.put(key, submap);
            return submap;
        }
    }
    
    /**
     * Get the given list, or create it and add it to the map
     * @param <K>
     * @param <V>
     * @param map
     * @param key
     * @return
     */
    public static <K, V> List<V> getOrCreateList(Map<K, List<V>> map, K key) {
        if(map.containsKey(key)) {
            return map.get(key);
        } else {
            List<V> list = new ArrayList<V>();
            map.put(key, list);
            return list;
        }
    }
    
    /**
     * Get the given set, or create it and add it to the map
     * @param <K>
     * @param <V>
     * @param map
     * @param key
     * @return
     */
    public static <K, V> Set<V> getOrCreateSet(Map<K, Set<V>> map, K key) {
        if(map.containsKey(key)) {
            return map.get(key);
        } else {
            Set<V> set = new HashSet<V>();
            map.put(key, set);
            return set;
        }
    }
}
