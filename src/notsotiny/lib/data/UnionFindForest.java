package notsotiny.lib.data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * An implementation of the UNION-FIND data structure which maintains both path-compressed and uncompressed representation
 * Contains a forest of trees where each tree's root is the representative of its equivalence class.
 * 
 * The uncompressed representation is maintained by the TreeNodes making up the trees in the forest.
 * The path-compressed representation is maintained by a compressed-parent map.
 */
public class UnionFindForest<T> {
    
    // Tree roots/equivalence class representatives
    private List<TreeNode<T>> representatives;
    
    // Maps elements to their nodes
    private Map<T, TreeNode<T>> elementNodeMap;
    
    // Maps elements to their compressed parents
    private Map<T, T> compressedParentMap;
    
    /**
     * Creates a UNION-FIND data structure
     */
    public UnionFindForest() {
        this.representatives = new ArrayList<>();
        this.elementNodeMap = new HashMap<>();
        this.compressedParentMap = new HashMap<>();
    }
    
    /**
     * Add an element if it is not present. It will be in its own equivalency class.
     * @param element
     */
    public void add(T element) {
        // Is the node present
        if(this.elementNodeMap.containsKey(element)) {
            return;
        }
        
        // No, add it.
        TreeNode<T> node = new TreeNode<>(element);
        this.representatives.add(node);
        this.elementNodeMap.put(element, node);
        this.compressedParentMap.put(element, element);
    }
    
    /**
     * Performs FIND(element), returning the element associated with the representative node
     * @param element
     * @return
     */
    public T findElement(T element) {
        // Find
        T compressedParent = this.compressedParentMap.get(element);
        
        if(!compressedParent.equals(element)) {
            // Compress if appropriate
            T newParent = findElement(compressedParent);
            this.compressedParentMap.put(element, newParent);
            return newParent;
        } else {
            return element;
        }
    }
    
    /**
     * Performs FIND(node), returning the representative element
     * @param node
     * @return
     */
    public T findElement(TreeNode<T> node) {
        return findElement(node.getElement());
    }
    
    /**
     * Performs FIND(element), returning the representative node
     * @param element
     * @return
     */
    public TreeNode<T> findNode(T element) {
        // Get representative via path compression, then get node for element
        return this.elementNodeMap.get(findElement(element));
    }
    
    /**
     * Performs FIND(node), returning the representative node
     * @param node
     * @return
     */
    public TreeNode<T> findNode(TreeNode<T> node) {
        // Get representative via path compression
        return findNode(node.getElement());
    }
    
    /**
     * Merges the equivalency classes of elementA and elementB
     * @param elementA
     * @param elementB
     */
    public void union(T elementA, T elementB) {
        union(this.elementNodeMap.get(elementA), this.elementNodeMap.get(elementB));
    }
    
    /**
     * Merges the equivalency classes of nodeA and nodeB
     * @param nodeA
     * @param nodeB
     */
    public void union(TreeNode<T> nodeA, TreeNode<T> nodeB) {
        // Get representatives
        TreeNode<T> repA = findNode(nodeA),
                    repB = findNode(nodeB);
        
        // Make the representative of A the parent of the representative of B
        repB.setParent(repA);
        this.compressedParentMap.put(repB.getElement(), repA.getElement());
        this.representatives.remove(repB);
    }
    
    public List<TreeNode<T>> getRoots() { return this.representatives; }
    public Map<T, TreeNode<T>> getElementNodeMap() { return this.elementNodeMap; }
    
    /**
     * logs the forest
     * @param log
     */
    public void logForest(Logger log, Level level) {
        if(log.isLoggable(level)) {
            for(TreeNode<T> node : this.elementNodeMap.values()) {
                node.logTree(log, level);
            }
        }
    }
    
}
