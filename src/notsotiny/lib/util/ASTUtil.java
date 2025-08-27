package notsotiny.lib.util;

import fr.cenotelie.hime.redist.ASTNode;
import fr.cenotelie.hime.redist.SymbolType;
import fr.cenotelie.hime.redist.TextPosition;

/**
 * Provides utility funtions for printing AST nodes
 */
public class ASTUtil {
    
    /**
     * Gets a name from a node. If the name is a library reference, removes the prefix underscore
     * @param node
     * @return
     */
    public static String getName(ASTNode node) {
        String s = node.getValue();
        
        if(s.startsWith("_")) {
            return s.substring(1);
        } else {
            return s;
        }
    }
    
    /**
     * Gets the line number of a node
     * @param node
     * @return
     */
    public static int getLineNumber(ASTNode node) {
        return getPosition(node).getLine();
    }
    
    /**
     * Get the position of an ASTNode
     * @param node
     * @return
     */
    public static TextPosition getPosition(ASTNode node) {
        try {
            // Traverse AST until a terminal is found
            while(node.getSymbolType() != SymbolType.Terminal) {
                node = node.getChildren().get(0);
            }
            
            return node.getPosition();
        } catch(Exception e) {
            return new TextPosition(-1, -1);
        }
    }
    
    /**
     * Prints the name and the names of the children of an ASTNode
     * @param node
     * @return
     */
    public static String detailed(ASTNode node) {
        if(node.getChildren().size() == 0) {
            return node.toString();
        } else {
            StringBuilder sb = new StringBuilder();
            
            sb.append(node);
            sb.append(" {");
            
            for(ASTNode n : node.getChildren()) {
                sb.append(n.toString() + ", ");
            }
            
            sb.delete(sb.length() - 2, sb.length());
            sb.append("}");
            
            return sb.toString();
        }
    }
}
