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

public class NotFileFilter extends AbstractFileFilter implements Serializable {
    private static final long serialVersionUID = 6131563330944994230L;
    
    private final IOFileFilter filter;
    
    public NotFileFilter(IOFileFilter filter) {
        if (filter == null)
            throw new IllegalArgumentException("The filter must not be null"); 
        this.filter = filter;
    }
    
    public boolean accept(File file) {
        return !this.filter.accept(file);
    }
    
    public boolean accept(File file, String name) {
        return !this.filter.accept(file, name);
    }
    
    public String toString() {
        return super.toString() + "(" + this.filter.toString() + ")";
    }
}
