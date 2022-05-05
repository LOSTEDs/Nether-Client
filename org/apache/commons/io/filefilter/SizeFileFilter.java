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

public class SizeFileFilter extends AbstractFileFilter implements Serializable {
    private static final long serialVersionUID = 7388077430788600069L;
    
    private final long size;
    
    private final boolean acceptLarger;
    
    public SizeFileFilter(long size) {
        this(size, true);
    }
    
    public SizeFileFilter(long size, boolean acceptLarger) {
        if (size < 0L)
            throw new IllegalArgumentException("The size must be non-negative"); 
        this.size = size;
        this.acceptLarger = acceptLarger;
    }
    
    public boolean accept(File file) {
        return (this.acceptLarger != ((file.length() < this.size)));
    }
    
    public String toString() {
        String condition = this.acceptLarger ? ">=" : "<";
        return super.toString() + "(" + condition + this.size + ")";
    }
}
