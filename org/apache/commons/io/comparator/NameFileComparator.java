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

public class NameFileComparator extends AbstractFileComparator implements Serializable {
    private static final long serialVersionUID = 8397947749814525798L;
    
    public static final Comparator<File> NAME_COMPARATOR = new NameFileComparator();
    
    public static final Comparator<File> NAME_REVERSE = new ReverseFileComparator(NAME_COMPARATOR);
    
    public static final Comparator<File> NAME_INSENSITIVE_COMPARATOR = new NameFileComparator(IOCase.INSENSITIVE);
    
    public static final Comparator<File> NAME_INSENSITIVE_REVERSE = new ReverseFileComparator(NAME_INSENSITIVE_COMPARATOR);
    
    public static final Comparator<File> NAME_SYSTEM_COMPARATOR = new NameFileComparator(IOCase.SYSTEM);
    
    public static final Comparator<File> NAME_SYSTEM_REVERSE = new ReverseFileComparator(NAME_SYSTEM_COMPARATOR);
    
    private final IOCase caseSensitivity;
    
    public NameFileComparator() {
        this.caseSensitivity = IOCase.SENSITIVE;
    }
    
    public NameFileComparator(IOCase caseSensitivity) {
        this.caseSensitivity = (caseSensitivity == null) ? IOCase.SENSITIVE : caseSensitivity;
    }
    
    public int compare(File file1, File file2) {
        return this.caseSensitivity.checkCompareTo(file1.getName(), file2.getName());
    }
    
    public String toString() {
        return super.toString() + "[caseSensitivity=" + this.caseSensitivity + "]";
    }
}
