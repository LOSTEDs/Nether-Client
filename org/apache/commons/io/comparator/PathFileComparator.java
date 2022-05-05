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
import java.util.List;
import org.apache.commons.io.IOCase;

public class PathFileComparator extends AbstractFileComparator implements Serializable {
    private static final long serialVersionUID = 6527501707585768673L;
    
    public static final Comparator<File> PATH_COMPARATOR = new PathFileComparator();
    
    public static final Comparator<File> PATH_REVERSE = new ReverseFileComparator(PATH_COMPARATOR);
    
    public static final Comparator<File> PATH_INSENSITIVE_COMPARATOR = new PathFileComparator(IOCase.INSENSITIVE);
    
    public static final Comparator<File> PATH_INSENSITIVE_REVERSE = new ReverseFileComparator(PATH_INSENSITIVE_COMPARATOR);
    
    public static final Comparator<File> PATH_SYSTEM_COMPARATOR = new PathFileComparator(IOCase.SYSTEM);
    
    public static final Comparator<File> PATH_SYSTEM_REVERSE = new ReverseFileComparator(PATH_SYSTEM_COMPARATOR);
    
    private final IOCase caseSensitivity;
    
    public PathFileComparator() {
        this.caseSensitivity = IOCase.SENSITIVE;
    }
    
    public PathFileComparator(IOCase caseSensitivity) {
        this.caseSensitivity = (caseSensitivity == null) ? IOCase.SENSITIVE : caseSensitivity;
    }
    
    public int compare(File file1, File file2) {
        return this.caseSensitivity.checkCompareTo(file1.getPath(), file2.getPath());
    }
    
    public String toString() {
        return super.toString() + "[caseSensitivity=" + this.caseSensitivity + "]";
    }
}
