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
     * Get the position of a node in text form
     * @param node
     * @return
     */
    public static String getSourceInfoString(ASTNode node) {
        TextPosition pos = ASTUtil.getPosition(node);
         
        return "(" + pos.getLine() + ":" + pos.getColumn() + ")";
    }
    
    /**
     * Prints the name and the names of the children of an ASTNode
     * @param node
     * @return
     */
    public static String detailed(ASTNode node) {
        return detailed(node, 0);
    }
    
    /**
     * Prints the name and the names of the children of an ASTNode, recursing until depth=0
     * @param node
     * @param depth # additional layers of detailed
     * @return
     */
    public static String detailed(ASTNode node, int depth) {
        if(node.getChildren().size() == 0) {
            return node.toString();
        } else {
            StringBuilder sb = new StringBuilder();
            
            sb.append(node);
            sb.append(" {");
            
            for(ASTNode n : node.getChildren()) {
                sb.append((depth == 0 ? n.toString() : detailed(n, depth - 1)) + ", ");
            }
            
            sb.delete(sb.length() - 2, sb.length());
            sb.append("}");
            
            return sb.toString();
        }
    }
    
    /**
     * Parse integer literals
     * 
     * @param s
     * @return
     */
    public static long parseInteger(String s, int bytes, boolean signed) {
        long v;
        
        // Ignore underscores
        s = s.replace("_", "");
        
        // Parse with specified base
        if(s.startsWith("0x")) {
            v = Long.parseUnsignedLong(s.substring(2), 16);
        } else if(s.startsWith("0o")) {
            v = Long.parseUnsignedLong(s.substring(2), 8);
        } else if(s.startsWith("0d")) {
            v = Long.parseUnsignedLong(s.substring(2));
        } else if(s.startsWith("0b")) {
            v = Long.parseUnsignedLong(s.substring(2), 2);
        } else {
            v = Long.parseUnsignedLong(s);
        }
        
        // Make desired size
        if(bytes != 0) {
            if(signed) {
                // sign extend
                v = (v << (64 - bytes*8)) >> (64 - bytes*8);
            } else {
                // truncate
                v = (v << (64 - bytes*8)) >>> (64 - bytes*8);
            }
        }
        
        return v;
    }
}
