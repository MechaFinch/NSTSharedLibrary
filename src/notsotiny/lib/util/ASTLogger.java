package notsotiny.lib.util;

import java.util.logging.Level;
import java.util.logging.Logger;

import fr.cenotelie.hime.redist.ASTNode;
import fr.cenotelie.hime.redist.SymbolType;
import fr.cenotelie.hime.redist.TextPosition;

/**
 * Methods for logging with source information
 */
public class ASTLogger {
    
    private Logger log;
    
    /**
     * @param log
     */
    public ASTLogger(Logger log) {
        this.log = log;
    }
    
    /**
     * General log
     * @param level
     * @param node
     * @param message
     */
    public void log(Level level, ASTNode node, String message) {
        if(this.log.isLoggable(level)) {
            this.log.log(level, ASTUtil.getSourceInfoString(node) + ": " + message);
        }
    }
    
    /**
     * SEVERE log
     * @param node
     * @param message
     */
    public void severe(ASTNode node, String message) {
        this.log.severe(ASTUtil.getSourceInfoString(node) + ": " + message);
    }
    
    /**
     * WARNING log
     * @param node
     * @param message
     */
    public void warning(ASTNode node, String message) {
        this.log.warning(ASTUtil.getSourceInfoString(node) + ": " + message);
    }
    
    /**
     * INFO log
     * @param node
     * @param message
     */
    public void info(ASTNode node, String message) {
        this.log.info(ASTUtil.getSourceInfoString(node) + ": " + message);
    }
    
    /**
     * FINE log
     * @param node
     * @param message
     */
    public void fine(ASTNode node, String message) {
        this.log.fine(ASTUtil.getSourceInfoString(node) + ": " + message);
    }
    
    /**
     * FINER log
     * @param node
     * @param message
     */
    public void finer(ASTNode node, String message) {
        this.log.finer(ASTUtil.getSourceInfoString(node) + ": " + message);
    }
    
    /**
     * FINEST log
     * @param node
     * @param message
     */
    public void finest(ASTNode node, String message) {
        this.log.finest(ASTUtil.getSourceInfoString(node) + ": " + message);
    }
}
