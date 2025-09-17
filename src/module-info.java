/**
 * NST Shared Library
 * Contains utility code used by multiple NST projects and a copy of the himecc redistributable
 */
module NSTSharedLibrary {
    requires transitive java.logging;
    
    // centralize hime redist
    exports fr.cenotelie.hime.redist;
    exports fr.cenotelie.hime.redist.lexer;
    exports fr.cenotelie.hime.redist.parsers;
    exports fr.cenotelie.hime.redist.utils;
    
    // nst library
    exports notsotiny.lib.data;
    exports notsotiny.lib.printing;
    exports notsotiny.lib.util;
}