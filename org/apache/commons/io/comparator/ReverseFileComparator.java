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

package org.apache.commons.io.comparator;

import java.io.File;
import java.io.Serializable;
import java.util.Comparator;

class ReverseFileComparator extends AbstractFileComparator implements Serializable {
    private static final long serialVersionUID = -4808255005272229056L;
    
    private final Comparator<File> delegate;
    
    public ReverseFileComparator(Comparator<File> delegate) {
        if (delegate == null)
            throw new IllegalArgumentException("Delegate comparator is missing"); 
        this.delegate = delegate;
    }
    
    public int compare(File file1, File file2) {
        return this.delegate.compare(file2, file1);
    }
    
    public String toString() {
        return super.toString() + "[" + this.delegate.toString() + "]";
    }
}
