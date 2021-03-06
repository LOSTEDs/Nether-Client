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
import java.util.regex.Pattern;
import org.apache.commons.io.IOCase;

public class RegexFileFilter extends AbstractFileFilter implements Serializable {
    private static final long serialVersionUID = 4269646126155225062L;
    
    private final Pattern pattern;
    
    public RegexFileFilter(String pattern) {
        if (pattern == null)
            throw new IllegalArgumentException("Pattern is missing"); 
        this.pattern = Pattern.compile(pattern);
    }
    
    public RegexFileFilter(String pattern, IOCase caseSensitivity) {
        if (pattern == null)
            throw new IllegalArgumentException("Pattern is missing"); 
        int flags = 0;
        if (caseSensitivity != null && !caseSensitivity.isCaseSensitive())
            flags = 2; 
        this.pattern = Pattern.compile(pattern, flags);
    }
    
    public RegexFileFilter(String pattern, int flags) {
        if (pattern == null)
            throw new IllegalArgumentException("Pattern is missing"); 
        this.pattern = Pattern.compile(pattern, flags);
    }
    
    public RegexFileFilter(Pattern pattern) {
        if (pattern == null)
            throw new IllegalArgumentException("Pattern is missing"); 
        this.pattern = pattern;
    }
    
    public boolean accept(File dir, String name) {
        return this.pattern.matcher(name).matches();
    }
}
