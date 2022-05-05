/*
Decompiled By LOSTED
https://github.com/LOSTEDs
LOSTED#8754
https://www.youtube.com/watch?v=xg2M21todDU&t=55s
"...Minecraft client created by professional developers exclusively for me..." - SuchSpeed
Here is a better way to say this, "...Minecraft client skidded by some random script kittens exclusively for me"
Please SuchSpeed, don't sue me... I just dumped the source...
For Educational Purposes Only...
*/

package org.apache.commons.io.filefilter;

import java.io.File;
import java.io.Serializable;
import java.util.List;
import org.apache.commons.io.IOCase;

public class PrefixFileFilter extends AbstractFileFilter implements Serializable {
    private static final long serialVersionUID = 8533897440809599867L;
    
    private final String[] prefixes;
    
    private final IOCase caseSensitivity;
    
    public PrefixFileFilter(String prefix) {
        this(prefix, IOCase.SENSITIVE);
    }
    
    public PrefixFileFilter(String prefix, IOCase caseSensitivity) {
        if (prefix == null)
            throw new IllegalArgumentException("The prefix must not be null"); 
        this.prefixes = new String[] { prefix };
        this.caseSensitivity = (caseSensitivity == null) ? IOCase.SENSITIVE : caseSensitivity;
    }
    
    public PrefixFileFilter(String... prefixes) {
        this(prefixes, IOCase.SENSITIVE);
    }
    
    public PrefixFileFilter(String[] prefixes, IOCase caseSensitivity) {
        if (prefixes == null)
            throw new IllegalArgumentException("The array of prefixes must not be null"); 
        this.prefixes = new String[prefixes.length];
        System.arraycopy(prefixes, 0, this.prefixes, 0, prefixes.length);
        this.caseSensitivity = (caseSensitivity == null) ? IOCase.SENSITIVE : caseSensitivity;
    }
    
    public PrefixFileFilter(List<String> prefixes) {
        this(prefixes, IOCase.SENSITIVE);
    }
    
    public PrefixFileFilter(List<String> prefixes, IOCase caseSensitivity) {
        if (prefixes == null)
            throw new IllegalArgumentException("The list of prefixes must not be null"); 
        this.prefixes = prefixes.<String>toArray(EMPTY_STRING_ARRAY);
        this.caseSensitivity = (caseSensitivity == null) ? IOCase.SENSITIVE : caseSensitivity;
    }
    
    public boolean accept(File file) {
        String name = file.getName();
        for (String prefix : this.prefixes) {
            if (this.caseSensitivity.checkStartsWith(name, prefix))
                return true; 
        } 
        return false;
    }
    
    public boolean accept(File file, String name) {
        for (String prefix : this.prefixes) {
            if (this.caseSensitivity.checkStartsWith(name, prefix))
                return true; 
        } 
        return false;
    }
    
    public String toString() {
        StringBuilder buffer = new StringBuilder();
        buffer.append(super.toString());
        buffer.append("(");
        if (this.prefixes != null)
            for (int i = 0; i < this.prefixes.length; i++) {
                if (i > 0)
                    buffer.append(","); 
                buffer.append(this.prefixes[i]);
            }  
        buffer.append(")");
        return buffer.toString();
    }
}
