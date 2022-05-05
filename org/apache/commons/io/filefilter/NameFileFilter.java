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

public class NameFileFilter extends AbstractFileFilter implements Serializable {
    private static final long serialVersionUID = 176844364689077340L;
    
    private final String[] names;
    
    private final IOCase caseSensitivity;
    
    public NameFileFilter(String name) {
        this(name, (IOCase)null);
    }
    
    public NameFileFilter(String name, IOCase caseSensitivity) {
        if (name == null)
            throw new IllegalArgumentException("The wildcard must not be null"); 
        this.names = new String[] { name };
        this.caseSensitivity = (caseSensitivity == null) ? IOCase.SENSITIVE : caseSensitivity;
    }
    
    public NameFileFilter(String... names) {
        this(names, (IOCase)null);
    }
    
    public NameFileFilter(String[] names, IOCase caseSensitivity) {
        if (names == null)
            throw new IllegalArgumentException("The array of names must not be null"); 
        this.names = new String[names.length];
        System.arraycopy(names, 0, this.names, 0, names.length);
        this.caseSensitivity = (caseSensitivity == null) ? IOCase.SENSITIVE : caseSensitivity;
    }
    
    public NameFileFilter(List<String> names) {
        this(names, (IOCase)null);
    }
    
    public NameFileFilter(List<String> names, IOCase caseSensitivity) {
        if (names == null)
            throw new IllegalArgumentException("The list of names must not be null"); 
        this.names = names.<String>toArray(EMPTY_STRING_ARRAY);
        this.caseSensitivity = (caseSensitivity == null) ? IOCase.SENSITIVE : caseSensitivity;
    }
    
    public boolean accept(File file) {
        String name = file.getName();
        for (String name2 : this.names) {
            if (this.caseSensitivity.checkEquals(name, name2))
                return true; 
        } 
        return false;
    }
    
    public boolean accept(File dir, String name) {
        for (String name2 : this.names) {
            if (this.caseSensitivity.checkEquals(name, name2))
                return true; 
        } 
        return false;
    }
    
    public String toString() {
        StringBuilder buffer = new StringBuilder();
        buffer.append(super.toString());
        buffer.append("(");
        if (this.names != null)
            for (int i = 0; i < this.names.length; i++) {
                if (i > 0)
                    buffer.append(","); 
                buffer.append(this.names[i]);
            }  
        buffer.append(")");
        return buffer.toString();
    }
}
