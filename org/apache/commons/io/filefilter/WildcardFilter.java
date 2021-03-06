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
import org.apache.commons.io.FilenameUtils;

@Deprecated
public class WildcardFilter extends AbstractFileFilter implements Serializable {
    private static final long serialVersionUID = -5037645902506953517L;
    
    private final String[] wildcards;
    
    public WildcardFilter(String wildcard) {
        if (wildcard == null)
            throw new IllegalArgumentException("The wildcard must not be null"); 
        this.wildcards = new String[] { wildcard };
    }
    
    public WildcardFilter(String... wildcards) {
        if (wildcards == null)
            throw new IllegalArgumentException("The wildcard array must not be null"); 
        this.wildcards = new String[wildcards.length];
        System.arraycopy(wildcards, 0, this.wildcards, 0, wildcards.length);
    }
    
    public WildcardFilter(List<String> wildcards) {
        if (wildcards == null)
            throw new IllegalArgumentException("The wildcard list must not be null"); 
        this.wildcards = wildcards.<String>toArray(EMPTY_STRING_ARRAY);
    }
    
    public boolean accept(File dir, String name) {
        if (dir != null && (new File(dir, name)).isDirectory())
            return false; 
        for (String wildcard : this.wildcards) {
            if (FilenameUtils.wildcardMatch(name, wildcard))
                return true; 
        } 
        return false;
    }
    
    public boolean accept(File file) {
        if (file.isDirectory())
            return false; 
        for (String wildcard : this.wildcards) {
            if (FilenameUtils.wildcardMatch(file.getName(), wildcard))
                return true; 
        } 
        return false;
    }
}
